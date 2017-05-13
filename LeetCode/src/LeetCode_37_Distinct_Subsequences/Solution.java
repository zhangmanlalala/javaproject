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
	 * 求T作为S的子序列出现的次数，即字串是T，母串是S
	 * 网上大牛的解法才知道用DP，我也想到了用这个，但是找不到状态转移方程：
	 * 设母串长度为j，字串长度为i，求字串在母串中出现的次数，记为table[i][j],
	 * 1:如果字串和母串作为最后一个字符不同，则table[i][j] = table[i][j-1],就等于长度为i的字串在长度为j-1
	 * 的母串中出现的次数；
	 * 2：如果字串和母串的最后一个字符相同，则table[i][j] = table[i-1][j-1] + table[i][j-1],
	 * 即长度为i-1的字串在长度为j-1的母串中出现的次数+长度为i的字串在长度为j-1的母串中出现的次数
	 * 
	 * 
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	
	/**
	 * 本身来讲，长度0的子串在长度i母串中出现的次数没有什么意义，但我们要考虑递推关系

		设长度为1的子串刚刚好和长度为i母串的最后一个字符相等，并且是第一次出现，
		
		则 table[1][i] 应该等于1，由于之前都没有出现过，table[1][i-1] 应该等于0，根据递推关系
		
		table[1][i] = table[1][i-1] + table[0][i-1] ，所以 table[0][i-1] 应该是0，
		由此类推，table[0][0]到table[0][S.length()-1]都应该是1.
	 * @param s
	 * @param t
	 * @return
	 */
	public int numDistinct(String s, String t) {
		int[][] table = new int[t.length()+1][s.length()+1];
		for(int i=0;i<s.length();i++){
			table[0][i] = 1;
		}
		//dp算法
		
		for(int i=1;i<=t.length();i++){
			for(int j=1;j<=s.length();j++){
				if(t.charAt(i-1) == s.charAt(j-1)){//长度为i和j的字串的最后一个字符的位置为i-1和j-1
					table[i][j] = table[i][j-1]+table[i-1][j-1];
				}else{
					table[i][j] = table[i][j-1];
				}
			}
		}
		
		return table[t.length()][s.length()];
    }
	/**
	 * 回溯算法，显然搞不定，时间越界
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
				//向前搜索
				dfs(s,i+1,t,startt+1,sb);
				
				//恢复现场
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
