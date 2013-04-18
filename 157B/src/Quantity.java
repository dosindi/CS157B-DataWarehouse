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
        
            //SJ
            Date date1 = Date.find(1);
            Store store1 = Store.find(1);
            Product product1 = Product.find(1);
            Quantity post1 = new Quantity(3);
            post1.setStore(store1);
            post1.setDate(date1);
            post1.setProduct(product1);
            
            // NYC
            Date date2 = Date.find(3);
            Store store2 = Store.find(2);
            Product product2 = Product.find(2);
            Quantity post2 = new Quantity(6);
            post2.setStore(store2);
            post2.setDate(date2);
            post2.setProduct(product2);
            
            // SF
            Date date3 = Date.find(4);
            Store store3 = Store.find(3);
            Product product3 = Product.find(2);
            Quantity post3 = new Quantity(7);
            post3.setStore(store3);
            post3.setDate(date3);
            post3.setProduct(product3);
            
            // SJ
            Date date4 = Date.find(5);
            Store store4 = Store.find(4);
            Product product4 = Product.find(3);
            Quantity post4 = new Quantity(7);
            post4.setStore(store4);
            post4.setDate(date4);
            post4.setProduct(product4);
                
            // Fill the Post table in a transaction.
            Transaction tx = session.beginTransaction(); 
            {
                session.save(post1);
                session.save(post2);
                session.save(post3);
                session.save(post4);
            }
            tx.commit();
            session.close();
        
            System.out.println("Posts table loaded.");
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
