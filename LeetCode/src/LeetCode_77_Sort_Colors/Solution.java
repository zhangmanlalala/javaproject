package LeetCode_77_Sort_Colors;


/**
 * 
 *  Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively. 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * ��׼�ⷨ
	 * @param nums
	 */
	
	/**
	 * 
	 *ά������i��j��kָ�룬ָ��0��1��2������λ�ã���ô������֪����

	     ����0�������iλ�ú���1λ�����1��2������Ҫ�������1λ��

	    ����1������jλ�ú���1λ�����2��һ��Ҫ�������1λ��

	   ����2����ֻ��k����1λ�� 
	 * 
	 * @param nums
	 */
	
	/**
	 * 
	 * ���䣬��������
	 * @param nums
	 */
	public void sortColors(int[] nums) {
        if(nums==null || nums.length == 0) return;
        
        int i=-1,j=-1,k=-1;
        for(int p=0;p<nums.length;p++){
        	if(nums[p] == 0){  //�⼸�任�ܾ���
        		nums[++k] = 2;
        		nums[++j] = 1;
        		nums[++i] = 0;
        	}else if(nums[p] == 1){
        		nums[++k] = 2;
        		nums[++j] = 1;
        	}else if(nums[p] == 2){
        		nums[++k] = 2;
        	}
        }
    }
}
