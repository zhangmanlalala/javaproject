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
 * 分析：

先把T中字母放在一个HashMap里，字母为key，个数为value；

遍历T，每碰到一个在HashMap里出现的字母，就将map里相应个数减1；

个数减1之后检查，如果此时个数已经小于0，说明之前已经减过，这个是重复的，如果此时个数不小0，说明这个是有效的，则count加1；

当count等于T的长度的时候，说明当前子串一定包含T，但不一定是最小，所以从子串头开始检查；

如果串头字母不在map里，则start后移1位，继续；

如果串头字母在map里，则map相应个数先加1；

加1后如果个数仍小于0，说明后面还有，当前不是关键字母，start后移1位；

加1后如果个数不小于0，说明这个是关键字母，没有这个字母子串就不满足题意，于是找到当前子串的最小window。

重复以上直到S串尾。

时间复杂度为O(2n) = O(n).
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 看标准解法，自己没解出来
	 * @param s
	 * @param t
	 * @return
	 */
	
	//我硬是没看懂
	 public String minWindow(String s, String t) {
	        String res="";
	        //检查参数
	        if(s==null || t==null || s.length()==0 || t.length()==0){
	        	return res;
	        }
	        //HashMap存放T中字母个数对
	        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
	        for(int i=0;i<t.length();i++){
	        	char key = t.charAt(i);
	        	if(map.containsKey(key)){
	        		map.put(key, map.get(key)+1);
	        	}else{
	        		map.put(key, 1);
	        	}
	        }
	        
	        
	        int start = 0;//记录子字符串的开头，
	        int count = 0;//记录已经找到的在T中的字母个数
	        int minLen = Integer.MAX_VALUE;
	        
	        //遍历s，先找到一个包含T中所有字母的子串，再从头缩小范围
	        for(int i=0;i<s.length();i++){
	        	char key = s.charAt(i);
	        	if(map.containsKey(key)){
	        		map.put(key, map.get(key)-1);
	        		if(map.get(key)>=0) //当减1之后还不小于0说明是有效的
	        			count++;
	        		//找到一个非最小集，从头缩小范围
	        		while(count == t.length()){
	        			char startkey = s.charAt(start);
	        			if(map.containsKey(startkey)){
	        				map.put(startkey, map.get(startkey)+1);
	        				if(map.get(startkey)>0){
	        					//找到关键开头
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
