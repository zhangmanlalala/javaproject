package _161_One_Edit_Distance;
/**
 * One Edit Distance Total Accepted: 709 Total Submissions: 3097

Given two strings S and T, determine if they are both one edit distance apart.


[分析]

delete, add, replace 三种都是 One edit distance. delete 和add 两个string长度差1, replace相同长度.

[注意] 
 * 
 * 
 * @author Administrator
 *
 */


/**
 * 
 * 
 * 分析：
 * 如果两个字符串只有一个编辑距离，则只有两种情况：

   1 两个字符串一样长的时候，说明有一个替换操作，我们只要看对应位置是不是只有一个字符不一样就行了

   2一个字符串比另一个长1，说明有个增加或删除操作，我们就找到第一个对应位置不一样的那个字符，如果较长字符串在那个字符之后的部分和较短字符串那个字符及之后的部分是一样的，则符合要求

   3如果两个字符串长度差距大于1，肯定不对

 * @author Administrator
 *
 */
public class Solution {
	public boolean isOneEditDistance(String s,String t){
		int m=s.length();
		int n = t.length();
		if(m==n){
			return isOneModified(s,t);
		}
		if(m-n == 1) return isOneDeleted(s,t);
		if(n-m == 1) return isOneDeleted(t,s);
		
		//长度差大于2直接返回false
		return false;
	}
	
	
	private boolean isOneModified(String s,String t){
		boolean modified = false;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i) != t.charAt(i)){
				if(modified) return false;
				modified = true;
			}
		}
		
		return modified;
	}
	
	
	public boolean isOneDeleted(String longer,String shorter){
		//找到第一个不一样的字符，看后面是否都一样
		boolean first = true;
		for(int i=0;i<longer.length();i++){
			if(longer.charAt(i) != shorter.charAt(i) && first){
				return longer.substring(i+1).equals(shorter.substring(i));
			}
			
		}
		
		return true;
	}
}
