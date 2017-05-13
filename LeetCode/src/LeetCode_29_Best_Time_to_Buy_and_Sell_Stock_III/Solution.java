package LeetCode_29_Best_Time_to_Buy_and_Sell_Stock_III;

import java.util.Arrays;

/**
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the 
stock before you buy again).
 * @author Administrator
 *
 */
public class Solution {
	 public int maxProfit(int[] prices) {
		 if(prices == null || prices.length<=1){
			 return 0;
		 }
		 int sum = 0;
		 int min_num = prices[0];
		 for(int i=1;i<prices.length;i++){
			 int firstTrans = prices[i] - min_num;
			 if(prices[i] < min_num){
				 min_num = prices[i] ;
			 }
			 if(firstTrans<0){
				 continue;
			 }
			 int secondTrans = maxProfit1(prices,i+1,prices.length-1);
			 if(firstTrans+secondTrans>sum){
				 sum = firstTrans+secondTrans;
			 }
		 }
		 
		 return sum;
		
	 }
	 public int maxProfit2(int[] prices) {
		 if(prices == null || prices.length<=1){
			 return 0;
		 }
		 /**
		  * 先找第一次交易
		  */
		 int sum = 0;
		 int i=1;
		 for(;i<prices.length;i++){
			 int firstTrans = prices[i] - prices[i-1];
			 if(firstTrans<= 0){
				 if(sum==0){
					 continue;
				 }else{
					 break;
				 }
			 }else{
				 sum = sum+firstTrans;
			 }
		 }
		 
		 int secondTrans = maxProfit1(prices,i,prices.length-1);
		 
		 return sum+secondTrans;
		 
	 }
	 
	 public int maxProfit1(int[] prices,int start,int end) {
	    	if(start>=end){
	    		return 0;
	    	}
	        int[] p =new int[end-start+1];
	        
	        p[0] = 0;
	        int min_num = prices[start];
	        for(int i=1;i<p.length;i++){
	        	
	        	
	        	int temp = prices[i+start]-min_num;
	        	p[i] = Integer.max(p[i-1], temp);
	        			
	        	if(prices[i+start]<min_num){
	        		min_num = prices[i+start];
	        	}
	        	
	        }
	        
	        return p[p.length-1];
	    }
}
