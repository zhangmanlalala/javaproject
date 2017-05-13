package leetcode_023_Merge_k_Sorted_lists;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
}
public class Solution {
	
	/**
	 * 
	 * 关键的一点是使用优先队列
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(List<ListNode> lists) {
		if(lists == null || lists.size() == 0) return null;
		
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){

			@Override
			public int compare(ListNode arg0, ListNode arg1) {
				// TODO Auto-generated method stub
				if(arg0.val<arg1.val){
					return -1;
				}else if(arg0.val == arg1.val){
					return 0;
				}else{
					return -1;
				}
			}});
		
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		
		
		for(ListNode node:lists){
			if(node!=null){
				queue.add(node);
			}
		}
		
		while(!queue.isEmpty()){
			tail.next = queue.poll();//移除队列
			tail = tail.next;
			
			if(tail.next!=null){
				queue.add(tail.next);
			}
		}
		
		return dummy.next;
		
	}
}
