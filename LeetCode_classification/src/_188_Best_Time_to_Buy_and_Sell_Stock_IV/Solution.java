package _188_Best_Time_to_Buy_and_Sell_Stock_IV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 *Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again). 
 * @author Administrator
 *
 */
public class Solution {
	
    public int maxProfit(int k, int[] prices) {
        List<Integer> lst = new ArrayList<Integer>();
        if(prices==null || prices.length <=1){
        	return 0;
        }
        int first = prices[0];
        int second = 0;
        for(int i=1;i<prices.length;i++){
        	if(prices[i]>prices[i-1]){
        		second = prices[i];
        	}else{
        		if(second>first){
        			lst.add(second-first);
        		}
        		first = prices[i];
        		second = prices[i];
        	}
        }
        if(second>first){
			lst.add(second-first);
		}
        
        Object[] a = lst.toArray();
        Arrays.sort(a);
        if(a==null){
        	return 0;
        }
        int[] profit = new int[k+1];
        profit[0] = 0;
        for(int i=1;i<=k;i++){
        	if(a.length-i>=0){
        		profit[i] = profit[i-1]+(Integer)a[a.length-i];
        	}else{
        		profit[i] = profit[i-1];
        	}
        }
        
        return profit[k];
    }
    
    public static void main(String[] args) {
		int[] prices = new int[]{2,4,6,3,5};
		System.out.println(new Solution().maxProfit(2, prices));
	}
    /**
     * 
     * 如使用动态规划，我找到的子问题是最多执行i次交易获得的最大利润为profit[i]
     * 
     */
    
    /**
     * 
     * 标准解法，使用动态规划
     */
    
    public int maxProfit2(int k,int[] prices){
    	int len = prices.length;
    	if(k>=len/2) 
    		return quickSolve(prices);
    	
    	int[][] t = new int[k+1][len];
    	for(int i=1;i<=k;i++){
    		int tmpMax = -prices[0];
    		for(int j=1;j<len;j++){
    			t[i][j] = Math.max(t[i][j-1], prices[j]+tmpMax);
    			tmpMax = Math.max(tmpMax, t[i-1][j-1]-prices[j]);
    		}
    	}
    	
    	return t[k][len-1];
    	
    }
    
    private int quickSolve(int[] prices){
    	int len = prices.length,profit = 0;
    	for(int i=1;i<len;i++){
    		if(prices[i]>prices[i-1]) 
    			profit += prices[i]-prices[i-1];
    	}
    	
    	return profit;
    }
}
