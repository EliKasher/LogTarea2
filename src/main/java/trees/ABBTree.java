package trees;

/**
 * Clase que describe los ABBTrees.
 */
public class ABBTree {
  // La raíz de este arbol
  public ABBNode root;

  /**
   * El constructor para el ABB Tree.
   */
  public ABBTree() {
    root = null;
  }

  /**
   * Constructor para el ABBTree.
   * @param rt La raíz del árbol.
   */
  public ABBTree(ABBNode rt) {
    this.root = rt;
  }

  /**
   * Busca como en un ABB clásico.
   * @param x El valor buscado.
   * @return True si encuentra x.
   */
  public boolean search(int x) {
    ABBNode actNode = root;

    // Mientras aún exista árbol
    while (actNode != null) {
      // Si el nodo actual = x, se encontró
      if (actNode.value == x) {
        return true;
      } else if (actNode.value > x) { // Si x es menor al valor actual
        actNode = actNode.left; // Debe estar a la izq
      } else { // Si x es mayor al valor actual
        actNode = actNode.right; // Debe estar a la derecha
      }
    }

    return false;
  }

  /**
   * Inserta un elemento x al árbol como en un ABB normal, pero
   * hace splay al final.
   *
   * @param x El valor insertado.
   * @return El árbol con x insertado.
   */
  public ABBNode insert(int x) {
    // El nodo actual
    ABBNode actNode = root;
    // El nodo padre
    ABBNode parentNode = null;

    // El nodo que contiene el nuevo nodo creado
    ABBNode temp = new ABBNode(x);

    // Si no hay nada a la raíz se entrega el nodo creado
    if (actNode == null) {
      root = temp;

      return temp;
    }

    // Mientras no se encuentre el lugar que le corresponde
    while (actNode != null) {
      // Se actualiza el nodo padre, ya que necesitamos actualizarlo posteriormente
      parentNode = actNode;

      if (actNode.value > x) { // Si es menor al val actual va a la izquierda
        actNode = actNode.left;
      } else if (actNode.value < x) { // Si es mayor al val actual va a la derecha
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

    // Se devuelve el nuevo arbol
    return root;
  }

}
