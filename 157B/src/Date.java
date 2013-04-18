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
public class Date
{    
    private long id;
    private String year;
    private String month;
    private String day;
    private Quantity quantity;
    
    public Date() {}
    
    public Date(String year, String month, String day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    
    @Column(name = "years")
    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}
    
    @Column(name = "months")
    public String getMonth() {return month;}
    public void setMonth(String month) {this.month = month;}
    
    @Column(name = "days")
    public String getDay() {return day;}
    public void setDay(String day) {this.day = day;}
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "quantityId")
    public Quantity getUserDetails() {return quantity;}
    public void setUserDetails(Quantity quantity) 
                                        { this.quantity = quantity; }
    
    
    /**
     * Print user attributes.
     */
    public void print()
    {
        System.out.printf("%d: year: %s month: %s day: %s\n", id, year, month, day);
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
            session.save(new Date("2010", "December", "12"));
            session.save(new Date("2011", "December", "12"));
            session.save(new Date("2012", "December", "12"));
            session.save(new Date("2012", "December", "13"));
            session.save(new Date("2013", "January", "1"));
            session.save(new Date("2013", "January", "8"));
            session.save(new Date("2013", "January", "15"));
            session.save(new Date("2013", "January", "22"));
            session.save(new Date("2013", "January", "29"));
            session.save(new Date("2013", "February", "5"));
        }
        tx.commit();
        session.close();
        
        System.out.println("Date table loaded.");
    }
    
    /**
     * List all the users.
     */
    public static void list()
    {
        Session session = HibernateContext.getSession();
        Query query = session.createQuery("from User");
        
        System.out.println("All Product: ");
        
        for (Date date : (List<Date>) query.list())
        {
            date.print();
            System.out.println("");
        }
        
        session.close();
    }
    
    public static Date find(long id)
    {
        Session session = HibernateContext.getSession();
        Query query = session.createQuery("from Date where id = :idvar");
        
        query.setLong("idvar", id);
        Date user = (Date) query.uniqueResult();
        System.out.printf("ID you searched for: %d\n", id);
        
        session.close();
        return user;
    }
    
}
