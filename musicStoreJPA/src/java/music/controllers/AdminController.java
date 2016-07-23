package music.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import music.business.*;
import music.data.*;
import static music.data.ProductDB.selectProducts;

public class AdminController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "/admin";
        if (requestURI.endsWith("/displayInvoices")) {
            url = displayInvoices(request, response);
        } else if (requestURI.endsWith("/processInvoice")) {
            url = processInvoice(request, response);
        } else if (requestURI.endsWith("/displayReport")) {
            displayReport(request, response);
        } else if (requestURI.endsWith("/displayInvoices2")) {
            url = displayInvoices(request, response);
        } else if (requestURI.endsWith("/productMaint")) {
            url = displayProducts(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "/admin";
        if (requestURI.endsWith("/displayInvoice")) {
            url = displayInvoice(request, response);
        } else if (requestURI.endsWith("/displayInvoices")) {
            url = displayInvoices(request, response);
        } else if (requestURI.endsWith("/productMaint")) {
            url = displayProducts(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String displayInvoices(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        List<Invoice> unprocessedInvoices
                = InvoiceDB.selectUnprocessedInvoices();
        
        String url;
        if (unprocessedInvoices != null) {
            if (unprocessedInvoices.size() <= 0) {
                unprocessedInvoices = null;
            }
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("unprocessedInvoices", unprocessedInvoices);
        url = "/admin/invoices.jsp";
        return url;
    }
    
    private String displayInvoices2(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        List<Invoice> unprocessedInvoices
                = InvoiceDB.selectUnprocessedInvoices();
        
        String url;
        if (unprocessedInvoices != null) {
            if (unprocessedInvoices.size() <= 0) {
                unprocessedInvoices = null;
            }
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("unprocessedInvoices", unprocessedInvoices);
        url = "/admin/invoices.jsp";
        return url;
    }
    private String displayInvoice(HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession();

        String invoiceNumberString = request.getParameter("invoiceNumber");
        int invoiceNumber = Integer.parseInt(invoiceNumberString);
        List<Invoice> unprocessedInvoices = (List<Invoice>) 
                session.getAttribute("unprocessedInvoices");

        Invoice invoice = null;
        for (Invoice unprocessedInvoice : unprocessedInvoices) {
            invoice = unprocessedInvoice;
            if (invoice.getInvoiceNumber() == invoiceNumber) {
                break;
            }
        }

        session.setAttribute("invoice", invoice);

        return "/admin/invoice.jsp";
    }

    
    private String processInvoice(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        Invoice invoice = (Invoice) session.getAttribute("invoice");
        InvoiceDB.update(invoice);

        return "/adminController/displayInvoices";
    }
    
    private void displayReport(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String reportName = request.getParameter("reportName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        
        Workbook workbook;
        if (reportName.equalsIgnoreCase("userEmail")) {
            workbook = ReportDB.getUserEmail();
        } else if (reportName.equalsIgnoreCase("downloadDetail")) {
            workbook = ReportDB.getDownloadDetail(startDate, endDate);
        } else {
            workbook = new HSSFWorkbook();
        }
        
        response.setHeader("content-disposition", 
                "attachment; filename=" + reportName + ".xls");
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        }
    }
    
    private String displayProducts(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        // Initiate returl URL, default to home page if logic error
        String url = "/admin/index.jsp";
        
        // Populate data, from earlier project
        //String path = this.getServletContext().getRealPath("/WEB-INF/products.txt");
        //ProductIO.init(path);
        
        // get current action
        String action = request.getParameter("action");
        
        // Set default action
        if (action == null) {
            action = "displayProducts";   
        }
        
        // Handles displayProducts action
        if (action.equalsIgnoreCase("displayProducts")) {            
            List<Product> products = ProductDB.selectProducts();
            request.setAttribute("products", products);
            url = "/admin/products.jsp";
        }
        
        // Handles addProduct action
        // When adding a product, we must provide an empty form
        else if (action.equalsIgnoreCase("addProduct")) {
            url = "/admin/product.jsp";
        } else if (action.equalsIgnoreCase("createProduct")){
            
            Long newId = ProductDB.incrementedID();

            //finish insert
            String productCode = request.getParameter("productCode");
            String productDescription = request.getParameter("productDescription");
            String productPrice = request.getParameter("productPrice");
//
//            // Filling in product object with data
            Product p = new Product();
            p.setDescription(productDescription);
            p.setCode(productCode);
            p.setPrice(Double.parseDouble(productPrice));
            p.setId(newId);
            ProductDB.insertProduct(p);
//
            List<Product> result = ProductDB.selectProducts();
            request.setAttribute("products", result);
            url="/admin/products.jsp";
        }
       
        // Handles editProduct action
        else if (action.equalsIgnoreCase("editProduct")) {
            // Get productCode from request
            String productCode = request.getParameter("productCode");
            
            // Filling in product object with data
            Product p = new Product();
            p = ProductDB.selectProduct(productCode);
            
            // Set data on page
            request.setAttribute("productCode", productCode);
            request.setAttribute("productDescription", p.getDescription());
            request.setAttribute("productPrice", p.getPrice());
            
            // Set page we need to go to
            url = "/admin/product.jsp";
        }
        
        
        // Handles deleteProduct action
        else if (action.equalsIgnoreCase("deleteProduct")) {
            // Get productCode from request
            String productCode = request.getParameter("productCode");
            
            // Filling in product object with data
            Product p = new Product();
            p = ProductDB.selectProduct(productCode);
            
            // Set data on page
            request.setAttribute("productCode", productCode);
            request.setAttribute("productDescription", p.getDescription());
            request.setAttribute("productPrice", p.getPrice());
            
            // Set page we need to go to
            url="/admin/deleteProduct.jsp";
        }
        
        // Handle updateProduct action
        else if (action.equalsIgnoreCase("updateProduct")) {
            String message ="";
            String numberMessage="";
            String productCode = request.getParameter("productCode");
            String productDescription = request.getParameter("productDescription");
            String price = request.getParameter("productPrice");
            double priceAsDouble=0L;
            try {
              priceAsDouble = Double.parseDouble(price);
            } catch(NumberFormatException e) {
                numberMessage = "Please Enter a valid price value";
                url="/admin/product.jsp";
                request.setAttribute("numberMessage", numberMessage);
            }
            
            if (productCode == null || productDescription == null ||
                price == null || productCode.isEmpty() ||
                productDescription.isEmpty() || price.isEmpty()) {
                message = "Please fill out all three text boxes";
                url = "/admin/product.jsp";
            }
            if(numberMessage.isEmpty()) {
                Product p = ProductDB.selectProduct(productCode);
                    p.setDescription(productDescription);
                    p.setPrice(priceAsDouble);
                    ProductDB.updateProduct(p);
                url="/admin/products.jsp";
            }
            List<Product> products = ProductDB.selectProducts();    
            
            request.setAttribute("products", products);
            request.setAttribute("message", message);
            request.setAttribute("productCode", productCode);
            request.setAttribute("productDescription", productDescription);
            request.setAttribute("productPrice", price);  
        }
     
        //Handle confirmDelete Action
        else if(action.equalsIgnoreCase("confirmDelete")) {
            String productCode = request.getParameter("productCode");
            Product p = ProductDB.selectProduct(productCode);
            ProductDB.deleteProduct(p);
            List<Product> products = ProductDB.selectProducts();
            request.setAttribute("products", products);
            url="/admin/products.jsp";
        }
        
        return url;
    }
}