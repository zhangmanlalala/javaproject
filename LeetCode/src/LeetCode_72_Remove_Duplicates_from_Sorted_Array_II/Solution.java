package LeetCode_72_Remove_Duplicates_from_Sorted_Array_II;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Follow up for "Remove Duplicates":
	What if duplicates are allowed at most twice?

	For example,
	Given sorted array nums = [1,1,1,2,2,3],

	Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.

	Subscribe to see which companies asked this question
 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 我自己的尝试解法
	 * 就是维护一个计数器，来记录重复的次数，这个之前在matlab中也做过
	 * @param nums
	 * @return
	 */
	 public int removeDuplicates(int[] nums) {
		 if(nums.length == 0 || nums==null){
			 return 0;
		 }
//	     List<Integer> list = new ArrayList<Integer>();  
	   
		 int temp=nums[0];
		 int dup_num = 1;
		 int remain_bum = 1;
		 for(int i=1;i<nums.length;i++){
			 if(nums[i] == temp){
				 dup_num++;
				 if(dup_num < 3){
//					 list.add(nums[i]);
					 nums[remain_bum] = nums[i];
					 remain_bum++;
				 }
			 }else{
				 dup_num = 1;
				 temp = nums[i];
//				 list.add(nums[i]);
				 nums[remain_bum] = nums[i];
				 remain_bum++;
			 }
		 }
		 
		 return remain_bum;
	 }
	 public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3};
		System.out.println(new Solution().removeDuplicates(nums));
	}
}
