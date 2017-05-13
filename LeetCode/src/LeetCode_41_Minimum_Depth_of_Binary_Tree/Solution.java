package LeetCode_41_Minimum_Depth_of_Binary_Tree;


/**
 * 
 * Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from 
the root node down to the nearest leaf node.
 * 
 * @author Administrator
 *
 */
public class Solution {
	public int minDepth(TreeNode root) {
        if(root==null){
        	return 0;
        }
        if(root.left == null){
        	return minDepth(root.right)+1;
        }else if(root.right == null){
        	return minDepth(root.left)+1;
        }else{       	
        	int ldepth = minDepth(root.left);
        	int rdepth = minDepth(root.right);
        	return ldepth<rdepth? ldepth+1:rdepth+1;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
