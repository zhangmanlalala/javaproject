package 字符串匹配问题;
/**
 * 
 * 使用正则表达式
 * @author Administrator
 *
 */
public class LeetCode_87_ValidateNum {
	public boolean isNumber(String s) {
	       if(s == null || s.trim().length()==0)  
	            return false;  
	        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";  
	        if(s.trim().matches(regex))  
	            return true;  
	        return false;  
	 }
	
	
	public static void main(String[] args) {
		String s = "+";
		String regex = "[-+]?"; //代表负号或正号出现0次或者1次
		System.out.println(s.matches(regex));
		
		s = ".958";
		System.out.println(new LeetCode_87_ValidateNum().isNumber(s));
		
	}
}
