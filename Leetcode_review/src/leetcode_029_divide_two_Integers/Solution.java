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
	 * ���ǿ���׼��
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
        
        //С�ı߽�����������ĸΪ0������Ϊ0���߷�ĸ>���ӵ����
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
     * ������һ�������뵽��ͨ�ݹ飬���Σ���̬�滮
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
    	//�������ͻ����������⣬����������õݹ������⣬̫�����ˡ�������һ���뵽�ݹ�
    	return multiple+ldivide(ldividend-sum,ldivisor);
    	
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().divide(5
				, 2));
	}
}
