package LeetCode_27_Valid_Palindrome;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Given a string, determine if it is a palindrome, 
 * considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.  
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean isPalindrome(String s) {
    	
    	if(s==null || s.length()==0){
    		return true;
    	}
    	s = s.trim();
        Stack<Character> st = new Stack<Character>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
        	char a = s.charAt(i);
        	int tp = a+0;
        	if((a+0>='a'+0 && a+0<='z'+0) || (a+0>='A'+0 && a+0<='Z'+0) || (a+0>='0'+0 && a+0<='9'+0)){
        		st.push(a);
        		sb.append(a);
        	}
        }
        StringBuilder tmp = new StringBuilder();
        while(!st.isEmpty()){
        	tmp.append(st.pop());
        }
        if(tmp.length()==0 && sb.length()==0){
        	return true;
        }
        return tmp.toString().equalsIgnoreCase(sb.toString());
    }
    /**
     * 
     * 
     * 标准解法，使用两个游标，先使用正则表达式去除非字母数字
     * @param args
     */
    //很标准
    public boolean isPalindrome2(String s) {  
        
        if(s == null) return false;  
        //将非字母和数字的字符替换为空，即删去  
        s = s.replaceAll("[^a-z0-9A-Z]","");  
        if(s.length() == 0) return true;  
        s = s.toLowerCase();  
          
        int i = 0;  
        int j = s.length()-1;  
        while(i < j){  
            if(s.charAt(i) != s.charAt(j))  
                return false;  
            i++;  
            j--;  
        }  
        return true;  
    }  
    public static void main(String[] args) {
    	Stack<Character> st = new Stack<Character>();
    	st.push('a');
    	st.push('b');
    	System.out.println('A'+0);
		System.out.println(new Solution().isPalindrome("0P"));
	}
}
