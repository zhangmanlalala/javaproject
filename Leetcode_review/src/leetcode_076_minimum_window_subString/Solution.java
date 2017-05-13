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
	 * 看标准答案
	 * @param s
	 * @param t
	 * @return
	 */
	
	/**
	 * 
	 * 思路，用map维护一个满足条件的动态窗口，不断比较窗口的大小
	 * @param s
	 * @param t
	 * @return
	 */
    public String minWindow(String s, String t) {
        
    	
    	//标准答案
    	if(s.length() == 0 || t.length()==0 || s.length()<t.length()){
    		return "";
    	}
    	
    	int count=t.length(),left = 0 ,start = 0,end = s.length()-1;
    	int minLen = Integer.MAX_VALUE;
    	
    	//required中的任何一个字符c出现的次数i，如果i>0,则表示还需要更多，i=0表示刚好满足，i<0表示还有富裕的
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
    			if(required.get(c)>0){//如果该字符是富裕的，则不能影响count
    				count--;
    			}
    			required.put(c, required.get(c)-1);
    		}
    		
    		
    	    
    	    if(count == 0){//count只在第一个找到满足的窗口时使用，后面就不在用了，后面就维护一个满足条件的窗口
    	    	
    	    	char head = s.charAt(left);
    	    	
    	    	while(!required.containsKey(head) || required.get(head)<0){//当left位置的字符不是我们需要的，或者有富余
                    if(required.containsKey(head)){//如果位置left处的字符有富余的，减去
                    	int num = required.get(head);
                    	required.put(head, num+1);//减掉富余中的一个
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
