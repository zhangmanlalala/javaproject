package LeetCode_80_Edit_Distance;
/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character

c) Replace a character
 * 
 * @author Administrator
 *
 */

/**
 * 
 * 我的理解：求最长公共子序列
 * @author Administrator
 *
 */

/**
 * 
 * 该方法是失败的
 * 不能够考虑所有的情况
 * @author Administrator
 *
 */
public class Solution {
	//word2是转化目标
//	public int minDistance(String word1, String word2) {
//        int len1 = word1.length();
//        int len2 = word2.length();
//        int stepNum = 0;
//        int preFlag2 = -1;
//        int preFlag1 = -1;
//        for(int i=0;i<len2;i++){//word2
//        	
//        	for(int j=preFlag1+1;j<len1;j++){//word1
//        		if(word1.charAt(j) == word2.charAt(i)){
//        			int gap1 = j-preFlag1;
//        			int gap2 = i-preFlag2;
//        			if(gap1-gap2 == 0){//相等只用替换或者什么都不用做
//        				stepNum = stepNum+gap1-1;
//        			}else if(gap1-gap2>0){//不相等，要先删除，再替换，公用gap1步
//        				stepNum = stepNum+gap1-1;
//        			}else{//不相等，要先替换，再添加字符，总共用gap2步
//        				stepNum = stepNum+gap2-1;
//        			}
//        			
//        			preFlag1 = j;
//        			preFlag2 = i;
//        			break;
//        		}
//        		
//        	}
//        	
//        }
//        
//        int gap1 = len1-1-preFlag1;
//        int gap2 = len2-1-preFlag2;
//        if(gap1-gap2 == 0){//相等只用替换或者什么都不用做
//			stepNum = stepNum+gap1;
//		}else if(gap1-gap2>0){//不相等，要先删除，再替换，公用gap1步
//			stepNum = stepNum+gap1;
//		}else{//不相等，要先替换，再添加字符，总共用gap2步
//			stepNum = stepNum+gap2;
//		}
//        
//        return stepNum;
//        
//    }
//	
	/**
	 * 看到最优问题，先想到动态规划算法
	 * 采用动态规划算法，自底向上地计算最优解，并将结果保存在二级字典或一级字典中
	 * @param word1
	 * @param word2
	 * @return
	 */
	
	/**
	 * 
	 * 状态转移方程：

	因为有三种转移方式，replace, insert, delete,
	
	如果i，j位置上字符相同，则不需要操作，dp[i+1][j+1] = dp[i][j];
	
	如果不相同，则有三种转移方式：
	
	replace = dp[i][j] + 1;
	
	insert = dp[i][j+1] + 1;
	
	delete = dp[i+1][j] + 1;
	
	dp[i+1][j+1] = min{ replace, insert, delete }.
	 * @param word1
	 * @param word2
	 * @return
	 */
	public int minDistance(String word1, String word2) {
		if(word1==null || word2==null){
			return -1;
		}
		
		int len1 = word1.length();
		int len2 = word2.length();
		//转移矩阵，记录从Word1前i个字符到Word2前j个字符转换所需要的步数
		int[][] dp = new int[len1+1][len2+1];
		for(int i=0;i<=len1;i++)
			dp[i][0] = i;
		for(int j=0;j<=len2;j++)	
			dp[0][j] = j;
        for(int i=0;i<len1;i++){
        	char c1 = word1.charAt(i);
        	for(int j=0;j<len2;j++){
        		char c2 = word2.charAt(j);
        		if(c1 == c2)
        			dp[i+1][j+1] = dp[i][j];
        		else{
        			int replace = dp[i][j]+1;
        			int insert = dp[i][j+1]+1;
        			int delete = dp[i+1][j]+1;
        			
        			//find the min one
        			int min = Math.min(replace, insert);
        			min = Math.min(min, delete);
        			dp[i+1][j+1] = min;
        		}
        	}
        }
        
        return dp[len1][len2];
    }
	
	
	public static void main(String[] args) {
		System.out.println(new  Solution().minDistance("sea", "ate"));
	}
}
