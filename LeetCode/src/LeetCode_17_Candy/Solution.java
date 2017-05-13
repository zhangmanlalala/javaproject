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
    	
    	int sum = 0;//��һλ�ĸ���
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
     *1�����ȱ���һ�飬ÿ��������һ���ǹ�
     *2�������ұ����������ұ������������������������ӣ�����ұߵ�rating����ߵĸߣ����ұ߱���߶�һ���ǹ�
     *3���������������������ߵ����������������ڵ��������ӣ�������ߵ�rating���ұߵĸߣ�����߱��ұ߶�һ���ǹ���
     *4�����һ�α�������ÿ�����ӵ��ǹ�����Ӽ��ɵõ������
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
