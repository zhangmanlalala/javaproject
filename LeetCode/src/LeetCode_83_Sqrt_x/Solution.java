package LeetCode_83_Sqrt_x;


/**
 * 
 * Implement int sqrt(int x).
  Compute and return the square root of x.
 * 
 * @author Administrator
 *
 */
/**
 * 
 * ţ�ٵ�������ƽ������û����
 * @author Administrator
 *
 */
public class Solution {
	 public int sqrt(int x) {  
	        if(x==0) return 0;  
	        double last = 0;  
	        double res = 1;  
	        while(res != last){  
	            last = res;  
	            res = (res + x/res)/2;  
	        }  
	        return (int)res;  
	    }  
}
