package leetcode_019;
/**
 *Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Given n will always be valid.
Try to do this in one pass 
 * @author Administrator
 *
 */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        
        ListNode slow = start,fast = start;
        start.next = head;
        
        for(int i=1;i<n+1;i++){
        	fast = fast.next;
        }
        
        while(fast!=null){
        	slow = slow.next;
        	fast = fast.next;
        }
        
        slow.next = slow.next.next;
        return start.next;
        
        
    }
}
