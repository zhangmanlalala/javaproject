package LeetCode_9_Reorder_List;

import java.util.Stack;

/**
 * 
 * Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3} 
 * 
 * @author Administrator
 *
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class Solution {
    public void reorderList(ListNode head) {
    	if(head == null){
    		return ;
    	}
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
        	slow = slow.next;
        	fast = fast.next.next;
        }
        ListNode sl = slow;
        if(fast == null || fast.next==null){
        	while(slow.next!=null){
        		stack.add(slow.next);
        		slow = slow.next;
        	}
        	sl.next = null;     
        }
        
        slow = head;
        while(!stack.isEmpty()){
        	ListNode insertNode = stack.pop();
        	insertNode.next = slow.next;
        	slow.next = insertNode;
        	
        	slow = insertNode.next;
        }
    }
    
    public static void main(String[] args) {
    	
		ListNode node1 = new ListNode(1);
		node1.next = new ListNode(2);
		node1.next.next = new ListNode(3);
		new Solution().reorderList(node1);
	}
}
