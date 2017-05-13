package _222_Count_Complete_Tree_Nodes;
/**
 * 
 *Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, 
is completely filled, and all nodes in the last level are as far left 
as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Subscribe to see which companies asked this question
 
 * 
 * @author Administrator
 *
 */

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
public class Solution {
	/**
	 * 
	 * 求完全而二叉树的节点数
	 * @param root
	 * @return
	 */
	
	/**
	 * 
	 * 标准答案，如果从某节点一直向左的高度==一直向右的高度，那么以该节点为root的子树一定是完全二叉树
	 * 而完全二叉树的节点数，可以用公式算出2^h-1.如果高度不相等，则递归调用return countNode(left)+countNode
	 * (right)+1,复杂度是O(h^2)
	 * 
	 * 
	 * @param root
	 * @return
	 */
	/**
	 * 
	 * 对于二叉树，首先要想到递归去做，对每一个子节点来说，他也可能是父节点
	 * @param root
	 * @return
	 */
    public int countNodes(TreeNode root) {
       if(root == null) return 0;
       
       int l = getLeft(root) + 1 ;
       int r = getRight(root) + 1 ;
       if(l==r){ //如果某个节点一直向左和一直向右的高度相等，则该节点为root的子树一定是一个完全二叉树
    	   return (2<<(l-1)) -1;
       }else{
    	   //递归完成，对于任意一个节点，其左子树的节点个数+右子树的节点个数+1==总的节点个数
    	   return countNodes(root.left) + countNodes(root.right)+1;
       }
    }
    
    
    private int getLeft(TreeNode root){
    	int count = 0;
    	while(root.left!=null){
    		root = root.left;
    		++count;
    	}
    	
    	return count;
    }
    
    
    private int getRight(TreeNode root){
    	int count = 0;
    	while(root.right!=null){
    		root = root.right;
    		++count;
    	}
    	
    	return count;
    }
}
