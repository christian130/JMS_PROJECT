package manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import consumidor.Consumer;
import publicador.Publisher;

public class App {
	@Autowired
	static Publisher publisher;
	@Autowired 
	static Consumer consumer;
	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(PoolDeConfiguracion.class);
	    ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
	    Publisher Task001 = 	publisher;	    
	    taskExecutor.execute(Task001 );
	    Consumer Task002 = consumer;
	    taskExecutor.execute( Task002);
	    for (;;) {
			int count = taskExecutor.getActiveCount();
			System.out.println("Threads Activos : " + count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 0) {
				taskExecutor.shutdown();
				break;
			}
		}

	}

}
