package trees;

/**
 * Clase para los Splay Nodes.
 * O(log n)
 */
public class SplayNode {
  // El valor contenido en el nodo actual
  public int value;
  // El nodo izquierdo (null o elementos menores a value)
  public SplayNode left;
  // El nodo derecho (null o elementos mayores a value)
  public SplayNode right;
  // El nodo que está por encima del actual
  public SplayNode parent;

  /**
   * El constructor del SplayNode.
   * @param val El valor que posee el nodo actual.
   */
  public SplayNode(int val) {
    this.value = val;
  }

}
