package LeetCode_20_Palindrome_Partitioning_II;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.  
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 使用动态规划，最小问题，我怎么没想到呢
	 * 我自己的状态转移方程不对，时间越界
	 * @param s
	 * @return
	 */
    public int minCut(String s) {
    	if(s==null){
    		return 0;
    	}
    	int len[] = new int[s.length()+1];
    	len[0] = -1;
    	len[1] = 0;
    	int min_val ;
    	for(int i=2;i<=s.length();i++){
    		min_val = Integer.MAX_VALUE;

    		for(int j=0;j<i;j++){
    			String temp = s.substring(j, i);
    			if(isPalindrome(temp)){
    				int a = 1+len[j];
    				if(a<min_val){
    					min_val = a;
    				}
    				if(min_val==0){
    					break;
    				}
    						
    			}
    		}
    		len[i] = min_val;
    	}
    	
    	return len[s.length()];

    }
    
    /**
     * 
     * 看看标准解法
     * 动态规划：看到最小切割数，首先想到动态规划，之后就是怎么提取子问题，以及寻找状态转移方程
     * 
     * 假设字符串的长度为len
     * 子问题：从i到n-1的最小切割数，设为cuts[i],可知，cuts[n-1] = 0,因为只有一个字符，初始，cuts[i] = len-i-1,
     * 即初始认为只有一个字符才是一个回文
     * 状态转移方程：假设区间[i,j]上的字串是回文，则i处的切割只要在j+1出的最小切割数+1，
     * cuts[i] = min{cuts[i],cuts[j+1]+1},
     * 那么cuts[0]就是最后的结果
     * @param 
     * @return
     */
    /**
     * 
     * 判断是不是回文字符串，首先想到递归，在递归中每次都要求解某个区间是不是回文，产生重复的子问题，对于子问题重叠的情况，想到使用
     * 动态规划
     * 用mat[i][j]来表示，区间[i,j]是不是回文，记录中间结果
     * 则，如果mat[i+1][j-1]位true，同时i,j位置字符相等，则mat[i][j] = true
     * @param s
     * @return
     */
    
    public int minCut2(String s){
    	int len = s.length();
    	boolean[][] matrix = new boolean[len][len];
    	int cuts[] = new int[len+1];
    	//初始化cuts，在每一个字符出都切一下
    	for(int i=0;i<=len;i++){
    		cuts[i] = len-i-1;
    	}
    	//更新从i到最后的状态
    	for(int i=len-1;i>=0;i--){
    		
    		for(int j=i;i<len;j++){
    			if((s.charAt(i) == s.charAt(j) &&(j-i<2)) || 
    			(s.charAt(i)==s.charAt(j) && matrix[i+1][j-1])){  //判断回文字符串也用动态规划，并且和主程序嵌套在一起是我从未想到的
    				matrix[i][j] = true;
    				cuts[i] = Math.min(cuts[i], cuts[j+1]+1);//这一点很经典
    			}
    		}
    	}
    	
    	return cuts[0];
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
		System.out.println(new Solution().minCut("aab"));
	}
}
