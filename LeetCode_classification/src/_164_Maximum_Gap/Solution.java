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
	 * 该题考查桶排序算法：桶排序和计数排序的时间复杂度为O(N)
	 * 1.假设有n个元素在数组中，最大值和最小值分别为min和max，按照桶排序的规则，
	 * min和max之间共可以设置n-1个桶，第k个桶的范围为[min+(k-1)*gap，min+k*gap],gap=ceiling[(max-min)/(n-1)]
	 * 并且最后要求的最大的gap一定比这个平均gap要大
	 * 2.划分好桶后我们执行桶排序，(用两个桶序列，第i个桶中的值只保存最大的和最小的)
	 * 3.错位相减(保证元素相邻)
	 * 
	 * @param nums
	 * @return
	 */
	
	//很是经典
    public int maximumGap(int[] nums) {
    	if(nums == null || nums.length<2){
    		return 0;
    	}
    	//取得数组中的最大值和最小值
    	int min = nums[0];
    	int max = nums[0];
    	//这个是一趟比较花费一个N
    	for(int i:nums){
    		min = Math.min(min, i);
    		max = Math.max(max, i);
    	}
    	//求(n-1)个桶中，每个桶之间的距离
    	int gap = (int) Math.ceil((double)(max-min)/(nums.length-1));
    	int[] bucketMIN = new int[nums.length-1];
    	int[] bucketMAX = new int[nums.length-1];
    	Arrays.fill(bucketMIN, Integer.MAX_VALUE);
    	Arrays.fill(bucketMAX, Integer.MIN_VALUE);
    	
    	//执行桶排序
    	for(int i:nums){
    		if(i==min || i==max)
    			continue;
    		int idx = (i-min)/gap;//计算元素i在那个桶中
    		bucketMIN[idx] = Math.min(i, bucketMIN[idx]);
    		bucketMAX[idx] = Math.max(i, bucketMAX[idx]);
    	}
    	
    	int maxGap =Integer.MIN_VALUE;
    	int previous = min;
    	for(int i=0;i<nums.length-1;i++){
    		if(bucketMIN[i] == Integer.MAX_VALUE && bucketMAX[i] == Integer.MIN_VALUE)
    			//桶为空
    			continue;
    		//相当于使用最小桶中第i+1个桶中的值-最大桶中第i个桶中的值，这样能保证这两个元素相邻
    		maxGap = Math.max(maxGap, bucketMIN[i]-previous);//总结：错位相减
    		
    		//更像previous的值
    		previous = bucketMAX[i];
    	}
    	
    	maxGap = Math.max(maxGap, max-previous);
    	
    	return maxGap;
    }
}
