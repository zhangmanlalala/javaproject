package LeetCode_60_Reverse_Linked_List_II;
/**
 * 
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:

Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.
 * 
 * @author Administrator
 *
 */
public class Solution {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if(head == null || head.next == null){
        	return head;
        }
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode temp,pre,cut;
        temp = head.next;
        pre = head;
        cut = root;
        int i=2;
        
        while(temp!=null){
        	if(i<=m){
        		cut = pre;
        		pre = temp;
        		temp = temp.next;
        		i++;
        	}else if(i>m && i<=n){
        		pre.next = temp.next;
        		temp.next = cut.next;
        		cut.next = temp;
        		
        		temp = pre.next;
        		i++;
        		
        	}else{
        		break;
        	}
        }
        
        return root.next;
        
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
