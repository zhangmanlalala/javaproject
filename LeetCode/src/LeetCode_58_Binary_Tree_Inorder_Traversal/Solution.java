package LeetCode_58_Binary_Tree_Inorder_Traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
	
	/**
	 * 
	 * 递归实现
	 * @param root
	 * @return
	 */
	 public List<Integer> inorderTraversal(TreeNode root) {
	        
		 List<Integer> list = new ArrayList<Integer>();
		 inorder(root,list);
		 return list;
		 
	 }
	 
	 public void inorder(TreeNode root,List<Integer> list){
		 
		 if(root!=null){
			 inorder(root.left,list);
			 list.add(root.val);
			 inorder(root.right,list);
			 
		 }
	 }
	 
	 
	 /**
	  * 
	  * 非递归实现
	  * @param root
	  * @return
	  */
	 public List<Integer> inorderTraversal2(TreeNode root) {
	        
		 List<Integer> list = new ArrayList<Integer>();
		
		 TreeNode temp = root;
		 Stack<TreeNode> st = new Stack<TreeNode>();
		 while(!st.isEmpty() || temp!=null){
			 

			 while(temp!=null){
				 st.push(temp);
				 
				 temp = temp.left;
				 
			 }
			 
			 temp = st.pop();
			 list.add(temp.val);//很是经典
			 temp = temp.right;
			
		 }
		 
		 return list;
		 
	 }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}