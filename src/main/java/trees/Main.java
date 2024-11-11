package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Clase Principal donde se realizan los testeos.
 */
public class Main {
  //Objetos para poder leer los inputs y queries, además de escribir los resultados
  static final Writer write = new Writer();
  static final Reader reader = new Reader();
  static final Random random = new Random();
  // Los valores de decimal que se utilizarán para testear, hasta N valores
  static ArrayList<Double> dec_test = new ArrayList<>();
  // La lista de numeros que se utilizarán para testear insert
  static ArrayList<Integer> numbers = new ArrayList<>();
  // La lista de lista numeros para testear por dec_test desordenada
  static ArrayList<ArrayList<Integer>> c = new ArrayList<>();
  // La lista de constantes para cada N por dec_test
  static ArrayList<Double> ctes = new ArrayList<>();
  // La lista de SplayTrees
  static ArrayList<SplayTree> st = new ArrayList<>();
  // La lista de ABBs
  static ArrayList<ABBTree> abb = new ArrayList<ABBTree>();

  /**
   * Genera una secuencia de size números
   * y los guarda en un archivo de texto inputs/numbers.txt
   */
  public static void generateTestingNumbers(int size) {
    //Hacemos un for sobre el size y agregamos numeros distintos
    for (int j = 1; j < size + 1; j++) {
      int suma = random.nextInt(100);
      write.write("inputs/numbers.txt", Integer.toString(j+suma));
    }
  }

  /**
   * Crea un SplayTree y un ABB para cada rango de testeo
   */
  public static void createTrees() {
    for (int i = 0; i < dec_test.size(); i++) {
      st.add(new SplayTree());
      abb.add(new ABBTree());
    }
  }

  /**
   * @param actLimit El límite de números a leer
   * @param M        El número de búsquedas a realizar para cada valor
   * @return un arreglo donde para cada valor de numbers para actLimit números,
   * aparezcan actLimit/M veces en un orden aleatorio
   */
  public static ArrayList<Integer> generateSearchOrder(int actLimit, int M) {
    ArrayList<Integer> searchOrder = new ArrayList<>();

    // Para cada valor i-esimo se añade M/actLimit veces
    for (int i = 0; i < actLimit; i++) {
      for (int j = 0; j < actLimit / M; j++) {
        searchOrder.add(numbers.get(i));
      }
    }

    Collections.shuffle(searchOrder);

    return searchOrder;
  }

  /**
   * @param C La constante para un N especificado.
   * @param i El elemento i buscado.
   * @return Se entrega la probabilidad de buscar el elemento i.
   */
  public static double f(double C, int i) {
    return C/Math.pow(i+1, 2);
  }

  /**
   * @param actLimit El límite de valores que están siendo utilizados actualmente,
   *                 determinado por N*act_dec_test.
   * @param index El índice correspondiente a la constante que se debe aplicar
   *              para mantener la probabilidad entre [0,1].
   * @param M El número de veces que se debe buscar cada elemento.
   * @param use un string que indica qué arreglo se está usando para las probabilidades.
   * @return La lista con elementos a buscar, determinado por una probabilidad f(i), desordenada.
   */
  public static ArrayList<Integer> generateProb(int actLimit, int index, int M, String use) {
    ArrayList<Integer> searchOrder = new ArrayList<>();

    if (use.equals("C")) {
      // El arreglo para el dec_test actual
      ArrayList<Integer> actList = c.get(index);

      // Para cada valor i-esimo se añade f(i) veces
      for (int i = 0; i < actList.size(); i++) {
        // se calcula la probabilidad
        double prob = f(ctes.get(index), i);
        // se añade el valor floor(M*prob) veces
        for (int j = 0; j < Math.floor(M*prob); j++) {
          searchOrder.add(actList.get(i));
        }
      }
    } if (use.equals("B")){
      // Para cada valor i-esimo se añade f(i) veces
      for (int i = 0; i < actLimit; i++) {
        // se calcula la probabilidad
        double prob = f(ctes.get(index), i);
        // se añade el valor floor(M*prob) veces
        for (int j = 0; j < Math.floor(M*prob); j++) {
          searchOrder.add(numbers.get(i));
        }
      }
    }

    Collections.shuffle(searchOrder);
    return searchOrder;
  }


