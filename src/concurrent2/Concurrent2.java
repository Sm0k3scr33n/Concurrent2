/*
This program represents a methed accessed non-concurrently
the atomic() method is not concurrent because the count variable is produced
by each thread individually thus both threads count to 1000 seperatly rather than togather
 */
package concurrent2;
import java.util.ConcurrentModificationException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Gabbard
 */
class SimpleThreadCounter extends Concurrent2 implements Runnable{
    public void atomic(){    
        int count = 0;
        //I want to cout to 1000 so + 1 to make it inclusive 
        while (count < 1000+1) 
        {
            try{
                Thread.sleep(10);
                } catch (InterruptedException ex)
                {}catch (ConcurrentModificationException ex){}
               System.out.println(Thread.currentThread().getName() +" "+count);
               count++;
        }
    }   
   public void run()
    {   
       //using the run method of the class to call the atomic(); method 
       atomic();                           
    }   
}
 class Concurrent2  {
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            SimpleThreadCounter funwiththreads = new SimpleThreadCounter();
            Thread one = new Thread(funwiththreads);
            Thread two = new Thread(funwiththreads);
            Thread three = new Thread(funwiththreads);
            one.setName("Thread_1");
            two.setName("Thread_2");
            three.setName("Thread_3");
            one.start();
            two.start();
            one.join();
            two.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Concurrent2.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
