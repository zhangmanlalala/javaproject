package _224_Basic_Calculator;

import java.util.Stack;

/**
 *Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or 
minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:

"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

Note: Do not use the eval built-in library function.  
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 
	 * @param s
	 * @return
	 */
    public int calculate2(String s) {
        Stack<String> stack = new Stack<String>();
        String ss = s.replaceAll(" ", "");
        String temp;
        int i=0;
        int j=0;
        int sum = 0;
        while(j<ss.length()){
        	char c = ss.charAt(j)  ;
        	if(c == '+' || c == '-'){
        		if(i<j){
	        		stack.push(ss.substring(i,j));
	        		stack.push(c+"");
        		}else{
        			stack.push(c+"");        			
        		}
        		j++;
        		i = j;
        	}else if(c>='0' && c<='9'){
        		j++;
        	}else if(c == '('){
        		stack.push(c+"");
        		i++;
        		j++;
        	}else if(c == ')'){
        		
        		int ch;
        		//这是")"符号前的第一个数字
        		if(i<j){
        			ch = Integer.parseInt(ss.substring(i,j));
        		}else{
        			ch = Integer.parseInt(stack.pop());
        		}
        		while(!stack.peek().equals("(")){
        			temp = stack.pop();
        			if(temp.equals("+")){
        				ch = ch+Integer.parseInt(stack.pop());       				
        			}else if(temp.equals("-") ){
        				ch = Integer.parseInt(stack.pop())-ch;
        			}else{
        				break;
        			}
        		}
        		if(!stack.isEmpty()){
        			stack.pop();
        		}
        		stack.push(String.valueOf(ch));
        		
        		j = j+1;
        		i = j;
        	}
 
        }
        
        if(i<j){
        	stack.push(ss.substring(i,j));
        }
        
        if(stack.size() == 1){
        	return Integer.parseInt(stack.pop());
        }else{
        	sum = Integer.parseInt(stack.pop());
        	while(stack.size()>0){
        		temp = stack.pop();
    			if(temp.equals("+")){
    				sum = sum+Integer.parseInt(stack.pop());
    				
    			}else if(temp.equals("-") ){
    				sum = Integer.parseInt(stack.pop())-sum;
    			}else{
    				break;
    			}
        	}
        	
        	return sum;
        }
    }
    
    public int calculate(String s){
    	if(s==null || s.length() ==0) return 0;
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	
    	int res = 0;
    	int sign = 1;
    	for(int i=0;i<s.length();i++){
    		char c = s.charAt(i);
    		if(Character.isDigit(s.charAt(i))){
    			int cur = c - '0';
    			while(i+1<s.length() && Character.isDigit(s.charAt(i+1))){
    				cur = 10*cur+s.charAt(++i) - '0';
    			}
    			
    			res +=sign*cur;
    		}else if(c=='-'){
    			sign = -1;
    		}else if(c=='+'){
    			sign = 1;
    		}else if(c=='('){
    			
    			stack.push(sign);
    			res = 0;
    			sign = 1;
    		}else if(c == ')'){
    			res = stack.pop()*res + stack.pop();
    			sign = 1;
    		}
    	}
    	
    	return res;
    }
    
    
    public static void main(String[] args) {
		System.out.println(new Solution().calculate2("2-1+2"));
	}
    
   
}
