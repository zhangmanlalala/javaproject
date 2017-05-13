package _202_Happy_Number;

import java.util.HashSet;
import java.util.Set;

/**
 *Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in 
a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.


Example: 19 is a happy number

    12 + 92 = 82
    82 + 22 = 68
    62 + 82 = 100
    12 + 02 + 02 = 1
 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean isHappy(int n) {
    	if(n<=0){
    		return false;
    	}
        Set<Integer> set = new HashSet<Integer>();
        int temp = 0;
        set.add(n);
    	while(n!=1){
    		temp = 0;
    		while(n!=0){
    			temp += Math.pow((n%10), 2);
    			n = n/10;
    		}
    		n = temp;
    		if(!set.contains(n)){   			
    			set.add(n);
    		}else{
    			break;
    		}
    	}
    	if(n==1){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().isHappy(19));
	}
}
