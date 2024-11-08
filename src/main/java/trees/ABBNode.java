package trees;

/**
 * Clase para los ABB Nodes.
 * O(log n)
 */
public class ABBNode {
  // El valor contenido en el nodo actual
  public int value;
  // El nodo izquierdo (null o elementos menores a value)
  public ABBNode left;
  // El nodo derecho (null o elementos mayores a value)
  public ABBNode right;
  // El nodo que está por encima del actual
  public ABBNode parent;

  /**
   * El constructor del ABBNode.
   * @param val El valor que posee el nodo actual.
   */
  public ABBNode(int val) {
    this.value = val;
  }

}
