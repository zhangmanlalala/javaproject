package LeetCode_92_Rotate_List;

/**
 * 
 * 
 *Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,

return 4->5->1->2->3->NULL. 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	public LinkedNode rotateList(LinkedNode ori,int k){
		LinkedNode pre = new LinkedNode();
		pre.next = ori;
		LinkedNode temp = new LinkedNode();
		LinkedNode end = new LinkedNode();
		temp.next = ori;
		end.next = ori;
		int length = 0;
		while(end.next!=null){
			end = end.next;
			length++;
		}
		for(int i=1;i<=length-k;i++){
			temp = temp.next;
		}
		
		pre.next = temp.next;
		end.next = ori;
		temp.next = null;
		return pre.next;
		
		
	}

}


class LinkedNode{
	LinkedNode next;
	int value;
	public LinkedNode(){
		this.next = null;
	}
	public LinkedNode(int value){
		this();
		this.value = value;
	}
}
