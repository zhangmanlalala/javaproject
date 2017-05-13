package leetcode_003;

import java.util.HashMap;

/**
 * Given a string, find the length of the longest substring without 
 * repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer 
must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 采用动态规划试一下,发现超时
	 * @param s
	 * @return
	 */
    public int lengthOfLongestSubstring2(String s) {
    	if(s==null || s.length()<1){
    		return 0;
    	}
        int result[] = new int[s.length()+1];
        result[0] = 0;
        result[1] = 1;
        for(int i=2;i<=s.length();i++){
        	int j=i-1;
        	for(;j>=1;j--){
        		String temp = s.substring(j, i);

        		if(temp.indexOf(s.charAt(j-1)) != -1){
        			break;
        		}      			
        		
        	}
        	
        	result[i] = Math.max(result[i-1], i-j);
        }
        
        return result[s.length()];
    }
    
    /**
     * 
     * 看一下标准解法
     */
    public int lengthOfLongestSubstring(String s) {

    	if(s.length()== 0 ) return 0;
    	HashMap<Character,Integer> map = new HashMap<Character,Integer>();
    	int max = 0;
    	for(int i=0,j=0;i<s.length();++i){
    		if(map.containsKey(s.charAt(i))){
    			j = Math.max(j, map.get(s.charAt(i))+1);
    		}
    		map.put(s.charAt(i), i);
    		max=  Math.max(max, i-j+1);
    	}
    	return max;
    }
}
