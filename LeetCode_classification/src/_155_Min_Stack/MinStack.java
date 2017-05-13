package _155_Min_Stack;

import java.util.LinkedList;

public class MinStack {
    /** initialize your data structure here. */
	LinkedList<Integer> lst;
	private int min = Integer.MAX_VALUE;
    public MinStack() {
        lst = new LinkedList<Integer>();
    }
    
    public void push(int x) {
    	
        lst.addFirst(x);
        if(min>x){
        	min = x;
        }
    }
    
    public void pop() {
    	if(lst.size()>0){
    		int temp = top();
    		lst.removeFirst();
    		if(temp == min){
    			min = Integer.MAX_VALUE;
    			 for(Integer tp:lst){
    		        	if(min>tp){
    		        		min = tp;
    		        	}
    		        }
    		}
    		
    	}
    }
    
    public int top() {
    	if(lst.size()>0){
           return lst.getFirst();
    	}else{
    		return -1;
    	}
    }
    
    public int getMin() {
    	return min;
    }
}
