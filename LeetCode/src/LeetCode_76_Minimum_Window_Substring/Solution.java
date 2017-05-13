package LeetCode_76_Minimum_Window_Substring;

import java.util.HashMap;

/**
 * 
 * Given a string S and a string T, find the minimum window in

S which will contain all the characters in T in complexity O(n).

For example,

S = "ADOBECODEBANC"

T = "ABC"

Minimum window is "BANC".

Note:

If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 * 
 * @author Administrator
 *
 */

/**
 * ������

�Ȱ�T����ĸ����һ��HashMap���ĸΪkey������Ϊvalue��

����T��ÿ����һ����HashMap����ֵ���ĸ���ͽ�map����Ӧ������1��

������1֮���飬�����ʱ�����Ѿ�С��0��˵��֮ǰ�Ѿ�������������ظ��ģ������ʱ������С0��˵���������Ч�ģ���count��1��

��count����T�ĳ��ȵ�ʱ��˵����ǰ�Ӵ�һ������T������һ������С�����Դ��Ӵ�ͷ��ʼ��飻

�����ͷ��ĸ����map���start����1λ��������

�����ͷ��ĸ��map���map��Ӧ�����ȼ�1��

��1�����������С��0��˵�����滹�У���ǰ���ǹؼ���ĸ��start����1λ��

��1�����������С��0��˵������ǹؼ���ĸ��û�������ĸ�Ӵ��Ͳ��������⣬�����ҵ���ǰ�Ӵ�����Сwindow��

�ظ�����ֱ��S��β��

ʱ�临�Ӷ�ΪO(2n) = O(n).
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * ����׼�ⷨ���Լ�û�����
	 * @param s
	 * @param t
	 * @return
	 */
	
	//��Ӳ��û����
	 public String minWindow(String s, String t) {
	        String res="";
	        //������
	        if(s==null || t==null || s.length()==0 || t.length()==0){
	        	return res;
	        }
	        //HashMap���T����ĸ������
	        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
	        for(int i=0;i<t.length();i++){
	        	char key = t.charAt(i);
	        	if(map.containsKey(key)){
	        		map.put(key, map.get(key)+1);
	        	}else{
	        		map.put(key, 1);
	        	}
	        }
	        
	        
	        int start = 0;//��¼���ַ����Ŀ�ͷ��
	        int count = 0;//��¼�Ѿ��ҵ�����T�е���ĸ����
	        int minLen = Integer.MAX_VALUE;
	        
	        //����s�����ҵ�һ������T��������ĸ���Ӵ����ٴ�ͷ��С��Χ
	        for(int i=0;i<s.length();i++){
	        	char key = s.charAt(i);
	        	if(map.containsKey(key)){
	        		map.put(key, map.get(key)-1);
	        		if(map.get(key)>=0) //����1֮�󻹲�С��0˵������Ч��
	        			count++;
	        		//�ҵ�һ������С������ͷ��С��Χ
	        		while(count == t.length()){
	        			char startkey = s.charAt(start);
	        			if(map.containsKey(startkey)){
	        				map.put(startkey, map.get(startkey)+1);
	        				if(map.get(startkey)>0){
	        					//�ҵ��ؼ���ͷ
	        					int len = i-start+1;
	        					if(len<minLen){
	        						minLen = len;
	        						res = s.substring(start, i+1);
	        					}
	        					count--;
	        				}
	        			}
	        			
	        			start++;
	        		}
	        		
	        	}
	        }
	        
	        return res;
	 }
	
	
	
}
