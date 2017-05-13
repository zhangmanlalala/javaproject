package _156_Binary_Tree_Upside_Down;

import java.util.Stack;

/**
 * 
 *  Given a binary tree where all the right nodes are either 
 *  leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
 *  flip it upside down and turn it into a tree where the original right nodes turned 
 *  into left leaf nodes. Return the new root.
	For example:
	Given a binary tree {1,2,3,4,5},
	1
	/ \
	2 3
	/ \
	4 5
	
	return the root of the binary tree [4,5,2,#,#,3,1].
	4
	/ \
	5 2
	  / \
	
	 3 1
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
	public TreeNode UpsideDownBinaryTree(TreeNode root) { 
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		if(root == null) return null;
		while(root.left!=null){
			stack.push(root);
			root = root.left;
		}
		stack.push(root);
		
		while(!stack.empty()){
			TreeNode node = stack.pop();
			if(!stack.isEmpty()){
				node.right = stack.peek();
				node.left = stack.peek().right;
			}else{
				node.left = null;
				node.right = null;
			}
		}
		
		return root;
	}
}
