package LeetCode_2_Evaluate_Reverse_Polish_Notation;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:

  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        
        int i=0;
        while(i<tokens.length){
        	if(tokens[i].equals("+")){
        		int a = stack.pop();
        		int b = stack.pop();
        		stack.push(a+b);
        	}else if(tokens[i].equals("-")){
        		int a = stack.pop();
        		int b = stack.pop();
        		stack.push(b-a);
        	}else if(tokens[i].equals("*")){
        		int a = stack.pop();
        		int b = stack.pop();
        		stack.push(a*b);
        	}else if(tokens[i].equals("/")){
        		int a = stack.pop();
        		int b = stack.pop();
        		stack.push(b/a);
        	}else{
        		stack.push(Integer.parseInt(tokens[i]));
        	}
        }
        
        return stack.pop();
    }
}
