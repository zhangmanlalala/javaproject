package _239_Sliding_Window_Maximum;
/**
 * Given an array nums, there is a sliding window of size k which is moving 
 * from the very left of the array to the very right. You can only see the k 
 * numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Therefore, return the max sliding window as [3,3,5,5,6,7].

Note:
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
    	if(nums == null || nums.length<=1 || k==1){
        	return nums;
        }
        int max1 = Integer.MIN_VALUE;//存放每一window中的最大值
        int max2 = 0;//存放每一window中的次大值，现在要维护这两个值
        
        int[] result = new int[nums.length-k+1];
        
        int idx = 0;
        for(int i=0;i<k;i++){//现在第一个window中找出max1和max2后面只需要维护着两个值
        	if(nums[i]>max1){
        		max1 = nums[i];
        		idx = i;
        	}       	
        }
        
        for(int j=0;j<k;j++){
        	if(j!=idx){
	        	if(nums[j]>max2 ){
	        		max2 = nums[j];
	        	}
        	}
        }
        
        result[0] = max1;
        for(int i=k;i<nums.length;i++){
        	if(nums[i]>=max1){
        		result[i-k+1] = nums[i];
        		max2 = max1;
        		max1 = nums[i];
        		
        	}else{
        		if(nums[i-k] == max1){//如果从窗口中移除的nums[i-k]是最大值，这从窗口中新增的值要和次大值比较
        			if(nums[i]>=max2){
        				result[i-k+1] = nums[i];
        				max1 = nums[i];
        			}else{
        				result[i-k+1] = max2;
        				max1 = max2;//次大值变为最大值
        				//现在更新次大
        				max2 = Integer.MIN_VALUE;
        				int num = 0;
        				for(int j=i-k+1;j<=i;j++){
        					if(nums[j] == max1 && num ==0){
        						num++;
        						continue;
        					}
        					
        					if(nums[j]>max2){
        						max2 = nums[j];
        					}
        				}
        			}
        		}else{
        			
        			result[i-k+1] = max1;
        			//现在更新次大
    				max2 = Integer.MIN_VALUE;
    				int num = 0;
    				for(int j=i-k+1;j<=i;j++){
    					if(nums[j] == max1 && num ==0){
    						num++;
    						continue;
    					}
    					
    					if(nums[j]>max2){
    						max2 = nums[j];
    					}
    				}
        		}
        	}
        }
        
        return result;
    }
}
