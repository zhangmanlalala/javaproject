package leetcode_007;
/**
 * 
 *Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Note:
The input is assumed to be a 32-bit signed integer. Your function should
 return 0 when the reversed integer overflows.  
 * @author Administrator
 *
 */
public class Solution {
    public int reverse(int x) {
        
    	int res = 0;
    	long newNum = 0;
    	int sign = 0;
    	if(x<0){
    		sign = -1;
    		x = -x;
    	}else{
    		sign = 1;
    	}
    	while(x>0){   		
    		res = x%10;
    		
    		x = x/10;
    		
    		newNum =newNum*10+res;   		   		
    	}
    	if(sign<0){
    		if(newNum>Integer.MAX_VALUE-1){
    			return 0;
    		}else{
    			return (int)-newNum;
    		}
    	}else{
    		if(newNum>Integer.MAX_VALUE){
    			return 0;
    		}else{
    			return (int)newNum;
    		}
    	}
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().reverse(-123));
	}
}
