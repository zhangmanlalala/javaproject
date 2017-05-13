package _199_Binary_Tree_Right_Side_View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 *Given a binary tree, imagine yourself standing on the right side of it, 
 *return the values of the nodes you can see ordered from top to bottom.
	For example:
	Given the following binary tree,
	
	   1            <---
	 /   \
	2     3         <---
	 \     \
	  5     4       <---
	
	You should return [1, 3, 4] 
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
	
	/**
	 * 
	 * 
	 * 考察层次遍历，真实天才
	 * @param root
	 * @return
	 */
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> lst = new ArrayList<Integer>();
        if(root == null){
        	return lst;
        }
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	while(queue.size()>0){
    		if(queue.size()==1){
    			TreeNode temp = queue.peek();
    			lst.add(temp.val);
    		}
    		TreeNode node =	queue.remove();
    		
    		if(node.left!=null) queue.add(node.left);
    		if(node.right!=null) queue.add(node.right);
    	}
    	
    	return lst; 
    }
}
