package leetcode_024_swap_Nodes_in_pairs;
/**
 *  Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, 
only nodes itself can be changed. 
 * @author Administrator
 *
 */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
public class Solution {
    public ListNode swapPairs(ListNode head) {
    	if(head==null){
    		return head;
    	}
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode temp_pre = root;
        ListNode temp = head,temp_next = temp.next;
        while(temp_next != null){
        	temp_pre.next = temp_next;
        	temp.next =temp_next.next;
        	temp_next.next = temp;
        	
        	
        	temp_pre = temp_pre.next.next;
        	temp = temp_pre.next;
        	if(temp == null){
        		temp_next = null;
        		break;
        	}else{
        		temp_next = temp.next;
        	}
        	
        }
        
        return root.next;
    }
}
