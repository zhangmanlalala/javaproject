package _201_Bitwise_AND_of_Numbers_Range;
/**
 *Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers 
 *in this range, inclusive.
 *For example, given the range [5, 7], you should return 4.  
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 越界解法
	 * @param m
	 * @param n
	 * @return
	 */
    public int rangeBitwiseAnd2(int m, int n) {
    	if(m==n){
    		return m;
    	}
    	int temp = 2147483647;

        	int j=m;
        	for(;j<=n;j++){
        		temp = (temp&j);
        		if(temp == 0){
        			break;
        		}
        	}
        
        
        return temp;
    }
    /**
     * 
     * 标准解法   代码中，通过不断地右移直到两个数字相等，然后再左移相同的位数，这样做的效果
     * 其实就是将位置不相同的都置0
     * @param args
     */
    
    public int rangeBitwiseAnd(int m,int n){
    	int bit = 0;
    	while(m!=n){
    		m>>=1;
        	n>>=1;
        	bit++;
    	}
    	
    	return m<<bit;
    }
    
    
    
    public static void main(String[] args) {
		System.out.println(new Solution().rangeBitwiseAnd(5, 7));
		System.out.println(1<<2);
	}
}
