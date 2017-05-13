package LeetCode_38_Flatten_Binary_Tree_to_Linked_List;

import java.util.Stack;

public class Solution {
	/**
	 * 看标准解法，还没弄懂，以后再弄
	 */
	 public void flatten(TreeNode root) {  
	        if(root==null) return;  
	        toLink(root);  
	    }  
	      
	    public TreeNode toLink(TreeNode node){  
	        if(node.left==null && node.right==null)  
	            return node;  
	        TreeNode rHead = null;  
	        if(node.right != null){  
	            rHead = toLink(node.right);  
	            rHead.left = null;  
	        }  
	        TreeNode p = node;  
	        if(node.left != null){  
	            TreeNode lHead = toLink(node.left);  
	            node.right = lHead;  
	            node.left = null;  
	            lHead.left = null;  
	            while(p.right != null)  
	                p = p.right;  
	        }  
	        p.right = rHead;  
	        return node;  
	    }  
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
