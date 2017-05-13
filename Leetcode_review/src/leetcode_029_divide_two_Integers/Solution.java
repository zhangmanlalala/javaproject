package leetcode_029_divide_two_Integers;
/**
 * 
 * Divide two integers without using multiplication, division and mod operator.

	If it is overflow, return MAX_INT.  
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 还是看标准答案
	 * @param dividend
	 * @param divisor
	 * @return
	 */
    public int divide(int dividend, int divisor) {      
        int sign = 1;
        if((dividend>0 && divisor<0) || (dividend<0 && divisor>0))
        	sign = -1;
        
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        
        //小心边界情况，比如分母为0；分子为0或者分母>分子的情况
        if(ldivisor == 0) return Integer.MAX_VALUE;
        if(ldividend == 0 || ldividend<ldivisor) return 0;
        
        long lans = ldivide(ldividend,ldivisor);
        int ans;
        if(lans>Integer.MAX_VALUE){
        	ans = (sign == 1)?Integer.MAX_VALUE:Integer.MIN_VALUE;
        }else{
        	ans = (int)(sign * lans);
        }
        
        
        return ans;
        
    }
    
    
    /**
     * 
     * 子问题一定首先想到普通递归，分治，动态规划
     * @param ldividend
     * @param ldivisor
     * @return
     */
    private long ldivide(long ldividend,long ldivisor){
    	if(ldividend < ldivisor) return 0;
    	
    	
    	long sum = ldivisor;
    	long multiple = 1;
    	while((sum+sum)<ldividend){
    		sum += sum;
    		multiple +=multiple;
    	}
    	//接下来就会余留子问题，子问题可以用递归进行求解，太经典了。子问题一定想到递归
    	return multiple+ldivide(ldividend-sum,ldivisor);
    	
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().divide(5
				, 2));
	}
}
