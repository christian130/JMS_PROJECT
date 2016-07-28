/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.hilos;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.apache.qpid.amqp_1_0.jms.impl.QueueImpl;
import org.apache.qpid.amqp_1_0.jms.impl.TopicImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
public class Publisher implements Runnable {
	
	@Bean
    public static Publisher miMetodo01() throws Exception {
        //com.datatraffic.parseo.gui.ParseoPrincipal.main(args);
    	//nnew String [] {"1", "2",  "3"}    	
    	String[] miArreglo= new String [] {"5796b8b6d0a9ea592d7be863","5796b8b6d0a9ea592d7be864","5796b8b6d0a9ea592d7be865","5796b8b6d0a9ea592d7be866","5796b8b6d0a9ea592d7be867","5796b8b6d0a9ea592d7be868","5796b8b6d0a9ea592d7be869","5796b8b6d0a9ea592d7be86a","5796b8b6d0a9ea592d7be86b","5796b8b6d0a9ea592d7be86c","5796b8b6d0a9ea592d7be86d","5796b8b6d0a9ea592d7be86e","5796b8b6d0a9ea592d7be86f","5796b8b6d0a9ea592d7be870","5796b8b6d0a9ea592d7be871"};
    	
        String user = env("APOLLO_USER", "admin");
        String password = env("APOLLO_PASSWORD", "password");
        String host = env("APOLLO_HOST", "127.0.0.1");
        //String host = env("APOLLO_HOST", "192.168.0.242");
        int port = Integer.parseInt(env("APOLLO_PORT", "7102"));
      //  String destination = arg(args, 0, "topic://testspring");
        String destination="queue://testspring";
        destination="queue://event";
        destination="queue://tracksporter";

        int messages = 500;
        int size = 256;

        String DATA = "abcdefghijklmnopqrstuvwxyz";
        String body = "";
        for( int i=0; i < size; i ++) {
            body += DATA.charAt(i%DATA.length());
        }

        ConnectionFactoryImpl factory = new ConnectionFactoryImpl(host, port, user, password);
        Destination dest = null;
        if( destination.startsWith("topic://") ) {
            dest = new TopicImpl(destination);
        } else {
            dest = new QueueImpl(destination);
        }

        Connection connection = factory.createConnection(user, password);
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(dest);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //producer.setTimeToLive(size);
        SimpleDateFormat formatdate = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat formattime = new SimpleDateFormat("HHmmss");
        
        
        Calendar fecha=null;
        for( int i=0; i < miArreglo.length; i ++) {
        	
            /*fecha= Calendar.getInstance();
            String trama= "\\0{\\0##\\0 00783180 C0,D7  7 $GPRMC,"+formattime.format(fecha.getTime())+".00,A,0440.829157,N,07403.668724,W,1.6,167.1,"+formatdate.format(fecha.getTime())+",5.5,E,A*25\\r\\n     182700 160317154428\\0";
            ///trama= "\\0}\\0##\\0 00563169 D1,DE 68 $GPRMC,062950.00,A,0504.560631,N,07352.389543,W,0.0,0.0,040516,5.6,E,A*2F\\r\\n   43224059 160504062951\\0";
            msg.setIntProperty("id", i);
            msg.setStringProperty("trama",trama);
            msg.setStringProperty("imei", "00783180");
            msg.setIntProperty("puerto", 11444);
            msg.setStringProperty("_groupID", "Nestor");
            msg.setStringProperty("JMSXGroupID", ""+(i%21));
            msg.setStringProperty("message_group", ""+(i%21));*/
        	TextMessage msg = session.createTextMessage("#:"+i);
        	
            String id = miArreglo[i];
            
            msg.setStringProperty("process_id",id );
        	producer.send(msg);            
            if( (i % 1000) == 0) {
                System.out.println(String.format("Sent %d messages", i));
            }
            Thread.sleep(1000*30*0);
        }

        //producer.send(session.createTextMessage("SHUTDOWN"));
        Thread.sleep(1000*3);
        connection.close();
        System.exit(0);
		return null;
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
		// TODO Auto-generated method stub
		try {
			Publisher.miMetodo01();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}