  /**
   * Se inserta los valores de acuerdo al criterio de experimentación 1
   */
  public static void insertAST() {
    // Se randomizan los valores
    Collections.shuffle(numbers);

    // Para cada dec_test inserción en su SplayTree correspondiente
    for (int i = 0; i < dec_test.size(); i++) {
      // El arbol actual para el dec_test*N hasta el que se testea
      SplayTree act_tree = st.get(i);
      double act_dec_test = dec_test.get(i);
      int act_limit = (int) (numbers.size() * act_dec_test);

      // Insertamos los N*dec_test números 1 por 1
      for (int j = 0; j < act_limit; j++) {
        System.out.println(j);
        int act = numbers.get(j);
        act_tree.insert(act);
      }
    }
  }

  /**
   * Se inserta los valores de acuerdo al criterio de experimentación 1
   */
  public static void insertAABB() {
    // Se randomizan los valores
    Collections.shuffle(numbers);

    // Para cada dec_test inserción en su SplayTree correspondiente
    for (int i = 0; i < dec_test.size(); i++) {
      // El arbol actual para el dec_test*N hasta el que se testea
      ABBTree act_tree = abb.get(i);
      double act_dec_test = dec_test.get(i);
      int act_limit = (int) (numbers.size() * act_dec_test);

      // Insertamos los N*dec_test números 1 por 1
      for (int j = 0; j < act_limit; j++) {
        System.out.println(j);
        int act = numbers.get(j);
        act_tree.insert(act);
      }
    }
  }

  /**
   * Se inserta los valores de acuerdo al criterio de experimentación 3
   */
  public static void insertBST() {
    // Se ordenan los valores
    Collections.sort(numbers);

    // Para cada dec_test inserción en su SplayTree correspondiente
    for (int i = 0; i < dec_test.size(); i++) {
      // El arbol actual para el dec_test*N hasta el que se testea
      SplayTree act_tree = st.get(i);
      double act_dec_test = dec_test.get(i);
      int act_limit = (int) (numbers.size() * act_dec_test);

      // Insertamos los N*dec_test números 1 por 1
      for (int j = 0; j < act_limit; j++) {
        int act = numbers.get(j);
        act_tree.insert(act);
      }
    }
  }

  /**
   * Se inserta los valores de acuerdo al criterio de experimentación 3
   */
  public static void insertBABB() {
    // Se ordenan los valores
    Collections.sort(numbers);

    // Para cada dec_test inserción en su SplayTree correspondiente
    for (int i = 0; i < dec_test.size(); i++) {
      // El arbol actual para el dec_test*N hasta el que se testea
      ABBTree act_tree = abb.get(i);
      double act_dec_test = dec_test.get(i);
      int act_limit = (int) (numbers.size() * act_dec_test);

      // Insertamos los N*dec_test números 1 por 1
      for (int j = 0; j < act_limit; j++) {
        int act = numbers.get(j);
        act_tree.insert(act);
      }
    }
  }

  /**
   * Se inserta los valores de acuerdo al criterio de experimentación 4
   */
  public static void insertCST() {
    // Se ordena la lista
    Collections.sort(numbers);

    // Para cada dec_test inserción en su SplayTree correspondiente
    for (int i = 0; i < dec_test.size(); i++) {
      // El arbol actual para el dec_test*N hasta el que se testea
      SplayTree act_tree = st.get(i);
      double act_dec_test = dec_test.get(i);
      int act_limit = (int) (numbers.size() * act_dec_test);
      // Nueva lista desordenada para dec_test actual
      ArrayList<Integer> cDec = new ArrayList<>();

      // Insertamos los N*dec_test números 1 por 1
      for (int j = 0; j < act_limit; j++) {
        int act = numbers.get(j);

        // Se inserta el valor a la lista cDec
        cDec.add(act);
        // Se inserta el valor al arbol
        act_tree.insert(act);
      }

      Collections.shuffle(cDec);
      c.add(cDec);
    }
  }

