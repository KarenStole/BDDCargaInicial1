/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bddcargainicial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 *
 * @author Karen
 */
public class UpdateData {
    
    /**
     * Metodo encargado de armar la sentencia SQL INSERT TO con los valores
     * definidos en el archivo, y el nombre de la tabla determinada a 
     * ingresar los valores.
     * @param archivo
     * @param nombreTabla
     * @return sentenciaSQL
     */
    public static String ArmarSQL(String archivo, String nombreTabla){
       String[] lineas_archivos= ManejadorArchivosGenerico.leerArchivo(archivo);
       String SQLp1= "INSERT INTO " + nombreTabla + "("; //Se define la primer parte del Insert (tabla y columnad)
       String SQLp2="";                                 //Se define la segunda parte del Insert (valores)  
       String sentenciaSQL="";                          //Sentencia completa
       String [] primerLinea= lineas_archivos[0].split(",");
       int i;
       int e;
       for( i= 0; i<(primerLinea.length); i++){
           if (i== (primerLinea.length)-1){
               SQLp1+= primerLinea[i] + ") VALUES (";
           }
           else{
               SQLp1+= primerLinea[i] + ",";
           }
       }
       for(e= 1; e<(lineas_archivos.length); e++){
           String[] valores_lineas= lineas_archivos[e].split(",");
           for(int e2= 0; e2<(valores_lineas.length); e2++){
               if(isNumeric(valores_lineas[e2]) || "null".equals(valores_lineas[e2]) ){//Si el elemento es numerico o null, se ingresa como esta, sino entre comillas simples
                   if (e2==(valores_lineas.length)-1){  //Hay que tener en cuenta el ultimo valor a ingresar, cambia elementos a agregar a la sentencia.
                       SQLp2+= valores_lineas[e2]+"); ";
                       sentenciaSQL+= SQLp1+ SQLp2;
                       SQLp2= "";
                              
                   }
                   else{
                       SQLp2+= valores_lineas[e2]+",";
                   }
               }
               else {
                   if (e2==(valores_lineas.length)-1){
                       SQLp2+= "'"+valores_lineas[e2]+"');";
                       sentenciaSQL+= SQLp1+ SQLp2;
                       SQLp2= "";
                   }
                   else{
                       SQLp2+= "'"+valores_lineas[e2]+"',";
                   }                   
               }
           }
       }
       return sentenciaSQL;
    }
    
    /**
     * Metodo encargado de insertar los valores a la base de datos donde se realizo la concexion
     * Invoca a metodo ArmadoSQL() para realizar la sentencia SQL
     * @param nombre
     * @param archivo
     * @param c 
     */
    public static void insertarValores( String nombre, String archivo, Connection c){
        String sentenciaSQL= ArmarSQL(archivo, nombre);
        System.out.println(sentenciaSQL);
        Queries.enviarConsulta(sentenciaSQL, c);
        System.out.println("Elementos cargados con exito!");
    }
    
    /**
     * Dada una cadena verificar si es solo numerica
     * @param cadena
     * @return 
     */
    public static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    /**
     * Dada una imagen en formato  jpg se pasa a binario, para luego insertar en la tabla
     * de imagen de la base de datos que se realizo conexion, y a su vez asociarlo a una mascota mediante
     * el IDMascota
     * @param file
     * @param idMascota
     * @param con
     * @throws FileNotFoundException
     * @throws SQLException
     * @throws IOException 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.lang.InstantiationException 
     * @throws java.lang.IllegalAccessException 
     */
    public static void SubirImagen(String file, int idMascota, Connection con) throws FileNotFoundException, SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
            FileInputStream fis = new FileInputStream(file);
            PreparedStatement ps = con.prepareStatement("INSERT INTO imagen VALUES ("+idMascota+", ?)");
            ps.setBinaryStream(1, fis, (int)file.length());
            ps.executeUpdate();
            ps.close();
            PreparedStatement ps2 = con.prepareStatement("SELECT idimagen FROM imagen WHERE idmascota = "+idMascota);
            //ps.setString(2, "perro.jpg");
            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                byte[] imgBytes = rs.getBytes(1);
            }
            ps2.close();
            rs.close();
            fis.close();
    }
    
}
