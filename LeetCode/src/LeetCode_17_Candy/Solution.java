package LeetCode_17_Candy;

import java.util.Stack;

/**
 * 
 *here are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give?  
 * @author Administrator
 *
 */
public class Solution {
    public int candy(int[] ratings) {
    	
    	int sum = 0;//第一位的个数
    	int candy = 1;
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(ratings[0]);
       for(int i=1;i<ratings.length;i++){
    	   if(ratings[i]>=ratings[i-1]){
    		   if(stack.size()==1){
    			   sum = sum+candy;
				   candy = candy+1;
				   stack.pop();
    		   }else if(stack.size()>1){ 
    			   if(stack.size()<candy){
    				   sum = sum+candy;
    				   stack.pop();
    			   }
    			   candy = 1;
    			   while(!stack.isEmpty()){
    				   sum = sum+candy;
    				   candy = candy+1;
    				   stack.pop();
    			   }
    			   candy = 2;
    		   }
    		   stack.push(ratings[i]);
    		  
    		   
    	   }else{
    		   stack.push(ratings[i]);
    		   
    	   }
    		   
       }
       
       if(stack.size()==1){
		   sum = sum+candy;

	   }else if(stack.size()>1){ 
		   if(stack.size()<candy){
			   sum = sum+candy;
			   stack.pop();
		   }
		   candy = 1;
		   while(!stack.isEmpty()){
			   sum = sum+candy;
			   candy = candy+1;
			   stack.pop();
		   }
	   }
       
       return sum;
    }
    
    
    /**
     * 
     *1，首先遍历一遍，每个孩子先一个糖果
     *2，从左到右遍历，想让右边满足条件，即相邻两个孩子，如果右边的rating比左边的高，则右边比左边多一个糖果
     *3，从右向左遍历，再让左边的满足条件，即相邻的两个孩子，若果左边的rating比右边的高，则左边比右边多一个糖果；
     *4，最后一次遍历，将每个孩子的糖果数相加即可得到结果；
     * 
     */
    
    public int candy2(int[] ratings){
    	int n = ratings.length;
    	int[] candy = new int[n];
    	for(int i=0;i<n;i++){
    		candy[i] = 1;
    	}
    	
    	for(int i=1;i<n;i++){
    		if(ratings[i]>ratings[i-1]){
    			candy[i] = candy[i-1]+1;
    		}
    	}
    	
    	for(int i=n-2;i>=0;i--){
    		if(ratings[i]>ratings[i+1] && candy[i]<=candy[i+1]){
    			candy[i] = candy[i+1]+1;
    		}
    	}
    	
    	int num=0;
    	for(int i=0;i<n;i++){
    		num = num+candy[i];
    	}
    	
    	return num;
    }
}
