package _159_Longest_Substring_with_At_Most_Two_Distinct_Characters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Longest Substring with At Most Two Distinct Characters

Given a string, find the length of the longest substring T that contains 
at most 2 distinct characters.

For example, Given s = ��eceba��,

T is "ece" which its length is 3.

 

����Longest Substring Without Repeating Characters���տ���
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * ��̬�滮���ҵ������⣬�������ص������뵽ʹ�ö�̬�滮
	 * 
	 * ��̬�滮�Ľⷨʱ�临�ӶȽϸ�
	 * ���״̬ת�Ʒ���
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if(s==null){
			return 0;
		}
		if(s.length()<=2){
			return s.length();
		}
		int result[] = new int[s.length()+1];
		result[0] = 0;
		result[1] = 1;
		result[2] = 2;
		Set<Character> set = new HashSet<Character>();
		for(int i=2;i<s.length();i++){
			
			int j=i;
			for(;j>=0;j--){
				set.add(s.charAt(j));
				if(set.size()>2){					
					break;
				}
			}
			set.clear();
			if(j<0){
				result[i+1] = Math.max(i+1, result[i]);
			}else{
				result[i+1] = Math.max(i-j, result[i]);
			}
		}
		
		return result[s.length()];
	}
	
	
	/**
	 * 
	 * 
	 * ��׼�ⷨ����HashTable��¼��ǰ�����ַ�����Ŀ����������µ��ַ����ƶ�startָ���λ�ã�֪���µ�startλ�õ���ǰλ��
	 * ֻ���������ַ�
	 * 
	 * ����˼��ʹ����i��ǰ�ƶ�ʱʹstart��i֮�䲻ͬ���ַ�����ʼ��ά����С�ڵ���2
	 * @param args
	 */
	
	
	//�ܾ��䣬���֮�¶�̬�滮�Ľⷨʱ�临�ӶȽϸ�
	 public int lengthOfLongestSubstringTwoDistinct2(String s) {  
		 if(s== null || s.length() == 0){
			 return 0;
		 }
		 Map<Character,Integer> map = new HashMap<Character,Integer>();
		 int start = 0;
		 int max = 0;
		 
		 //����˼��ʹ����i��ǰ�ƶ�ʱʹstart��i֮�䲻ͬ���ַ�����ʼ��ά����С�ڵ���2
		 for(int i=0;i<s.length();i++){
			 char c = s.charAt(i);
			 if(map.containsKey(c)){
				 map.put(c, map.get(c)+1);
			 }else if(map.size()<2){
				 map.put(c, 1);
			 }else{
				while((map.size()==2) && start<i){
					 char temp = s.charAt(start);
					 int x = map.get(temp);
					 --x;
					 if(x==0) {//startλ�õ��ַ�ֻ����һ�Σ���start��ǰ�ƶ�һλʱ��ֱ�Ӵ�map�г�ȥ����ַ�
						 map.remove(s.charAt(start));
					 }else {//startλ�õ��ַ����ֳ���һ�Σ���start��ǰ�ƶ�ʱ�����ַ��ĳ��ִ���Ҫ-1��Ȼ����map�и���
						 map.put(s.charAt(start), x);
					 }
					 ++start;	//start��ǰ�ƶ���ֱ��map��sizeΪ1����Ȼ��ʱstart<iҪ����				 
					 
				}
				 
				map.put(c, 1); //��map��size��Ϊ1�󣬴�ʱ���Խ�c����map��
				 
			 }
			 max = Math.max(max, i-start+1);
		 }
		 
		 return max;
	 }
	
	public static void main(String[] args) {
		System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("abcdedcba"));
	}
	
}
