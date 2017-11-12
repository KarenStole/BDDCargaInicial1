package bddcargainicial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase encargada de manegar los archivos: Lectura y escritura.
 * @author Karen
 */
public class ManejadorArchivosGenerico {
	/**
         * Metodo encardado de escribir en un archivo.
         * Se pasa como parametro el archivo a escribir, y un array en donde contiene
         * cada linea que se escribira en el archivo.
	 * @param nombreCompletoArchivo
	 * @param listaLineasArchivo lista con las lineas del archivo
	 */
	public static void escribirArchivo(String nombreCompletoArchivo, String[] listaLineasArchivo) {
		FileWriter fw;
		try {
			fw = new FileWriter(nombreCompletoArchivo,true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < listaLineasArchivo.length; i++){
				String lineaActual = listaLineasArchivo[i];
				bw.write(lineaActual);
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Error al escribir el archivo "+nombreCompletoArchivo);
			e.printStackTrace();
		}
	}

    /**
     * Metodo ecargado de leer el archivo con la ruta pasada por parametro.
     * Como resultado devuelve una lista de String, donde cada elemento es una linea del archivo.
     *
     * @param nombreCompletoArchivo
     * @return
     */
    public static String[] leerArchivo(String nombreCompletoArchivo){
		FileReader fr;
		ArrayList<String> listaLineasArchivo = new ArrayList<String>();
		try {
			fr = new FileReader(nombreCompletoArchivo);
			BufferedReader br = new BufferedReader(fr);
			String lineaActual = br.readLine();
			while (lineaActual != null){
				listaLineasArchivo.add(lineaActual);
				lineaActual = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error al leer el archivo "+nombreCompletoArchivo);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo "+nombreCompletoArchivo);
			e.printStackTrace();
		}
		System.out.println("Archivo leido satisfactoriamente");

		return listaLineasArchivo.toArray (new String [0]);
	}
}
