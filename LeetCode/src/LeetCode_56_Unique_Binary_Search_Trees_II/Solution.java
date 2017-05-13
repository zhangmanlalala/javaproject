package LeetCode_56_Unique_Binary_Search_Trees_II;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * Given an integer n, generate all structurally unique BST's (binary search trees) that 
 * store values 1...n.

	For example,
	Given n = 3, your program should return all 5 unique BST's shown below. 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 我自己所有的尝试都失败了，还是看看标准答案，这个坎一直过不去
	 * @param n
	 * @return
	 */
	public List<TreeNode> generateTrees(int n) {
		return generateTrees(0, n-1); 
    }
	/**
	 * 
	 * 标准答案1，我有些看不懂，略过
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<TreeNode> generateTrees(int begin,int end){
		List<TreeNode> res = new ArrayList<TreeNode>();
		
		if(begin>end){
			res.add(null);
			return res;
		}
		
		//每一个数都当根节点
		
		for(int i=begin;i<end;i++){
			//递归的对左右建数
			//这时可以认为所建的左右子树已经放在left,right里
			
			List<TreeNode> left = generateTrees(begin,i-1);
			List<TreeNode> right = generateTrees(i+1,end);
			
			//当前数为节点，并尝试左边的每一颗数，作为左子树
			//右边的每一颗数作为右子树
			for(int j=0;j<left.size();j++){
				for(int k=0;k<right.size();k++){
					TreeNode root =  new TreeNode(i+1);
					
					root.left = left.get(j);
					root.right = right.get(k);
					res.add(root);
				}
			}
		}
		
		return res;
	}
	/**
	 * 
	 * 标准答案2，紧接着上一题，使用动态规划
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<TreeNode> generateTrees2(int start,int end){
		
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		if(start == end){
			list.add(new TreeNode(start));
			return list;
		}
		
		for(int i=start;i<=end;i++){
			List<TreeNode> left = null;
			if(i!=start){
				left = generateTrees2(start,i-1);//先检查
			}
			List<TreeNode> right = null;
			if(i!=end){
				right = generateTrees2(i+1,end);//先检查
			}
			
			if(left==null){
				for(TreeNode node:right){
					TreeNode root = new TreeNode(i);
					root.right = node;
					list.add(root);
				}
			}else if(right==null){
				for(TreeNode node:left){
					TreeNode root = new TreeNode(i);
					root.left = node;
					list.add(root);
				}
			}else{
				for(TreeNode n1:left){
					for(TreeNode n2:right){
						TreeNode root = new TreeNode(i);
						root.left = n1;
						root.right = n2;
						list.add(root);
					}
				}
			}
			
		}
		return list;

	}
	 
	 public static void main(String[] args) {
		
	}
}



class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
