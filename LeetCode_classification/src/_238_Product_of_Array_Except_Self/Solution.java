package _238_Product_of_Array_Except_Self;

import java.util.Arrays;

/**
 *  Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

	Solve it without division and in O(n).

	For example, given [1,2,3,4], return [24,12,8,6]. 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int[] productExceptSelf(int[] nums) {
    	if(nums == null || nums.length<1){
    		return new int[]{};
    	}
        int temp =1;
        int num0 = 0;
        int idx = 0;//´æ´¢0µÄÎ»ÖÃ
        for(int i=0;i<nums.length;i++){
        	if(nums[i] == 0){
        		num0++;
        		idx = i;
        	}else{
        		temp *=nums[i];
        	}
        }
        int[] output = new int[nums.length];
        if(num0 >=2){
        	Arrays.fill(output, 0);
        }else if(num0 == 1){
        	Arrays.fill(output, 0);
        	output[idx] = temp;
        }else{
        	for(int i=0;i<nums.length;i++){
        		output[i] = temp/nums[i];
        	}
        }
        
        return output;
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().productExceptSelf(new int[]{1,0}));
	}
}
