package LeetCode_52_Same_Tree;



/**
 * Given two binary trees, write a function to check if they are equal or not.
   Two binary trees are considered equal if they are structurally identical and the
    nodes have the same value.
   Subscribe to see which companies asked this question
 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	//使用中序遍历
	public boolean isSameTree(TreeNode p, TreeNode q) {
        
		
		if(p==null && q==null){
			return true;
		}else if(p==null && q!=null){
			return false;
		}else if(p!=null && q==null){
			return false;
		}
		
		/**
		 * 
		 * 采用中序遍历，去进行相关操作，很经典
		 */
		if(!isSameTree(p.left,q.left)){
			return false;
		}
		
		if(p.val != q.val){  //采用中序遍历，去进行相关操作，很经典
			return false;
		}
		
		if(!isSameTree(p.right,q.right)){
			return false;
		}
		
		return true;
		
		
    }
	
	
	
	/*
	 * 标准解法：根节点值相同，同时左子树和右子树相同，则是相等的
	 */
	public boolean isSameTree2(TreeNode p, TreeNode q) {  
		if(p==null && q==null) return true;
		if(p==null || q==null) return false;
		
		return (p.val == q.val) && isSameTree2(p.left,q.left) && isSameTree2(p.right,q.right);
		
		//递归，采用从后往前的思维，假设前面的子问题的解已知
	}
	
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
