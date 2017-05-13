package leetcode_041_first_missing_positives;
/**
 * 
 * 
 *  Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space. 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 
	 * �ܾ���Ľⷨ
	 * ��ÿһ��Ԫ�طŵ���ȷ��λ���ϣ�
	 * ����A[i]=a>0,��a�ŵ�A[a-1]�ϣ�
	 * @param nums
	 * @return
	 */
	public int firstMissingPositives(int[] nums){
		
		for(int i=0;i<nums.length;i++){
			while(nums[i]>0 && nums[i]<=nums.length && nums[i]!=nums[nums[i]-1])
				swap(nums,i,nums[i]-1);//����nums[i]��nums[nums[i]-1]
		}
		int i=0;
		for(;i<nums.length;i++){
			if(nums[i]!=i+1){
				return i+1;
			}
		}
		
		return i+1;
	}
	
	private void swap(int[] nums,int i,int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[i] = temp;
	}
}
