package LeetCode_74_Subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *Given a set of distinct integers, S, return all possible subsets. 
 *Note:

Elements in a subset must be in non-descending order.

The solution set must not contain duplicate subsets.

For example,

If S = [1,2,3], a solution is: 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 找出所有可能，肯定用回溯，dfs(循环加递归)，注意保护现场
	 * @param nums
	 * @return
	 */
	 public List<List<Integer>> subsets(int[] nums) {
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 List<Integer> list = new ArrayList<Integer>();
		 if(nums.length == 0){
			 return null;
		 }
		 for(int j=1;j<=nums.length;j++){
			 sub(nums,0,j,result,list);
			 list.clear();
		 }
		 result.add(new ArrayList<Integer>());//加入空元素
		 return  result;
	 }
	 
	 public void sub(int[] nums,int start,int k,List<List<Integer>> result,List<Integer> list){
		 
		 if(list.size() == k){
			 result.add(new ArrayList<Integer>(list));
			 return ;
		 }
		 
		 for(int i=start;i<nums.length;i++){
			 //添加元素
			 list.add(nums[i]);
			 //向后搜索
			 sub(nums,i+1,k,result,list);
			 //保护现场
			 list.remove(list.size()-1);
		 }
	 }
	 
	 
	 
	 
	 public static void main(String[] args) {
		System.out.println(new Solution().subsets2(new int[]{1,2,3}));
	}
	 
	 /**
	  * 
	  * 
	  * 方法二，回溯 dfs(循环加递归) 注意保护现场
	  * @param nums
	  * @return
	  */
	 public List<List<Integer>> subsets2(int[] nums) {
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 List<Integer> list = new ArrayList<Integer>();
		 if(nums.length == 0){
			 return null;
		 }
		
		 sub2(nums,0,result,list);
		
		 result.add(new ArrayList<Integer>());//加入空元素
		 return  result;
	 }
	 
	 public void sub2(int[] nums,int start,List<List<Integer>> result,List<Integer> list){
		 
		 if(list.size() >= nums.length){
			 return ;
		 }
		 
		 for(int i=start;i<nums.length;i++){
			 //添加元素
			 list.add(nums[i]);
			 result.add(new ArrayList<Integer>(list));
			 //向后搜索
			 sub2(nums,i+1,result,list);
			 //保护现场
			 list.remove(list.size()-1);
		 }
	 }

}
