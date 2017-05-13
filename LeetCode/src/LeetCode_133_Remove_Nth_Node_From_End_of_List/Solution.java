package LeetCode_133_Remove_Nth_Node_From_End_of_List;


/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.  
   After removing the second node from the end, the linked list becomes 1->2->3->5.  
   
 * @author Administrator
 * 
 * 
 * 分析：快慢指针法，让快指针先走n步，再一起走
 * 
 * 小技巧：链表问题，想建一个dummy头总是很有帮助
 *
 */
public class Solution {
	public ListNode removeNthFromEnd(ListNode head,int n){
		if(head ==null ){
			return null;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		//快慢指针法
		ListNode fast = dummy;
		ListNode slow = dummy;
		while(n>0){
			fast = fast.next;
			n--;
		}
		while(fast.next!=null){
			fast = fast.next;
			slow = slow.next;
		}
		slow.next = slow.next.next;
		return dummy.next;
			
	}

}


class ListNode{
	int val;
	ListNode next;
	public ListNode(int val){
		this.val = val;
		next = null;
	}
}
