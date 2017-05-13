package leetcode_001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that 
 * they add up to a specific target.

You may assume that each input would have exactly one solution, and 
you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

 * @author Administrator
 *
 */
public class Two_sum_1 {
	
	/**
	 * 
	 * 使用hashMap,key代表数组中的整数，value代表整数出现的次数
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<nums.length;i++){
				map.put(nums[i], i);
		}
		
		for(int i=0;i<nums.length;i++){
			int remain = target-nums[i];

			if(map.containsKey(remain) && map.get(remain)!=i){

				return new int[]{i,map.get(remain)};
				
			}
		}
		return new int[]{};
	}

	public int[] twoSum_mod(int[] nums, int target) {
		Arrays.sort(nums);
		int i=0,j=nums.length-1;
		while(i<j){
			if(nums[i]+nums[j] == target){
				
				return new int[]{i,j};
			}else if(nums[i]+nums[j] > target){
				j--;
			}else{
				i++;
			}
		}
		
		return new int[]{};
	}
	
	public static void main(String[] args) {
		System.out.println(new Two_sum_1().twoSum_mod(new int[]{3, 2,4},6));

	}
	
	

}
