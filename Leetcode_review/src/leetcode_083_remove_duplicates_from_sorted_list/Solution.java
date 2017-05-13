package leetcode_083_remove_duplicates_from_sorted_list;
/**
 *  Given a sorted linked list, delete all duplicates such that each element appear only once.
	For example,
	Given 1->1->2, return 1->2.
	Given 1->1->2->3->3, return 1->2->3.
	
	Subscribe to see which companies asked this question.

 * 
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
    public ListNode deleteDuplicates(ListNode head) {
    	
    	if(head==null || head.next == null){
    		return head;
    	}
    	
    	ListNode root = new ListNode(Integer.MAX_VALUE);
    	root.next = head;
    	
    	ListNode latest = root,temp = head;
    	
    	while(temp!=null){
    		
    		if(temp.val == latest.val){
    			temp = temp.next;
    		}else{
    			latest.next = temp;
    			latest = latest.next;
    			temp = temp.next;
    		}
    	}
    	
    	latest.next = null;
    	
    	return root.next;
    }
}
