package _198_House_Robber;
/**
 * You are a professional robber planning to rob houses along a street. Each house 
 * has a certain amount of money stashed, the only constraint stopping you from robbing 
 * each of them is that adjacent houses have security system connected and it will automatically 
 * contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine 
the maximum amount of money you can rob tonight without alerting the police.
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * �������С���⣬��Ȼ��ʹ�ö�̬�滮
	 * �����⣺����Ϊi�����飬���Ի�ȡ������ֵΪresult[i]
	 * ���״̬ת�Ʒ���
	 * @param nums
	 * @return
	 */
    public int rob(int[] nums) {
        if(nums==null || nums.length==0){
        	return 0;
        }
        if(nums.length==1){
        	return nums[0];
        }
    	int result[] = new int[nums.length+1];
    	result[0] = 0;
    	result[1] = nums[0];
    	//��һ��������¼�������һ��Ԫ����û�б�͵��
    	for(int i=2;i<=nums.length;i++){//����Ҫ������״̬ת�Ʒ���
    		result[i] = Math.max(nums[i-1]+result[i-2], result[i-1]);
    	}
    	
    	return result[nums.length];
    }
    
    public static void main(String[] args) {
    	int nums[] = new int[]{2,3,6,9,10};
		System.out.println(new Solution().rob(nums));
	}
}
