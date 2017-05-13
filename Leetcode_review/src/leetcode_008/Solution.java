package leetcode_008;
/**
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public int myAtoi(String str) {
		int idx = 0,sign = 1,total = 0;
		//1.Empty string
		if(str.length() == 0){
			return 0;
		}
		
		//.remove Spaces
		while(str.charAt(idx) == ' ' && idx<str.length() ){
			idx++;
		}
		
		//3.handel signs
		if(str.charAt(idx) == '+' || str.charAt(idx)=='-'){
			sign = str.charAt(idx)=='+' ? 1:-1;
			idx++;
		}
		
		//4.Convert number and avoid overflow
		while(idx<str.length()){
			int digit = str.charAt(idx)-'0';
			if(digit < 0 || digit>9) break;
			
			//check if total will be overflow after 10 times and add digit
			//考虑越界的情况，方法是用1.Integer.MAX_VALUE/10<total
			//				  2.Integer.MAX_VALUE/10==total || Integer.MAX_VAUE%10<digit
			if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE%10<digit){
				return sign == 1?Integer.MAX_VALUE:Integer.MIN_VALUE;
			}
			
			total = 10 * total + digit;
			idx++;
		}
		
		return total*sign;
	}
}
