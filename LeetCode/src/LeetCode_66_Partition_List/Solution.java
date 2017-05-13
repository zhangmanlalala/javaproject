package LeetCode_66_Partition_List;
/**
 * 
 * 
 *Given a linked list and a value x, partition it such that all nodes 
 *less than x come before nodes greater than or equal to x.
  You should preserve the original relative order of the nodes in each of the two partitions.
For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5 
 * @author Administrator
 *
 */
public class Solution {
	
	
	
	public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
        	return head;
        }
        
        ListNode root = new ListNode(0);
        root.next = head;//root.next()始终返回根节点
        ListNode lessNodeLast;
        ListNode temp_pre;
        ListNode temp;
        
        lessNodeLast = root;
        temp_pre = root;
        temp = root.next;
        
        while(temp!=null){
        	if(temp.val>=x){
        		temp_pre = temp;
        		temp = temp.next;
        	}else{
        		//插入与交换
        		if(temp!=lessNodeLast.next){
	        		temp_pre.next = temp.next;
	        		temp.next = lessNodeLast.next;
	        		lessNodeLast.next = temp;
	        		
	        		
	        		temp = temp_pre.next;
	        		lessNodeLast = lessNodeLast.next;
        		}else{
        			temp_pre = temp;
            		temp = temp.next;
            		lessNodeLast = lessNodeLast.next;
        		}
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