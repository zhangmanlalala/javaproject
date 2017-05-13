package _205_Isomorphic_Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given two strings s and t, determine if they are isomorphic.
	Two strings are isomorphic if the characters in s can be replaced to get t.
	All occurrences of a character must be replaced with another character while 
	preserving the order of characters. No two characters may map to the same character but a 
	character may map to itself.
	For example,
	Given "egg", "add", return true.
	
	Given "foo", "bar", return false.
	
	Given "paper", "title", return true.
	
	Note:
	You may assume both s and t have the same length.
 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean isIsomorphic(String s, String t) {
    	if(s==null && t==null){
    		return true;
    	}else if(s==null || t==null){
    		return false;
    	}
    	
    	if(s.length() != t.length()){
    		return false;
    	}
        Map<Character,Character> map1 = new HashMap<Character,Character>();
        Map<Character,Character> map2 = new HashMap<Character,Character>();
        
        for(int i=0;i<s.length();i++){
        	if(map1.containsKey(s.charAt(i))){
        		if(map1.get(s.charAt(i)) != t.charAt(i)){
        			return false;
        		}
        	}else{
        		map1.put(s.charAt(i), t.charAt(i));
        	}
        	
        	
        	if(map2.containsKey(t.charAt(i))){
        		if(map2.get(t.charAt(i)) != s.charAt(i)){
        			return false;
        		}
        		
        	}else{
        		map2.put(t.charAt(i), s.charAt(i));
        	}
        }
        
        return true;
    }
}
