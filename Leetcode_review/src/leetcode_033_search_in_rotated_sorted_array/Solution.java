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
    	
    	if(nums[mid]>nums[left]){//�������
    		if(nums[mid] == target){
    			result = mid;
    			return ;
    		}else if(nums[mid]<target){//����������ߵ�Ԫ��,�������ұ�Ѱ��
    			banarySearch(nums,mid+1,right,target);
    		}else{//��ߺ��ұߵĶ���������,ֻ���������м��
    			banarySearch(nums,left,mid-1,target);
    			banarySearch(nums,mid+1,right,target);
    		}
    	}else if(nums[mid]<nums[left]){//�ұ�����
    		if(nums[mid] == target){
    			result = mid;
    			return ;
    		}else if(nums[mid]>target){//���������ұߵ�Ԫ�أ����������Ѱ��
    			banarySearch(nums,left,mid-1,target);
    		}else{//���߶�����������ֻ�������м��
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
