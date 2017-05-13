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
	 * ���Լ��Ľⷨ����ʧ�ܵģ����������ö��ֲ��ҷ��ĵݹ鷽ʽ
	 * ���ö��ֲ��ҷ��ķǵݹ鷽ʽ
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
		while(left<=right){ //���ֲ��ҷ��ķǵݹ�ʵ�֣�Ҳ�ܼ�
			int mid = (left+right)/2;
			if(nums[mid] == target){
				return true;
			}
			if(left == mid){//��ֻ���������Ԫ�ص�ʱ��
				return (nums[right]==target);
			}
			if(nums[mid]>nums[left]){//˵����������
				if(target>=nums[left] && target<nums[mid]){//˵��target�����ߣ������Ұ��
					right = mid-1;
				}else{//target�������Ұ�ߣ���������
					left = mid+1;
				}
			}else if(nums[mid]<nums[right]){//˵���Ұ������
				if(target>nums[mid] && target<=nums[right]){//˵��target���Ұ�ߣ�ֱ����������
					left = mid+1;
				}else{//target���������ߣ������Ұ�ߣ������Ұ��
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
		//����nums
		return false;
    }
	

	
	public static void main(String[] args) {
		int[] nums = {1,1,3};
		int target = 3;
		System.out.println(new Solution().search(nums, target));
	}
}
