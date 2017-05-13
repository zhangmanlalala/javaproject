package _234_Palindrome_Linked_List;
/**
 * 
 *Given a singly linked list, determine if it is a palindrome. 
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
	
	/**
	 * 
	 * 快慢指针法+翻转链表
	 * @param head
	 * @return
	 */
    public boolean isPalindrome(ListNode head) {
     	if(head==null || head.next == null){
    		return true;
    	}
    	if(head.next.next == null){
    		return head.val == head.next.val;
    	}
       ListNode start = new ListNode(0);
       start.next = head;
       
       //快慢指针法
       ListNode slow = head;
       ListNode fast = head;
       ListNode temp = head.next;
       while(fast!=null && fast.next!=null){
    	   slow = slow.next;
    	   fast = fast.next.next;
       }
       ListNode last = head;
       while(temp!=slow){
    	   last.next = temp.next;
    	   
    	   temp.next = start.next;
    	   start.next = temp;
    	   
    	   temp = last.next;
    	   
       }
       if(fast == null){//对应于偶数个
    	  temp = start.next;
    	  while(slow!=null){
    		  if(slow.val!=temp.val){
    			  return false;
    		  }
    		  temp = temp.next;
    		  slow = slow.next;
    	  }
       }else{//对应于奇数个
    	 temp = start.next;
    	 slow = slow.next;
    	 while(slow!=null){
    		if(slow.val!=temp.val){
   			  return false;
   		  	}
   		  	temp = temp.next;
   		  	slow = slow.next;
    	 }
       }
       
       return true;
       
   

    }
    
    /**
     * 
     * 
     * 看标准解法
     */

    
    
}
