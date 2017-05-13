package LeetCode_10_Linked_List_Cycle_II;

/**
 * 
 *  Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
	Note: Do not modify the linked list.
	
	Follow up:
	Can you solve it without using extra space? 


 * @author Administrator
 *
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class Solution {
 
    
    /**
     * 
     * 
     * 标准答案:
     * 经典链表题，快慢指针法。
     * 假设链表头到环开头有k个节点，则慢指针走到换头为k步，快指针为2k步，设环长度为size，则快指针在m*size-k步之后与慢指针相遇
     * ，此时慢指针在经过k步到达环头，k未知，则把快指针重新指向链表头，快慢指针每次向前移动一步，相遇点即为环头
     * 
     */
    public ListNode detectCycle2(ListNode head){
    	ListNode fast = head;
    	ListNode slow = head;
    	while(fast!=null && fast.next!=null){
    		fast = fast.next.next;
    		slow = slow.next;
    		if(fast == slow){
    			break;
    		}
    	}
    	
    	if(fast == null || fast.next == null){
    		return null;
    	}
    	
    	fast = head;
    	while(fast!=slow){
    		fast = fast.next;
    		slow = slow.next;
    	}
    	
    	return slow;
    }
}
