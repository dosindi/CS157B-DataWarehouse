package forum;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * A forum user
 * 
 * One User to many ForumPost
 * Many User to many Forum (moderator relationship)
 * One User to One UserDetails
 * 
 * @author Ryan
 * @version  2/6/13
 */

@Entity
public class Product
{    
    private long id;
    private String category;
    private String subcategory;
    private String product;
    private List<Quantity> quantities = new ArrayList<Quantity>();
    
    public Product() {}
    
    public Product(String category, String subcategory, String product)
    {
        this.category = category;
        this.subcategory = subcategory;
        this.product = product;
    }
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    @Column(name = "category")
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    
    @Column(name = "subcategory")
    public String getSubcategory() {return subcategory;}
    public void setSubcategory(String subcategory) {this.subcategory = subcategory;}
    
    @Column(name = "product")
    public String getProduct() {return product;}
    public void setProduct(String product) {this.product = product;}
//    
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "userDetailsId")
//    public UserDetails getUserDetails() {return userDetails;}
//    public void setUserDetails(UserDetails userDetails) 
//                                        { this.userDetails = userDetails; }
    
    @OneToMany(mappedBy = "quantity", targetEntity = Quantity.class,
                cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Quantity> getQuantities() { return quantities; }
    public void setQuantities(List<Quantity> posts) { this.quantities = quantities; }
    
    
    /**
     * Print user attributes.
     */
    public void print()
    {
        System.out.printf("%d: category: %s subcategory: %s product: %s\n", id, category, subcategory, product);
    }
    
    /**
     * Print Thread attributes within a session.
     */
    public void printInSession()
    {
        Session session = HibernateContext.getSession();
        session.update(this);
        print();
        session.close();
    }
    
    /**
     * Load the User table.
     */
    public static void load()
    {
        Session session = HibernateContext.getSession();
        
        //Fill the Student table in a transaction.
        Transaction tx = session.beginTransaction();
        {
            session.save(new Product("Team Sports", "football", "Football Pads"));
            session.save(new Product("Team Sports", "basketball", "Jordans"));
            session.save(new Product("Individual Sports", "snorkeling", "Flippers"));
            session.save(new Product("water sports", "rafting", "raft"));
            session.save(new Product("Team Sports", "foosball", "foosball"));  
        }
        tx.commit();
        session.close();
        
        System.out.println("Product table loaded.");
    }
    
    /**
     * List all the users.
     */
    public static void list()
    {
        Session session = HibernateContext.getSession();
        Query query = session.createQuery("from User");
        
        System.out.println("All Product: ");
        
        for (Product product : (List<Product>) query.list())
        {
            product.print();
            System.out.println("");
        }
        
        session.close();
    }
    
    public static Product find(long id)
    {
        Session session = HibernateContext.getSession();
        Query query = session.createQuery("from Product where id = :idvar");
        
        query.setLong("idvar", id);
        Product user = (Product) query.uniqueResult();
        System.out.printf("ID you searched for: %d\n", id);
        
        session.close();
        return user;
    }
    
}
