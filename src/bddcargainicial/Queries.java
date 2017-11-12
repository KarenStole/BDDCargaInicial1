/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bddcargainicial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Karen
 */
public class Queries {
    
    /**
     * Realizar la conexion a una base de datos determinada.
     * Pasando como parametros el nombre de la base de datos,
     * el usuario de PostgreSql, la contraseña y la ip:puerto del servidor de la 
     * base de datos. 
     * 
     * @param bdd
     * @param usuario
     * @param contrasenia
     * @param ip
     * @return Como resultado devuelve una conexion con la base de datos.
     */
    public static Connection realizaConexion(String bdd, String usuario, String contrasenia, String ip){
            Connection conn = null;
            String urlDatabase =  "jdbc:postgresql://"+ip+"/"+ bdd; 
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(urlDatabase,  usuario, contrasenia);
            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
            System.out.println("La conexión se realizo sin problemas! =) ");
            return conn;
    
    
    }
    
    /**
     * Envia una consulta SQL a la base de datos en postgreSQL.
     * Dado un String con el codigo SQL y una conexion a la base de datos, este
     * metodo ejecuta la consulta SQL en la base de datos y devuelve el resultado de la consulta.
     * @param querie
     * @param c
     * @return conecion de la base de datos.
     */
    public static Connection enviarConsulta( String querie, Connection c ) {
      Statement stmt = null;
      try {
         stmt = c.createStatement();   
         stmt.executeUpdate(querie);
         stmt.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
      return c;
   }
    
    
}
