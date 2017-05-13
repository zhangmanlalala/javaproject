package _230_Kth_Smallest_Element_in_a_BST;

import java.util.Stack;

/**
 *Given a binary search tree, write a function kthSmallest to find the kth smallest 
 *element in it.

Note:
You may assume k is always valid, 1 ¡Ü k ¡Ü BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find 
the kth smallest frequently? How would you optimize the kthSmallest routine? 
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
    public int kthSmallest(TreeNode root, int k) {
    	if(root == null){
    		return 0;
    	}
    	TreeNode temp = root;
    	Stack<TreeNode> ss = new Stack<TreeNode>();
    	int se = Integer.MAX_VALUE;
    	TreeNode change = root;
    	for(int i=1;i<=k;i++){
    		se = Integer.MAX_VALUE;
    		temp = root;
	    	while(temp!=null || !ss.isEmpty()){
	    		
	    		while(temp!=null){
	    			ss.add(temp);
	    			if(temp.val<se){
	    				se = temp.val;
	    				change = temp;
	    			}
	    			temp = temp.left;
	    		}
	    		
	    		if(!ss.isEmpty()){
	    			temp = ss.pop();
	    			temp = temp.right;
	    		}
	    	}
	    	
	    	change.val = Integer.MAX_VALUE;
	    	ss.clear();
    	}
    	
    	return se;
    	

    }

}
