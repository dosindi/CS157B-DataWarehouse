package forum;

import forum.Quantity;
import java.util.Set;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

/**
 * A forum Thread.
 * 
 * Many Thread to one Forum
 * One Thread to many ForumPost
 * 
 * @author chris
 * @version January 2013
 */
@Entity
public class Store{
    
    private long id;
    private String country;
    private String state;
    private String city;
    public Set<Quantity> quantities;
    
    public Store() {}
    
    public Store(String country, String state, String city)
    {
        this.country = country;
        this.state = state;
        this.city = city;
    }
    
    @Id
    @GeneratedValue
    @Column(name="id")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    @Column(name="country")
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    @Column(name="state")
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    @Column(name="city")
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    
    @OneToMany(mappedBy="quantity", targetEntity=Quantity.class,
        cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    public Set<Quantity> getQuantities() { return quantities; }
    public void setQuantities(Set<Quantity> quantities) { this.quantities = quantities; }
    
    /**
     * Print Thread attributes.
     */
    public void print()
    {
        System.out.printf("%d: city: %s state: %s country: %s\n", id, city, state, country);
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
    
    public static void load()
    {
        Session session = HibernateContext.getSession();
        
        Store thread1 = new Store("United States", "California", "San Jose");
        
        Store thread2 = new Store("United States", "New York", "New York City");
        
        Store thread3 = new Store("United States", "California", "San Francisco");
        
        Store thread4 = new Store("United States", "California", "San Jose");

        
        
        // Fill the Thread table in a transaction.
        Transaction tx = session.beginTransaction(); 
        {
            session.save(thread1);
            session.save(thread2);
            session.save(thread3);
            session.save(thread4);
        }
        tx.commit();
        session.close();
        
        System.out.println("Store table loaded.");
    }
    
    /**
     * List all the Threads.
     */
    public static void list()
    {
        Session session = HibernateContext.getSession();
        Query query = session.createQuery("from Store");
        
        System.out.println("All Threads:");
        
        for (Store store : (List<Store>) query.list()) {
            store.print();
        }

        session.close();
    }
    
    /**
     * Fetch the Thread with a matching id.
     * @param id the id to match.
     * @return the Thread or null.
     */
    public static Store find(long id)
    {
        Session session = HibernateContext.getSession();
        Query query = session.createQuery("from Store where id = :idvar");
        
        query.setLong("idvar", id);
        Store store = (Store) query.uniqueResult();
        System.out.printf("ID you searched for: %d\n", id);
        
        session.close();
        return store;
    }
        
}
