package LeetCode_31_Best_Time_to_Buy_and_Sell_Stock;
/**
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one 
share of the stock), design an algorithm to find the maximum profit.
Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5
max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)

Example 2:

Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.

 * 
 * @author Administrator
 *
 */

/**
 * 
 * 这实际是一个最大子数组问题
 * @author Administrator
 *
 */
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length<=1){
        	return 0;
        }
        int[] p = new int[prices.length-1];
        for(int i=0;i<p.length;i++){
        	p[i] = prices[i+1]-prices[i];
        }
        
        int result = max_subArray(p,0,p.length-1);
        if(result<0){
        	return 0;
        }else{
        	return result;
        }
    }
    
    public int max_subArray(int[] p,int start,int end){
    	
    	if(start<end){
    		
    		int mid = (start+end)/2;
    		
    		int left = max_subArray(p,start,mid);
    		int right = max_subArray(p,mid+1,end);
    		int mid_sum = midSum(p,start,mid,end);
    		if(left>right && left>mid_sum) return left;
    		if(right>left && right>mid_sum) return right;
    		if(mid_sum>left && mid_sum>right) return mid_sum;
    	}
    	
    	return p[start];
    	
    }
    
    public int midSum(int[] p,int start,int mid,int end){
    	int left_sum = Integer.MIN_VALUE;
    	int sum=0;
    	for(int i=mid;i>=start;i--){
    		sum = sum+p[i];
    		if(left_sum<sum){
    			left_sum = sum;
    		}
    	}
    	int right_sum = Integer.MIN_VALUE;
    	sum = 0;
    	for(int i=mid+1;i<=end;i++){
    		sum = sum+p[i];
    		if(right_sum<sum){
    			right_sum = sum;
    		}
    	}
    	
    	
    	return left_sum+right_sum;
    }
    
    /**
     * 
     * 看到最优化，想到动态规划
     * 第一步，找子问题，max[i]是[0,i]的最大利润，可知max[0] = 0;
     * 状态转移方程：max[i] = max{max[i-1],prices[i]-min},min是维持的最小股价
     */
    
    public int maxProfit1(int[] prices) {
    	if(prices.length == 0 || prices == null){
    		return 0;
    	}
        int[] p =new int[prices.length];
        
        p[0] = 0;
        int min_num = prices[0];
        for(int i=1;i<prices.length;i++){
        	
        	
        	int temp = prices[i]-min_num;
        	p[i] = Integer.max(p[i-1], temp);
        			
        	if(prices[i]<min_num){
        		min_num = prices[i];
        	}
        	
        }
        
        return p[p.length-1];
    }
}
