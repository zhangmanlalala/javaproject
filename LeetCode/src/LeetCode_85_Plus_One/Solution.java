package LeetCode_85_Plus_One;

/**
 * 
 * Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
 * @author Administrator
 *
 */

/**
 * 
 * 需要注意的是，当最后一次进位位不为0时，要新开辟数组
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
}
