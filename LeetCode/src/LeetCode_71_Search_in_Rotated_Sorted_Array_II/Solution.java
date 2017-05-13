package LeetCode_71_Search_in_Rotated_Sorted_Array_II;
/**
 * Follow up for "Search in Rotated Sorted Array":
	What if duplicates are allowed?

	Would this affect the run-time complexity? How and why?

	Write a function to determine if a given target is in the array.
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 我自己的解法：是失败的，不能用再用二分查找法的递归方式
	 * 得用二分查找法的非递归方式
	 * @param nums
	 * @param target
	 * @return
	 */
	public boolean search(int[] nums, int target) {
		if(nums.length == 0|| nums==null){
			return false;
		}
		
		int left =0;
		int right = nums.length-1;
		while(left<=right){ //二分查找法的非递归实现，也很简单
			int mid = (left+right)/2;
			if(nums[mid] == target){
				return true;
			}
			if(left == mid){//在只有最后两个元素的时候，
				return (nums[right]==target);
			}
			if(nums[mid]>nums[left]){//说明左半边有序
				if(target>=nums[left] && target<nums[mid]){//说明target在左半边，舍弃右半边
					right = mid-1;
				}else{//target可能在右半边，舍弃左半边
					left = mid+1;
				}
			}else if(nums[mid]<nums[right]){//说明右半边有序
				if(target>nums[mid] && target<=nums[right]){//说明target在右半边，直接舍弃左半边
					left = mid+1;
				}else{//target可能在左半边，不在右半边，舍弃右半边
					right = mid-1;
				}
			}else{
				if(nums[mid] == nums[right]){
					right--;
				}
				if(nums[left] == nums[mid]){
					left++;
				}
			}
		}
		//代表nums
		return false;
    }
	

	
	public static void main(String[] args) {
		int[] nums = {1,1,3};
		int target = 3;
		System.out.println(new Solution().search(nums, target));
	}
}
