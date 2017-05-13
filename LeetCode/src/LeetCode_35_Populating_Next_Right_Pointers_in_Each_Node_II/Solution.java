package LeetCode_35_Populating_Next_Right_Pointers_in_Each_Node_II;

import java.util.LinkedList;
import java.util.Queue;



/**
 * 
 *Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

    You may only use constant extra space.

For example,
Given the following binary tree,

         1
       /  \
      2    3
     / \    \
    4   5    7

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public void connect(TreeLinkNode root) {
		if(root == null){
			return;
		}
        Queue<TreeLinkNode> queue1 = new LinkedList<TreeLinkNode>();
        Queue<TreeLinkNode> queue2 = new LinkedList<TreeLinkNode>();
        queue1.add(root);
        while(queue1.size()>0 || queue2.size()>0){
        	
        	if(queue1.size()>0){
        		TreeLinkNode pre = queue1.remove();
        		if(pre.left!=null) queue2.add(pre.left);
            	if(pre.right!=null) queue2.add(pre.right);
            	TreeLinkNode tmp;
        		while(queue1.size()>0){
        			tmp= queue1.remove();
        			pre.next = tmp;
        			pre = pre.next;
            		if(tmp.left!=null) queue2.add(tmp.left);
                	if(tmp.right!=null) queue2.add(tmp.right);
        			
        		}
        		
        		continue;
        	}
        	if(queue2.size()>0){
        		TreeLinkNode pre = queue2.remove();
        		if(pre.left!=null) queue1.add(pre.left);
            	if(pre.right!=null) queue1.add(pre.right);
            	TreeLinkNode tmp;
        		while(queue2.size()>0){
        			tmp = queue2.remove();
        			pre.next = tmp;
        			pre = pre.next;
        			if(tmp.left!=null) queue1.add(tmp.left);
                	if(tmp.right!=null) queue1.add(tmp.right);
        		}
        	}
        	
        	
        }
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}
