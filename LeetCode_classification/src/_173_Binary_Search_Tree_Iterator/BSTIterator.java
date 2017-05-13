package _173_Binary_Search_Tree_Iterator;
/**
 * 
 * Implement an iterator over a binary search tree (BST). Your iterator will be 
 * initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, 
where h is the height of the tree. 
 * 
 * @author Administrator
 *
 */
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class BSTIterator {
	/**
	 * 
	 * 
	 */
	private Stack<TreeNode> stack;
	TreeNode tmp;
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        tmp = root;
        while(tmp!=null){
        	stack.add(tmp);
        	tmp = tmp.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(tmp!=null || !stack.isEmpty()){
        	return true;
        }else{
        	return false;
        }
    }

    /** @return the next smallest number */
    
    public int next() {
    	int num = 0;
        if(hasNext()){
        	if(stack.size()>0){
	        	tmp = stack.pop();
	        	num = tmp.val;
	    		tmp = tmp.right;
        	}

            while(tmp!=null){
            	stack.add(tmp);
            	tmp = tmp.left;
            }
        	
        }else{
        	return -1;
        }
        return num;
        
      
    }
}
