package LeetCode_70_Remove_Duplicates_from_Sorted_List;



/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,

Given 1->1->2, return 1->2.

Given 1->1->2->3->3, return 1->2->3.
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	 public ListNode deleteDuplicates(ListNode head) {
		 	if(head==null){
		 		return null;
		 	}
	        ListNode pre = new ListNode(-1);
	        ListNode temp = new ListNode(-1);
	        ListNode temp_pre = new ListNode(-1);
	        pre.next = head;
	        temp_pre = head;
	        temp = head.next;
	        while(temp != null){
	        	if(temp.val != temp_pre.val){
	        		temp = temp.next;
	        	}else{
	        		if(temp.next!=null){
	        			temp_pre.next = temp.next;
	        		}else{
	        			temp_pre.next = null;
	        		}
	        		temp = temp_pre.next;
	        	}
	        }
	        return pre.next;
	        
	        
	 }
	 public static void main(String[] args) {
		
	}
	 
}

class ListNode {
	  int val;
	  ListNode next;
	  ListNode(int x) { val = x; }
}

