package manager;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import consumidor.Consumer;
import publicador.Publisher;

@Component
@Scope("prototype")
public class DirectorDeOrquesta implements Runnable{

	
	
	private Thread th;

	public void run() {
		
		//System.out.println("estoy iniciando un hilo");
		try {
			System.out.println("estoy llamando al publicador");
			Publisher.miMetodo01();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		try {
			System.out.println("estoy llamando al consumer");
			Consumer.miMetodo02();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//aqui tengo que colocar todo el
//		System.out.println();

	}

}