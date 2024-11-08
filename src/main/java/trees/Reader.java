package trees;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase para leer un archivo de texto
 */
public class Reader {
  /**
   * Lee un archivo de texto con |N| = 10^6 números y los guarda en una lista
   * @param filename El nombre del archivo leído
   * @param list La lista donde se guardan los números
   */
  public void read(String filename, ArrayList<Integer> list) {
    File fichero = new File(filename);
    Scanner s = null;
    int index = 0;
    try {
      // Leemos el contenido del fichero
      s = new Scanner(fichero);

      int limit = (int) Math.pow(10,6);

      // Leemos linea a linea el fichero
      while (s.hasNextLine() && index<limit) {
        String linea = s.nextLine(); 	// Guardamos la linea en un String
        int number = Integer.parseInt(linea);
        list.add(number);
        index++;
      }
    } catch (Exception e) {
      System.out.println("Mensaje: " + e.getMessage());
    } finally {
      // Cerramos el fichero tanto si la lectura ha sido correcta o no
      try {
        if (s != null)
          s.close();
      } catch (Exception e2) {
        System.out.println("Mensaje 2: " + e2.getMessage());
      }
    }
  }
}