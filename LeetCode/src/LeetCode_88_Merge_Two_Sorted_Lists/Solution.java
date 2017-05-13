package LeetCode_88_Merge_Two_Sorted_Lists;
/**
 * 
 * 
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the
 *  first two lists.
 * @author Administrator
 *
 */
/**
 * 
 * 我的答案和标准答案一样
 * @author Administrator
 *
 */
public class Solution {
	 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        ListNode root = new ListNode(-1);
	        ListNode pre = root;//一直指向最开头
	        while(l1!=null && l2!=null){
	        	if(l1.val<l2.val){
	        		root.next = l1;
	        		l1 = l1.next;
	        	}else{
	        		root.next = l2;
	        		l2 = l2.next;
	        	}
	        }
	        if(l1 !=null){
	        	root.next = l1;
	        }
	        
	        if(l2 !=null){
	        	root.next = l2;
	        }
	        
	        return root.next;
	 }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { 
    	  val = x; 
    }
}
