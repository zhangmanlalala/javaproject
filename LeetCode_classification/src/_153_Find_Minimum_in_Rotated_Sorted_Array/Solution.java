package _153_Find_Minimum_in_Rotated_Sorted_Array;
/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.
 * 
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
    	int min = nums[0];
    	while(start<end){
    		if(start == end-1){
    			min =  Math.min(min, Math.min(nums[start],nums[end]));
    			break;
    		}
    		int cen = (start+end)/2;
    		if(nums[start]<nums[cen]){//��������������,��Сֵһ�����ұ�
    			start = cen;
    		}else{//�ұ���������cen+1����ģ���Сֵһ�������
    			end = cen;
    		}
    		
    	}
    	return min;
    		
    }
    
    
  
}
