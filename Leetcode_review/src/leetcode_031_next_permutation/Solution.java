package leetcode_031_next_permutation;

import java.util.Arrays;

public class Solution {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length<2){
        	return ;
        }
    	int i=nums.length-2;
    	for(;i>=0;i--){
    		if(nums[i]<nums[i+1]){
    			//在已经遍历过的元素中找到大于nums[i]的
    			int j=nums.length-1;
    			for(;j>=i+1;j--){
    				if(nums[j]>nums[i]){
    					break;
    				}
    			}
    			//交换nums[i]和nums[j]
    			int temp = nums[i];
    			nums[i] = nums[j];
    			nums[j] = temp;

    			int m=nums.length-1,n = i+1;
    			while(n<m){
    				temp = nums[n];
    				nums[n] = nums[m];
    				nums[m] = temp;
    				n++;
    				m--;
    			}
    			

    			
    			break;
    		
    		}
    	}
    	
    	if(i<0){
    		Arrays.sort(nums);;
    	}
    }
    
    public static void main(String[] args) {
		new Solution().nextPermutation(new int[]{1,2,3});
	}
}
