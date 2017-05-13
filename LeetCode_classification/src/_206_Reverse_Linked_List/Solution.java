package _206_Reverse_Linked_List;
/**
 * 
 *Reverse a singly linked list.

click to show more hints.
Hint:

A linked list can be reversed either iteratively or recursively. 
Could you implement both?
 
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
    public ListNode reverseList(ListNode head) {
    	if(head == null){
    		return null;
    	}
        ListNode root = new ListNode(-1);
        ListNode temp = head;
        ListNode tempNext = head.next;
        while(tempNext!=null){
        	temp.next = root.next;
        	root.next = temp;
        	
        	temp = tempNext;
        	tempNext = tempNext.next;
        	
        }
        
        temp.next = root.next;
    	root.next = temp;
    	
    	return root.next;
    }
}
