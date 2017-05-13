package LeetCode_37_Distinct_Subsequences;
/**
 * 
 *Given a string S and a string T, count the number of distinct subsequences ofT inS.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:

S = "rabbbit", T = "rabbit"

Return 3. 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * ��T��ΪS�������г��ֵĴ��������ִ���T��ĸ����S
	 * ���ϴ�ţ�Ľⷨ��֪����DP����Ҳ�뵽��������������Ҳ���״̬ת�Ʒ��̣�
	 * ��ĸ������Ϊj���ִ�����Ϊi�����ִ���ĸ���г��ֵĴ�������Ϊtable[i][j],
	 * 1:����ִ���ĸ����Ϊ���һ���ַ���ͬ����table[i][j] = table[i][j-1],�͵��ڳ���Ϊi���ִ��ڳ���Ϊj-1
	 * ��ĸ���г��ֵĴ�����
	 * 2������ִ���ĸ�������һ���ַ���ͬ����table[i][j] = table[i-1][j-1] + table[i][j-1],
	 * ������Ϊi-1���ִ��ڳ���Ϊj-1��ĸ���г��ֵĴ���+����Ϊi���ִ��ڳ���Ϊj-1��ĸ���г��ֵĴ���
	 * 
	 * 
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	
	/**
	 * ��������������0���Ӵ��ڳ���iĸ���г��ֵĴ���û��ʲô���壬������Ҫ���ǵ��ƹ�ϵ

		�賤��Ϊ1���Ӵ��ոպúͳ���Ϊiĸ�������һ���ַ���ȣ������ǵ�һ�γ��֣�
		
		�� table[1][i] Ӧ�õ���1������֮ǰ��û�г��ֹ���table[1][i-1] Ӧ�õ���0�����ݵ��ƹ�ϵ
		
		table[1][i] = table[1][i-1] + table[0][i-1] ������ table[0][i-1] Ӧ����0��
		�ɴ����ƣ�table[0][0]��table[0][S.length()-1]��Ӧ����1.
	 * @param s
	 * @param t
	 * @return
	 */
	public int numDistinct(String s, String t) {
		int[][] table = new int[t.length()+1][s.length()+1];
		for(int i=0;i<s.length();i++){
			table[0][i] = 1;
		}
		//dp�㷨
		
		for(int i=1;i<=t.length();i++){
			for(int j=1;j<=s.length();j++){
				if(t.charAt(i-1) == s.charAt(j-1)){//����Ϊi��j���ִ������һ���ַ���λ��Ϊi-1��j-1
					table[i][j] = table[i][j-1]+table[i-1][j-1];
				}else{
					table[i][j] = table[i][j-1];
				}
			}
		}
		
		return table[t.length()][s.length()];
    }
	/**
	 * �����㷨����Ȼ�㲻����ʱ��Խ��
	 * @param s
	 * @param starts
	 * @param t
	 * @param startt
	 * @param sb
	 */
	public void dfs(String s,int starts,String t,int startt,StringBuilder sb){
		
		if(sb.toString().equals(t)){
			//num++;
			return;
		}
		for(int i=starts;i<s.length();i++){
			
			if(s.charAt(i) == t.charAt(startt)){
				sb.append(t.charAt(startt));
				//��ǰ����
				dfs(s,i+1,t,startt+1,sb);
				
				//�ָ��ֳ�
				sb.deleteCharAt(sb.length()-1);
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		String s="rarabbbit";
		String t = "rabbit";
		System.out.println(new Solution().numDistinct(s, t));
	}
}
