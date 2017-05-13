package LeetCode_51_Symmetric_Tree;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

[java] view plain copy
在CODE上查看代码片派生到我的代码片

         1  
       /   \  
      2     2  
     / \   / \  
    3   4 4   3  

But the following is not:

[java] view plain copy
在CODE上查看代码片派生到我的代码片

      1  
     / \  
    2   2  
     \   \  
      3   3  
 * 
 * @author Administrator
 *
 */

/**
 * 
 * 判断二叉树是否对称
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 自己的方法搞不定，还是来看看标准答案
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		return isSymmetric(root.left,root.right);
    }
	//很经典，要看
	public boolean isSymmetric(TreeNode left,TreeNode right) {
		if(left == null) return right==null;
		if(right == null) return false;
		if(left.val != right.val ) return false;
		if(!isSymmetric(left.left,right.right)) return false;//递归，假设前面的子问题的解已知
		if(!isSymmetric(left.right,right.left)) return false;
		
		return true;
		
    }
	
}




class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}