package trees;

/**
 * Clase para un árbol binario clásico.
 */
public class ABB {
  // El valor contenido en el nodo actual
  public int value;
  // El árbol izquierdo (null o elementos menores a value)
  public ABB left;
  // El árbol derecho (null o elementos mayores a value)
  public ABB right;

  /**
   * Constructor del ABB.
   * @param val El valor que posee el árbol actual.
   */
  public ABB(int val) {
    value = val;
  }

  /**
   * Búsqueda en un árbol binario, si no está en el nodo actual,
   * busca en los hijos, dependiendo si el valor es mayor o menor
   * al actual.
   * @param root La raíz del árbol.
   * @param x El valor buscado.
   * @return True si encontró el valor.
   */
  public boolean search(ABB root, int x) {
    ABB actNode = root;

    // Mientras aún exista árbol
    while (actNode != null) {
      // Si el nodo actual = x, se encontró
      if (actNode.value == x) {
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
   * Inserta el elemento x en el arbol entregado.
   * @param root La raíz del árbol.
   * @param x El valor a insertar.
   * @return El nuevo arbol con x insertado.
   */
  public ABB insert(ABB root, int x) {
    // El nodo actual
    ABB actNode = root;
    // El nodo padre
    ABB parentNode = null;

    // El nodo que contiene el nuevo nodo creado
    ABB temp = new ABB(x);

    // Si no hay nada a la raíz se entrega el nodo creado
    if (actNode == null) {
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

    // Se devuelve el nuevo arbol
    return root;
  }
}
