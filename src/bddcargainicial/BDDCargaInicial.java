/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bddcargainicial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Karen
 */
public class BDDCargaInicial {

    /**
     * @param args the command line arguments
     * Aqui se ejecutan todos los comandos para cargar de valores en la base de datos
     * Hay que tener en cuenta que los archivos son csv, que se hallan en la misma carpeta de este proyrcto
     * @throws java.io.FileNotFoundException
     * @throws java.sql.SQLException
     * @throws java.lang.InstantiationException
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     * @throws java.lang.IllegalAccessException
     */
    public static void main(String[] args) throws FileNotFoundException, SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        /**
         * Se realiza conexion a la base de datos: Hay que ingresar el nombre de la base de datos a utilizar
         * el usuario y contraseña de de la base de datos personal  y tambien la ip del servidor y puerto.
         */
        Connection c=Queries.realizaConexion("Mascotas", "postgres", "people098", "192.168.56.56:5432");
        
        /**
         * Se cargan los datos predefinidos a las tablas de la base de datos 
         */
        UpdateData.insertarValores("Persona", "Persona.csv",c);
        UpdateData.insertarValores("Veterinaria", "Veterinaria.csv",c);
        UpdateData.insertarValores("TipoDenuncia", "TipoDenuncia.csv",c);
        UpdateData.insertarValores("Raza", "Raza.csv",c);
        UpdateData.insertarValores("TipoAnimal", "TipoAnimal.csv",c);
        UpdateData.insertarValores("RazaTipoAnimal", "RazaTipoAnimal.csv",c);
        UpdateData.insertarValores("Mascota", "Mascota.csv",c);
        UpdateData.insertarValores("DuenioMascota", "DueñoMascota.csv",c);
        UpdateData.insertarValores("Denuncia", "Denuncia.csv",c);
        /**
         * En esta seccion se suben las imagenes correspondientes a cada mascota.
         */
        UpdateData.SubirImagen("1.jpg", 1, c);
        UpdateData.SubirImagen("2.jpg", 2, c);
        UpdateData.SubirImagen("perro.jpg", 3, c);
        UpdateData.SubirImagen("gato.jpg", 4, c);
        UpdateData.SubirImagen("sinraza.jpg", 5, c);
        UpdateData.SubirImagen("marron.jpg", 6, c);
        UpdateData.SubirImagen("cocker125k.jpg", 7, c);
        UpdateData.SubirImagen("negrokhe.jpg", 8, c);
        UpdateData.SubirImagen("peludolindo.jpg", 9,c );
        UpdateData.SubirImagen("10.jpg",10,c);
        UpdateData.SubirImagen("nosequehacer.jpg",11,c);
        c.close();

    }
    
}
