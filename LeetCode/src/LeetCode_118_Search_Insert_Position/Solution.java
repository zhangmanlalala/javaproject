package LeetCode_118_Search_Insert_Position;

/**
 * Given a sorted array and a target value, return the index if 
 * the target is found. If not, return the index where it would be if it were inserted 
 * in order.
   You may assume no duplicates in the array.
 * 
 * 
 * 
 * Here are few examples.
	[1,3,5,6], 5 → 2
	[1,3,5,6], 2 → 1
	[1,3,5,6], 7 → 4

[1,3,5,6], 0 → 0
 * @author Administrator
 *
 */

/**
 * 
 * 这个是折半查找的变种
 * @author Administrator
 *
 */
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int search(int arr[],int target,int start,int end){
		if(start+1 == end ){
			if(arr[start] == target){
				return start;
			}else if(arr[end] == target){
				return end;
			}else if(arr[start]>target){
				if(start-1>=0){
					return start-1;
				}else{
					return 0;
				}
			}else if(arr[end]<target){
				return end+1;
			}else{
				return start+1;
			}
		}
		
		int mid = (int)((start+end)/2);
		if(arr[mid] > target){
			return search(arr, target, 0, mid-1);
		}else if(arr[mid] < target){
			return search(arr, target, mid+1, end);
		}else{
			return mid;
		}
	}

}
