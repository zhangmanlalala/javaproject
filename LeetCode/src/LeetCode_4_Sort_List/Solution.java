package LeetCode_4_Sort_List;
/**
 * 
 * Sort a linked list in O(n log n) time using constant space complexity.
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
	
	
    public ListNode sortList(ListNode head) {
    	if(head == null){
    		return null;
    	}
    	
    	ListNode slow = head;
    	ListNode fast = head;
    	ListNode slow_pre = head;
    	
    	while(fast!=null && fast.next!=null){
    		slow_pre = slow;
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	slow_pre.next = null;
    	
    	if(slow!=fast){   		
    		ListNode head1 = sortList(head);
    		ListNode head2 = sortList(slow);
    		ListNode newHead = mergeSort(head1,head2);
    		return newHead;
    	}
    	
    	return head;
    	
    	
        
    }
    
    private ListNode mergeSort(ListNode head1,ListNode head2){
    	
    	ListNode root = new ListNode(-1);
    	ListNode temp = root;
    	while(head1!=null && head2!=null){
    		if(head1.val>head2.val){
    			temp.next = head2;
    			temp = temp.next;
    			head2 = head2.next;
    		}else{
    			temp.next = head1;
    			temp = temp.next;
    			head1 = head1.next;
    		}
    	}
    	
    	if(head1!=null){
			temp.next = head1;
    	}
    	
    	if(head2!=null){
    		temp.next = head2;
    	}
    	
    	return root.next;
    }
}
