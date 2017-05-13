package LeetCode_12_Word_Break_II;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, add spaces in 
 * s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given

 s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"]. 
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 
 * 分析，一看到返回所有，应该想到DFS（回溯算法），应该注意恢复现场
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 我与标准答案的差距竟然是事先没判断能不能分割
	 * @param s
	 * @param wordDict
	 * @return
	 */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> lst = new ArrayList<String>();
        if(s==null || s.length()==0) return lst;  
        //首先判断s能不能刚好被分割  
        //这里用动态规划的方法，table[i]记录[0,i)这个子问题的解  
        //[0,i)可以分割为true,否则为false  
        boolean[] table = new boolean[s.length()+1];  
        table[0] = true;  
        for(int i=1; i<=s.length(); i++){  
            for(int k=0; k<i; k++){  
                //[0,k)可以分割，同时[k,i)在字典里，说明[0,i）可以分割  
                //记录中间结果避免重复求解子问题  
                table[i] = table[k] && wordDict.contains(s.substring(k,i));  
                if(table[i] == true)  
                    break;  
            }  
        }  
        //如果s不能被分割，就没有必要继续求解下去  
        if(!table[s.length()]) return lst;  
          
        StringBuilder sb = new StringBuilder();
        dfs(s,wordDict,sb,lst);
        return lst;
    }
    
    
    public void dfs(String s,Set<String> wordDict,StringBuilder sb,List<String> lst){
    	
    	if(s.length()==0){
    		String ss = sb.toString();
    		lst.add(ss.substring(0,ss.length()-1));
    	}
    	
    	for(int i=1;i<=s.length();i++){
    		String temp = s.substring(0, i);
    		if(wordDict.contains(temp)){
    			sb.append(temp);
    			sb.append(" ");
    			
    			dfs(s.substring(i,s.length()),wordDict,sb,lst);
    			
    			sb.delete(sb.length()-temp.length()-1, sb.length());
    		}
    	}
    }
    
    public static void main(String[] args) {
    	
    	Set<String> wordDict = new HashSet<String>();
    	Collections.addAll(wordDict, "cat", "cats", "and", "sand", "dog");
    	
		System.out.println(new Solution().wordBreak2("catsanddog", wordDict).toString());
	}
   
    
    /**
     * 
     * 我的算法时间又越界了，还是看标准算法吧
     * 
     * 分析：最基本的思路就是逐个尝试，从第一个字符开始检查，能否在字典中找到子串
     * 如果可以找到，加入解，在从其后的位置开始找，知道最后，刚好结束，就找到一个解
     * 因为可能从某个位置开始能找到两个不同的单词，所以要回溯寻找不同的解
     * 
     * 处理技巧：
     * 1，先判断整个字符串能不能刚好分割，因为有重复问题，考虑动态规划
     * 2，用递归来处理回溯，加上一个单词，递归往后求解，之后再进去这个单词，以保证可以遍历以此
     * 点开始的所有单词
     * 
     */
    
    
    public List<String> wordBreak2(String s, Set<String> dict) {  
    	List<String> res = new ArrayList<String>();
    	if(s==null || s.length()==0) return res;
    	
    	//首先判断s能不能刚好分割
    	//这里使用动态规划，table[i]记录[0,i)这个子问题
    	//[0,i)可以分割为true和false
    	
    	boolean[] table = new boolean[s.length()+1];
    	table[0] = true;
    	for(int i=1;i<s.length();i++){
    		for(int k=0;k<i;k++){
    			//[0,k)可以分割，同时[k,i)在字典里，说明[0,i)可以分割
    			//记录中间结果避免重复求解子问题
    			table[i] = table[k] && dict.contains(s.substring(k, i));
    			if(table[i]==true){
    				break;
    			}
    		}
    	}
    	
    	//如果s不能分割，就没必要继续求解下去
    	if(!table[s.length()]) return res;
    	
    	
    	StringBuilder sb = new StringBuilder();
    	dfs2(s, 0, sb, res, dict);  
        return res;
    }
    
    private void dfs2(String s, int start, StringBuilder sb, List<String> res, Set<String> dict){  
        //递归终结  
        if(start >= s.length()){  
            //注意要新建一个字符串  
            res.add(new String(sb));  
            return;  
        }  
        for(int i=start+1; i<=s.length(); i++){  
            String sub = s.substring(start, i);  
            if(dict.contains(sub)){  
                int currLen = sb.length();  
                if(currLen != 0){  
                    sb.append(" ");  
                }  
                sb.append(sub);  
                dfs2(s, i, sb, res, dict);  
                sb.delete(currLen, sb.length());  
            }  
        }  
    }  
    
    
}
