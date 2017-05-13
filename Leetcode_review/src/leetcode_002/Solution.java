package leetcode_002;
/**
 * 
 * You are given two non-empty linked lists representing
 *  two non-negative integers. The digits are stored in reverse 
 *  order and each of their nodes contain a single digit. Add the 
 *  two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero,
 except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 * @author Administrator
 *
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution {
	
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	
    	int count = 0;//½øÎ»
        ListNode pre = l1;
        ListNode l1_root = l1;
        ListNode l2_root = l2;
        while(l1!=null && l2!=null){
        	int temp = l1.val+l2.val+count;
        	if(temp>9){
        		count =1;
        		temp = temp-10;
        	}else{
        		count =0;
        	}
        	l1.val = temp;
        	l2.val = temp;
        	
        	pre = l1;
        	l1 = l1.next;
        	l2 = l2.next;
        }

        if(l1!=null){
        	pre = l1;
	        while(l1!=null){
	        	int temp = l1.val+count;
	        	if(temp>9){
	        		count =1;
	        		temp = temp-10;
	        	}else{
	        		count =0;
	        	}
	        	l1.val = temp;
	        	pre = l1;
	        	l1 = l1.next;
	        }
	        if(count ==1){
	        	pre.next = new ListNode(1);
	        }
	        return l1_root;
        }else if(l2!=null){
        	pre = l2;
	        while(l2!=null){
	        	int temp = l2.val+count;
	        	if(temp>9){
	        		count =1;
	        		temp = temp-10;
	        	}else{
	        		count =0;
	        	}
	        	l2.val = temp;
	        	pre = l2;
	        	l2 = l2.next;
	        }
	        if(count ==1){
	        	pre.next = new ListNode(1);
	        }
	        return l2_root;
        }else{
	        if(count ==1){
	        	pre.next = new ListNode(1);
	        }
	        return l1_root;
        }

    }
}
