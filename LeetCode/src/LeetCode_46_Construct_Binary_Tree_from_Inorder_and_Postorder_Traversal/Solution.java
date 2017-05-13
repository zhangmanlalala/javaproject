package LeetCode_46_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal;



/**
 * 
 * 根据中序遍历和后续遍历建立二叉树
 * @author Administrator
 *
 */
public class Solution {
	 public TreeNode buildTree(int[] inorder, int[] postorder) {
	        if(inorder.length == 0 || postorder.length == 0){
	        	return null;
	        }
	        
	        return buildTree(null,inorder,0,inorder.length-1,postorder,postorder.length-1);
	 }
	 
	 public TreeNode buildTree(TreeNode node,int[] inorder, int instart,int inend,int[] postorder,int postend) {
	        if(instart>inend || postend<0){
	        	return null;
	        }
	        int i=instart;
	        for(;i<=inend;i++){
	        	if(inorder[i] == postorder[postend])
	        		break;
	        }
	        node = new TreeNode(postorder[postend]);
	        
	        node.right = buildTree(node.right,inorder,i+1,inend,postorder,postend-1);
	        node.left = buildTree(node.right,inorder,instart,i-1,postorder,postend-1-(inend-i));
	        
	        return node;
	        
	 }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
