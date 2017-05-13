package leetcode_050_Pow_x_n;
/**
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 递归、分治、动态规划求解子问题，这个题目典型的递归，求解子问题
	 * @param x
	 * @param n
	 * @return
	 * 
	 * 
	 * 很经典的，递归解法
	 */
	public double myPow(double x, int n) {
		if(x == 0 || x==1){
			return x;
		}
		if(x == -1){
			if(n%2 == 0){
				return 1;
			}else{
				return -1;
			}
		}
		
		if(n<0){
			n = -n;
			x = 1/x;
		}
		
		
		return recursive(x,n);
    }
    
    
    private double recursive(double x,int n){
    	if(n==0){
    		return 1;
    	}else if(n==1){
    		return x;
    	}
    	
    	double result = x;
    	int i=1;
    	for(;i+i<=n;i=i+i){
    		result = result*result;
    		if(result == 0){
    			return 0;
    		}
    	}
    	
    	return result*recursive(x,n-i);
    }
    
    
   public static void main(String[] args) {
	System.out.println(new Solution().recursive(3, 5));
}
}
