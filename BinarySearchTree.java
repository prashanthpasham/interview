package com.interview;

public class BinarySearchTree {
	// Time Complexity - O(H)
	// H - Height of the Tree
	// Wrost Time Complexity : O(N)
	static class Node {
		private int data;
		private Node left;
		private Node right;

		public Node(int data) {
			this.data = data;
		}
	}

	public static Node insertNode(Node root, int val) {
		// Insert value into Binary Tree
		if (root == null) {
			root = new Node(val);
			return root;
		}
		if (root.data > val) {
			root.left = insertNode(root.left, val);
		} else {
			root.right = insertNode(root.right, val);
		}
		return root;
	}

	public static void preOrder(Node root) {
		// preOrder Traversal
		// Rules : 1.Root 2.Left Subtree 3.Right Subtree
		if (root == null)
			return;
		System.out.println(root.data);
		preOrder(root.left);
		preOrder(root.right);
	}

	public static void inOrder(Node root) {
		// InOrder Traversal
		// Rules : 1.Left Subtree 2.Root 3.Right Subtree
		if (root == null)
			return;
		inOrder(root.left);
		System.out.println(root.data);
		inOrder(root.right);
	}

	public static void postOrder(Node root) {
		// PostOrder Traversal
		// Rules : 1.Left Subtree 2.Right Subtree 3.Root
		if (root == null)
			return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.println(root.data);
	}

	public static boolean searchKey(Node root, int key) {
		if (root == null)
			return false;
		if (root.data > key) {
			return searchKey(root.left, key);
		} else if (root.data == key)
			return true;
		else
			return searchKey(root.right, key);

	}

	public static Node deleteNode(Node root, int val) {
		if (root != null) {

			if (root.data > val) {
				root.left = deleteNode(root.left, val);
			} else if (root.data < val) {
				System.out.println(root.data);
				root.right = deleteNode(root.right, val);
			} else {
				/**
				 * @DeleteLeafNode
				 * @Case1:No Child (Leaf Node) Delete Node and return Null to Parent
				 */
				if (root.left == null && root.right == null) {
					return null;
				}
				/**
				 * @DeleteOneChildNode
				 * @Case2 : OneChild: Delete Node & Replace with child node
				 */
				if (root.left == null)
					return root.right;
				else if (root.right == null)
					return root.left;

				/**
				 * @DeleteTwoChildrenNode
				 * @Case3: Two Children : Replace value with inorder successor & delete the node
				 *         for inorder successor.
				 */
				Node ins = inOrderSucessor(root.right, val);
				root.data = ins.data;
				root.right = deleteNode(root.right, ins.data);
			}
		}else {
			System.out.println("root is null");
			
		}
		//System.out.println("finally :: "+root);
		return root;
	}

	public static Node inOrderSucessor(Node root, int val) {
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}

	public static void main(String[] args) {
		int nodes[] = { 4, 5, 2, 7, 11, 8, 9, 1 };
		Node root = null;
		for (int val : nodes) {
			root = insertNode(root, val);
			// System.out.println(root);
		}
		System.out.println("PreOrder Traversal");
		preOrder(root);
		System.out.println("InOrder Traversal");
		/**
		 * InOrder Traversal provides ascending order values
		 */
		inOrder(root);
		System.out.println("PostOrder Traversal");
		postOrder(root);

		System.out.println("search in BST");
		System.out.println(searchKey(root, 21));

		System.out.println("Delete Node Operation");
		Node deleteNode = deleteNode(root, 11);
		System.out.println(deleteNode);
		
		inOrder(deleteNode);
		

	}
	
	public static boolean checkBalanceTree(Node root, int rootData) {

		if (root.left != null || root.right != null) {
			// root node
			rootData = root.data;
			if (root.left != null) {
				if (root.left.data < rootData)
					return checkBalanceTree(root.left, rootData);
				else
					return false;
			} else if (root.right != null) {
				if (root.right.data > rootData)
					return checkBalanceTree(root.right, rootData);
				else
					return false;
			}
		}

		return true;

	}
}
