package leetcode_080_remove_duplicates_from_sorted_array_2;
/**
 * 
 *  Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of 
nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the
 new length. 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
    public int removeDuplicates(int[] nums) {
    	if(nums ==null || nums.length == 0){
            return 0;
        }
    	
    	int i=1,j=1,num=1,latest = nums[0];
    	
    	for(j=1;j<nums.length;j++){
    		if(nums[j] == latest){
    			num++;   			
    		}else{
    			num = 1;
    		}
    		
    		if(num<=2){
				nums[i] = nums[j];
				latest = nums[i];
				i++;
    		}
		}
    	
    	
    	return i;
    		
    
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().removeDuplicates(new int[]{1,1,1,2,2,3}));
	}
}
