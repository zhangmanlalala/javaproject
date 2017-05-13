package _203_Remove_Linked_List_Elements;
/**
 *Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5  
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
    public ListNode removeElements(ListNode head, int val) {
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode pre = root;
        ListNode temp = head;
        while(temp!=null){
        	if(temp.val!=val){
        		pre = temp;
        		temp = temp.next;
        	}else{
        		pre.next = temp.next;
        		temp = pre.next;
        	}
        }
        
        return root.next;
    }
}