  /**
   * Se inserta los valores de acuerdo al criterio de experimentación 4
   */
  public static void insertCABB() {
    // Se ordena la lista
    Collections.sort(numbers);

    // Para cada dec_test inserción en su SplayTree correspondiente
    for (int i = 0; i < dec_test.size(); i++) {
      // El arbol actual para el dec_test*N hasta el que se testea
      ABBTree act_tree = abb.get(i);
      double act_dec_test = dec_test.get(i);
      int act_limit = (int) (numbers.size() * act_dec_test);
      // Nueva lista desordenada para dec_test actual
      ArrayList<Integer> cDec = new ArrayList<>();

      // Insertamos los N*dec_test números 1 por 1
      for (int j = 0; j < act_limit; j++) {
        int act = numbers.get(j);

        // Se inserta el valor a la lista cDec
        cDec.add(act);
        // Se inserta el valor al arbol
        act_tree.insert(act);
      }

      Collections.shuffle(cDec);
      c.add(cDec);
    }
  }

  /**
   * Toma la lista de N números y los lee hasta determinados dec_test*N.
   * Luego inserta los N*dec_test valores al SplayTree.
   * Hace M/N*dec_test búsquedas en cada arbol, de manera aleatoria.
   * Escribe los resultados a un archivo de texto en la carpeta filename
   *
   * @param filename El nombre del archivo donde se guardan los resultados
   * @param M        El número de búsquedas realizadas
   */
  public static void searchAST(String filename, int M) {

    write.write(filename, "Costo Promedio de Búsqueda");

    // Para cada dec_test experimentamos la inserción en su SplayTree correspondiente y recopilamos resultados
    for (int i = 0; i < dec_test.size(); i++) {
      // El arbol actual para el dec_test*N hasta el que se testea
      SplayTree act_tree = st.get(i);
      double act_dec_test = dec_test.get(i);
      int act_limit = (int) (numbers.size() * act_dec_test);

      // La lista con los valores a buscar randomizada
      ArrayList<Integer> searchOrder = generateSearchOrder(act_limit, M);

      long totalSearchTime = 0;

      long initialTime = System.nanoTime();
      // Se busca M/N veces cada valor
      for (Integer integer : searchOrder) {
        long startTime = System.nanoTime();
        act_tree.search(integer);
        long endTime = System.nanoTime();

        // duracion de la busqueda en nanosegundos
        long duration = endTime - startTime;
        totalSearchTime += duration;
      }
      long finalTime = System.nanoTime();
      double totalTime = (finalTime - initialTime) / 1e9;

      double avgSearch = ((double) totalSearchTime / act_limit) / 1e9;

      // Luego de las inserciones y búsqueda se escriben los resultados
      write.write(filename, act_dec_test + ": " + avgSearch + " seg");
      write.write(filename, "+++++++++++++++++++++++++++++++++++++++++");

      System.out.println("Tiempo total: " + totalTime + " segundos");
    }
  }


  /**
   * Toma la lista de N números y los lee hasta determinados dec_test*N.
   * Luego inserta los N*dec_test valores al ABB.
   * Hace M/N*dec_test búsquedas en cada arbol, de manera aleatoria.
   * Escribe los resultados a un archivo de texto en la carpeta filename
   *
   * @param filename El nombre del archivo donde se guardan los resultados
   * @param M        El número de búsquedas realizadas
   */
  public static void searchAABB(String filename, int M) {

    write.write(filename, "Costo Promedio de Búsqueda");

    // Para cada dec_test experimentamos la inserción en su ABBTree correspondiente y recopilamos resultados
    for (int i = 0; i < dec_test.size(); i++) {
      // El arbol actual para el dec_test*N hasta el que se testea
      ABBTree act_tree = abb.get(i);
      double act_dec_test = dec_test.get(i);
      int act_limit = (int) (numbers.size() * act_dec_test);

      // La lista con los valores a buscar randomizada
      ArrayList<Integer> searchOrder = generateSearchOrder(act_limit, M);

      long totalSearchTime = 0;

      long initialTime = System.nanoTime();
      // Se busca M/N veces cada valor
      for (Integer integer : searchOrder) {
        long startTime = System.nanoTime();
        act_tree.search(integer);
        long endTime = System.nanoTime();

        // duracion de la busqueda en nanosegundos
        long duration = endTime - startTime;
        totalSearchTime += duration;
      }
      long finalTime = System.nanoTime();
      double totalTime = (finalTime - initialTime) / 1e9;

      double avgSearch = ((double) totalSearchTime / act_limit) / 1e9;

      // Luego de las inserciones y búsqueda se escriben los resultados
      write.write(filename, act_dec_test + ": " + avgSearch + " seg");
      write.write(filename, "+++++++++++++++++++++++++++++++++++++++++");

      System.out.println("Tiempo total: " + totalTime + " segundos");
    }
  }


