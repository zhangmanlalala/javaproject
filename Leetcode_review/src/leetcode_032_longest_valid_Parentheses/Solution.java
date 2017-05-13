package leetcode_032_longest_valid_Parentheses;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length(),longest = 0;
        Stack<Integer> st = new Stack<Integer>();
        for(int i=0;i<n;i++){
        	if(s.charAt(i) == '(') st.push(i);
        	else{
        		if(!st.isEmpty()){
        			if(s.charAt(st.peek()) == '(') st.pop();
        			else st.push(i);
        		}else{
        			st.push(i);
        		}
        	}
        }
        
        
        if(st.empty()) 
        	longest = n;
        else{
        	int a = n,b = 0;
        	while(!st.isEmpty()){
        		b = st.peek();
        		st.pop();
        		longest = Math.max(longest, a-b-1);
        		a = b;
        	}
        	
        	longest = Math.max(longest,a);
        }
        
        return longest;
    }
    
    /**
     * 
     * 看动态规划算法
     * @param s
     * @return
     */
    
    public int longestValidParentheses2(String s) {
        if(s==null || s.length()<2){
        	return 0;
        }

    	boolean result[][] = new boolean[s.length()][s.length()];//表示从索引i到j之间是不是有效的括号
    	int maxLen = 0;
    	
    	for(int i=1;i<s.length();i++){
    		if(s.charAt(i-1)=='(' && s.charAt(i)==')'){
    			result[i-1][i] = true;
    			maxLen = 2;
    		}else{
    			result[i-1][i] = false;
    		}
    	}
    	
    	for(int j=3;j<=s.length()-1;j=j+2){
    		for(int i=0;i+j<s.length();i++){
    			result[i][i+j] = (result[i][i+j-2] && result[i+j-1][i+j]) || 
    							(result[i+1][i+j-1] && s.charAt(i)=='(' && s.charAt(i+j)==')') ||
    							(result[i][i+1] && result[i+2][i+j]);
    			
    			if(result[i][i+j]){
    				maxLen = Math.max(maxLen, j+1);
    			}
    		}
    	}
    	
    	return maxLen;
    	
    	
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().longestValidParentheses2(")(((((()())()()))()(()))("));
	}
}
