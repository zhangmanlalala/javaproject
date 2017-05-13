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
	//ʹ���������
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
		 * �������������ȥ������ز������ܾ���
		 */
		if(!isSameTree(p.left,q.left)){
			return false;
		}
		
		if(p.val != q.val){  //�������������ȥ������ز������ܾ���
			return false;
		}
		
		if(!isSameTree(p.right,q.right)){
			return false;
		}
		
		return true;
		
		
    }
	
	
	
	/*
	 * ��׼�ⷨ�����ڵ�ֵ��ͬ��ͬʱ����������������ͬ��������ȵ�
	 */
	public boolean isSameTree2(TreeNode p, TreeNode q) {  
		if(p==null && q==null) return true;
		if(p==null || q==null) return false;
		
		return (p.val == q.val) && isSameTree2(p.left,q.left) && isSameTree2(p.right,q.right);
		
		//�ݹ飬���ôӺ���ǰ��˼ά������ǰ���������Ľ���֪
	}
	
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
