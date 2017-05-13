package _231_Power_of_Two;

public class Solution {
    public boolean isPowerOfTwo(int n) {
    	if(n==1 || n==2){
    		return true;
    	}
        double temp = Math.log10(n)/Math.log10(2);
        if(Math.floor(temp) == temp){
        	return true;
        }else{
        	return false;
        }
    }
    
    public static void main(String[] args) {
		new Solution().isPowerOfTwo(536870912);
	}
}
