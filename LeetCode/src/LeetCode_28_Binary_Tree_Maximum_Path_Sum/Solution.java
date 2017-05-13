package LeetCode_28_Binary_Tree_Maximum_Path_Sum;

import java.util.List;

/**
 * Given a binary tree, find the maximum path sum.
	For this problem, a path is defined as any sequence of nodes 
	from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.
	For example:
	Given the below binary tree,

       1
      / \
     2   3

Return 6.  
 * @author Administrator
 *
 */
public class Solution {
	public int maxPathSum(TreeNode root) {
        if(root == null){
        	return 0;
        }
        postorder(root);
        return Maxsum;
       
    }
	public int Maxsum = Integer.MIN_VALUE;
	
	public int postorder(TreeNode root){
		
		if(root == null){
			return 0;
		}
		
		int left_val = postorder(root.left);
		int right_val = postorder(root.right);
		
		if(left_val>0 && right_val>0){
			root.val = root.val+left_val+right_val;
		}else if(left_val>0){
			root.val = root.val+left_val;
		}else if(right_val>0){
			root.val = root.val+right_val;
		}else{
			;
		}
		if(root.val>Maxsum){
			Maxsum = root.val;
		}
		return root.val;
		
	}
}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
