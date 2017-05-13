package LeetCode_13_Word_Break;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Given a string s and a dictionary of words dict, determine if s can be 
 * segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".  
 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
    	String[] arr =  new String[wordDict.size()];
    	int i=0;
    	for(String temp:wordDict){
    		arr[i] = temp;
    		i++;
    	}

       Arrays.sort(arr, new Comparator<String>(){

		@Override
		public int compare(String o1, String o2) {
			if(o1.length()>o2.length()){
				return 1;
			}else if(o1.length()<o2.length()){
				return -1;
			}else{
				return 0;
			}
		}});
       
       i = arr.length-1;
       while(s.length()!=0 && i>=0){
    	   int idx = s.indexOf(arr[i]);
    	   if(idx !=-1){
    		   s = s.substring(0,idx)+s.substring(idx+arr[i].length(),s.length());
    		   i--;
    	   }else{
    		   break;
    	   }
       }
       if(i==-1 && s.length()==0){
    	   return true;
       }else{
    	   return false;
       }
    }
    
    /**
     * 
     * 标准解法：动态规划
     * 最简单的想法，分解问题，假设我们从头开始找一个单词，则如果剩下的字符串可以被分割，则总的字符串就可以被分割
     * 由此产生最初的递归解法，但是我们发现，在判断后面的字符串能否分割的过程中，求解了大量的重复子问题，只要重复子问题，
     * 则应该想到动态规划，动态规划的基本思路就是用一个字典记录中间结果，以备后面查询
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, Set<String> wordDict) {
    	boolean[] table = new boolean[s.length()+1];
    	table[0] = true;
    	
    	for(int i=1;i<s.length();i++){
    		for(int k=0;k<i;k++){
    			table[i] = table[k] && wordDict.contains(s.substring(k, i));
    			if(table[i])
    				break;
    		}
    	}
    	
    	return table[s.length()];
    }
    public static void main(String[] args) {
    	Set<String> wordDict = new HashSet<String>();
    	Collections.addAll(wordDict, "a","b","bbb","bbbb");
		System.out.println(new Solution().wordBreak2("bb", wordDict));
	}
       
       

	


}
