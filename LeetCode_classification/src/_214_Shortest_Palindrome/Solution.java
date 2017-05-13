package _214_Shortest_Palindrome;
/**
 * Given a string S, you are allowed to convert it to a palindrome by adding 
 * characters in front of it. Find and return the shortest palindrome you can 
 * find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd". 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 直接看的标准答案
	 * 思路：
	 * 从某个char开始向两边扩展(左右两边的字符相等)，如果能一直扩展到字符串的头部
	 * 则将末尾的余下的reverse，再加到原字符串的头部即可
	 * 1.中轴字符从中间开始选，这样找到的即为最短
	 * 2.中轴字符可能为1个，也可能为2个
	 * 
	 * @param s
	 * @return
	 */
    public String shortestPalindrome(String s) {
        if(s.length() <=1 ) return s;
        int center = (s.length()-1)/2;
        String res = "";
        for(int i=center;i>=0;i--){
        	if(s.charAt(i) == s.charAt(i+1)){
        		if((res=check1(s,i,i+1)) != null) return res;
        	}
        	
        	if((res = check1(s,i,i)) != null) return res;
        }
        
        return res;
    }
    
    //aabaac
    private String check1(String s,int l,int r){
    	int i=1;
    	for(;l-i>=0 && r+i<s.length();i++){
    		if(s.charAt(l-i) != s.charAt(r+i)) break;
    	}
    	
    	if(l-i>=0) return null;
    	StringBuilder sb = new StringBuilder(s.substring(r+i));
    	sb.reverse();
    	return sb+s;
    }
}
