package LeetCode_45_Binary_Tree_Level_Order_Traversal_II;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 *Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
 *(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its bottom-up level order traversal as:

[
  [15,7],
  [9,20],
  [3]
]
 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if(root == null){
			return new ArrayList<List<Integer>>();
		}
		LinkedList<List<Integer>> stack = new LinkedList<List<Integer>>();
		Queue<TreeNode> q1 = new LinkedList<TreeNode>();
		Queue<TreeNode> q2 = new LinkedList<TreeNode>();
		q1.add(root);
		while(!q1.isEmpty() || !q2.isEmpty()){
			List<Integer> level = new ArrayList<Integer>();
			if(q1.size()>0){
				while(q1.size()>0){
					TreeNode temp = q1.remove();
					level.add(temp.val);
					if(temp.left!=null) q2.add(temp.left);
					if(temp.right!=null) q2.add(temp.right);
					
				}
				stack.addFirst(level);
				continue;
			}
			
			
			if(q2.size()>0){
				while(q2.size()>0){
					TreeNode temp = q2.remove();
					level.add(temp.val);
					if(temp.left!=null) q1.add(temp.left);
					if(temp.right!=null) q1.add(temp.right);
				}
				stack.addFirst(level);
			}
		}
		List<List<Integer>> list = new ArrayList<List<Integer>>(stack);
		
		return list;
    }
	
	
	
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
