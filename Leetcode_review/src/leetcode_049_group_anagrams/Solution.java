package leetcode_049_group_anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note: All inputs will be in lower-case.
 * 
 * @author Administrator
 *
 */
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
    	if(strs== null || strs.length==0){
    		return new ArrayList<List<String>>();
    	}
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        for(int j=0;j<strs.length;j++){
        	String temp = sortedString(strs[j]);
        	if(map.containsKey(temp)){
        		List<String> tp = map.get(temp);
        		tp.add(strs[j]);
        		map.put(temp, tp);
        	}else{
        		List<String> tp = new ArrayList<String>();
        		tp.add(strs[j]);
        		map.put(temp, tp);
        	}
        	
        	
        }
        
        List<List<String>> result = new ArrayList<List<String>>(map.values());
        
        return result;
    }
    
    
    
    private boolean isAnangrams(String str1,String str2){
    	if(str1.length() !=str2.length()){
    		return false;
    	}
    	int i=0,idx=0;
    	while(i<str2.length()){
    		idx = str1.indexOf(str2.charAt(i));
    		if(idx>-1){
    			str1 = str1.substring(0,idx)+str1.substring(idx+1);
    			i++;
    		}else{
    			break;
    		}
    	}
    	
    	if(i<str2.length()){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    private String sortedString(String str){
    	if(str.length()==0){
    		return str;
    	}
    	StringBuilder sb = new StringBuilder();
    	sb.append(str.charAt(0));
    	for(int i=1;i<str.length();i++){
    		  int j=i-1;
              for(;j>=0;j--){
            	  if(sb.charAt(j)<str.charAt(i)){
            		  sb.insert(j+1, str.charAt(i));
            		  break;
            	  }
              }
              if(j<0){
            	  sb.insert(0, str.charAt(i));
              }
    	}
    	
    	return sb.toString();
    }
    
    public static void main(String[] args) {
    	System.out.println(new Solution().sortedString("tea"));
		System.out.println(new Solution().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
	}
}
