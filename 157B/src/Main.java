/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forum;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Greg
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static final String HELP_MESSAGE =
        "*** Commands: "
            + "\n\t create, load, "
            + "\n\t find_user <n>, find_forum <n>, find_thread <n>, find_post <n>  "
            + "\n\t users, forums, forum_posts, threads, quit "
            + "\n***";
    public static void main(String[] args) {
         BufferedReader stdin = 
                new BufferedReader(new InputStreamReader(System.in));
        String command;
        Class klasses[] = {Forum.class, Thread.class, MyForumPost.class,
                            User.class, UserDetails.class};
        HibernateContext.addClasses(klasses);
        
        
        HibernateContext.createSchema();
        User.load();
        Forum.load();
        Thread.load();
        MyForumPost.load();
        
        
        //command 
       
        do {
            System.out.print("\nCommand? ");
            
            try {
                command = stdin.readLine();
            }
            catch (java.io.IOException ex) {
                command = "?";
            }
            
            String parts[] = command.split(" ");
            
            if (command.equalsIgnoreCase("drill")) {
                System.out.println("Drill Down/Up Here");
            }
            else if (command.equalsIgnoreCase("slice")) {
                System.out.println("Slice and Dice Baby");
            }
            else if (!command.equalsIgnoreCase("quit")) {
                System.out.println(HELP_MESSAGE);
            }
        } while (!command.equalsIgnoreCase("quit"));
        //
            
        /*
        Forum forum = Forum.find(1);
        if (thread != null && post != null) {
            thread.print();
            post.print();
        }
        else {
            System.out.printf("*** No student with id %d\n", 1);
        }
        thread = Thread.find("I hate using PASCAL! It sucks!");
        if (thread != null) {
            thread.print();
        }
        else {
            System.out.printf("*** No student with title 'I hate using PASCAL! It sucks!'\n");
        }
        System.out.println("");
        Thread postThread = post.getThread();
        System.out.println("Showing many to one relationship with posts -> "
                + "thread");
        post.print();
        post.thread.print();
        post.user.printInSession();
        post2.print();
        post2.user.printInSession();
        
        System.out.println("");
        System.out.println("Showing one to many relationship with forum -> "
                + "threads");
        forum.print();
        for(Thread individualThread : forum.threads){
            individualThread.print();
        }
        */
    }//end of main
}
