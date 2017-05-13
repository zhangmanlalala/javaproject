package LeetCode_53_Recover_Binary_Search_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *  Two elements of a binary search tree (BST) are swapped by mistake.
	Recover the tree without changing its structure.
	Note:
	A solution using O(n) space is pretty straight forward. Could you devise a constant space solution? 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public void recoverTree2(TreeNode root) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		drive(root,list);
		TreeNode temp = list.get(0);
		temp.val = list.get(1).val;
		list.get(1).val = temp.val;
    }
	
	public void drive(TreeNode root,List<TreeNode> list){
		if(list.size()==2){
			return ;
		}
		
		if(root == null){
			return ;
		}
		//ʹ�ú��������������У����μ��
		drive(root.left,list);
		
		drive(root.right,list);
		
		if(!isValidBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE)){
			list.add(root.left);
		}
		

	}
	public boolean isValidBST(TreeNode node, int min, int max){  
        if(node == null)  
            return true;  
        if(node.val>min && node.val<max && isValidBST(node.left, min, node.val)   
            && isValidBST(node.right, node.val, max) )  
            return true;  
        else  
            return false;  
    }  
	
	/**
	 * 
	 * �Լ����뷨�㲻��������������׼�ⷨ��
	 * 
	 * 
	 */
	
	/**
	 * 
	 * ������
	 *�����Ҫ��ȷ��������������ʣ���������������
	 *��ˣ�������Ҫ�����ǣ��ҵ������������Լ�λ���ϵ�Ԫ�ز�����ֵ�������ͨ���Ƚ���������Ԫ����ʵ��
	 *
	 *��Ϊ���������
	 *1�������쳣Ԫ�����ڣ�ֻ�����һ������
	 *2�������쳣Ԫ�ز����ڣ�������������򣬵�һ������ǰ������쳣���ڶ��������������쳣(�����֪����ô֤��������ͨ���������Կ�����)
	 *���ԣ����������������������¼�쳣Ԫ��ָ�뼴��
	 */
	
	TreeNode pre;
	TreeNode first;
	TreeNode second;
	
	
	public void recoverTree(TreeNode root){
		pre = first = second;
		inorder(root);
		if(first !=null && second !=null){
			int temp = first.val;
			first.val = second.val;
			second.val = temp;
		}
	}
	
	//�ܾ��䣬��֪��զ�뵽��
	/**
	 * 
	 * ���õ����������
	 * @param root
	 */
	public void inorder(TreeNode root){
		if(root == null) return;
		inorder(root.left);
		if(pre == null)
			pre = root;
		else{
			if(pre.val>root.val){
				//first����null˵����һ���쳣��û�ҵ�
				//���չ˵�һ���쳣��������Ե�ǰһ��
				if(first == null)
					first = pre;
				//������second == root�������ڲ������������ͳһ��
				//������ڣ����治����������ԣ�second�����ٱ���ֵ
				//��������ڣ�����������Ե�ʱ��second�ᱻ����
				second = root;
			}
			
			pre = root;
		}
		
		inorder(root.right);
		
	}
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
 
