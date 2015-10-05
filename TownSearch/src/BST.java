public class BST<E extends Comparable<E>> extends BinaryTree<E> {
	// Data field(s)
	private int size = 0;

	// Constructor(s)

	// Methods

	public int size() {
		return this.size;
	}

	public void add(E item) {
		root = add(root, item);
		size++;
	} // add(item)

	private Node<E> add(Node<E> node, E item) {
		// insert item (preserving BST) at or below node
		// returns node at/under which item was inserted
		if (node == null) {
			// insert item right here
			return new Node<E>(item);
		} else if (item.compareTo(node.data) < 0) {
			// item is less than the data in node
			node.left = add(node.left, item);
			return node;
		} else if (item.compareTo(node.data) > 0){
			// item is greater than the data in node
			node.right = add(node.right, item);
			return node;
		} else {
			// item is equal to the data in node
			node.data = item;
			return node;
		}
	} // add(node, item)

	public E find(E item) {
		return find(root, item);
	} // find(item)

	private E find(Node<E> node, E item) {
		if (node == null) {
			return null;
		}
		int compResult = item.compareTo(node.data);
		if (compResult == 0)
			return node.data;
		else if (compResult < 0)
			return find(node.left, item);
		else
			return find(node.right, item);
	} // find(node, item)
	
	public int findComparisons(E item) {
		return findComparisons(root, item, 0);
	} // find(item)

	private int findComparisons(Node<E> node, E item, int comp) {
		if (node == null) {
			return comp;
		}
		int compResult = item.compareTo(node.data);
		comp++;
		if (compResult == 0)
			return comp;
		else if (compResult < 0)
			return findComparisons(node.left, item, comp);
		else
			return findComparisons(node.right, item, comp);
	} // find(node, item)


} // class BST<E>
