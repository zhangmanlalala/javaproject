package leetcode_072_edit_distance;


/**
 *  Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
	You have the following 3 operations permitted on a word:	
	a) Insert a character
	b) Delete a character
	c) Replace a character
 * @author Administrator
 *
 */
public class Solution {
    
	
	/**
	 * 
	 * 
	 * 这个题目又是标准的动态规划
	 * 子问题result[i][j]代表字符串a[0,..,i]和字符串b[0,..,j]的最小转换步骤
	 * (1)当a[i] == b[j]时，result[i][j] = result[i-1][j-1]
	 * (2)当a[i] != b[j]时，
	 * (a)插入操作：将b[j]插入到a[i]之后   result[i][j] = result[i][j-1]+1
	 * (b)替换操作：将a[i]替换成b[j]  result[i][j] = result[i-1][j-1] + 1
	 * (c)删除操作：将a[i]删掉   result[i][j] = result[i-1][j]+1
	 * 也就是说，经过上述三个步骤的操作之后，result[i][j]只和result[i-1][j-1],result[i-1][j]和result[i][j-1]有关
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	
	public int minDistance(String word1, String word2) {
    	if(word1 == null || word2 == null){
    		return -1;
    	}
		
    	
		int word1Len = word1.length();
		int word2Len = word2.length();
		
		int result[][] = new int[word1Len+1][word2Len+1];
		
		for(int i=0;i<=word1Len;i++){
			result[i][0] = i;
		}
		
		for(int j=0;j<=word2Len;j++){
			result[0][j] = j;
		}
		
		for(int i=1;i<=word1Len;i++){
			
			for(int j=1;j<=word2Len;j++){
				
				if(word1.charAt(i-1) == word2.charAt(j-1)){
					result[i][j] = result[i-1][j-1];
				}else{
					result[i][j] = Math.min(Math.min(result[i-1][j], result[i][j-1]), result[i-1][j-1])+1;
					
				}
			}
		}
		
		return result[word1Len][word2Len];
    	
    }
}
