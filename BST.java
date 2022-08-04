/**
 * name: Eric Osterman
 * Assignment: ALA9
 * assistance from Prof.Urban
*/

public class BST<E extends Comparable<E>> {
	private TreeNode root;
	private int size;
	
	private class TreeNode{
		E value;
		TreeNode left;
		TreeNode right;
		TreeNode(E val){
			value = val;
			left = right = null;
		}
	}
	BST(){  // O(1)
		root = null;
		size = 0; 
	}
	public int size() {  // O(1)
		return size; 
	}
	public boolean isEmpty() { // O(1)
		return (size == 0); 
	}
	public void clear() { // O(1)
		root = null; 
		size = 0;
	}
	// Search method
	public int contains(E value) {// O(logn) - O(n)
		int iterations = 0;
		TreeNode node = root;
		while (node != null) {
			iterations++;
			if( value.compareTo(node.value) < 0)
				node = node.left; // go left
			else if (value.compareTo(node.value)> 0)
				node = node.right;// go right
			else
				return iterations;// value found
		}
		return iterations;
	}
	// Method add()
	public int add(E value) { // O(log n) - O(n)
		int iterations = 0;
		if (root == null) // first node to be inserted
			root = new TreeNode(value);
		else {
			TreeNode parent, node;
			parent = null; node = root;
			while (node != null) {// Looking for a leaf node
				iterations++;
				parent = node;
				if(value.compareTo(node.value) < 0) {
					node = node.left; 
				}
				else if (value.compareTo(node.value) > 0) {
					node = node.right; 
				}
				else
					return iterations; // duplicates are not allowed
			}
			if (value.compareTo(parent.value)< 0)
				parent.left = new TreeNode(value);
			else
				parent.right = new TreeNode(value);
		}
		size++;
		return iterations; 
	}
	// Method remove()
	public int remove(E value) { // O(log n) - O(n)
		int iterations = 0;
		TreeNode parent, node;
		parent = null; node = root;
		// Find value first
		while (node != null) { // O(log n) - O(n)
			iterations++;
			if (value.compareTo(node.value) < 0) {
				parent = node;
				node = node.left;
			}
			else if (value.compareTo(node.value) > 0) {
				parent = node;
				node = node.right;
			}
			else
				break; // value found
		}
		if (node == null) // value not in the tree
			return iterations;

		// Case 1: node has no children
		if(node.left == null && node.right == null){
			if(parent == null){ // delete root
				root = null;
			}
			else{
				changeChild(parent, node, null);
			}
		}
		//case 2: node has one right child
		else if(node.left == null){
			if (parent == null){ // delete root
				root = node.right;
			}
			else{
				changeChild(parent, node, node.right);
			}
		}
		//case 2: node has one left child
		else if(node.right == null){
			if (parent == null){ // delete root
				root = node.left;
			}
			else{
				changeChild(parent, node, node.left);
			}
		}
		//case 3: node has two children
		else {
			TreeNode rightMostParent = node;
			TreeNode rightMost = node.left;
			// go right on the left subtree
			while (rightMost.right != null) { // O(logn) - O(n)
				iterations++;
				rightMostParent = rightMost;
				rightMost = rightMost.right;
			}
			// copy the value of rigthMost to node
			node.value = rightMost.value;
			//delete rigthMost
			changeChild(rightMostParent, rightMost, 
					rightMost.left);
		}
		size--;
		return iterations;
	}
	private void changeChild(TreeNode parent,
			TreeNode node, TreeNode newChild){ // O(1)
		if(parent.left == node)
			parent.left = newChild;
		else
			parent.right = newChild;
	}
	// Recursive method inorder()
	public void inorder() { // O(n)
		inorder(root); // helper method
	}
	private void inorder(TreeNode node) {// O(n)
		if (node != null) {
			inorder(node.left); //LVR
			System.out.print(node.value + " ");
			inorder(node.right);
		}
	}
	// Recursive method preorder()
	public void preorder() {// O(n)
		preorder(root);
	}
	private void preorder(TreeNode node) {// O(n)
		if (node != null) {
			System.out.print(node.value + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}
	// Recursive method postorder()
	public void postorder() {// O(n)
		postorder(root);
	}
	private void postorder(TreeNode node)  {// O(n)
		if (node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.value + " ");	
		}
	}
	
	public int height() { // O(n)
		return height(root);
	}
	public int height(TreeNode node) { // O(n)
		if(node != null) {
			return 1 + Math.max(height(node.left), 
					            height(node.right));
		}
		else 
			return 0;
	}
	public boolean isBalanced() { // O(n^2)
		return isBalanced(root);
	}
	public boolean isBalanced(TreeNode node) { // O(n^2)
		if(node == null) {
			return true;
		}
		else {
			int heightL = height(node.left);
			int heightR = height(node.right);
			int diff = Math.abs((heightL-heightR));
			if(diff > 1) {
				return false;
			}
			else {
				return isBalanced(node.left) && 
					   isBalanced(node.right);
			}
		}
	}
}
