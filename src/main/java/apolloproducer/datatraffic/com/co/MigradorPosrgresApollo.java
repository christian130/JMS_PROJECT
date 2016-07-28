/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apolloproducer.datatraffic.com.co;


import apolloproducer.datatraffic.com.co.postgres.ConexionPostgres;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.apache.qpid.amqp_1_0.jms.impl.*;
import javax.jms.*;

class MigradorPosrgresApollo {

    public static void main(String []args) throws Exception {
        //com.datatraffic.parseo.gui.ParseoPrincipal.main(args);

        String user = env("APOLLO_USER", "admin");
        String password = env("APOLLO_PASSWORD", "password");
        String host = env("APOLLO_HOST", "localhost");
        //String host = env("APOLLO_HOST", "192.168.0.242");
        int port = Integer.parseInt(env("APOLLO_PORT", "61613"));
        String destination = arg(args, 0, "queue://tracksporter");
        
        String hostdb = env("POSTGRES_HOST", "192.168.0.148");
        int portdb = Integer.parseInt(env("DBPORT", "5432"));
        String dbUser = env("DBUSER", "postgres");
        String dbpassword = env("DBPASSWORD", "123456");
        String database = env("DATABASE", "tlcsa");
        
        ConexionPostgres conexion = new ConexionPostgres(hostdb, portdb, dbUser, dbpassword, database);
        Calendar fechai= Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+0"));
        Calendar fechaf= Calendar.getInstance();
        fechai.set(2016, Calendar.JUNE, 16, 6, 0, 0);
        fechaf.set(2016, Calendar.JUNE, 16, 23, 59, 59);
        //fechai.add(Calendar.HOUR_OF_DAY, 5);
        //fechaf.add(Calendar.HOUR_OF_DAY, 5);
        
        String imei="356612024679823";
        
        //List<String> tramas= conexion.getTramas(865908, new Timestamp(fechai.getTimeInMillis()) , new Timestamp(fechaf.getTimeInMillis()));
        List<String> tramas= conexion.getTramas(18, new Timestamp(fechai.getTimeInMillis()) , new Timestamp(fechaf.getTimeInMillis()));
        
        
        System.out.println("termina consulta ");
        /*if( 1== 1){
            System.exit(100);
        }*/
        

        int messages = 50;
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
        int k=0;
       for(String trama: tramas){
           //trama= trama.replaceAll("356612026348492", "356612026362402");
           trama= trama.replaceAll("356612024692073", "356612026362402");
            System.out.println(k+"  "+trama);
       
            fecha= Calendar.getInstance();
            
            ///trama= "\\0}\\0##\\0 00563169 D1,DE 68 $GPRMC,062950.00,A,0504.560631,N,07352.389543,W,0.0,0.0,040516,5.6,E,A*2F\\r\\n   43224059 160504062951\\0";
            TextMessage msg = session.createTextMessage("#:"+k);
            msg.setIntProperty("id", k);
            msg.setStringProperty("trama",trama);
            msg.setStringProperty("imei", "00783180");
            msg.setIntProperty("puerto", 12333);
            msg.setIntProperty("remotePort", 65139);
            msg.setStringProperty("remoteHost", "10.10.10.10");
            msg.setStringProperty("_groupID", imei);
            msg.setStringProperty("JMSXGroupID", imei);
            msg.setStringProperty("message_group", imei);
            producer.send(msg);
            System.out.println("trama "+ trama);
            if( (k % 100) == 0) {
                System.out.println(String.format("Sent %d messages", k));
            }
            k++;
        }

        producer.send(session.createTextMessage("SHUTDOWN"));
        Thread.sleep(1000*3);
        connection.close();
        System.exit(0);
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

}