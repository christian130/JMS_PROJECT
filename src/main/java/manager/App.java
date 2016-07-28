package manager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class App {


	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(PoolDeConfiguracion.class);
	    ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
	    DirectorDeOrquesta printTask2 = (DirectorDeOrquesta)  context.getBean("DirectorDeOrquesta");
	    taskExecutor.execute(printTask2);
		for (;;) {
			int count = taskExecutor.getActiveCount();
			System.out.println("Active Threads : " + count);
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

