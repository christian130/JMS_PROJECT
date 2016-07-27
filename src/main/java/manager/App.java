package manager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import consumidor.Consumer;
import publicador.Publisher;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(PoolDeConfiguracion.class);
	    ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
	    //Publisher Task001 = 	(Publisher) context.getBean("publicador.Publisher");
	    Publisher publicador = new Publisher(); 
	    taskExecutor.execute((Runnable)publicador );

	    //Consumer Task002 = (Consumer) context.getBean("Consumer");
	    Consumer consumidor = new Consumer();
	    taskExecutor.execute((Runnable) consumidor);
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
