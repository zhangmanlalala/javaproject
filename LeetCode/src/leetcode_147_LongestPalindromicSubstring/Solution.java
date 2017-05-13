package leetcode_147_LongestPalindromicSubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there 
 * exists one unique longest palindromic substring.
	分析：
	对于每一个位置，都向两边找对称的字串，更新最长对称字串。
	注意，对称有两种情况，
	1，aba的情况
	2，abba的情况
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
 
	public static String find(String string){
		Map<Integer,String> map = new HashMap<Integer,String>();
		String subString = null;
		int len = 1;
		int flag=0;
		for(int i=1;i<string.length()-1;i++){
			 map.put(i,findPalindromicSubstring(i,string));
			 if(len<map.get(i).length()){
				len =map.get(i).length();
				flag = i;
			}
		}
		if(len>1){
			return map.get(flag);
		}else{
			return null;
		}
	}
	
	public static String findPalindromicSubstring(int indx,String string){
		int i= indx;
		int j= indx;
		if(string.charAt(indx-1)==string.charAt(indx)){
			i = i-1;
		}else if(string.charAt(indx+1)==string.charAt(indx)){
			j = j+1;
	    }
		while(i>=1 && j<string.length()-1){
			 if(string.charAt(i-1)==string.charAt(j+1)){
				i = i-1;
				j = j+1;
			}else{
				break;
			}
		}
	    return string.substring(i, j+1);
	    		
	}
	
	public static void main(String[] args) {
		String string1 = "fabcdcba";
		String string2 = "abcddcba";
		System.out.println(find(string1));
		System.out.println(find(string2));
	}
}
