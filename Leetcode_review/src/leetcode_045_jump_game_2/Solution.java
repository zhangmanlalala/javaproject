

package leetcode_045_jump_game_2;
/**
 *  Given an array of non-negative integers, you are initially positioned at the 
 *  first index of the array.
	Each element in the array represents your maximum jump length at that position.	
	Your goal is to reach the last index in the minimum number of jumps.	
	For example:
	Given array A = [2,3,1,1,4]
	
	The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, 
	then 3 steps to the last index.) 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
	
	/**
	 * 
	 * 这个是我真正意义上第一次自己做出来的hard，有点贪婪算法的意思
	 * @param nums
	 * @return
	 */
    public int jump(int[] nums) {
    	if(nums==null || nums.length==0){
    		return 0;
    	}
        int step=0,i=0;
        int maxLoc = nums[0];
        int maxLoc_idx = 0;
        while(i<nums.length){
        	int j=i+1;
        	while(j<nums.length && j<=i+nums[i]){
        		if(j+nums[j]>maxLoc){
        			maxLoc = j+nums[j];
        			maxLoc_idx = j;
        		}
        		j++;
        	}
        	if(j>=nums.length){
        		step++;
        		break;
        	}
        	
        	if(maxLoc>i+nums[i]){
        		i = maxLoc_idx;
        		step++;
        	}
        	
        	
        }
        
        return step;
        	
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().jump(new int[]{2,3,1,1,4}));
	}
}
