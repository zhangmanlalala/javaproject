package _164_Maximum_Gap;

import java.util.Arrays;

/**
 * 
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 
32-bit signed integer range.
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 
	 * ���⿼��Ͱ�����㷨��Ͱ����ͼ��������ʱ�临�Ӷ�ΪO(N)
	 * 1.������n��Ԫ���������У����ֵ����Сֵ�ֱ�Ϊmin��max������Ͱ����Ĺ���
	 * min��max֮�乲��������n-1��Ͱ����k��Ͱ�ķ�ΧΪ[min+(k-1)*gap��min+k*gap],gap=ceiling[(max-min)/(n-1)]
	 * �������Ҫ�������gapһ�������ƽ��gapҪ��
	 * 2.���ֺ�Ͱ������ִ��Ͱ����(������Ͱ���У���i��Ͱ�е�ֵֻ�������ĺ���С��)
	 * 3.��λ���(��֤Ԫ������)
	 * 
	 * @param nums
	 * @return
	 */
	
	//���Ǿ���
    public int maximumGap(int[] nums) {
    	if(nums == null || nums.length<2){
    		return 0;
    	}
    	//ȡ�������е����ֵ����Сֵ
    	int min = nums[0];
    	int max = nums[0];
    	//�����һ�˱Ƚϻ���һ��N
    	for(int i:nums){
    		min = Math.min(min, i);
    		max = Math.max(max, i);
    	}
    	//��(n-1)��Ͱ�У�ÿ��Ͱ֮��ľ���
    	int gap = (int) Math.ceil((double)(max-min)/(nums.length-1));
    	int[] bucketMIN = new int[nums.length-1];
    	int[] bucketMAX = new int[nums.length-1];
    	Arrays.fill(bucketMIN, Integer.MAX_VALUE);
    	Arrays.fill(bucketMAX, Integer.MIN_VALUE);
    	
    	//ִ��Ͱ����
    	for(int i:nums){
    		if(i==min || i==max)
    			continue;
    		int idx = (i-min)/gap;//����Ԫ��i���Ǹ�Ͱ��
    		bucketMIN[idx] = Math.min(i, bucketMIN[idx]);
    		bucketMAX[idx] = Math.max(i, bucketMAX[idx]);
    	}
    	
    	int maxGap =Integer.MIN_VALUE;
    	int previous = min;
    	for(int i=0;i<nums.length-1;i++){
    		if(bucketMIN[i] == Integer.MAX_VALUE && bucketMAX[i] == Integer.MIN_VALUE)
    			//ͰΪ��
    			continue;
    		//�൱��ʹ����СͰ�е�i+1��Ͱ�е�ֵ-���Ͱ�е�i��Ͱ�е�ֵ�������ܱ�֤������Ԫ������
    		maxGap = Math.max(maxGap, bucketMIN[i]-previous);//�ܽ᣺��λ���
    		
    		//����previous��ֵ
    		previous = bucketMAX[i];
    	}
    	
    	maxGap = Math.max(maxGap, max-previous);
    	
    	return maxGap;
    }
}
