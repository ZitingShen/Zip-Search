public class BinaryTree<E> {

	// Data members/fields...just the root is needed
	protected Node<E> root;

	// Constructor(s)
	public BinaryTree() {
		root = null;
	} // BinaryTree()

	protected BinaryTree(Node<E> node) {
		this.root = node;
	} // BinaryTree(node)

	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<E>(data);
		if (leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}

		if (rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
	} // BinaryTree(d, l, r)

	// Methods: Accessors

	public E getData() throws BinaryTreeException {
		if (root != null) {
			return root.data;
		} else {
			throw new BinaryTreeException(
					"Trying to access data from empty tree.");
		}
	}

	public boolean isEmpty() {
		return root == null;
	}

	public BinaryTree<E> getLeftSubtree() {
		if (root != null && root.left != null) {
			return new BinaryTree<E>(root.left);
		} else {
			return new BinaryTree<E>(null);
		}
	} // getLeftSubtree()

	public BinaryTree<E> getRightSubtree() {
		if (root != null && root.right != null) {
			return new BinaryTree<E>(root.right);
		} else {
			return new BinaryTree<E>(null);
		}
	} // getRightSubtree()

	public boolean isLeaf() {
		return root == null || (root.left == null && root.right == null);
	} // isLeaf()
	
	public int height() {
		return height(root);
	} // height()
	
	private int height(Node<E> node) {
		if (node == null)
			return 0;
		else
			return 1 + Math.max(height(node.left), height(node.right));
	} // height_h()

	public String toString() {
		return preOrder();
	} // toString()
	
	// Traversals
	public String preOrder() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	} // preOrder()
	
	public String postOrder() {
		StringBuilder sb = new StringBuilder();
		postOrderTraverse(root, 1, sb);
		return sb.toString();
	} // postOrder()
	
	public String inOrder() {
		StringBuilder sb = new StringBuilder();
		inOrderTraverse(root, 1, sb);
		return sb.toString();
	} // inOrder()

	// Traversal helpers
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		if (node == null) {
			// sb.append("null\n");
			sb.append("");
		} else {
			for (int i = 1; i < depth; i++) {
				sb.append("  ");
			}
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		} 
	} // preOrderTraverse()
	
	private void postOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		if (node == null) {
			// sb.append("null\n");
			sb.append("");
		} else {
			postOrderTraverse(node.left, depth + 1, sb);
			postOrderTraverse(node.right, depth + 1, sb);
			for (int i = 1; i < depth; i++) {
				sb.append("  ");
			}
			sb.append(node.toString());
			sb.append("\n");
		} 
	} // postOrderTraverse()
	
	private void inOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		if (node == null) {
			// sb.append("null\n");
			sb.append("");
		} else {
			inOrderTraverse(node.left, depth + 1, sb);
			for (int i = 1; i < depth; i++) {
				sb.append("  ");
			}
			sb.append(node.toString());
			sb.append("\n");
			inOrderTraverse(node.right, depth + 1, sb);
		} 
	} // inOrderTraverse()

	// ===========================================================
	// Needs an inner class of nodes
	protected static class Node<E> {
		protected E data;
		protected Node<E> left, right;

		// Constructor
		public Node(E data) {
			this.data = data;
			left = right = null;
		}

		// Methods
		public String toString() {
			return data.toString();
		}
	} // Node<E> inner class

	@SuppressWarnings("serial")
	private static class BinaryTreeException extends Exception {
		BinaryTreeException(String message) {
			super(message);
		}
	} // end of inner class BinaryTreeException
} // BinaryTree<E> class

