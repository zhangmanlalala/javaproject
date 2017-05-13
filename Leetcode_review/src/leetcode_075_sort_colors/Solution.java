package leetcode_075_sort_colors;
/**
 * 
 * Given an array with n objects colored red, white or blue, 
 * sort them so that objects of the same color are adjacent, 
 * with the colors in the order red, white and blue. 
Here, we will use the integers 0, 1, and 2 to represent the
 color red, white, and blue respectively. 
思路，先把2移到数组最后，再把1移到数组最后

 * @author Administrator
 *
 */
public class Solution {
	   public void sortColors(int[] nums) {
	    	if(nums == null || nums.length==0){
	    		return ;
	    	}
	        int i=0,j=nums.length-1;
	        while(i<j){
	        	while(i<j && nums[i]!=2){
	        		i++;
	        	}
	        	
	        	while(j>=0 && nums[j] == 2){
	        		j--;
	        	}
	        	
	        	if(i<j){//交换，将2都移到最后
	        		int temp = nums[i];
	        		nums[i] = nums[j];
	        		nums[j] = temp;
	        	}
	        	
	        }
	        
	        i = 0;
	        while(i<j){
	        	while(i<j && nums[i]!=1){
	        		i++;
	        	}
	        	
	        	while(j>=0 && nums[j] == 1){
	        		j--;
	        	}
	        	
	        	if(i<j){
	        		int temp = nums[i];
	        		nums[i] = nums[j];
	        		nums[j] = temp;
	        	}
	        }
	    }
}
