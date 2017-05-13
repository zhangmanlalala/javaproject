package leetcode_055_jumpGame;
/**
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean canJump(int[] nums) {
        

    	
    	int i=0;
    	
    	int next_point = 0;
    	int max_position = 0+nums[0];
    	while(i<nums.length){

    		if(max_position>=nums.length-1){//可以到末尾，就跳出
    			return true;
    		}
    		int j=i+1;
    		for(;j<=i+nums[i];j++){
    			if(j+nums[j]>max_position){
    				next_point = j;
    				max_position = j+nums[j];
    			}
    		}
    		
    		if(nums[i]+i == max_position){
    			return false;
    		}else{
    		
    			i = next_point;
    		}
    	}
    	
    	return true;
    }
}
