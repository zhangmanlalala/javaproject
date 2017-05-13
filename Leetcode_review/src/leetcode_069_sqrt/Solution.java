package leetcode_069_sqrt;
/*
 * 
 * Implement int sqrt(int x).
	Compute and return the square root of x.
 */
public class Solution {
	
	
	/**
	 * 
	 * ʹ�ö��ֲ��ҷ�
	 * @param x
	 * @return
	 */
	
    public int mySqrt(int x) {
        if(x == 0)
        	return 0;
        
        int left = 1,right = x;
        while(true){
        	int mid = (right+left)/2;
        	if(mid > x/mid) {
        		right = mid - 1;
        	}else{	  		
    			if(mid+1>x/(mid+1)){
    				return mid;
    			}else{
    				left = mid+1;
    			}
        	}
        }
    }
    
    
}
