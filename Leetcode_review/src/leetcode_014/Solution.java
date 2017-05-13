package leetcode_014;
/**
 * 
 *Write a function to find the longest common prefix string amongst an array of strings.  
 * @author Administrator
 *
 */
public class Solution {
	
    public String longestCommonPrefix(String[] strs) {
    	if(strs== null || strs.length==0){
    		return "";
    	}
        StringBuilder sb = new StringBuilder();
        
        char[] c = strs[0].toCharArray();
        for(int j=0;j<c.length;j++){
        	char temp = c[j];
        	int i=1;
	        for(;i<strs.length;i++){
	        	if(strs[i].length()<=j || strs[i].charAt(j) != temp){
	        		break;
	        	}
	        }
	        
	        if(i<strs.length){
	        	break;
	        }else{
	        	sb.append(temp);
	        }
        }
        
        return sb.toString();
        
    }
}
