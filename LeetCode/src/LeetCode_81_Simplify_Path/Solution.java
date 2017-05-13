package LeetCode_81_Simplify_Path;

import java.util.ArrayList;

/**
 * 
 *Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".

In this case, you should ignore redundant slashes and return "/home/foo". 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public String simplifyPath(String path) {  
        
        String[] items = path.split("/");  
        ArrayList<String> list = new ArrayList<String>();  
        for(String item : items){  
            // //的情况和/.的情况，忽略  
            if(item.length()==0 || item.equals("."))  
                continue;  
            if(item.equals("..")){  
                if(!list.isEmpty())  
                    list.remove(list.size()-1);  
            }else  
                list.add(item);  
        }  
        StringBuffer sb = new StringBuffer();  
        for(String item : list){  
            sb.append("/");  
            sb.append(item);  
        }  
        String ans = sb.length()==0 ? "/" : sb.toString();  
        return ans;  
    }  
}
