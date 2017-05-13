package leetcode_088_merge_sorted_array;
/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into 
 * nums1 as one sorted array.

	Note:
	You may assume that nums1 has enough space (size that is greater or 
	equal to m + n) to hold additional elements from nums2. The number of 
	elements initialized in nums1 and nums2 are m and n respectively.
	 * 
 * @author Administrator
 *
 */
public class Solution {
	 public void merge(int[] nums1, int m, int[] nums2, int n) {
		 
		 int[] nums3 = new int[m];
		 
		 for(int i=0;i<m;i++){
			 nums3[i] = nums1[i];
		 }
		 
		 int i=0,j=0,k=0;
		 while(i<m && j<n){
			 if(nums3[i]>nums2[j]){
				 nums1[k] = nums2[j];
				 j++;
				 k++;
			 }else{
				 nums1[k] = nums3[i];
				 i++;
				 k++;
			 }
			 
		 }
		 
		 while(i<m){
			 nums1[k] = nums3[i];
			 i++;
			 k++;
		 }
		 
		 
		 while(j<n){
			 nums1[k] = nums2[j];
			 j++;
			 k++;
		 }
	 }
}
