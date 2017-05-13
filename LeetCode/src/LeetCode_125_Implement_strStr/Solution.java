package LeetCode_125_Implement_strStr;


/**
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	 public String strStr(String haystack, String needle) {  
         
	        int len = needle.length();  
	        for(int i=0; i<=haystack.length()-len; i++){  
	            String sub = haystack.substring(i,i+len);  
	            if(sub.equals(needle)){  
	                return haystack.substring(i);  
	            }  
	        }  
	        return null;  
	    }  
}
