package LeetCode_95_Length_of_Last_Word;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example,
Given s = "Hello World",

return 5.
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 
 * 分析：从后往前数，末尾的空格都不算，碰到第一个有效的空格终止
 * @author Administrator
 *
 */
public class Solution {
	public int lengthOfLastWord(String s) {  
        //从后往前数，后面的空格不算  
        int count=0;  
        for(int i=s.length()-1; i>=0; i--){  
            if(s.charAt(i) == ' '){  
                if(count == 0)//后面的空格不算  
                    continue;  
                else  
                    return count;  
            }  
            count++;  
        }  
        return count;  
    }  
}
