package LeetCode_11_Linked_List_Cycle;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space? 
 * 
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
	
	/**
	 * 
	 * 快慢指针法，记住，很经典
	 * @param head
	 * @return
	 */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;  
        ListNode slow = head;  
        boolean hasCircle = false;  
        while(fast != null && fast.next != null){  
            fast = fast.next.next;  
            slow = slow.next;  
            //find the meet point  
            if(fast == slow){  
                hasCircle = true;  
                break;  
            }  
        }  
        return hasCircle;  
        
    }
}
