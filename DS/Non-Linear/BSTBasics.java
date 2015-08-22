class BSTBasics
{
	public static void main(String[] args) throws Exception{
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = new TreeNode(4);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(2);
		TreeNode node5 = new TreeNode(6);
		TreeNode node6 = new TreeNode(7);
		TreeNode node7 = new TreeNode(8);
		TreeNode node8 = new TreeNode(9);
		TreeNode node9 = new TreeNode(10);
		node1.left = node3;
		node1.right = node7;
		node3.left = node4;
		node3.right = node2;
		node7.left = node6;
		node7.right = node8;
		node6.left = node5;
		node8.right = node9;

		printInRange(node1, 4, 12);
		System.out.println();
		printDescendingOrder(node1);
		System.out.println();
		System.out.println("Min : "  + findMin(node1));
		System.out.println("Is BST : " + checkBST(node1)); 
	}
	
	static int findMin(TreeNode root) throws Exception{
		if(root == null)
			throw new Exception("Invalid argument");
		TreeNode temp = root;

		while(temp.left != null) {
			temp = temp.left;
		}

		return temp.val;
	}

	static boolean checkBST(TreeNode root) {
		if(root == null || root.isLeafNode())
			return true;
		boolean leftCheck = checkBST(root.left);
		if(!leftCheck)
			return false;

		boolean rightCheck = checkBST(root.right);
		if(!rightCheck)
			return false;
		if((root.left != null && (root.left.val > root.val)) || (root.right != null && root.right.val < root.val))
			return false;

		return true;	
	}

	static void printInRange(TreeNode root, int a , int b) {
		if(root == null) {
			return;
		}
		if(root.val > a)
			printInRange(root.left, a, b);

		if(root.val >= a && root.val <= b) {
			System.out.print(root.val + " ");
		}

		if(root.val < b)
			printInRange(root.right, a, b);
	}

	static void printDescendingOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		if(root.isLeafNode()){
			System.out.print(root.val + " ");
		}
		printDescendingOrder(root.right);
		printDescendingOrder(root.left);
	}

	static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
		public TreeNode(int val) 
		{
			this.val = val; 
		}

		public boolean isLeafNode() {
			return left == null && right == null;
		}
	}
}