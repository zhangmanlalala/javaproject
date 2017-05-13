package _235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree;
/**
 * 
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	
        if(root == q || root ==p ){
        	return root;
        }
        
        boolean left = isContains(root.left,p,q);
        boolean right = isContains(root.right,p,q);
        if(left == true && right == true){
        	return root;
        }else if(left == true){
        	return lowestCommonAncestor(root.left,p,q);
        }else{
        	return lowestCommonAncestor(root.right,p,q);
        }
        
        
    }
    
    private boolean isContains(TreeNode root,TreeNode p,TreeNode q){
    	if(root == null){
    		return false;
    	}
    	
    	if(root == p || root ==q){
    		return true;
    	}
    	
    	return isContains(root.left,p,q) || isContains(root.right,p,q);
    	
    	
    }
    /**
     * 
     * 再看标准解法
     */
    
    public TreeNode lowestCommonAncestor2(TreeNode root,TreeNode p,TreeNode q){
    	if(root == null || root == p || root == q) return root;
    	TreeNode left = lowestCommonAncestor(root.left, p, q);
    	TreeNode right = lowestCommonAncestor(root.right, p, q);
    	return left == null ? right:right == null ? left:root;
    }
}
