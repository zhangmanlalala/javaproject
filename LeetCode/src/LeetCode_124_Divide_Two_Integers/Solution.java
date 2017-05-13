package LeetCode_124_Divide_Two_Integers;

/**
 * 
 * Divide two integers without using multiplication, 
 * division and mod operator.
 * @author Administrator
 * ʵ��������ʽdivisor*(2^n1+2^(n2)+...+2^0) = dividend;
 * ��һ���������������numʹ����ʽ�պô���dividend����ô��������n1= num-1;
 * �ڶ��������(2^n1+2^(n2)+...+2^0�����ɣ�ʹ��dividend-2^n1����������
 *
 */

public class Solution {
	public int divide(int dividend,int divisor){
		boolean sign = (dividend<0 && divisor <0) || (dividend>0 && divisor>=0);
		long dividendL = Math.abs((long)dividend);
		long divisorL = Math.abs((long)divisor);
		int res = 0;
		int move = 0;
		long te = 0;
		while(true){
			te = (divisorL << move);
			if((divisorL << move)>dividendL){//found the
				break;
			}
			move++;
		}
		
		long temp = dividendL;
		for(int i=move-1;i>=0;i--){
			long newDivisor = divisorL<<i;
			if(temp-newDivisor<0)
				continue;
			temp -=newDivisor;
			if(i ==0){
				res +=1;
			}else{
				res+=2 << (i-1);
			}
		}
		if(!sign)
			res = -res;
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(new Solution().divide(6, 5));
	}
}
