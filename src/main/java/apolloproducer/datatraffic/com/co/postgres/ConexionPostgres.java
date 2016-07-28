/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apolloproducer.datatraffic.com.co.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author datatraffic
 */
public class ConexionPostgres {

    private String host;
    private int port;
    private String username;
    private String password;
    private String database;
    private Connection conexion;
    
    public ConexionPostgres(String host,int port,String username,String password,String database ){
        this.host=host;
        this.port=port;
        this.username= username;
        this.password= password;
        this.database= database;
        this.conectarPg();
    }

    private void conectarPg() {
        try {
            //se busca la existencia de la clase Driver en el paguete
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        try {
            //se establece la conexion a la base de datos
            conexion = DriverManager.getConnection("jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.database + "?characterEncoding=latin1", this.username, this.password);

        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public List<String> getTramas(int vehiculo, Timestamp fecharecibidoDesde,  Timestamp fecharecibidoHasta ) throws SQLException {
        ArrayList<String> respuesta= new ArrayList<>();
        try {
            //comando para llamar al procedimiento
            //String llamada = "SELECT distinct x.texto FROM (SELECT texto from parseo.trama_1 where texto ilike ? and fecha >= ? and fecha <= ? order by fecha asc ) AS X";
            String llamada = "select distinct t.texto, p.updatetime from seguimiento_datos.historial_posicion_16_2016 p inner join parseo.trama_1 t on (p.trama=t.id)where p.vehiculo = ? and p.updatetime >= ? and p.updatetime <= ?  and p.velocidad >= 0  and t.texto ilike '%EV18%' and p.esgprs= true order by updatetime asc";
            llamada = "select t.texto, p.updatetime from seguimiento_datos.historial_posicion_24_2016 p inner join parseo.trama_1 t on (p.trama=t.id)where p.vehiculo = ? and p.updatetime >= ? and p.updatetime <= ?   order by fecha asc";
            //crea una llamada de procedimiento
            PreparedStatement stmt = conexion.prepareStatement(llamada);
            //asignda todas las ? con los valores
            stmt.setInt(1, vehiculo);
            stmt.setTimestamp(2,fecharecibidoDesde );
            stmt.setTimestamp(3, fecharecibidoHasta );
            System.out.print(stmt.toString());
            //ejecutar el procedimiento
            ResultSet rst = stmt.executeQuery();
            
            while (rst.next()) {
                respuesta.add(rst.getString("texto"));
            }
            stmt.close();
            rst.close();
            return respuesta;
        } catch (SQLException ex) {
            ex.printStackTrace();
            String err = ex.toString();
            if (err.contains("backend")) {
                System.out.println("Sin conexion");
                conectarPg();
            }

            throw ex;
        }
    }

}
