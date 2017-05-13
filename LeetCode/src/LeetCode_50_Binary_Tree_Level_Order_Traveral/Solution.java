package LeetCode_50_Binary_Tree_Level_Order_Traveral;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]

 * 
 * @author Administrator
 *
 */

/**
 * 
 * 本题考查二叉查找树的层次遍历
 * 包括递归和非递归实现
 * @author Administrator
 *
 */
public class Solution {
	 public List<List<Integer>> levelOrder(TreeNode root) {
		 	List<List<Integer>> list1 = new ArrayList<List<Integer>>();
		 	if(root == null){
		 		return list1;
		 	}
	        List<TreeNode> rootlist = new ArrayList<TreeNode>();
	        rootlist.add(root);
	        List<List<TreeNode>> list = new ArrayList<List<TreeNode>>();
	        list.add(rootlist);
	        drive(list,0);
	        
	        
	        for(List<TreeNode> temp:list){
	        	List<Integer> lst = new ArrayList<Integer>();
	        	for(TreeNode tn:temp){
	        		lst.add(tn.val);
	        	}
	        	list1.add(lst);
	        }
	        
	        return list1;
	        
	 }
	 
	 public void drive(List<List<TreeNode>> list,int n){
		 
	

		 if(list.size()>0 && n<=list.size()){
			 List<TreeNode> tp = list.get(n);
			 List<TreeNode> nlist = new ArrayList<TreeNode>();
			 for(TreeNode t:tp){
				 if(t.left!=null){
					 nlist.add(t.left);
				 }
				 if(t.right!=null){
					 nlist.add(t.right);
				 }
			 }
			 if(nlist.size()>0){
				 list.add(nlist);
				 drive(list,n+1);
			 }else{
				 return ;
			 }			 
			
		 }
	 }
	 
	 
	 /**
	  * 
	  * 
	  * 再来看一下标准解法
	  */
	 
	 
	 public List<List<Integer>> levelOrder2(TreeNode root){
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 if(root == null) return result;
		 Queue<TreeNode> q1 = new LinkedList<TreeNode>();
		 Queue<TreeNode> q2 = new LinkedList<TreeNode>();
		 q1.add(root);
		 while(q1.size()>0 || q2.size()>0){
			 if(q1.size()>0){
				 List<Integer> level = new ArrayList<Integer>();
				 while(q1.size()>0){
					 TreeNode temp = q1.remove();
					 level.add(temp.val);
					 if(temp.left!=null) q2.add(temp.left);
					 if(temp.right!=null) q2.add(temp.right);
				 }
				 result.add(level);
				 continue;//结束本次循环,为了重新创建level
			 }
			 
			 if(q2.size()>0){
				 LinkedList<Integer> lst = new LinkedList<Integer>();
				 while(q2.size()>0){
					 TreeNode temp = q2.remove();
					 lst.addFirst(temp.val);
					 if(temp.left != null) q1.add(temp.left);
					 if(temp.right != null) q1.add(temp.right);
				 }
				 List<Integer> level = new ArrayList<Integer>(lst);
				 
				 result.add(level);
			 }
			 
		 }
		 return result;
		 
	 }
	 
	 /**
	  * 层序遍历的变种，每层反向，可用栈代替队列来实现反向。
	  * 标准解法
	  */
	 public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {  
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 if(root == null) return result;
		 Stack<TreeNode> st1 = new Stack<TreeNode>();
		 Stack<TreeNode> st2 = new Stack<TreeNode>();
		 st1.push(root);
		 while(!st1.isEmpty() || !st2.isEmpty()){
			 List<Integer> level = new ArrayList<Integer>();
			 if(st1.size()>0){
				 while(st1.size()>0){
					 TreeNode temp = st1.pop();
					 level.add(temp.val);
					 if(temp.left!=null) st2.push(temp.left);
					 if(temp.right!=null) st2.push(temp.right);
				 }
				 result.add(level);
				 continue;
			 }
			 
			 if(st2.size()>0){
				 while(st2.size()>0){
					 TreeNode temp = st2.pop();
					 level.add(temp.val);
					 if(temp.left!=null) st1.push(temp.left);
					 if(temp.right!=null) st1.push(temp.right);
				 }
				 result.add(level);
			 }
		 }
		 
		 return result;
	 }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
