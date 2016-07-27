/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidor;


import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.apache.qpid.amqp_1_0.jms.impl.QueueImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import conexion.despliegueJMS.MiConexionMongo;
import conexion.despliegueJMS.Process;
import conexion.despliegueJMS.ProcessRepository;

public class Consumer implements Runnable {
	
	@Bean
    public static void miMetodo2() throws JMSException   {
    	
    	ApplicationContext  ctx= new AnnotationConfigApplicationContext(MiConexionMongo.class);
    	ProcessRepository proRepository  = ctx.getBean(ProcessRepository.class);
    	//@Autowired ProcessRepository processRepository;
    	
    	
        String user = env("APOLLO_USER", "admin");
        String password = env("APOLLO_PASSWORD", "password");
        String host = env("APOLLO_HOST", "127.0.0.1");
        //String host = env("APOLLO_HOST", "192.168.0.148");
        int port = Integer.parseInt(env("APOLLO_PORT", "7102"));
        String destination = "queue://tracksporter";//arg(args, 0, "topic://event");

        int messages = 10000;
        int size = 256;

        String DATA = "abcdefghijklmnopqrstuvwxyz";
        String body = "";
        for( int i=0; i < size; i ++) {
            body += DATA.charAt(i%DATA.length());
        }

        ConnectionFactoryImpl factory = new ConnectionFactoryImpl(host, port, user, password);
        Destination dest = null;
        dest = new QueueImpl(destination);
        

        Connection connection = factory.createConnection(user, password);
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //MessageProducer producer = session.createProducer(dest);
        MessageConsumer consumer= session.createConsumer(dest);
        //
        int consumir= 10;
        Message receive = null;
        for (int i = 0; true ; i++) {
            receive= consumer.receive();
            receive.acknowledge();	
            System.out.println("estoy trayendome los mensajes del QUEUE" +receive.getJMSMessageID()+" tipo " + receive.getStringProperty("process_id") );
           // Process miproceso = new Process();
            //miproceso.setStatus(receive.getStringProperty("process_id"));
          proRepository.save(new Process().changeStatus(receive.getStringProperty("process_id"), "Por hacer..."));    
            
        }
        //consumer.close();
        
        
        
        
        
        
        /**
        
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        for( int i=1; i <= messages; i ++) {
            TextMessage msg = session.createTextMessage("#:"+i);
            msg.setIntProperty("id", i);
            msg.setStringProperty("JMSXGroupID", ""+(i%21));
            producer.send(msg);
            if( (i % 1000) == 0) {
                System.out.println(String.format("Sent %d messages", i));
            }
        }

        producer.send(session.createTextMessage("SHUTDOWN"));
        **/
       /* Thread.sleep(1000*3);
        connection.close();
        System.exit(0);*/
    }
    

    private static String env(String key, String defaultValue) {
        String rc = System.getenv(key);
        if( rc== null )
            return defaultValue;
        return rc;
    }

    private static String arg(String []args, int index, String defaultValue) {
        if( index < args.length )
            return args[index];
        else
            return defaultValue;
    }


	public void run() {
		try {
			Consumer.miMetodo2();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	

}