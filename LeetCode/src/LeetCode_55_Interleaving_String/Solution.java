package LeetCode_55_Interleaving_String;
/**
 *  Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false. 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 我自己的解法，递归时间复杂度很高，通不过
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public boolean isInterleave2(String s1, String s2, String s3) {
        
		
		if(s1.length()+s2.length() !=s3.length()){
			return false;
		}
		
		if(s3.length() == 0 && s1.length()==0 && s2.length()==0){
			return true;
		}
		
		char ch3 = s3.charAt(s3.length()-1);
		
		if(s1.length()>0 && s2.length()>0){
			char ch1 = s1.charAt(s1.length()-1);
			char ch2 = s2.charAt(s2.length()-1);
			if( ch3!=ch1 && ch3!=ch2 ){//最后一个元素都不相等，直接返回false
				return false;
			}else if(ch3!=ch1){//ch3 == ch2
				return isInterleave(s1,s2.substring(0, s2.length()-1),s3.substring(0, s3.length()-1));
			}else if(ch3!=ch2){//ch3 == ch1
				return isInterleave(s1.substring(0, s1.length()-1),s2,s3.substring(0, s3.length()-1));
			}else{//ch3 == ch2 && ch3 == ch1
				return isInterleave(s1,s2.substring(0, s2.length()-1),s3.substring(0, s3.length()-1)) || isInterleave(s1.substring(0, s1.length()-1),s2,s3.substring(0, s3.length()-1));
			}
		}else if(s1.length()>0){
			char ch1 = s1.charAt(s1.length()-1);
			if( ch3!=ch1 ){//最后一个元素都不想等，直接返回false
				return false;
			}else{
				return isInterleave(s1.substring(0, s1.length()-1),s2,s3.substring(0, s3.length()-1));
			}
		}else if(s2.length()>0){
			char ch2 = s2.charAt(s2.length()-1);
			if(ch3!=ch2){
				return false;
			}else{
				return isInterleave(s1,s2.substring(0, s2.length()-1),s3.substring(0, s3.length()-1));
			}
		}else{
			return false;
		}
    }
	/**
	 * 
	 * 
	 * 标准解法，动态规划，找出子问题，找出状态转移方程求出子问题的解
	 * 子问题：设mat[i][j]表示s1从头开始长度为i字串和s2从头开始长度为j的字串能否交叉成s3上长度为(i+j)的子串
	 * 我们可以看出这个问题跟最终问题同质，只是规模不同，这是标准的动态规划
	 * 
	 * 接下来找状态转移方程
	 * mat[i][j] = mat[i-1][j] &&(s1.charAt(i) == s3.charAt(i+j-1))  || mat[i][j-1] &&(s2.charAt(j) == s3.charAt(i+j-1))
	 * 
	 * 
	 * 接下来考虑初始化
	 * mat[0][0]应该是true，另外初始化mat[i][0]和mat[0][j]
	 * @param args
	 */
	
	public boolean isInterleave(String s1, String s2, String s3) {
		if(s1 == null || s2 == null || s3==null){
			return false;
		}
		
		if(s3.length()!=s1.length()+s2.length()){
			return false;
		}
		
		boolean mat[][] = new boolean[s1.length()+1][s2.length()+1];
		mat[0][0] = true;//初始化
		
		for(int i=1;i<s1.length()+1;i++){
			if(s1.charAt(i-1) == s3.charAt(i-1) && mat[i-1][0])
				mat[i][0] =true;
			else{
				break;
			}
		}
		
		for(int j=1;j<s2.length()+1;j++){
			if(s2.charAt(j-2) == s3.charAt(j-1) && mat[0][j-1])
				mat[0][j] = true;
			else
				break;
		}
		
		
		for(int i=1;i<s1.length()+1;i++){
			for(int j=1;j<s2.length()+1;j++){
				if(s1.charAt(i-1) == s3.charAt(i+j-1) && mat[i-1][j])
					mat[i][j] = true;
				if(s2.charAt(j-1) == s3.charAt(i+j-1) && mat[i][j-1])
					mat[i][j] = true;
			}
		}
		
		return mat[s1.length()][s2.length()];
		
		
		//很标准的动态规划，找出状态转移方程求出子问题的解
		
	}
	
	public static void main(String[] args) {
		String s1 = "aabcc";
		String s2 = "dbbca";
		//String s3 = "aadbbcbcac";
		String s3 = "aadbbbaccc";
		//System.out.println(s1.substring(0, 0));
		System.out.println(new Solution().isInterleave(s1, s2, s3));
	}
}
