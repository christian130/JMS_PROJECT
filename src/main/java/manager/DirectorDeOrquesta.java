package manager;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DirectorDeOrquesta implements Runnable{

	
	
	public void run() {
		
		System.out.println();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//aqui tengo que colocar todo el
//		System.out.println();

	}

}