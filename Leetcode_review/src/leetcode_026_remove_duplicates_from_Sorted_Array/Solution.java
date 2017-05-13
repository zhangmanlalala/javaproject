package leetcode_026_remove_duplicates_from_Sorted_Array;
/**
 * Given a sorted array, remove the duplicates in place such that 
 * each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place
 with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums 
being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

Subscribe to see which companies asked this question.
 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
    	if(nums == null || nums.length==0){
    		return 0;
    	}
        if(nums.length == 1){
        	return 1;
        }
        int first = 0,a = 1;
        
        int i=1;
        
        while(i <= nums.length-1){

        	if(nums[i] == nums[first]){
        		i++;

        	}else{
        		int len = i-first;
        		if(len>1){
        			nums[first+1] = nums[i];
        			first++;        			    			
        			i++;
        			a++;
        		}else{
        			
        			first = i;
        			i++;
        			a++;
        		}        		       		      		
        	}

        }
        
        
        return a;
    }
    
    public static void main(String[] args) {
    	int[] arr = new int[]{1,2,2,3,4,4};
    	int k = new Solution().removeDuplicates(arr);
		System.out.println(k);
		for(int i=0;i<k;i++){
			System.out.println(arr[i]);
		}
	}
}
