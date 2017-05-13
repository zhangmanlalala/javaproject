package LeetCode_82_Climbing_Stairs;

public class Solution {
	public int climbStairs(int n) {
		int[] step;
        if(n==0){
        	return 0;
        }else if(n==1 ){
        	return 1;
        }else if(n==2) 
        	return 2;
        else{
        	step = new int[n];
        	step[0] = 1;
        	step[1] = 2;
        	for(int i=2;i<n;i++){
        		step[i] = step[i-1]+step[i-2];
        	}
        }
        return step[n-1];
    }
	
	public static void main(String[] args) {
		System.out.println(new Solution().climbStairs(5));
	}
}
