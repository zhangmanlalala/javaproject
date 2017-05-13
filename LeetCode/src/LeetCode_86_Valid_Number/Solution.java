package LeetCode_86_Valid_Number;

/**
 * 
 *Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false

"2e10" => true

Note: It is intended for the problem statement to be 
ambiguous. You should gather all requirements up front 
before implementing one. 
 * @author Administrator
 *
 */

/**
 * 
 * 解决方法，使用正则表达式
 * @author Administrator
 *
 */
public class Solution {
	
	 public boolean isNumber(String s) {
	       if(s == null || s.trim().length()==0)  
	            return false;  
	        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";  
	        if(s.trim().matches(regex))  
	            return true;  
	        return false;  
	 }
}
