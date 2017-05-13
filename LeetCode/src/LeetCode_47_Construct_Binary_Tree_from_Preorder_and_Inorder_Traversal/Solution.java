package LeetCode_47_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;

/**
 * 利用先序遍历，中序遍历的结果建立二叉树。
 * 原理，先序遍历的每个节点相当于根节点，然后再中序遍历的结果中找到每一个前面的根节点的左右节点
 * 很经典
 * 
 * @author Administrator
 *
 */



public class Solution {
	static int num = 0;
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder.length == 0 || inorder.length == 0){
			return null;
		}
		
		return buildTree(null,preorder,inorder,0,inorder.length);
    }
	
	/**
	 * 
	 * 方法1，在地递归外面定义了一个全局变量，递归里面的每一次变化都能在这个变量上体现，并且不受回溯的影响
	 * @param node
	 * @param preorder
	 * @param inorder
	 * @param start2
	 * @param end2
	 * @return
	 */
	public TreeNode buildTree(TreeNode node,int[] preorder,int[] inorder,int start2,int end2){
		if(start2>end2 ){
			return null;
		}
		if(num>=preorder.length)
			return null;
		int i= start2;
		for(;i<=end2;i++){
			if(inorder[i] == preorder[num]){
				break;
			}
		}
		node = new TreeNode(preorder[num]);
		num++;
		
		node.left = buildTree(node.left,preorder,inorder,start2,i-1);
		node.right = buildTree(node.right,preorder,inorder,i+1,end2);
		
		return node;
	}
	
	/**
	 * 方法二，很经典，看看别人是怎么解决这个问题的
	 */
	public TreeNode buildTree(TreeNode node,int[] preorder,int prestart,int[] inorder,int start2,int end2){
		if(start2>end2 ){
			return null;
		}
		if(prestart>=preorder.length)
			return null;
		int i= start2;
		for(;i<=end2;i++){
			if(inorder[i] == preorder[prestart]){
				break;
			}
		}
		node = new TreeNode(preorder[prestart]);
		
		node.left = buildTree(node.left,preorder,prestart+1,inorder,start2,i-1);
		node.right = buildTree(node.right,preorder,prestart+1+i-start2,inorder,i+1,end2);//很经典
		//prestart+1+i-start2是多加了一部分左子树上节点的个数，右子树是从prestart+1+i-start2开始的
		
		return node;
	}
	
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
