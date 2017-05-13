package LeetCode_21_Palindrome_Partitioning;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 
	 * 完美，回溯算法，好久没用了，生疏了，做了这么久
	 * @param s
	 * @return
	 */
    public List<List<String>> partition(String s) {
    	List<List<String>> result = new ArrayList<List<String>>();
    	if(s==null){
    		return result;
    	}
    	List<String> lst = new ArrayList<String>();
    	partition(s.trim(),lst,result);
    	return result;
    }
    /**
     * 
     * 基本思想是，第一个字符单独出现，和不单独出现两种情况，剩下的子字符串变为子问题，又可以分为这两种情况，递归求解
     * dfs回溯算法(for循环加递归)，最关键的是要恢复现场
     * @param s
     * @param lst
     * @param result
     */
    private void partition(String s,List<String> lst,List<List<String>> result){
    	
    	
    	if(s.length()==0){   		
    		List<String> tp = new ArrayList<String>();
    		tp.addAll(lst);
    		result.add(tp);
    		return ;
    	}

    	
 
    	
    	for(int i=1;i<=s.length();i++){
    		String temp = s.substring(0, i);
    		if(isPalindrome(temp)){
    			lst.add(temp);
    
    			partition(s.substring(i,s.length()),lst,result);
    			
    			lst.remove(lst.size()-1);
    			
    		}
    	}
    }
    
    private boolean isPalindrome(String s){
    	if(s.length()==1){
    		return true;
    	}
    	int i=0;
    	int j=s.length()-1;
    	while(i<j){
    		if(s.charAt(i)==s.charAt(j)){
    			i++;
    			j--;
    		}else{
    			break;
    		}
    	}
    	if(i<j){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    
    public static void main(String[] args) {
//		List<String> lst = new ArrayList<String>();
//		Collections.addAll(lst, "abc","def");
		List<List<String>> result = new Solution().partition("aab");
		System.out.println("ab".substring(2, 2).length());
		for(List<String> temp:result){
			
			System.out.println(temp.toString());
		}
	}
}
