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
 * �ҵ���⣺�������������
 * @author Administrator
 *
 */

/**
 * 
 * �÷�����ʧ�ܵ�
 * ���ܹ��������е����
 * @author Administrator
 *
 */
public class Solution {
	//word2��ת��Ŀ��
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
//        			if(gap1-gap2 == 0){//���ֻ���滻����ʲô��������
//        				stepNum = stepNum+gap1-1;
//        			}else if(gap1-gap2>0){//����ȣ�Ҫ��ɾ�������滻������gap1��
//        				stepNum = stepNum+gap1-1;
//        			}else{//����ȣ�Ҫ���滻��������ַ����ܹ���gap2��
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
//        if(gap1-gap2 == 0){//���ֻ���滻����ʲô��������
//			stepNum = stepNum+gap1;
//		}else if(gap1-gap2>0){//����ȣ�Ҫ��ɾ�������滻������gap1��
//			stepNum = stepNum+gap1;
//		}else{//����ȣ�Ҫ���滻��������ַ����ܹ���gap2��
//			stepNum = stepNum+gap2;
//		}
//        
//        return stepNum;
//        
//    }
//	
	/**
	 * �����������⣬���뵽��̬�滮�㷨
	 * ���ö�̬�滮�㷨���Ե����ϵؼ������Ž⣬������������ڶ����ֵ��һ���ֵ���
	 * @param word1
	 * @param word2
	 * @return
	 */
	
	/**
	 * 
	 * ״̬ת�Ʒ��̣�

	��Ϊ������ת�Ʒ�ʽ��replace, insert, delete,
	
	���i��jλ�����ַ���ͬ������Ҫ������dp[i+1][j+1] = dp[i][j];
	
	�������ͬ����������ת�Ʒ�ʽ��
	
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
		//ת�ƾ��󣬼�¼��Word1ǰi���ַ���Word2ǰj���ַ�ת������Ҫ�Ĳ���
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
