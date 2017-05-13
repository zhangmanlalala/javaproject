package leetcode_082_remove_duplicates_from_sorted_list_II;






class ListNode {
     int val;
     ListNode next;
      ListNode(int x) { val = x; }
}

public class Solution {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(1);
		node1.next = node2;
		new Solution().deleteDuplicates(node1);

	}
	
    public ListNode deleteDuplicates(ListNode head) {
    	
    	if(head == null || head.next == null){
    		return head;
    	}
    	
    	ListNode pre = new ListNode(-1);
    	pre.next = head;
    	ListNode latest = pre,temp_pre = head,temp = head.next;
    	
    	while(temp!=null){
    		if(temp.val!=temp_pre.val){//说明temp_pre有效，可以加入到新的链表中
    			latest.next = temp_pre;
    			latest = latest.next;
    			temp_pre = temp;
    			temp = temp.next;
    		}else{
    			while(temp!=null && temp.val==temp_pre.val){
    				temp_pre = temp;
    				temp = temp.next;
    			}
    			temp_pre = temp;
    			if(temp != null){	
    				temp = temp.next;
    			}else{
    				latest.next = null;
    				return pre.next;
    				
    			}
    		}
    	}
    	

		latest.next = temp_pre;
		latest = latest.next;
    	latest.next = null;
    	
    	return pre.next;
    	
    }
    
    
    

}
