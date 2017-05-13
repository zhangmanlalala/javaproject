package LeetCode_121_Longest_Valid_Parentheses;

import java.util.Stack;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 
 * Given a string containing just the characters '(' and ')', find the length 
 * of the longest valid (well-formed) parentheses substring.
	For "(()", the longest valid parentheses substring is "()", which has length = 2.
    Another example is ")()())", where the longest valid parentheses substring is "()()", 
    which has length = 4.
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {

	public static void main(String[] args) {
		//String string = ")()())";
		String string = "(()";
		System.out.println(new Solution().findLongestParentheses(string));
	}
	
	public int findLongestParentheses(String str){
		Stack<Character> ss = new Stack<Character>();
		if(str==null || str==""){
			return 0;
		}
		int len = 0;
		str = str.trim();
		for(int i=0;i<str.length();i++){
			if(str.charAt(i) == ')' && !ss.isEmpty()){
				ss.pop();
				len +=2;
			}else if(str.charAt(i) == '('){
				ss.push('(');
			}
		}
		return len;
		
	}

}
