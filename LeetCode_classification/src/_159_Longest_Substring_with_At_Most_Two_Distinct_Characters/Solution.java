package _159_Longest_Substring_with_At_Most_Two_Distinct_Characters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Longest Substring with At Most Two Distinct Characters

Given a string, find the length of the longest substring T that contains 
at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.

 

可与Longest Substring Without Repeating Characters对照看。
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 动态规划，找到子问题，子问题重叠，就想到使用动态规划
	 * 
	 * 动态规划的解法时间复杂度较高
	 * 求出状态转移方程
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if(s==null){
			return 0;
		}
		if(s.length()<=2){
			return s.length();
		}
		int result[] = new int[s.length()+1];
		result[0] = 0;
		result[1] = 1;
		result[2] = 2;
		Set<Character> set = new HashSet<Character>();
		for(int i=2;i<s.length();i++){
			
			int j=i;
			for(;j>=0;j--){
				set.add(s.charAt(j));
				if(set.size()>2){					
					break;
				}
			}
			set.clear();
			if(j<0){
				result[i+1] = Math.max(i+1, result[i]);
			}else{
				result[i+1] = Math.max(i-j, result[i]);
			}
		}
		
		return result[s.length()];
	}
	
	
	/**
	 * 
	 * 
	 * 标准解法：用HashTable记录当前两个字符的数目，如果遇到新的字符，移动start指针的位置，知道新的start位置到当前位置
	 * 只包含两个字符
	 * 
	 * 核心思想使，当i向前移动时使start到i之间不同得字符个数始终维持在小于等于2
	 * @param args
	 */
	
	
	//很经典，相比之下动态规划的解法时间复杂度较高
	 public int lengthOfLongestSubstringTwoDistinct2(String s) {  
		 if(s== null || s.length() == 0){
			 return 0;
		 }
		 Map<Character,Integer> map = new HashMap<Character,Integer>();
		 int start = 0;
		 int max = 0;
		 
		 //核心思想使，当i向前移动时使start到i之间不同得字符个数始终维持在小于等于2
		 for(int i=0;i<s.length();i++){
			 char c = s.charAt(i);
			 if(map.containsKey(c)){
				 map.put(c, map.get(c)+1);
			 }else if(map.size()<2){
				 map.put(c, 1);
			 }else{
				while((map.size()==2) && start<i){
					 char temp = s.charAt(start);
					 int x = map.get(temp);
					 --x;
					 if(x==0) {//start位置的字符只出现一次，当start向前移动一位时，直接从map中除去这个字符
						 map.remove(s.charAt(start));
					 }else {//start位置的字符出现超过一次，当start向前移动时，该字符的出现次数要-1，然后再map中更新
						 map.put(s.charAt(start), x);
					 }
					 ++start;	//start向前移动，直到map的size为1，当然此时start<i要成立				 
					 
				}
				 
				map.put(c, 1); //将map的size减为1后，此时可以将c放入map中
				 
			 }
			 max = Math.max(max, i-start+1);
		 }
		 
		 return max;
	 }
	
	public static void main(String[] args) {
		System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("abcdedcba"));
	}
	
}
