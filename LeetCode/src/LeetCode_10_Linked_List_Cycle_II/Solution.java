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
     * ��׼��:
     * ���������⣬����ָ�뷨��
     * ��������ͷ������ͷ��k���ڵ㣬����ָ���ߵ���ͷΪk������ָ��Ϊ2k�����軷����Ϊsize�����ָ����m*size-k��֮������ָ������
     * ����ʱ��ָ���ھ���k�����ﻷͷ��kδ֪����ѿ�ָ������ָ������ͷ������ָ��ÿ����ǰ�ƶ�һ���������㼴Ϊ��ͷ
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
