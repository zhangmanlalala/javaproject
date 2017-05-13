package leetcode_033_search_in_rotated_sorted_array;
/**
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 * @author Administrator
 *
 */
public class Solution {
	private int result = -1;
    public int search(int[] nums, int target) {
    	if(nums == null || nums.length==0){
    		return -1;
    	}
    	banarySearch(nums,0,nums.length-1,target);
    	return result;
    	
    }
    
    public void banarySearch(int nums[],int left,int right,int target){
    	if(left>right){
    		return ;
    	}
    	
    	int mid = (right+left)/2;
    	
    	if(nums[mid]>nums[left]){//左边有序
    		if(nums[mid] == target){
    			result = mid;
    			return ;
    		}else if(nums[mid]<target){//可以舍弃左边的元素,继续在右边寻找
    			banarySearch(nums,mid+1,right,target);
    		}else{//左边和右边的都舍弃不掉,只可以舍弃中间的
    			banarySearch(nums,left,mid-1,target);
    			banarySearch(nums,mid+1,right,target);
    		}
    	}else if(nums[mid]<nums[left]){//右边有序
    		if(nums[mid] == target){
    			result = mid;
    			return ;
    		}else if(nums[mid]>target){//可以舍弃右边的元素，继续在左边寻找
    			banarySearch(nums,left,mid-1,target);
    		}else{//两边都舍弃不掉，只能舍弃中间的
    			banarySearch(nums,left,mid-1,target);
    			banarySearch(nums,mid+1,right,target);
    		}
    	}else{
    		if(nums[left] == target){
    			result = left;
    		}
    		if(nums[right] == target){
    			result = right;
    		}
    		
    		return ;
    	}
    }
}
