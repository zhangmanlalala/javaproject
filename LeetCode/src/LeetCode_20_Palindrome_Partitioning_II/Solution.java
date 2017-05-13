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
	 * ʹ�ö�̬�滮����С���⣬����ôû�뵽��
	 * ���Լ���״̬ת�Ʒ��̲��ԣ�ʱ��Խ��
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
     * ������׼�ⷨ
     * ��̬�滮��������С�и����������뵽��̬�滮��֮�������ô��ȡ�����⣬�Լ�Ѱ��״̬ת�Ʒ���
     * 
     * �����ַ����ĳ���Ϊlen
     * �����⣺��i��n-1����С�и�������Ϊcuts[i],��֪��cuts[n-1] = 0,��Ϊֻ��һ���ַ�����ʼ��cuts[i] = len-i-1,
     * ����ʼ��Ϊֻ��һ���ַ�����һ������
     * ״̬ת�Ʒ��̣���������[i,j]�ϵ��ִ��ǻ��ģ���i�����и�ֻҪ��j+1������С�и���+1��
     * cuts[i] = min{cuts[i],cuts[j+1]+1},
     * ��ôcuts[0]�������Ľ��
     * @param 
     * @return
     */
    /**
     * 
     * �ж��ǲ��ǻ����ַ����������뵽�ݹ飬�ڵݹ���ÿ�ζ�Ҫ���ĳ�������ǲ��ǻ��ģ������ظ��������⣬�����������ص���������뵽ʹ��
     * ��̬�滮
     * ��mat[i][j]����ʾ������[i,j]�ǲ��ǻ��ģ���¼�м���
     * �����mat[i+1][j-1]λtrue��ͬʱi,jλ���ַ���ȣ���mat[i][j] = true
     * @param s
     * @return
     */
    
    public int minCut2(String s){
    	int len = s.length();
    	boolean[][] matrix = new boolean[len][len];
    	int cuts[] = new int[len+1];
    	//��ʼ��cuts����ÿһ���ַ�������һ��
    	for(int i=0;i<=len;i++){
    		cuts[i] = len-i-1;
    	}
    	//���´�i������״̬
    	for(int i=len-1;i>=0;i--){
    		
    		for(int j=i;i<len;j++){
    			if((s.charAt(i) == s.charAt(j) &&(j-i<2)) || 
    			(s.charAt(i)==s.charAt(j) && matrix[i+1][j-1])){  //�жϻ����ַ���Ҳ�ö�̬�滮�����Һ�������Ƕ����һ�����Ҵ�δ�뵽��
    				matrix[i][j] = true;
    				cuts[i] = Math.min(cuts[i], cuts[j+1]+1);//��һ��ܾ���
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
