package leetcode_035_search_insert_position;
/**
 * Given a sorted array and a target value, return the index if 
 * the target is found. If not, return the index where it would be if it were
 *  inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 ¡ú 2
[1,3,5,6], 2 ¡ú 1
[1,3,5,6], 7 ¡ú 4
[1,3,5,6], 0 ¡ú 0 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0){
        	return 0;
        }
        
        int result = binarySearch(nums,0,nums.length-1,target);
        if(result!=-1){
        	return result;
        }else{
        	for(int i=0;i<nums.length;i++){
        		if(target<nums[i]){
        			return i;
        		}
        	}
        	
        	return nums.length;
        }
    }
    
    private int binarySearch(int[] nums,int left,int right,int target){
    	
    	if(left>right){
    		return -1;
    	}
    	
    	int mid = (left+right)/2;
    	
    	if(nums[mid] == target){
    		return mid;
    	}else if(nums[mid]>target){
    		return binarySearch(nums,left,mid-1,target);
    	}else{
    		return binarySearch(nums,mid+1,right,target);
    	}
    }
}
