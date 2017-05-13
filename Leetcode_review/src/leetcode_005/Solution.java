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
	 * ��ʹ�ö�̬�滮��һ��,�ҵķ����������ҵĲ��ԣ����˷���֮�������
	 * boolean result[i][j]�����±�������i��j���ִ��ǲ��ǻ����ַ���
	 * �ҳ�״̬ת�Ʒ���
	 * result[i][j] = result[i+1][j-1] && s(i) == s(j)
	 * ��ʼ������result[i][i] = true,result[i][i+1] = s(i) == s(j)
	 * 
	 * @param s
	 * @return
	 */
	/**
	 * 
	 * �÷���Ҳ��ʱ
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
    	
    	for(int i=2;i<s.length();i++){//i��ʾi��j֮������ľ���
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
     * ����׼��:û�뵽������ٷ���������һЩ
     * ����ÿһ��λ�ã����������ҶԳƵ��ִ���������Գ��ִ���
		ע�⣬�Գ������������
	    1��aba�����
	    2��abba�����
     * @param args
     */
    private int lo,maxLen;
    public String longestPalindrome2(String s) {
    	int len = s.length();
    	if(len<2)
    		return s;
    	for(int i=0;i<len-1;i++){
    		extendPalindrome(s,i,i);//������������
    		extendPalindrome(s,i,i+1);//����ż������
    	}
    	
    	return s.substring(lo, lo+maxLen);
    	
    }
    private void extendPalindrome(String s,int j,int k){
    	while(j>=0 && k<s.length() && s.charAt(j) == s.charAt(k)){
    		j--;
    		k++;
    	}
    	if(maxLen<k-j-1){//����Ϊ(k-1-(j+1) + 1)
    		lo = j+1;
    		maxLen = k-j-1;
    	}
    }

    public static void main(String[] args) {
		System.out.println(new Solution().longestPalindrome("ccc"));
	}
}
