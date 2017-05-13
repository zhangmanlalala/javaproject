package LeetCode_16_Single_Number;

import java.util.Arrays;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. 
Could you implement it without using extra memory? 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int singleNumber(int[] nums) {
         Arrays.sort(nums);
         for(int i=1;i<nums.length;){
        	 if(nums[i] != nums[i-1]){
        		 return nums[i-1];
        	 }else{
        		 i = i+2; 
        	 }
         }
         return nums[nums.length-1];
    }
    
    /**
     * 
     * ��׼��
     * 
     * ������λ����
     * �����Single NumberҪ��һ�㣬ֻҪ����ǰ���������������λ����򣨰�λ��������Ϊ��������1��������0
     * �õ��Ľ������0���������Խ��������ε�����0��ʣ�µı�Ȼ��ֻ����һ�ε��Ǹ�����
     */
    
    public int singleNumber2(int[] A){
    	int result = 0;
    	for(int i=0;i<A.length;i++){
    		result= result^A[i];
    	}
    	return result;
    }
    
    public static void main(String[] args) {
		int[] A = new int[]{1,2,3,1,2};
		System.out.println(new Solution().singleNumber2(A));
	}
}
