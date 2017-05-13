package LeetCode_44_Convert_Sorted_Array_to_Binary_Search_Tree;


/**
 * 
 * Given an array where elements are sorted in ascending order, 
 * convert it to a height balanced BST.

Subscribe to see which companies asked this question

 * @author Administrator
 *
 */
public class Solution {
	public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0){
        	return null;
        }
        return sortedArrayToBST(null,nums,0,nums.length-1);
    }
	
	public TreeNode sortedArrayToBST(TreeNode node,int[] nums,int start,int end) {
        if(start>end){
        	return null;
        }
        int mid = (start+end+1)/2;
        node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(node.left,nums,start,mid-1);
        node.right = sortedArrayToBST(node.right,nums,mid+1,end);
        
        return node;
    }


	public TreeNode insert(TreeNode root,int x){
		
		if(root == null) {
			return new TreeNode(x);
		}
		
		int compareResult = x-root.val;
		if(compareResult<0){
			root.left = insert(root.left,x);
		}else if(compareResult>0){
			root.right = insert(root.right,x);
		}else{
			;
		}
		return root;
	}
	

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}