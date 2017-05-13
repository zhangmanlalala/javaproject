package leetcode_086_partition_list;

/**
 * Given a linked list and a value x, partition it such 
 * that all nodes less than x come before nodes greater than or equal to x.
	You should preserve the original relative order of the
	 nodes in each of the two partitions.
	For example,
	Given 1->4->3->2->5->2 and x = 3,
	return 1->2->2->4->3->5. 
 * @author Administrator
 *
 */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public ListNode partition(ListNode head, int x) {
    	if(head == null || head.next == null){
    		return head;
    	}
    	ListNode root = new ListNode(-1);
    	root.next = head;
    	ListNode temp = head,temp_pre = root,end = head,latest = head;
    	
    	while(end.next != null){
    		end = end.next;
    	}
    	latest = end;//指向新加入的节点
    	
    	while(temp != end){
    		
    		if(temp.val >= x){
    			temp_pre.next = temp.next;
    			latest.next = temp;
    			
    			latest = latest.next;
    			temp = temp_pre.next;
    		}else{
    			temp_pre = temp;
    			temp = temp.next;
    			
    		}
    	}
    	
    	if(temp.next == null){
    		return root.next;
    	}else{
    		if(temp.val >= x){
    			temp_pre.next = temp.next;
    			latest.next = temp;	
    			latest = latest.next;
    			latest.next = null;
    	    	return root.next;
    		}else{
    			latest.next = null;
    	    	return root.next;
    		}
		
    	}
    	
    	
    	

    }

}
