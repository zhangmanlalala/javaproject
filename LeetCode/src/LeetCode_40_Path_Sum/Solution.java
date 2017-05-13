package LeetCode_40_Path_Sum;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<List<Integer>> hasPathSum(TreeNode root, int sum) {

		List<Integer> lst = new ArrayList<Integer>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root == null){
			return result;
		}
		pathSum(root,sum,0,lst,result);
		
		return result;
    }
	public void pathSum(TreeNode root,int sum,int temp_sum,List<Integer> lst,List<List<Integer>> result){
		if(root==null){
			return;
		}
		if(root.left==null && root.right==null){
			temp_sum = temp_sum+root.val;
			lst.add(root.val);
			if(temp_sum == sum){
				List<Integer> tp = new ArrayList<Integer>();
				tp.addAll(lst);
				result.add(tp);
			}
			return ;
		}
		temp_sum = temp_sum+root.val;
		lst.add(root.val);
		if(root.left!=null)
			pathSum(root.left,sum,temp_sum,lst,result); //相当于借用回溯的想法
			lst.remove(lst.size()-1);
		if(root.right!=null)
			pathSum(root.right,sum,temp_sum,lst,result);
			lst.remove(lst.size()-1);
	
	}
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
