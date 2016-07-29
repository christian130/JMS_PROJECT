package apolloproducer.datatraffic.com.co;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.jms.JMSException;

public class Principal02 {
	
	final static String[] arguments = new String[] {"123"};
	public static void main(String[] args) throws Exception {
		
		
		new Runnable() {
			public void run() {
				try {
					 //while(!Thread.interrupted()) {
						    /* Do something. */
					Thread t = new Thread(Publisher.main(arguments));
					t.setDaemon(true);
						 //Publisher.main(arguments);
						//  }
					 //Thread.currentThread().interrupt();
					t.interrupt();
					Thread t2 = new Thread(Consumer.main(arguments));
					t2.setDaemon(true);
					t2.interrupt();
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.run();
		//System.out.println(Thread.currentThread().getName());
		//System.exit(0);

		// ...
		// Tell threads to finish off.
		
		// Wait for everything to finish.
		/*while (!threads.awaitTermination(10, TimeUnit.SECONDS)) {
			
		  //log.info("Awaiting completion of threads.");
		}*/
		
				/*final String[] arguments = new String[] {"123"};
				try {
					Publisher.main(arguments);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.sleep(5000);
				try {
					Consumer.main(arguments);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		

	}



