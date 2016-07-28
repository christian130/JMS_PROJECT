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
		ExecutorService threads = new ExecutorService() {
			
			@Override
			public void execute(Runnable command) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public <T> Future<T> submit(Runnable task, T result) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Future<?> submit(Runnable task) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <T> Future<T> submit(Callable<T> task) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<Runnable> shutdownNow() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void shutdown() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isTerminated() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isShutdown() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
					throws InterruptedException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		new Runnable() {
			public void run() {
				try {
					 //while(!Thread.interrupted()) {
						    /* Do something. */
						 Publisher.main(arguments);
						//  }
					 //Thread.currentThread().interrupt();
					
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.run();
		
		

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



