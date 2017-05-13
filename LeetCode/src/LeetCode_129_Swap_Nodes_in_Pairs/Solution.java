package LeetCode_129_Swap_Nodes_in_Pairs;

public class Solution {

	
	public ListNode swapPairs(ListNode head){
		if(head ==null || head.next==null){
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		while(pre.next !=null && pre.next.next!=null){
			ListNode first = pre.next;
			ListNode second = pre.next.next;
			
			//½»»»
			first.next = second.next;
			second.next = first;
			pre.next = second;
			first = pre;
		}
		return dummy.next;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


class ListNode{
	int val;
	ListNode next;
	ListNode(int x){
		this.val = x;
		next = null;
		
	}
}