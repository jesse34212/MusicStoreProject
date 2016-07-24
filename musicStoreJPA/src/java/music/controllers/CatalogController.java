package music.controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import music.business.*;
import music.data.*;
import music.util.CookieUtil;

public class CatalogController extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        String url;
        if (requestURI.endsWith("/listen")) {
            url = listen(request, response);
        } else if (requestURI.endsWith("/showcatalog")) {
            url = showCatalog(request, response);
        } else {
            url = showProduct(request, response);
        }
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String url = "/catalog";
        if (requestURI.endsWith("/register")) {
            url = registerUser(request, response);
        }
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }
    
    private String showProduct(HttpServletRequest request, 
            HttpServletResponse response) {
        String productCode = request.getPathInfo();
        // This should never be null. But just to be safe.
        if (productCode != null) {
            productCode = productCode.substring(1);
            Product product = ProductDB.selectProduct(productCode);
            
            // imageURL logic
            String imageURL = "/images/" + product.getCode() + "_cover.jpg";

            // Set this into the object
            product.setImageURL(imageURL);
            
            // Create file object
            File file = new File(request.getServletContext().getRealPath(imageURL));

            // Check if file exists, set no cover if it doesn't
            if(!file.exists()){ 
                imageURL = "/images/no_cover.jpg";
                product.setImageURL(imageURL);
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("product", product);
        }    

        return "/catalog/catalogitem.jsp";
    }
    
    private String listen(HttpServletRequest request, 
            HttpServletResponse response) {
        
        HttpSession session = request.getSession();        
        User user = (User) session.getAttribute("user");
        // if the User object doesn't exist, check for the email cookie
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress =
                    CookieUtil.getCookieValue(cookies, "emailCookie");
            // if the email cookie doesn't exist, go to the registration page
            if (emailAddress == null || emailAddress.equals("")) {
                return "/catalog/register.jsp";
            } else {
                user = UserDB.selectUser(emailAddress);
                // if a user for that email isn't in the database, go to the registration page
                if (user == null) {
                    return "/catalog/register.jsp";
                }
                session.setAttribute("user", user);
            }
        }
        
        Product product = (Product) session.getAttribute("product");

        Download download = new Download();
        download.setUser(user);
        download.setProductCode(product.getCode());        
        DownloadDB.insert(download);
        
        // soundURL logic
        String soundURL = "/catalog/" + product.getCode() + "/sound.jsp";

        // Create file object
        File file = new File(request.getServletContext().getRealPath(soundURL));

        // Check if file exists, redirect to no sound page if it doesn't
        if(!file.exists()){ 
            soundURL = "/catalog/noSound.jsp";
        }
            
        return soundURL;
    }  
    
    private String registerUser(HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        if (UserDB.emailExists(email)) {
            UserDB.update(user);
        } else {
            UserDB.insert(user);
        }

        session.setAttribute("user", user);

        Cookie emailCookie = new Cookie("emailCookie", email);
        emailCookie.setMaxAge(60 * 60 * 24 * 365 * 2);
        emailCookie.setPath("/");
        response.addCookie(emailCookie);
        
        Product product = (Product) session.getAttribute("product");
        String url = "/catalog/" + product.getCode() + "/sound.jsp";
        return url;
    }

    private String showCatalog(HttpServletRequest request, 
            HttpServletResponse response) {
        List<Product> products = ProductDB.selectProducts();
        request.setAttribute("products", products);
        
        String url = "/catalog/index.jsp";
        
        return url;
    }
}