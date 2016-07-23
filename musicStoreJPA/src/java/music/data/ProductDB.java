package music.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import music.business.Product;

public class ProductDB {

    public static Product selectProduct(String code) {
        EntityManager em = getEM();
        String qString = "SELECT p FROM Product p " +
                "WHERE p.code = :code";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setParameter("code", code);
        Product result = null;
        try {
            result = q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        
        return (Product)result;
    }
    
    public static Product selectProduct(long productId) {
        EntityManager em = getEM();
        
        return em.find(Product.class, productId);
    }
    
    public static List<Product> selectProducts() {
        EntityManager em = getEM();
        String qString = "SELECT p from Product p";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        List<Product> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        
        return results;
    }
    
     public static void insertProduct(Product product) {
//        turnOffSafeUpdates();
        EntityManager em = getEM();
        em.getTransaction().begin(); 
        
        Product p=em.find(Product.class, product.getId());
        if(p==null)
        {
            em.persist(product);
        }  else {
            em.merge(product);
        }
        em.getTransaction().commit();
    }

    public static void updateProduct(Product product) {
//        turnOffSafeUpdates();
        EntityManager em = getEM();
        em.getTransaction().begin(); 
        
        Product p=em.find(Product.class,product.getId());
        if(p!=null)
        {
            em.merge(product);
        } 
        em.getTransaction().commit();

    }

    public static void deleteProduct(Product product) {
//        turnOffSafeUpdates();
        EntityManager em = getEM();
        em.getTransaction().begin(); 
        Product p = em.find(Product.class, product.getId());
        em.remove(p);
        em.getTransaction().commit();
    }
    
    private static EntityManager getEM(){
        return DBUtil.getEmFactory().createEntityManager();
    }
    
    public static Long incrementedID(){
            Long highId = new Long(0);
            List<Product> products = selectProducts();
            for (int i = 0; i < products.size(); i++){
                if (products.get(i).getId() > highId ){   
                    highId = products.get(i).getId();
                }
            }
            highId = highId+=1;
            return highId;
    }
    
     public static void turnOffSafeUpdates() {
       EntityManager em = getEM();
       String query = "SET SQL_SAFE_UPDATES=0;";
   
       em.getTransaction().begin();
       try {
           em.createQuery(query);
       } catch (Exception e) {
           System.out.println(e);
       } finally {
           em.getTransaction().commit();
           em.close();
       }
    }

}