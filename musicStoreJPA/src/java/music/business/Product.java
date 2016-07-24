
package music.business;
/**
 *
 * @author Elliot Martinez
 * @author Matthew Kastro
 * 
 * changelog: 7/13/16
 *      Added JPA entity information
 */

import java.io.Serializable;
import java.text.NumberFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Product implements Serializable {
    
    @Id // primary key
    @Column(name = "PRODUCTID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    
    @Column(name = "CODE", nullable = false)
    private String code;
    
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    
    @Column(name = "PRICE", nullable = false)
    private double price;
    
    @Transient
    private String imageURL;
    
    public Long getId() {
        return productId;
    }
    
    public void setId(Long productId) {
        this.productId = productId;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getArtistName() {
        String artistName = description.substring(0,description.indexOf(" - "));
        return artistName;
    }
    
    public String getAlbumName() {
        String albumName = 
                description.substring(description.indexOf(" - ") + 3);
        return albumName;
    }
    
    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
    
    public String getImageURL() {
        return imageURL;
    }
    
    public void setImageURL(String URL) {
        this.imageURL = URL;
    }
    public String getProductType() {
        return "Audio CD";
    }
}
