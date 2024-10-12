package trees;

/**
 * Clase que describe los SplayTrees.
 */
public class SplayTree {
  // La raíz de este arbol
  public SplayNode root;

  /**
   * El constructor para el Splay Tree.
   */
  public SplayTree() {
    root = null;
  }

  /**
   * Constructor para el SplayTree.
   * @param rt La raíz del árbol.
   */
  public SplayTree(SplayNode rt) {
    this.root = rt;
  }

  /**
   * Busca como en un ABB clásico, pero hace splay(x) al final.
   * @param x El valor buscado.
   * @return True si encuentra x.
   */
  public boolean search(int x) {
    SplayNode actNode = root;

    // Mientras aún exista árbol
    while (actNode != null) {
      // Si el nodo actual = x, se encontró
      if (actNode.value == x) {
        root = splay(actNode);
        return true;
      } else if (actNode.value < x) { // Si x es menor al valor actual
        actNode = actNode.left; // Debe estar a la izq
      } else { // Si x es mayor al valor actual
        actNode = actNode.right; // Debe estar a la derecha
      }
    }

    // Si se llega a un nodo nulo, es porque no se encontró
    return false;
  }

  /**
   * Inserta un elemento x al árbol como en un ABB normal, pero
   * hace splay al final.
   * @param x El valor insertado.
   * @return El árbol con x insertado.
   */
  public SplayNode insert(int x) {
    // El nodo actual
    SplayNode actNode = root;
    // El nodo padre
    SplayNode parentNode = null;

    // El nodo que contiene el nuevo nodo creado
    SplayNode temp = new SplayNode(x);

    // Si no hay nada a la raíz se entrega el nodo creado
    if (actNode == null) {
      root = temp;

      return temp;
    }

    // Mientras no se encuentre el lugar que le corresponde
    while (actNode != null) {
      // Se actualiza el nodo padre, ya que necesitamos actualizarlo posteriormente
      parentNode = actNode;

      if (actNode.value < x) { // Si es menor al val actual va a la izquierda
        actNode = actNode.left;
      } else if (actNode.value > x) { // Si es mayor al val actual va a la derecha
        actNode = actNode.right;
      } else { // Es igual
        return root; // Ya existe
      }
    }

    // Si x es más grande que su padre, se asigna a la derecha
    if (parentNode.value < x) {
      parentNode.right = temp;
    } else { // Sino a la izquierda
      parentNode.left = temp;
    }

    // Se le asigna su nodo padre
    temp.parent = parentNode;

    root = splay(temp);

    // Se devuelve el nuevo arbol
    return root;
  }

  /**
   * Aplica las rotaciones correspondientes al momento donde se busca o inserta
   * un elemento a la estructura.
   * @param x El nodo al cual se le realiza la rotación.
   * @return La nueva raíz del árbol.
   */
  public SplayNode splay(SplayNode x) {
    if (root == null) {
      return null;
    }

    // z(A,B) arbol z con elemento raíz
    // A -> subarbol izq
    // B -> subarbol der

    // Caso 1: No hay abuelo
    // si está a la derecha de la raíz
    if (root.right.value == x.value) {
      // left rotation
      zag();
    } if (root.left.value == x.value) {
      // right rotation
      zig();
    }
    // Guardamos el abuelo del nodo actual
    SplayNode grandParentNode = x.parent.parent;

    // Caso 2: Hay abuelo
    if (grandParentNode.left.left.value == x.value) { // el nodo de x es abuelo.left.left
      root = zigzig();
    } if (grandParentNode.left.right.value == x.value) { // el nodo de x es abuelo.left.right
      root = zigzag();
    } if (grandParentNode.right.left.value == x.value)  { // el nodo de x es abuelo.right.left
      root = zagzig();
    } if (grandParentNode.right.right.value == x.value) { // el nodo de x es abuelo.right.right
      root = zagzag();
    }

    return root;
  }

  /**
   * Rotación a la izquierda del árbol root, que hace el hijo derecho el padre.
   * @return El árbol con una rotación a la izquierda.
   */
  public SplayNode zag() {
    SplayNode res = root.right;
    root.right = res.left;
    res.left = root;
    return res;
  }

  /**
   * Rotación a la derecha del árbol root, que hace al hijo izquierdo el padre.
   * @return El árbol con una rotación a la derecha.
   */
  public SplayNode zig() {
    SplayNode res = root.left;
    root.left = res.right;
    res.right = root;
    return res;
  }

  /**
   * @return La nueva raíz con la rotación zigzig aplicada.
   */
  public SplayNode zigzig() {
    root = zig();
    root = zig();

    return root;
  }

  /**
   * @return La nueva raíz con la rotación zigzag aplicada.
   */
  public SplayNode zigzag() {
    root = zig();
    root = zag();

    return root;
  }

  /**
   * @return La nueva raíz con la rotación zagzig aplicada.
   */
  public SplayNode zagzig() {
    root = zag();
    root = zig();

    return root;
  }

  /**
   * @return La nueva raíz con la rotación zagzag aplicada.
   */
  public SplayNode zagzag() {
    root = zag();
    root = zag();

    return root;
  }
}
