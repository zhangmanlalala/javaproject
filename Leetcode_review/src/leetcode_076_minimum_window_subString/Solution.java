package leetcode_076_minimum_window_subString;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 *  Given a string S and a string T, find the minimum window in S 
 *  which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"

Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, 
return the empty string "".

If there are multiple such windows, you are guaranteed that there 
will always be only one unique minimum window in S. 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * ����׼��
	 * @param s
	 * @param t
	 * @return
	 */
	
	/**
	 * 
	 * ˼·����mapά��һ�����������Ķ�̬���ڣ����ϱȽϴ��ڵĴ�С
	 * @param s
	 * @param t
	 * @return
	 */
    public String minWindow(String s, String t) {
        
    	
    	//��׼��
    	if(s.length() == 0 || t.length()==0 || s.length()<t.length()){
    		return "";
    	}
    	
    	int count=t.length(),left = 0 ,start = 0,end = s.length()-1;
    	int minLen = Integer.MAX_VALUE;
    	
    	//required�е��κ�һ���ַ�c���ֵĴ���i�����i>0,���ʾ����Ҫ���࣬i=0��ʾ�պ����㣬i<0��ʾ���и�ԣ��
    	Map<Character,Integer> required = new HashMap<Character,Integer>();
    	
    	for(int i=0;i<t.length();i++){
    		char c = t.charAt(i);
    		required.put(c, required.containsKey(c)?required.get(c)+1:1);
    	}
    	
    	
    	
    	for(int i=0;i<s.length();i++){
    		char c = s.charAt(i);
    		
    		if(!required.containsKey(c)){
    			continue;
    		}else{ 			
    			if(required.get(c)>0){//������ַ��Ǹ�ԣ�ģ�����Ӱ��count
    				count--;
    			}
    			required.put(c, required.get(c)-1);
    		}
    		
    		
    	    
    	    if(count == 0){//countֻ�ڵ�һ���ҵ�����Ĵ���ʱʹ�ã�����Ͳ������ˣ������ά��һ�����������Ĵ���
    	    	
    	    	char head = s.charAt(left);
    	    	
    	    	while(!required.containsKey(head) || required.get(head)<0){//��leftλ�õ��ַ�����������Ҫ�ģ������и���
                    if(required.containsKey(head)){//���λ��left�����ַ��и���ģ���ȥ
                    	int num = required.get(head);
                    	required.put(head, num+1);//���������е�һ��
                    }
                    
                    left++;
                    head = s.charAt(left);    	    		
    	    	}
    	    	
    	    	
    	    	if(minLen>i-left+1){
    	    		minLen = i-left+1;
    	    		start = left;
    	    		end = i;
    	    	}
    	    	
    	    	
    	    }
    	}
    	
    	
    	if(count == 0){
    		return s.substring(start, end+1);
    	}else{
    		return "";
    	}
    	
    }
    
 
    
    
    public static void main(String[] args) {
		System.out.println(new Solution().minWindow("bdab", "ab"));
	}
    
}
