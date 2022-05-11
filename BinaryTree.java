// Java program for different tree traversals

/* Class containing left and right child of current
node and key value*/
class Node {
	int key;
	Node left, right;

	public Node(int item)
	{
		key = item;
		left = right = null;
	}
}

public class BinaryTree {
	Node root;
	BinaryTree() { root = null; }

	void printPostorder(Node node) {
		if (node == null) return;
		printPostorder(node.left);
		printPostorder(node.right);
		System.out.print(node.key + " ");
	}
    
	void printInorder(Node node) {
		if (node == null) return;
		printInorder(node.left);
		System.out.print(node.key + " ");
		printInorder(node.right);
	}
    
	void printPreorder(Node node) {
		if (node == null) return;
		System.out.print(node.key + " ");
		printPreorder(node.left);
		printPreorder(node.right);
	}
    
}
