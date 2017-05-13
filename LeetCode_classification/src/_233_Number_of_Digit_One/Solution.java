package _233_Number_of_Digit_One;

public class Solution {
	/**
	 * 
	 * 需要记住
	 * @param n
	 * @return
	 */
    public int countDigitOne(int n) {
    	if(n<1){
    		return 0;
    	}
       int[] result = new int[n+1];
       result[0] = 0;
       result[1] = 1;
       int temp = 0;
       int i = 0;
       for(int j=2;j<=n;j++){
    	   
    	   temp = 0;
    	   
    	   i = j;
    	   while(i>0){
    		   if((i%10) == 1){
    			   temp++;
    		   }
    		   
    		   i = i/10;
    	   }
    	   
    	   result[j] = result[j-1]+temp;
       }
       
       return result[n];
    }
    
    /**
     * 
     * 标准解法
     */
    
    public int countDigitOne2(int n){
    	   if (n <= 0) return 0;
    	    int q = n, x = 1, ans = 0;
    	    do {
    	        int digit = q % 10;
    	        q /= 10;
    	        ans += q * x;
    	        if (digit == 1) ans += n % x + 1;
    	        if (digit >  1) ans += x;
    	        x *= 10;
    	    } while (q > 0);
    	    return ans;
    }
}
