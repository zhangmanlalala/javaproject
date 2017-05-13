package LeetCode_5_Insertion_Sort_List;
/**
 * 
 * Sort a linked list using insertion sort.
 * @author Administrator
 *
 */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
public class Solution {
    public ListNode insertionSortList(ListNode head) {
    	if(head == null){
    		return head;
    	}
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode temp = head.next;
        ListNode temp_pre = head;
        ListNode node = head;
        ListNode node_pre = root;

        
        while(temp!=null){
        
        	
        	while(node!=temp){
        		if(node.val<=temp.val){
        			node_pre = node;
        			node = node.next;
        		}else{
        			temp_pre.next = temp.next;
        			node_pre.next = temp;
        			temp.next =  node;

        			break;
        		}
        	}
        	if(node == temp){
        		temp_pre = temp;
        		temp = temp.next;
        	}else{
    			
    			temp = temp_pre.next;

        	}
        	
        	node = root.next;
        	node_pre = root;
        	
        }
        
        return root.next;
    }
}
