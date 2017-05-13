package construct_binary_tree;
/**
 * Given a singly linked list where elements are sorted in ascending 
 * order, convert it to a height balanced BST.
 * 
 * @author Administrator
 *
 */
/**
 * 
 * 基本思路：

先将链表中的数存入数组，转化成有序数组转换为二叉搜索树，对于一个有序数组先找到中间值作为根，左边的为左子树，
右边的为右子树，以此递归即可。
 * 
 * @author Administrator
 *
 */
class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int val) {
		this.val = val;
	}
}

class ListNode{
	int val;
	ListNode next;
	public ListNode(int val) {
		this.val = val;
		// TODO Auto-generated constructor stub
	}
}
public class Solution {
	public TreeNode sortedListToBST(ListNode head) {  
		if(head == null){
			return null;
		}
		
		ListNode temp = head;
		int num=0;
		while(temp!=null){
			num++;
			temp = temp.next;
		}
		
		temp = head;
		int[] arr = new int[num];
		num = 0;
		while(temp!=null){
			arr[num] = temp.val;
			num++;
			temp = temp.next;
		}
		
		return createBST(arr,0,arr.length-1);
		
	}
	
	private TreeNode createBST(int[] arr,int start,int end){
		
		if(start>end){
			return null;
		}
		
		int mid = (start+end)/2;
		TreeNode node = new TreeNode(arr[mid]);
		
		node.left = createBST(arr,start,mid-1);
		node.right = createBST(arr,mid+1,end);
		
		return node;
			
	}
}
