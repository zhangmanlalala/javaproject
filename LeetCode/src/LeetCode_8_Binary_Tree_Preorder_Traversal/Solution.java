package LeetCode_8_Binary_Tree_Preorder_Traversal;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 *Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3

return [1,2,3].  
 * 
 * @author Administrator
 *
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> lst = new ArrayList<Integer>();
        if(root == null){
        	return lst;
        }
        preorderTraversal(root,lst);
        return lst;
    }
    
    public void preorderTraversal(TreeNode root,List<Integer> lst){
    	
    	if(root != null){
    		lst.add(root.val);
    		preorderTraversal(root.left,lst);
    		preorderTraversal(root.right,lst);
    	}
    }
}
