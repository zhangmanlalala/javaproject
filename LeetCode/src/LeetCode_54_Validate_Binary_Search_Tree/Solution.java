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
	 * 错误解法，从上往下查找坑定是不可行的，只能保证当前节点的左右子数符合条件，典型错误
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		if(root == null){
			return true;
		}
              	     
        if(root.left!=null){
        	if(root.left.val<root.val){
        		if(isValidBST(root.left) == false)//保证能唯一返回值
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
        		if(isValidBST(root.right) == false)//保证能唯一返回值
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
	 * 正确解法应该是从下往上检查，回溯的时候再进行操作
	 */
	
	
	public boolean isValidBST2(TreeNode root) {
		if(root == null){
			return true;
		}
              	     

        if(root.left!=null){//检查左子树
        	if(!drive_left(root.left,root.val)){
        		return false;
        	}else{
        		if(!isValidBST2(root.left))
        			return false;
        	}
        }
       
        if(root.right!=null){//检查右子树
        	if(!drive_right(root.right,root.val)){
        		return false;
        	}else{
        		if(!isValidBST2(root.right)){
        			return false;
        		}
        	}
        	
        }
        //如果左右子树都没问题，那么返回true	
        return true;
       
    }  
	
	public boolean drive_left(TreeNode node,int value){
		
		if(node!=null){//使用先序遍历整个子树
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
		
		if(node!=null){//使用先序遍历整个子树
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
	 * 标准解法
	 */
	/**
	 *性质已经描述清楚了，换成递归思维就是：

1，左孩子值小于当前值；

2，右孩子值大于当前值；

3，左子树全部值小于当前值；

4，右子树全部值大于当前值。

前两条好满足，后两条怎么办？

可以维持两个边界，某棵子树的所有节点应该在这个范围里，否则，应该返回false. 
	 * 
	 */
	/**
	 * 
	 * 很经典
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

