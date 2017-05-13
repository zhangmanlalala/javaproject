package LeetCode_62_Decode_Ways;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

[java] view plain copy

    'A' -> 1  
    'B' -> 2  
    ...  
    'Z' -> 26  

Given an encoded message containing digits, determine the total number of ways to decode it.

For example,

Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 动态规划，自底向上，使用两层for循环，将计算结果保存在多维数组中
	 * @param s
	 * @return
	 */
	public int numDecodings(String s) {
        /**
         * 动态规划除了在最优化问题中用到之外，在这种问题也会用到，这种问题的特征是：
         * 1.字符串
         * 2.求方法的总数
         * 
         * 就此题而言，就类似于上楼梯方式的问题（上楼梯问题是最经典的动态规划问题）
         * 设num[i]是从0开始长度为i的字串 的解耦数，子问题有两种情况，即往前推一位和2位两种方式，直观上就是
         * num[i] = num[i-1] + num[i-2]
         * 这个问题，还要判断合法性问题，即，如果是1位的话，不能是0，如果是两位的话，前面一位不能是0同时两位数的值要在1和26之间
         * 如果这两种情况那种是不成立的，就不用加这种情况的结果了
         * 
         */
		
		if(s == null || s.length()==0 || s.charAt(0)=='0')
			return 0;
		int[] number = new int[s.length()+1];
		number[0] = number[1] = 1;
		int tmp;
		for(int i=2;i<=s.length();i++){
			//one存放1位的情况，如果1位是合法的，one=number[i-1],否则，one=0
			//two存放2位的情况，如果2位是合法的，two=number[i-2],否则，two=0
			int one=0,two=0;
			
			tmp = Integer.parseInt(s.substring(i-1, i));
			if(tmp!=0)
				one = number[i-1];
			if(s.charAt(i-2)!='0'){
				tmp = Integer.parseInt(s.substring(i-2,i));//这是把字符串转化为整形
				if(tmp>=1 && tmp<=26)
					two = number[i-2];
			}
			
			number[i] = one+two;
		}
		
		return number[s.length()];
    }
}
