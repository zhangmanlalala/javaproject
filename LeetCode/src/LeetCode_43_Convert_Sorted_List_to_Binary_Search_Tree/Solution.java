package LeetCode_43_Convert_Sorted_List_to_Binary_Search_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a singly linked list where elements are sorted in ascending order, 
 * convert it to a height balanced BST.
 * 
 * 
 * @author Administrator
 *
 */


public class Solution {
	public TreeNode sortedListToBST(ListNode head) {
        List<Integer> lst = new ArrayList<Integer>();
        while(head!=null){
        	lst.add(head.val);
        	head = head.next;
        }
        
        Object[] nums =lst.toArray();
        return sortedArrayToBST(null,nums,0,nums.length-1);
    }
	public TreeNode sortedArrayToBST(TreeNode node,Object[] nums,int start,int end) {
        if(start>end){
        	return null;
        }
        int mid = (start+end+1)/2;
        node = new TreeNode((int) nums[mid]);
        node.left = sortedArrayToBST(node.left,nums,start,mid-1);
        node.right = sortedArrayToBST(node.right,nums,mid+1,end);
        
        return node;
    }
	
	
	/**
	 * 
	 * 看见二叉树，先想递归，排序的链表，要想转换成平衡二叉树，则中间节点应为root，在递归的对前后段建平衡二叉树就可以了
	 * 
	 * 很经典
	 * 重点：找中间节点，使用快慢指针法
	 */
	
	
	public TreeNode sortedListToBST2(ListNode head){
		return sortedListToBST(head, null);
	}
	
	private TreeNode sortedListToBST(ListNode head,ListNode tail){
		if(head == null || head==tail)
			return null;
		ListNode fast = head;
		ListNode slow = head;
		while(fast!=tail && fast.next!=tail){
			fast = fast.next.next;
			slow = slow.next;
		}
		
		TreeNode root = new TreeNode(slow.val);
		root.left = sortedListToBST(head,slow);
		root.right = sortedListToBST(slow.next,tail);
		return root;
	}
	
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
 class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
}
