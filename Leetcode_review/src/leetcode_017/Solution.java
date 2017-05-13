package leetcode_017;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:
Although the above answer is in lexicographical order, your answer could be in any order you want. 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public List<String> letterCombinations(String digits) {
    	String[] str = new String[]{" ","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    	
    	
    	List<String> lst = new ArrayList<String>();
    	
    	if(digits == null || digits.length() == 0){
    		return lst;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	dfs(str,digits,lst,sb);
    	
    	return lst;
    	
    	
    }
    
    
    /**
     * 
     * 标准的回溯算法，注意恢复现场
     * @param str
     * @param digits
     * @param lst
     * @param sb
     */
    private void dfs(String[] str,String digits,List<String> lst,StringBuilder sb){
    	
    	if(digits.length()==0){
    		lst.add(sb.toString());
    		return;
    	}
    	String temp = str[digits.charAt(0)-'0'];
    	for(int i=0;i<temp.length();i++){
    		sb.append(temp.charAt(i));
    		  		
    		dfs(str,digits.substring(1),lst,sb);
    		
    		sb.deleteCharAt(sb.length()-1);
    	}
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().letterCombinations("23"));
	}
}
