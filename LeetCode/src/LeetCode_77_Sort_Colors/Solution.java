package LeetCode_77_Sort_Colors;


/**
 * 
 *  Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively. 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 标准解法
	 * @param nums
	 */
	
	/**
	 * 
	 *维护三个i，j，k指针，指向0，1，2结束的位置，那么，可以知道，

	     碰见0，则除了i位置后移1位，存放1和2的两段要整体后移1位；

	    碰见1，除了j位置后移1位，存放2的一段要整体后移1位；

	   碰见2，则只有k后移1位。 
	 * 
	 * @param nums
	 */
	
	/**
	 * 
	 * 经典，不服不行
	 * @param nums
	 */
	public void sortColors(int[] nums) {
        if(nums==null || nums.length == 0) return;
        
        int i=-1,j=-1,k=-1;
        for(int p=0;p<nums.length;p++){
        	if(nums[p] == 0){  //这几句换很经典
        		nums[++k] = 2;
        		nums[++j] = 1;
        		nums[++i] = 0;
        	}else if(nums[p] == 1){
        		nums[++k] = 2;
        		nums[++j] = 1;
        	}else if(nums[p] == 2){
        		nums[++k] = 2;
        	}
        }
    }
}
