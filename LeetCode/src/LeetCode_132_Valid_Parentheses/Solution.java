package LeetCode_132_Valid_Parentheses;

import java.util.Stack;

/**
 *Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 *determine if the input string is valid.

  The brackets must close in the correct order, "()" and "()[]{}" 
  are all valid but "(]" and "([)]" are not.
 * 
 * 
 * @author Administrator
 *
 */

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean isValid(String s){
		if(s==null || s.length()==0){
			return false;
		}
		
		Stack<Character> st = new Stack<Character>();
		
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='(' || s.charAt(i)=='[' || s.charAt(i)=='{'){
				st.push(s.charAt(i));
			}else if(s.charAt(i)==')' || s.charAt(i)==']' || s.charAt(i)=='}'){
			    if(s.charAt(i)==')' && (st.empty() || st.peek()!='(')){
					return false;
				}
				if(s.charAt(i)==']' && (st.empty() || st.peek()!=']')){
					return false;
				}
				if(s.charAt(i)==']' && (st.empty() || st.peek()!=']')){
					return false;
				}
				st.pop();
			}
		}
		if(!st.empty())
			return false;
		return true;
	}

}
