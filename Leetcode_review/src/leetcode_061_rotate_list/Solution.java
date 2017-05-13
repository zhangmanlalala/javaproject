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
	 * ����׼��
	 * @param head
	 * @param k
	 * @return
	 */
	//ע�ⰴ����Ŀ�е���˼��k�ǵ�����k���ڵ�
    public ListNode rotateRight(ListNode head, int k) {
    	if(head == null || head.next == null) return head;
    	ListNode dummy = new ListNode(0);
    	dummy.next = head; //���һ��ͷָ�� ����һ�����Ҫ  	
    	
    	//˫ָ�뷨
    	ListNode fast = dummy,slow = dummy;	
    	//����������ĳ���
    	int i=0;
    	while(fast.next!=null){
    		fast = fast.next;
    		i++;
    	}
    	
    	//�õ���i-k%i���ڵ�
    	for(int j=0;j<i-k%i;j++){
    		slow = slow.next;
    	}
    	
    	fast.next = dummy.next;
    	dummy.next = slow.next;
    	slow.next = null;
    	
    	return dummy.next;
    	
    	
    	
        
    }
}
