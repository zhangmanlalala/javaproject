package LeetCode_69_Remove_Duplicatesfrom_Sorted_List_II;

/**
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers, 
 * leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.  
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/*
	 * 我自己的解法，有点复杂了
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if(head==null){
	 		return null;
	 	}
		
        ListNode pre = new ListNode(-1);
        ListNode temp ;
        ListNode temp_pre ;
        ListNode temp_pre_pre;
        
        pre.next = head;
        temp_pre = head;
        temp = head.next;
        if(temp!=null && temp.val!=temp_pre.val){
        	temp_pre_pre = head;
        }else{
        	temp_pre_pre = pre;
        }
        
        int dif_num = 0;
        while(temp!=null){
        	if(temp.val != temp_pre.val){
        		dif_num++;
        		if(dif_num>=2){
        			temp_pre_pre = temp_pre;
        		}
        		temp_pre = temp;
        		temp = temp.next;
        	}else{
        		dif_num = 0;       		        			
        		temp_pre_pre.next = temp.next;
        		temp_pre = temp;
        		temp = temp.next;
        	}
        }
        
        return pre.next;
    }
	
	/**
	 * 
	 * 标准解法
	 */
	
	public ListNode deleteDuplicates2(ListNode head) {
        if(head == null)  
           return null;  
       ListNode newHead = new ListNode(0);  
       newHead.next = head;  
       ListNode tail = newHead;  
       ListNode p;  
       while(tail.next != null){  
           p = tail.next;  
           while(p.next!=null && p.val == p.next.val)  
               p = p.next;  
           if(tail.next != p)  
               tail.next = p.next;  
           else  
               tail = tail.next;  
       }  
       return newHead.next;  
   }


}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
