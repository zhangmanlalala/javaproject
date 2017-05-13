package _242_Valid_Anagram;

import java.util.ArrayList;
import java.util.List;

/**
 *Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution 
to such case? 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean isAnagram(String s, String t) {
    	if(s==null && t==null){
    		return true;
    	}else if(s==null || t==null){
    		return false;
    	}
    	if(s.length()!=t.length()){
    		return false;
    	}
    	while(s.length()!=0 && t.length()!=0){
		   char te =  s.charAt(0);
		   s = s.replaceAll(te+"", "");
		   t = t.replaceAll(te+"", "");
		   if(s.length()!=t.length()){
			   return false;
		   }
	
    	}    	
       if(s.length() == t.length()){
    	   return true;
       }else{
    	   return false;
       }
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().isAnagram("rat", "car"));
		
	}
}
