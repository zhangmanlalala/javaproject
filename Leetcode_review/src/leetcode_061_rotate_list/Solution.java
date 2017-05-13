package leetcode_061_rotate_list;
/**
 * 
 * Given a list, rotate the list to the right by k places, where k is non-negative.
	For example:
	Given 1->2->3->4->5->NULL and k = 2,
	return 4->5->1->2->3->NULL.
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
	 * 看标准答案
	 * @param head
	 * @param k
	 * @return
	 */
	//注意按照题目中的意思，k是倒数第k个节点
    public ListNode rotateRight(ListNode head, int k) {
    	if(head == null || head.next == null) return head;
    	ListNode dummy = new ListNode(0);
    	dummy.next = head; //添加一个头指针 ，这一点很重要  	
    	
    	//双指针法
    	ListNode fast = dummy,slow = dummy;	
    	//求整个链表的长度
    	int i=0;
    	while(fast.next!=null){
    		fast = fast.next;
    		i++;
    	}
    	
    	//得到第i-k%i个节点
    	for(int j=0;j<i-k%i;j++){
    		slow = slow.next;
    	}
    	
    	fast.next = dummy.next;
    	dummy.next = slow.next;
    	slow.next = null;
    	
    	return dummy.next;
    	
    	
    	
        
    }
}
