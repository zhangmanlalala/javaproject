package _161_One_Edit_Distance;
/**
 * One Edit Distance Total Accepted: 709 Total Submissions: 3097

Given two strings S and T, determine if they are both one edit distance apart.


[����]

delete, add, replace ���ֶ��� One edit distance. delete ��add ����string���Ȳ�1, replace��ͬ����.

[ע��] 
 * 
 * 
 * @author Administrator
 *
 */


/**
 * 
 * 
 * ������
 * ��������ַ���ֻ��һ���༭���룬��ֻ�����������

   1 �����ַ���һ������ʱ��˵����һ���滻����������ֻҪ����Ӧλ���ǲ���ֻ��һ���ַ���һ��������

   2һ���ַ�������һ����1��˵���и����ӻ�ɾ�����������Ǿ��ҵ���һ����Ӧλ�ò�һ�����Ǹ��ַ�������ϳ��ַ������Ǹ��ַ�֮��Ĳ��ֺͽ϶��ַ����Ǹ��ַ���֮��Ĳ�����һ���ģ������Ҫ��

   3��������ַ������Ȳ�����1���϶�����

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
		
		//���Ȳ����2ֱ�ӷ���false
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
		//�ҵ���һ����һ�����ַ����������Ƿ�һ��
		boolean first = true;
		for(int i=0;i<longer.length();i++){
			if(longer.charAt(i) != shorter.charAt(i) && first){
				return longer.substring(i+1).equals(shorter.substring(i));
			}
			
		}
		
		return true;
	}
}
