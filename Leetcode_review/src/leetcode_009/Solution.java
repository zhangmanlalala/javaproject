package leetcode_009;
/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean isPalindrome(int x) {
        
 
    	if(x<0){
    		return false;//负数不可能是回文的
    	}
       	int ori = x;
    	
    	int res = 0;
    	int newNum = 0;
    	while(x>0){
    		
    		res = x % 10;
    		x = x/10;
    		
    		newNum = newNum*10+res;
    	}
    	
    	if(newNum == ori){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    public static void main(String[] args) {
    	System.out.println(Integer.MIN_VALUE);
		System.out.println(new Solution().isPalindrome(-2147447412));
	}
}
