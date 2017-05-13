package leetcode_061_rotate_list;


/**
 * 
 * 经典题型，找一个链表的倒数第k个节点
 * @author Administrator
 *
 */
//分析：使用双指针法，第一个指针先走k-1步，然后两个指针一起走，第一个指针走到尾节点时，第二个指针指向的就是倒数第
//k个位置注
//注意的陷阱：k有可能大于节点总数
public class Solution1 {
	
	public static ListNode findKthToTail(ListNode head,int k){		
		if(head == null || head.next == null){
			return head;
		}	
		
		ListNode slow = head,fast = head;		
		for(int i=0;i<k-1;i++){//快指针先走k-1步
			fast = fast.next;
			if(fast == null){//防止k的值大于节点总数
				break;
			}
			
		}
		
		if(fast == null){
			return null;
		}
		while(fast.next!=null){//当快指针走到终端节点时，停下
			fast = fast.next;
			slow = slow.next;
		}
		
		return slow;		
	}
}