  /**
   * Toma la lista de N números y los lee hasta determinados dec_test*N.
   * Luego inserta los N*dec_test valores al SplayTree.Y hace M búsquedas.
   * Escribe los resultados a un archivo de texto en la carpeta filename
   *
   * @param filename El nombre del archivo donde se encuentran los numeros
   * @param M        El número de búsquedas realizadas
   * @param use un string que indica qué arreglo se está usando para las probabilidades.
   */
  public static void searchBST(String filename, int M, String use) {

    write.write(filename, "Costo Promedio de Búsqueda");

    // Para cada dec_test experimentamos la inserción en su SplayTree correspondiente y recopilamos resultados
    for (int i = 0; i < dec_test.size(); i++) {
      // El arbol actual para el dec_test*N hasta el que se testea
      SplayTree act_tree = st.get(i);
      double act_dec_test = dec_test.get(i);
      int act_limit = (int) (numbers.size() * act_dec_test);

      // La lista con los valores a buscar
      ArrayList<Integer> searchOrder = generateProb(act_limit, i, M, use);

      long totalSearchTime = 0;

      long initialTime = System.nanoTime();
      // Se busca M/N veces cada valor
      for (Integer integer : searchOrder) {
        long startTime = System.nanoTime();
        act_tree.search(integer);
        long endTime = System.nanoTime();

        // duracion de la busqueda en nanosegundos
        long duration = endTime - startTime;
        totalSearchTime += duration;
      }
      long finalTime = System.nanoTime();
      double totalTime = (finalTime - initialTime) / 1e9;

      double avgSearch = ((double) totalSearchTime / act_limit) / 1e9;

      // Luego de las inserciones y búsqueda se escriben los resultados
      write.write(filename, act_dec_test + ": " + avgSearch + " seg");
      write.write(filename, "+++++++++++++++++++++++++++++++++++++++++");

      System.out.println("Tiempo total: " + totalTime + " segundos");
    }
  }



    /**
     * Toma la lista de N números y los lee hasta determinados dec_test*N.
     * Luego inserta los N*dec_test valores al ABBTree.Y hace M búsquedas.
     * Escribe los resultados a un archivo de texto en la carpeta filename
     *
     * @param filename El nombre del archivo donde se encuentran los numeros
     * @param M        El número de búsquedas realizadas
     * @param use un string que indica qué arreglo se está usando para las probabilidades.
     */
    public static void searchBABB(String filename, int M, String use) {

      write.write(filename, "Costo Promedio de Búsqueda");

      // Para cada dec_test experimentamos la inserción en su SplayTree correspondiente y recopilamos resultados
      for (int i = 0; i < dec_test.size(); i++) {
        // El arbol actual para el dec_test*N hasta el que se testea
        ABBTree act_tree = abb.get(i);
        double act_dec_test = dec_test.get(i);
        int act_limit = (int) (numbers.size() * act_dec_test);

        // La lista con los valores a buscar
        ArrayList<Integer> searchOrder = generateProb(act_limit, i, M, use);

        long totalSearchTime = 0;

        long initialTime = System.nanoTime();
        // Se busca M/N veces cada valor
        for (Integer integer : searchOrder) {
          long startTime = System.nanoTime();
          act_tree.search(integer);
          long endTime = System.nanoTime();

          // duracion de la busqueda en nanosegundos
          long duration = endTime - startTime;
          totalSearchTime += duration;
        }
        long finalTime = System.nanoTime();
        double totalTime = (finalTime - initialTime) / 1e9;

        double avgSearch = ((double) totalSearchTime / act_limit) / 1e9;

        // Luego de las inserciones y búsqueda se escriben los resultados
        write.write(filename, act_dec_test + ": " + avgSearch + " seg");
        write.write(filename, "+++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Tiempo total: " + totalTime + " segundos");
      }
    }


