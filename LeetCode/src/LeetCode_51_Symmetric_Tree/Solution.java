package LeetCode_51_Symmetric_Tree;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

[java] view plain copy
��CODE�ϲ鿴����Ƭ�������ҵĴ���Ƭ

         1  
       /   \  
      2     2  
     / \   / \  
    3   4 4   3  

But the following is not:

[java] view plain copy
��CODE�ϲ鿴����Ƭ�������ҵĴ���Ƭ

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
 * �ж϶������Ƿ�Գ�
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * �Լ��ķ����㲻����������������׼��
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		return isSymmetric(root.left,root.right);
    }
	//�ܾ��䣬Ҫ��
	public boolean isSymmetric(TreeNode left,TreeNode right) {
		if(left == null) return right==null;
		if(right == null) return false;
		if(left.val != right.val ) return false;
		if(!isSymmetric(left.left,right.right)) return false;//�ݹ飬����ǰ���������Ľ���֪
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