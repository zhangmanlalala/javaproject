package leetcode_066_plus_one;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int[] plusOne(int[] digits) {
    	
        if(digits.length == 0 || digits == null ){
        	return digits;
        }
        int len = digits.length;
        digits[len-1] = digits[len-1]+1;
        int carry = 0;
        for(int i=len-1;i>=0;i--){
        	int temp = digits[i]+carry;
        	
        	digits[i] = temp%10;
        	carry = temp/10;
        }
        
        
        if(carry == 1){
        	int arr[] = new int[len+1];
        	arr[0] = carry;
        	for(int i=1;i<=len;i++){
        		arr[i] =digits[i-1]; 
        	}
        	
        	return arr;
        }
        return digits;
    		
    	
    	
    	
    }
    
    
    public static void main(String[] args) {
    	int[] arr = new Solution().plusOne(new int[]{9,8,7,6,5,4,3,2,1,0});
    	for(int i=0;i<arr.length;i++){
    		System.out.println(arr[i]);
    	}
    	System.out.println(Integer.MAX_VALUE);
	}
}
