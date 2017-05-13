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
	 * ֱ�ӿ��ı�׼��
	 * ˼·��
	 * ��ĳ��char��ʼ��������չ(�������ߵ��ַ����)�������һֱ��չ���ַ�����ͷ��
	 * ��ĩβ�����µ�reverse���ټӵ�ԭ�ַ�����ͷ������
	 * 1.�����ַ����м俪ʼѡ�������ҵ��ļ�Ϊ���
	 * 2.�����ַ�����Ϊ1����Ҳ����Ϊ2��
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
