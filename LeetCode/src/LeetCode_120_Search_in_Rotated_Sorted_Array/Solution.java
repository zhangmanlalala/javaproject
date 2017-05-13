package LeetCode_120_Search_in_Rotated_Sorted_Array;
/**
 * 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array
 * 
 * @author Administrator
 *
 */
/**
 * 
 * 这是属于折半查找的变形，折半查找每次都可以去掉左边一半或者右边一半，而这次变形使得去掉左边一半或右边一半的条件
 * 更加苛刻了
 * @author Administrator
 *
 */


public class Solution {

	public static void main(String[] args) {
		int[] arr ={4,5,6,7,0,1,2};
		System.out.println(new Solution().search(arr, 3, 0, arr.length-1));

	}
	public int search(int[] arr,int target,int start,int end){
		if(start>end){
			return -1;
		}
		int mid = (start+end)/2;
		if(arr[mid] == target){
			return 1;
		}else if(arr[mid]<arr[end]){ //右边有序的情况
				if(target<arr[mid]){
					return search(arr,target,start,mid-1); //可以直接去掉右半边
				}else{
					return Integer.max(search(arr,target,start,mid-1),search(arr,target,mid+1,end));
				}
		}else{					//左边有序的情况
			if(target>arr[end]){
				return search(arr,target,mid+1,end); //可以直接去掉左半边
			}else{
				return Integer.max(search(arr,target,start,mid-1),search(arr,target,mid+1,end));
			}
		}
			
	}

}
