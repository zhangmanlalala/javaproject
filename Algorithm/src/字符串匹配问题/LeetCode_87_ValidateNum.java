package �ַ���ƥ������;
/**
 * 
 * ʹ��������ʽ
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
		String regex = "[-+]?"; //�����Ż����ų���0�λ���1��
		System.out.println(s.matches(regex));
		
		s = ".958";
		System.out.println(new LeetCode_87_ValidateNum().isNumber(s));
		
	}
}
