package _189_Rotate_Array;
/**
 * 
 * Rotate an array of n elements to the right by k steps.
	For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
	Note:
	Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
	[show hint]
	Related problem: Reverse Words in a String II
 * 
 * @author Administrator
 *
 */
public class Solution {
    public void rotate(int[] nums, int k) {
    	if(nums==null || nums.length<=1 || k<=0){
    		return ;
    	}
    	if(k>nums.length){
    		k = k-nums.length;
    	}
        int newArr[] = new int[nums.length-k];
        for(int i=0;i<=nums.length-k-1;i++){
        	newArr[i] = nums[i];
        }

        for(int i=nums.length-k;i<nums.length;i++){
        	nums[i-nums.length+k] = nums[i];
        }
        int j=0;
        for(int i=k;i<nums.length;i++){
        	nums[i] = newArr[j];
        	j++;
        }
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[]{1,2,3,4,5,6,7};
		new Solution().rotate(nums, 4);
		for(int temp:nums){
			System.out.println(temp);
		}
	}
}
