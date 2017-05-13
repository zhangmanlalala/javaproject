package LeetCode_54_Validate_Binary_Search_Tree;
/**
 * 
 *  Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.

Example 1:

    2
   / \
  1   3

Binary tree [2,1,3], return true. 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * ����ⷨ���������²��ҿӶ��ǲ����еģ�ֻ�ܱ�֤��ǰ�ڵ�����������������������ʹ���
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		if(root == null){
			return true;
		}
              	     
        if(root.left!=null){
        	if(root.left.val<root.val){
        		if(isValidBST(root.left) == false)//��֤��Ψһ����ֵ
        			return false;
        		else{
        			;
        		}
        	}else{
        		return false;
        	}
        }
        
        if(root.right!=null){
        	if(root.right.val>root.val){
        		if(isValidBST(root.right) == false)//��֤��Ψһ����ֵ
        			return false;	
        		else{
        			;
        		}
        	}else{
        		return false;
        	}
        }
    
       return true;
    }
	
	/**
	 * 
	 * ��ȷ�ⷨӦ���Ǵ������ϼ�飬���ݵ�ʱ���ٽ��в���
	 */
	
	
	public boolean isValidBST2(TreeNode root) {
		if(root == null){
			return true;
		}
              	     

        if(root.left!=null){//���������
        	if(!drive_left(root.left,root.val)){
        		return false;
        	}else{
        		if(!isValidBST2(root.left))
        			return false;
        	}
        }
       
        if(root.right!=null){//���������
        	if(!drive_right(root.right,root.val)){
        		return false;
        	}else{
        		if(!isValidBST2(root.right)){
        			return false;
        		}
        	}
        	
        }
        //�������������û���⣬��ô����true	
        return true;
       
    }  
	
	public boolean drive_left(TreeNode node,int value){
		
		if(node!=null){//ʹ�����������������
			if(node.val >= value) 
				return false;
			if(!drive_left(node.left,value)){
				return false;
			}
			if(!drive_left(node.right,value)){
				return false;
			}
			
			return true;

			
		}else{
			return true;
		}
	}
	
	public boolean drive_right(TreeNode node,int value){
		
		if(node!=null){//ʹ�����������������
			if(node.val <= value) 
				return false;
			if(!drive_right(node.left,value)){
				return false;
			}
			if(!drive_right(node.right,value)){
				return false;
			}
			
			return true;

			
		}else{
			return true;
		}
	}
	
	
	/**
	 * 
	 * ��׼�ⷨ
	 */
	/**
	 *�����Ѿ���������ˣ����ɵݹ�˼ά���ǣ�

1������ֵС�ڵ�ǰֵ��

2���Һ���ֵ���ڵ�ǰֵ��

3��������ȫ��ֵС�ڵ�ǰֵ��

4��������ȫ��ֵ���ڵ�ǰֵ��

ǰ���������㣬��������ô�죿

����ά�������߽磬ĳ�����������нڵ�Ӧ���������Χ�����Ӧ�÷���false. 
	 * 
	 */
	/**
	 * 
	 * �ܾ���
	 * @param root
	 * @return
	 */
	public boolean isValidBST3(TreeNode root) {  
        
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);  
          
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
        
    
}




class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

}

