package LeetCode_42_Balanced_Binary_Tree;


/**
 * Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1. 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public boolean isBalanced(TreeNode root) {
		if(root == null){
			return true;
		}
		
        if(!isBalanced(root.left)){
        	return false;
        }
        if(Math.abs(height(root.left)-height(root.right))>1){
        	return false;
        }
        if(!isBalanced(root.right))
        	return false;
        return true;
    }
	
	private int height(TreeNode root){
		if(root == null){
			return 0;
		}
		int lheight = height(root.left);
		int rheight = height(root.right);
		return lheight>rheight? lheight+1:rheight+1;
	}
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
