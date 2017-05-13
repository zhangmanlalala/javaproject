package reverse_binary_tree;

import java.util.Stack;

/**
 * 
 * 
 * 二叉树的翻转
 * @author Administrator
 *
 */

class TreeNode{
	int val;
	public TreeNode(int val) {
		// TODO Auto-generated constructor stub
		this.val = val;
	}	
	TreeNode left;
	TreeNode right;
}
public class Solution {
	
	
	/**
	 * 
	 * 这个也是正确的
	 * @param root
	 * @return
	 */
	public TreeNode reverseBinaryTree(TreeNode root){
		
		if(root == null){
			return null;
		}else if(root.right == null && root.left == null){
			return root;
		}
		
		TreeNode leftNode = root.left;
		TreeNode rightNode = root.right;//先把左右节点保存不然会被覆盖
		root.left = reverseBinaryTree(rightNode);
		root.right = reverseBinaryTree(leftNode);
		
		return root;
		
	}
	/**
	 * 
	 * 典型的二叉树递归，每个节点都是一个子问题，用栈的思维
	 * @param root
	 * @return
	 */
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
        	return null;
        }
        
        //采用后续遍历
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        
        
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        
        return root;
  
    }
    
    /**
     * 
     * 用栈将二叉树递归改为非递归操作
     */
    public TreeNode invertTree1(TreeNode root) {
    	
    	
    	Stack<TreeNode> ss = new Stack<TreeNode>();
    	TreeNode node = root,pre = root;
    	
    	while(node!=null || !ss.isEmpty()){
    		
    		while(node!=null){
    			ss.push(node);
    			node = node.left;
    		}
    		
        	
        	if(!ss.isEmpty()){
        		
        		node = ss.peek();
        		if(node.right == null || node.right == pre){//如果右节点为空，或者右节点之前遍历过
        			node = ss.pop(); //弹栈
        			
        			TreeNode rightNode = node.right;
        			node.right = node.left;
        			node.left = rightNode;
        			
        			pre = node; //已经遍历过的节点赋值
        			node = null;//
        			
        		}else{
        			node = node.right;
        		}
        		
        		
        	}
    	}
    	
    	return root;

    }
}
