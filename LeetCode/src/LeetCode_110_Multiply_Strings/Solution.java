package LeetCode_110_Multiply_Strings;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
   Note: The numbers can be arbitrarily large and are non-negative
 * 
 * @author Administrator
 *
 */

public class Solution {
	public String multiply(String num1, String num2) {  
        //两数反向，String没有reverse()方法，所以借助于StringBuffer  
        num1 = new StringBuffer(num1).reverse().toString();  
        num2 = new StringBuffer(num2).reverse().toString();  
        //相乘结果最长是num1.length()+num2.length(), 例如，99*99  
        int[] res = new int[num1.length()+num2.length()];  
        for(int i=0; i<num1.length(); i++){  
            int a = num1.charAt(i)-'0';  
            for(int j=0; j<num2.length(); j++){  
                int b = num2.charAt(j)-'0';  
                res[i+j] = res[i+j] + a*b;//先把结果存在相应位置再考虑进位  
            }  
        }  
        StringBuffer sb = new StringBuffer();  
        for(int i=0; i<res.length; i++){  
            int digit = res[i]%10;  
            int carry = res[i]/10;  
            //从头插入  
            sb.insert(0,digit);  
            if(i < res.length-1)  
                res[i+1] = res[i+1]+carry;  
        }  
        while(sb.length()>0 && sb.charAt(0)=='0')  
            sb.deleteCharAt(0);  
        return sb.length()==0 ? "0" : sb.toString();  
    }  

}
