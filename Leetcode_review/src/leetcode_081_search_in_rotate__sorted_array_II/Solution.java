package leetcode_081_search_in_rotate__sorted_array_II;

/**
 * 
 * 
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?
Would this affect the run-time complexity? How and why?
Suppose an array sorted in ascending order is rotated at some 
pivot unknown to you beforehand.	
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).	
Write a function to determine if a given target is in the array.	
The array may contain duplicates.
 * 
 * @author Administrator
 *
 */
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public boolean search(int[] nums, int target) {
    	
    	if(nums == null || nums.length==0){
    		return false;
    	}
    	
    	return binarySearch(nums,0,nums.length-1,target);
    }
    
    
    public boolean binarySearch(int[] nums,int start,int end,int target){
    	
    	if(start<=end){
    		int mid = (start+end)/2;
    		
    		if(target == nums[mid]){
    			return true; 
    		}else if(target<nums[mid] && nums[mid] <nums[end]){//右边有序并且右边都比target大，舍弃右边
    			return binarySearch(nums,start,mid-1,target);
    		}else if(target>nums[mid] && nums[mid]>nums[start]){//左边有序，并且左边都比target小，舍弃左边
    			return binarySearch(nums,mid+1,end,target);
    		}else{
    			return binarySearch(nums,mid+1,end,target) || binarySearch(nums,start,mid-1,target);//只能舍弃中间一个
    		}
    	}else{
    		return false;
    	}
    }

}
