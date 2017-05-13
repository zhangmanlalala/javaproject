package _191_Number_of_1_Bits;
/**
 * 
 *Write a function that takes an unsigned integer and returns 
 *the number of ¡¯1' bits it has (also known as the Hamming weight).
	For example, the 32-bit integer ¡¯11' has binary representation 
	00000000000000000000000000001011, so the function should return 3. 
 * @author Administrator
 *
 */
public class Solution {
    public int hammingWeight(int n) {
        
    	int result = 0;
    	for(int i=0;i<32;i++){
    		int temp = (n>>i)&1;
    		result = result+temp;
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().hammingWeight(11));
	}
}
