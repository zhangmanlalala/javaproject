package leetcode_020;

import java.util.Stack;

/**
 * 
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]"
 are not.

Subscribe to see which companies asked this question.

 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> ss = new Stack<Character>();
        
        for(int i=0;i<s.length();i++){
        	if(s.charAt(i) == '(' || s.charAt(i)=='[' || s.charAt(i)=='{' ){
        		ss.push(s.charAt(i));
        	}else{
        		if(ss.isEmpty()){
        			return false;
        		}else{
        			char temp = ss.pop();
        			if(s.charAt(i) ==')'){
        				if(temp!='(')
        					return false;
        			}else if(s.charAt(i) == ']'){
        				if(temp!='[')
        					return false;
        			}else{
        				if(temp!='{')
        					return false;
        			}
        		}
        	}
        }
        
        if(ss.isEmpty()){
        	return true;
        }else{
        	return false;
        }
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().isValid("(){}([])"));
	}
}
