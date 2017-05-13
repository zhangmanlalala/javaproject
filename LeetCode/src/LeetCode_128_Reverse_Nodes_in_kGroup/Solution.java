package LeetCode_128_Reverse_Nodes_in_kGroup;


/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * 
 * 
 * For example,
	Given this linked list: 1->2->3->4->5
	
	For k = 2, you should return: 2->1->4->3->5
	
	For k = 3, you should return: 3->2->1->4->5
 * @author Administrator
 *
 */
public class Solution {
	
	
	public ListNode reverseNodes(ListNode root,int k){
		if(root.next==null || k<=1){
			return root;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = root;
		ListNode pre = dummy;
		
		int len = k;
		ListNode first = null;
		ListNode first_pre = null;
		ListNode second = null;
		ListNode second_pre = null;
		ListNode flag = null;
		while(pre!=null){
			flag = pre;
			while(len>1){
				int num = len;
				first = pre.next;
				first_pre = pre;
				while(num>1 && pre.next!=null){
					pre = pre.next;
					num--;
				}
				if(pre.next!=null){
					second = pre.next;
					second_pre = pre;
					//½»»»
					if(second_pre!=first){
						first_pre.next = second;
						ListNode temp = first.next;
						first.next = second.next;
						second.next = temp;
						second_pre.next = first;	
					}else{
						first_pre.next = second;
						first.next = second.next;
						second.next = first;
					}
				}else{
					return dummy;
				}
				pre = first_pre.next;
				len = len-2;
			}
			int g = k;
			while(g>1 && flag.next!=null){
				flag = flag.next;
				g--;
			}
			pre = flag.next;
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
	public ListNode(int x){
		this.val = x;
		this.next = null;
	}
}