  /**
   * Ejecuta los tests para generar los árboles desde 2^10 hasta 2^25 y realizar 100 queries
   * sobre cada uno.
   */
  public static void main(String[] args) {
    // Esto no se debe volver a ejecutar si es que ya se tiene el archivo numbers.txt, pues
    // el proceso es bastante largo y sería innecesario (por esto se deja comentado)
    // Generamos los numeros para 10^6
    // Basta crear un archivo para 10^6 y para testear el resto, se lee hasta la 10^6 * decimal
    int N = (int) Math.pow(10, 6);
    // El número de búsquedas realizadas
    int M = 100 * N;
    //generateTestingNumbers(N);

    // Se insertan los valores dec_test
    // Seleccionar aquel o aquellos tramos que se quiera testear
    // El resto mantenerlos comentados
    System.out.println("Guardando dec_test");
    dec_test.add(0.1);
    //dec_test.add(0.2);
    //dec_test.add(0.3);
    //dec_test.add(0.4);
    //dec_test.add(0.5);
    //dec_test.add(0.6);
    //dec_test.add(0.7);
    //dec_test.add(0.8);
    //dec_test.add(0.9);
    //dec_test.add(1.0);

    System.out.println("Guardando ctes");
    // Se insertan las constantes para cada valor de dec_test
    ctes.add(1.00001);
    //ctes.add(1.000005);
    //ctes.add(1.00000333);
    //ctes.add(1.0000025);
    //ctes.add(1.000002);
    //ctes.add(1.00000167);
    //ctes.add(1.00000143);
    //ctes.add(1.00000125);
    //ctes.add(1.00000111);
    //ctes.add(1.000001);

    System.out.println("Creando Arboles");

    // Se crea 1 arbol para cada dec_test
    createTrees();

    System.out.println("Arboles OK");

    // TESTEAR 1 EXPERIMENTO A LA VEZ

    reader.read("inputs/numbers.txt", numbers);

    System.out.println("Números OK");

    // Testeo para el experimento 1 ST
    //System.out.println("Insertando");
    //insertAST();
    //System.out.println("Inserción OK");

    //System.out.println("Buscando");
    //searchAST("results/splay1.txt", M);
    //System.out.println("Búsqueda OK");

    // Testeo para el experimento 1 ABB
    System.out.println("Insertando");
    insertAABB();
    System.out.println("Inserción OK");

    System.out.println("Buscando");
    searchAABB("results/abb1.txt", M);
    System.out.println("Búsqueda OK");

    // Testeo para el experimento 2 ST
    //System.out.println("Insertando");
    // insertAST();
    //System.out.println("Inserción OK");

    //System.out.println("Buscando");
    // searchBST("results/splay2.txt", M, "A");
    //System.out.println("Búsqueda OK");

    // Testeo para el experimento 2 ABB
    //System.out.println("Insertando");
    // insertAABB();
    //System.out.println("Inserción OK");

    //System.out.println("Buscando");
    // searchBABB("results/abb2.txt", M, "A");
    //System.out.println("Búsqueda OK");

    // Testeo para el experimento 3 ST
    //System.out.println("Insertando");
    // insertBST();
    //System.out.println("Inserción OK");

    //System.out.println("Buscando");
    // searchAST("results/splay3.txt", M);
    //System.out.println("Búsqueda OK");


    // Testeo para el experimento 3
    //System.out.println("Insertando");
    // insertBABB();
    //System.out.println("Inserción OK");

    //System.out.println("Buscando");
    // searchAABB("results/abb3.txt", M);
    //System.out.println("Búsqueda OK");

    // Testeo para el experimento 4 ST
    //System.out.println("Insertando");
    // insertCST();
    //System.out.println("Inserción OK");

    //System.out.println("Buscando");
    // searchBST("results/splay4.txt", M, "C");
    //System.out.println("Búsqueda OK");

    // Testeo para el experimento 4 ABB
    //System.out.println("Insertando");
    // insertCABB();
    //System.out.println("Inserción OK");

    //System.out.println("Buscando");
    // searchBABB("results/abb4.txt", M, "C");
    //System.out.println("Búsqueda OK");
  }
}