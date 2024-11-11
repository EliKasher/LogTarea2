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
   * Rotación a la izquierda del árbol root, que hace el hijo derecho el padre.
   * @param p El nodo padre, al que se le aplica la rotación
   * @param x El nodo hijo, al que se pone a la raíz
   */
  public void zag(SplayNode x, SplayNode p) {
    if (p.parent != null) {
      // El padre está a la izq de su padre
      if (p == p.parent.left) {
        // Intercambia p con x
        p.parent.left = x;
      } // El padre está a la der de su padre
      else {
        p.parent.right = x;
      }
    }

    // Se deja x.left a la der de p, por lo tanto cambia el padre
    if (x.left != null) {
      x.left.parent = p;
    }
    // El padre de x es es ahora el padre de p
    x.parent = p.parent;
    // El padre de p es ahora x
    p.parent = x;
    // A la der de p está el subárbol izq de x
    p.right = x.left;
    // A la izq de x está el padre p modificado
    x.left = p;
  }

  /**
   * Rotación a la derecha del árbol  p, que hace al hijo izquierdo el padre.
   * @param p El nodo padre, al que se rota.
   * @param x EL nodo hijo a quien se pone a la raíz.
   */
  public void zig(SplayNode x, SplayNode p) {
    if (p.parent != null) {
      // Se intercambia por x el hijo de p.parent
      // Padre al a izq de su padre
      if (p.parent.left == p) {
        p.parent.left = x;
      } else { // Padre a la derecha de su padre
        p.parent.right = x;
      }
    }

    // Como se pone p.left -> x.right, p ahora es el padre de x.right
    if (x.right != null) {
      x.right.parent = p;
    }

    // El padre de x es ahora el padre de p
    x.parent = p.parent;
    // El padre de p es ahora x
    p.parent = x;
    // Se cuelga de p.left -> x.right
    p.left = x.right;
    // p ahora cuelga de x.right
    x.right = p;
  }

  /**
   * Aplica 2 zig. Primero sobre el abuelo, y luego sobre el nodo objetivo.
   * @param g El nodo abuelo
   * @param p El nodo padre
   * @param x El nodo hijo
   */
  public void zigzig(SplayNode g, SplayNode p, SplayNode x) {
    zig(p,g);
    zig(x,p);
  }

  /**
   * Hace un zig, seguido de zag. Primero sobre el nodo objetivo y su padre.
   * Luego sobre el nodo hijo y el abuelo.
   * @param x El nodo hijo
   */
  public void zigzag(SplayNode x) {
    zig(x, x.parent);
    zag(x, x.parent);
  }

  /**
   * Hace un zag, seguido de zig. Primero sobre el nodo objetivo y su padre.
   * Luuego, sobre el nodo hijo y el abuelo.
   * @param x El nodo hijo
   */
  public void zagzig(SplayNode x) {
    zag(x, x.parent);
    zig(x, x.parent);
  }

  /**
   * Hace 2 zag. Primero con el padre y el abuelo. Luego con el nodo objetivo
   * y el padre.
   * @param p El nodo padre
   * @param g El nodo abuelo
   * @param x El nodo hijo
   */
  public void zagzag(SplayNode g, SplayNode p, SplayNode x) {
    zag(p,g);
    zag(x,p);
  }

  /**
   * Aplica las rotaciones correspondientes al momento donde se busca o inserta
   * un elemento a la estructura.
   * @param x El nodo al cual se le realiza la rotación.
   * @return La nueva raíz del árbol.
   */
  public SplayNode splay(SplayNode x) {
    // z(A,B) arbol z con elemento raíz
    // A -> subarbol izq
    // B -> subarbol der

    while (x.parent != null) {
      // Guardamos el padre del nodo actual
      SplayNode parentNode = x.parent;

      // Guardamos el abuelo del nodo actual
      SplayNode grandParentNode = parentNode.parent;

      // Caso 1: No hay abuelo
      if (grandParentNode == null) {
        // si está a la derecha de la raíz
        if (parentNode.right == x) {
          // left rotation
          zag(x,parentNode);
        } else {
          // right rotation
          zig(x,parentNode);
        }
      } else {
        // Caso 2: Hay abuelo
        if (grandParentNode.left == parentNode) {
          if (parentNode.left == x) { // el nodo de x es abuelo.left.left
            zigzig(grandParentNode, parentNode, x);
          } else { // el nodo de x es abuelo.left.right
            zagzig(x);
          }
        } else {
          if (parentNode.right == x)  { // el nodo de x es abuelo.right.left
            zagzag(grandParentNode, parentNode, x);
          } else { // el nodo de x es abuelo.right.right
            zigzag(x);
          }
        }
      }
    }

    root = x;

    return root;
  }

  /**
   * Busca como en un ABB clásico, pero hace splay(x) al final.
   * @param x El valor buscado.
   * @return True si encuentra x.
   */
  public boolean search(int x) {
    if (root != null && root.value == x) {
      return true;
    }

    if (root == null) {
      return false;
    }

    SplayNode prevNode = null;
    SplayNode actNode = root;

    // Mientras aún exista árbol
    while (actNode != null) {
      prevNode = actNode;

      // Si el nodo actual = x, se encontró
      if (actNode.value == x) {
        splay(actNode);
        return true;
      } else if (actNode.value > x) { // Si x es menor al valor actual
        actNode = actNode.left; // Debe estar a la izq
      } else { // Si x es mayor al valor actual
        actNode = actNode.right; // Debe estar a la derecha
      }
    }

    // Si se llega a un nodo nulo, es porque no se encontró
    // Se hace splay del último nodo
    splay(prevNode);

    return false;
  }

  /**
   * Inserta un elemento x al árbol como en un ABB normal, pero
   * hace splay al final.
   * @param x El valor insertado.
   * @return El árbol con x insertado.
   */
  public SplayNode insert(int x) {
    if (search(x)) {
      return root;
    }

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

      if (actNode.value > x) { // Si es menor al val actual va a la izquierda
        actNode = actNode.left;
      } else if (actNode.value < x) { // Si es mayor al val actual va a la derecha
        actNode = actNode.right;
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

    splay(temp);

    // Se devuelve el nuevo arbol
    return root;
  }
}
