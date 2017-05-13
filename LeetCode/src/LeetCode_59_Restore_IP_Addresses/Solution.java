package LeetCode_59_Restore_IP_Addresses;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * Given a string containing only digits, restore it by returning all possible 
 * valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter) 
 * @author Administrator
 *
 */
public class Solution {
	
	
	
	
	/**
	 * 
	 * 这个题回溯算法做不出来
	 * @param s
	 * @return
	 */
	
	/**
	 * 
	 * 标准答案
	 * @param s
	 * @return
	 */
	public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if(s==null || s.length()<4 || s.length()>12) return result;
        
        for(int i=1;i<4;i++){
        	String first = s.substring(0, i);
        	if(!isValid(first))
        		continue;
        	
        	for(int j=1;j<4 && i+j<s.length();j++){
        		String second = s.substring(i,i+j);
        		if(!isValid(second))
        			continue;
        		for(int k=1;k<4 && i+j+k<s.length();k++){
        			String third = s.substring(i+j,i+j+k);
        			String fourth = s.substring(i+j+k,s.length());
        			if(!isValid(third) || !isValid(fourth))
        				continue;
        			
        			String ip=first+"."+second+"."+third+"."+fourth;
        			result.add(ip);
        					
        		}
        	}
        }
        
        return result;
    }	
	

	private boolean isValid(String s){
		if(s.length()>1 && s.charAt(0)=='0')
			return false;
		int ip=Integer.parseInt(s);
		if(ip>=0 && ip<=255)
			return true;
		
		
		
		return false;
	}
	

}
