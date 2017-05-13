package _154_Find_Minimum_in_Rotated_Sorted_Array_II;
/**
 *

Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates. 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int findMin(int[] nums) {
        if(nums==null){
        	return 0;
        }
    	int start = 0;
    	int end = nums.length-1;
    	int min = nums[0]; //为了防止没有旋转的情况
    	while(start<end){
    		if(start == end-1){
    			min =  Math.min(min, Math.min(nums[start],nums[end]));
    			break;
    		}
    		int cen = (start+end)/2;
    		if(nums[start]<nums[cen]){//左边有序，舍弃左边,最小值一定在右边
    			start = cen;
    		}else if(nums[start]==nums[cen]){
    			start = start+1;//舍弃第一个元素，第一个元素又可能是最大值，所以比较一下，一面漏掉情况
    			if(min>nums[start]){
    				min = nums[start];
    			}
    		}else{//右边有序，舍弃cen+1后面的，最小值一定在左边
    			end = cen;
    		}
    		
    	}
    	return min;
    		
    }
    
 
}
