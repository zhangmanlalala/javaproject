package leetcode_025_Reverse_Nodes_in_k_Group;

import java.util.Stack;

/**
 *  Given a linked list, reverse the nodes of a linked list k at a time and 
 *  return its modified list.

	k is a positive integer and is less than or equal to the length of the linked list. 
	If the number of nodes is not a multiple of k then left-out nodes in the end should 
	remain as it is.
	
	You may not alter the values in the nodes, only nodes itself may be changed.
	
	Only constant memory is allowed.
	
	For example,
	Given this linked list: 1->2->3->4->5
	
	For k = 2, you should return: 2->1->4->3->5
	
	For k = 3, you should return: 3->2->1->4->5 
 * 
 * @author Administrator
 *
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> ss = new Stack<ListNode>();
        ListNode temp = head;
        ListNode node = temp;
        ListNode root = new ListNode(0);
        ListNode tp = root;
        while(temp!=null){
        	
        	node = temp;
        	
        	for(int i=0;i<k && node!=null;i++){
        		ss.push(node);
        		node = node.next;       		
        	}
        	
        	if(ss.size() == k){
        		while(!ss.isEmpty()){
        			tp.next = ss.pop(); 
        			tp = tp.next;
        		}
        		tp.next = null;
        	}else{
        		tp.next = temp;
        		break;
        	}
        	
        	temp = node;
        }
        
        return root.next;
    }
    
    public static void main(String[] args) {
		ListNode test = new ListNode(1);
		test.next = new ListNode(2);
		ListNode result = new Solution().reverseKGroup(test, 2);
		while(result!=null){
			System.out.println(result.val);
			result = result.next;
		}
	}
}
