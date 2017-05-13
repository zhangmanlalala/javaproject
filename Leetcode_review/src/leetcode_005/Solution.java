package leetcode_005;
/**
 *Given a string s, find the longest palindromic substring in s. You may 
 *assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.

Example:

Input: "cbbd"

Output: "bb"
 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 先使用动态规划试一下,我的方法子问题找的不对，看了分析之后的启发
	 * boolean result[i][j]代表下标索引从i到j的字串是不是回文字符串
	 * 找出状态转移方程
	 * result[i][j] = result[i+1][j-1] && s(i) == s(j)
	 * 初始化部分result[i][i] = true,result[i][i+1] = s(i) == s(j)
	 * 
	 * @param s
	 * @return
	 */
	/**
	 * 
	 * 该方法也超时
	 * @param s
	 * @return
	 */
    public String longestPalindrome(String s) {
    	if(s==null){
    		return null;
    	}
    	if(s.length()<=1){
    		return s;
    	}

    	boolean result[][] = new boolean[s.length()][s.length()];
    	String temp = s.charAt(0)+"";
    	for(int i=0;i<s.length();i++){
    		result[i][i] = true;
    	}
    	for(int i=0;i<s.length()-1;i++){
    		if(s.charAt(i) == s.charAt(i+1)){
    			result[i][i+1] = true; 
    			temp = s.substring(i,i+2);
    		}else{
    			result[i][i+1] = false; 
    		}
    	}
    	
    	for(int i=2;i<s.length();i++){//i表示i与j之间相隔的距离
    		for(int j=0;j+i<s.length();j++){
    			result[j][j+i] = (result[j+1][j+i-1] && (s.charAt(j) == s.charAt(j+i)) );
    			if(result[j][j+i]){
    				temp = s.substring(j, i+j+1);
    			}
    		}
    	
    		
    	}
    	return temp;
    }
    
    /**
     * 
     * 
     * 看标准答案:没想到是用穷举法，反而简单一些
     * 对于每一个位置，都向两边找对称的字串，更新最长对称字串。
		注意，对称有两种情况，
	    1，aba的情况
	    2，abba的情况
     * @param args
     */
    private int lo,maxLen;
    public String longestPalindrome2(String s) {
    	int len = s.length();
    	if(len<2)
    		return s;
    	for(int i=0;i<len-1;i++){
    		extendPalindrome(s,i,i);//假设奇数长度
    		extendPalindrome(s,i,i+1);//假设偶数长度
    	}
    	
    	return s.substring(lo, lo+maxLen);
    	
    }
    private void extendPalindrome(String s,int j,int k){
    	while(j>=0 && k<s.length() && s.charAt(j) == s.charAt(k)){
    		j--;
    		k++;
    	}
    	if(maxLen<k-j-1){//长度为(k-1-(j+1) + 1)
    		lo = j+1;
    		maxLen = k-j-1;
    	}
    }

    public static void main(String[] args) {
		System.out.println(new Solution().longestPalindrome("ccc"));
	}
}
