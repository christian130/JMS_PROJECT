package apolloproducer.datatraffic.com.co;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import conexion.MiConexionMongo;
import conexion.ProcessRepository;

public class Principal02 {
	 public static int numeroConsumidores=10;
	final static String[] arguments = new String[] {"123"};
	public static void main(String[] args) throws Exception {
		ApplicationContext  ctx= new AnnotationConfigApplicationContext(MiConexionMongo.class);
	     ProcessRepository proRepository  = ctx.getBean(ProcessRepository.class);
	  ExecutorService executor = Executors.newFixedThreadPool( numeroConsumidores );
	  
	  for(int i=1;  i<= numeroConsumidores;i++){
	   
	   Thread threadconsumidor = new Thread(new Consumer());
	   executor.execute(threadconsumidor);
	  
	  }
	  while (true) {
	   System.out.println("procesos consumidores ");
	   try {
	    Thread.sleep( 10000 );
	   } catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }    
	  }
	  

		
		/*new Runnable() {
			public void run() {
				try {*/
					 //while(!Thread.interrupted()) {
						    /* Do something. */
				/*	Thread t = new Thread(Publisher.main(arguments));
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
		}.run();*/
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



