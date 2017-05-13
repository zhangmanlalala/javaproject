package _215_Kth_Largest_Element_in_an_Array;

import java.util.Arrays;

/**
 * 
 * 
 * Find the kth largest element in an unsorted array. Note that it is the 
 * kth largest element in the sorted order, not the kth distinct element.
	For example,
	Given [3,2,1,5,6,4] and k = 2, return 5.
	
	Note:
	You may assume k is always valid, 1 ≤ k ≤ array's length.
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 投机取巧的做法
	 * @param nums
	 * @param k
	 * @return
	 */
    public int findKthLargest2(int[] nums, int k) {
    	if(nums == null ||  nums.length==0){
    		return 0;
    	}
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    
    /**
     * 
     * 不可取
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
    	if(nums == null ||  nums.length==0){
    		return 0;
    	}
    	boolean result[] = new boolean[nums.length];
    	
    	int temp =  Integer.MIN_VALUE;
    	int idx = 0;
    	for(int i=1;i<=k;i++){
    		temp = Integer.MIN_VALUE;
    		for(int j=0;j<nums.length;j++){
    			if(temp<nums[j] && !result[j]){
    				temp = nums[j];
    				idx = j;
    			}
    		}
    		
    		result[idx] = true;
    	}
    	
    	return temp;
    }
    
    /**
     * 看标准解法:快速查找
     * 
     */
    
    public int findKthLargest(int[] nums, int k) {
    	if(nums == null || nums.length == 0) return Integer.MAX_VALUE;
    	return findKthLargest(nums,0,nums.length-1,nums.length-k);
    	
    }
    
    //快速查找的思想，记住这种用法
    public int findKthLargest(int[] nums,int start,int end,int k){
    	if(start>end) return Integer.MAX_VALUE;
    	
    	int pivot = nums[end];// take A[end] as the pivot
    	int left = start-1;//快速排序的思想，找到一个关键位置使得左边的元素都小于它，右边的元素都大于它
    	for(int i=start;i<end;i++){
    		if(nums[i] <= pivot){
    			left++;
    			swap(nums,left,i);
    		}
    	}
    	
    	left++;
    	swap(nums,left,end);
    	
    	if(left == k)
    		return nums[left];
    	else if(left<k)
    		return findKthLargest(nums,left+1,end,k);
    	else
    		return findKthLargest(nums,start,left-1,k);
    }
    
    
    private void swap(int[] A,int i,int j){
    	int temp = A[i];
    	A[i] = A[j];
    	A[j] = temp;
    }
    
}
