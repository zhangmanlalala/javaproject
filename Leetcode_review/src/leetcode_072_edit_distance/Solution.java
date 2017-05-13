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
	 * �����Ŀ���Ǳ�׼�Ķ�̬�滮
	 * ������result[i][j]�����ַ���a[0,..,i]���ַ���b[0,..,j]����Сת������
	 * (1)��a[i] == b[j]ʱ��result[i][j] = result[i-1][j-1]
	 * (2)��a[i] != b[j]ʱ��
	 * (a)�����������b[j]���뵽a[i]֮��   result[i][j] = result[i][j-1]+1
	 * (b)�滻��������a[i]�滻��b[j]  result[i][j] = result[i-1][j-1] + 1
	 * (c)ɾ����������a[i]ɾ��   result[i][j] = result[i-1][j]+1
	 * Ҳ����˵������������������Ĳ���֮��result[i][j]ֻ��result[i-1][j-1],result[i-1][j]��result[i][j-1]�й�
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
