package LeetCode_104_Anagrams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import LeetCode_104_Anagrams.Solution;


/**
 *Given an array of strings, return all groups of strings that are anagrams.

	Note: All inputs will be in lower-case. 
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 分析：找出所有可能，就用回溯算法，回溯算法要注意保护现场
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public Set<String> drivePermut(String s){
		StringBuilder sb = new StringBuilder();
		List<Integer> index = new ArrayList<Integer>();
		Set<String> set = new HashSet<String>();
		permutations(s,index,sb,set);
		return set;
	}
	public void permutations(String s,List<Integer> index,StringBuilder sb,Set<String> set){
		
		if(s.isEmpty() || index.size()==s.length()){
			return ;
		}
		
		for(int i=0;i<s.length();i++){
			if(index.contains(i)){
				continue;
			}
			sb.append(s.charAt(i));
			index.add(i);
			permutations(s,index,sb,set);
			if(sb.length()==s.length()){				
				set.add(sb.toString());
			}
			sb.deleteCharAt(sb.length()-1);
			index.remove(index.size()-1);
		}
	}
	
	public static void main(String[] args) {
		String s = "abc";
		Set<String> set = new Solution().drivePermut(s);
		
		for(String temp:set){
			System.out.println(temp);
		}
	}
}
