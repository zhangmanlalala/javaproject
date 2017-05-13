package leetcode_043_muiliply_Strings;
/**
 *Given two non-negative integers num1 and num2 represented as strings, 
 *return the product of num1 and num2. 
 * 
 * Note:

    The length of both num1 and num2 is < 110.
    Both num1 and num2 contains only digits 0-9.
    Both num1 and num2 does not contain any leading zero.
    You must not use any built-in BigInteger library or 
    convert the inputs to integer directly.

 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 有误
	 * @param num1
	 * @param num2
	 * @return
	 */
    public String multiply(String num1, String num2) {
    	if(num1==null || num2==null){
    		return "";
    	}
        long num1_int = 0;
        long num2_int = 0;
        int i=0;
        while(i<num1.length() && i<num2.length()){
        	num1_int = num1_int*10+(num1.charAt(i)-'0');
        	num2_int = num2_int*10+(num2.charAt(i)-'0');
        	i++;
        }
        
        if(i<num1.length()){
        	while(i<num1.length()){
            	num1_int = num1_int*10+(num1.charAt(i)-'0');
            	i++;
            }
        }else{
        	while(i<num2.length()){
            	num2_int = num2_int*10+(num2.charAt(i)-'0');
            	i++;
            }
        }
        
        
        return num1_int*num2_int+"";
    }
    
    /**
     * 
     * 还是看标准揭发吧
     * 
     */
    //很经典，记住乘法的这一大特性
    //很经典num1[i]*num2[j]最终会影响数组result[i+j],和result[i+j+1]的位置上数
    public String multiply2(String num1, String num2) {
    	int m = num1.length(),n = num2.length();
    	int[] pos = new int[m+n];
    	
    	for(int i=m-1;i>=0;i--){
    		for(int j=n-1;j>=0;i--){
    			int mu1 = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
    			int p1 = i+j,p2=i+j+1;
    			int sum = mu1+pos[p2];
    			
    			pos[p1] +=sum/10;
    			pos[p2] = (sum)%10;
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(int p:pos) if(!(sb.length() == 0 && p==0)) sb.append(p);
    	return sb.length() == 0 ? "0":sb.toString();
    }
    
    
}
