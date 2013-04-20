package forum;

import java.util.LinkedList;
import java.util.List;

import forum.Store;
import forum.Product;
import forum.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

@Entity
@Table(name = "Quantity")
public class Quantity {
        
        private long id;
        private int quantity;
        public Store store;
	public Date date;
        public Product product;
        
        public Quantity() {}
        
        public Quantity(int quantity)
        {
            this.quantity = quantity;
        }
    
        @Id
        @GeneratedValue
        @Column(name="id")
        public long getId() { return id; }
        public void setId(long id) { this.id = id; }
        
        @Column(name="quantity")
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name="dateID")
        public Date getDate() { return date; }
        public void setDate(Date date) { this.date = date; }
        
        @ManyToOne
        @JoinColumn(name="storeID")
        public Store getStore() { return store; }
        public void setStore(Store store) { this.store = store; }
        
        @ManyToOne
        @JoinColumn(name="productID")
        public Product getProduct() { return product; }
        public void setProduct(Product product) { this.product = product; }
        
        public void print()
        {
            System.out.printf("postID: %d %s dateID: %d productID: %d storeID: %d\n",
                        id, quantity, this.date.getId(), this.product.getId(), this.store.getId());
        }
        
        /**
        * Print Post attributes within a session.
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
        
            /* STORES */
            Store sj = Store.find(1); //SJ
            Store nyc = Store.find(2); //NYC
            Store sf = Store.find(3); //SF
 
            /* PRODUCTS */
            Product p1 = Product.find(1); // Football pads
            Product p2 = Product.find(2); // Jordans
            Product p3 = Product.find(3); // Flippers 
            Product p4 = Product.find(4); // Raft
            Product p5 = Product.find(5); // Foosball
            
            //SJ
            Date date1 = Date.find(1);
            Quantity post1 = new Quantity(3);
            post1.setStore(sj);
            post1.setDate(date1);
            post1.setProduct(p1);
            
            // NYC
            Date date2 = Date.find(2);
            Quantity post2 = new Quantity(6);
            post2.setStore(nyc);
            post2.setDate(date2);
            post2.setProduct(p2);
            
            // SF
            Date date3 = Date.find(3);
            Quantity post3 = new Quantity(7);
            post3.setStore(sf);
            post3.setDate(date3);
            post3.setProduct(p1);
            
            // SJ
            Date date4 = Date.find(4);
            Quantity post4 = new Quantity(7);
            post4.setStore(sj);
            post4.setDate(date4);
            post4.setProduct(p4);
            
            // NYC
            Date date5 = Date.find(5);
            Quantity post5 = new Quantity(8);
            post5.setStore(nyc);
            post5.setDate(date5);
            post5.setProduct(p4);
            
            // NYC
            Date date6 = Date.find(6);
            Quantity post6 = new Quantity(8);
            post6.setStore(nyc);
            post6.setDate(date6);
            post6.setProduct(p5);
            
            // SF
            Date date7 = Date.find(7);
            Quantity post7 = new Quantity(15);
            post7.setStore(sf);
            post7.setDate(date7);
            post7.setProduct(p3);
            
            // NYC
            Date date8 = Date.find(8);
            Quantity post8 = new Quantity(15);
            post8.setStore(nyc);
            post8.setDate(date8);
            post8.setProduct(p3);
            
            // SJ
            Date date9 = Date.find(9);
            Quantity post9 = new Quantity(15);
            post9.setStore(sj);
            post9.setDate(date9);
            post9.setProduct(p3);
            
            // SF
            Date date10 = Date.find(10);
            Quantity post10 = new Quantity(15);
            post10.setStore(sf);
            post10.setDate(date10);
            post10.setProduct(p4);
            
            // Fill the Post table in a transaction.
            Transaction tx = session.beginTransaction(); 
            {
                session.save(post1);
                session.save(post2);
                session.save(post3);
                session.save(post4);
                session.save(post5);
                session.save(post6);
                session.save(post7);
                session.save(post8);
                session.save(post9);
                session.save(post10);
            }
            tx.commit();
            session.close();
        
            System.out.println("Quantity table loaded.");
        }
        
        public static Quantity find(long id)
        {
            Session session = HibernateContext.getSession();
            Query query = session.createQuery("from Quantity where id = :idvar");
        
            query.setLong("idvar", id);
            Quantity post = (Quantity) query.uniqueResult();
            System.out.printf("ID you searched for: %d\n", id);
        
            session.close();
            return post;
        }
        public static void list()
    {
        Session session = HibernateContext.getSession();
        Query query = session.createQuery("from Quantity");
        
        System.out.println("All quantities:");
        
        for (Quantity post : (List<Quantity>) query.list()) {
            post.print();
        }

        session.close();
    }

}
