package LeetCode_119_Search_for_a_Range;

import java.util.ArrayList;
import java.util.List;


/**
 *Given a sorted array of integers, find the starting and ending position of a 
 *given target value.Your algorithm's runtime complexity must be in the order of O(log n).
  If the target is not found in the array, return [-1, -1]. 
 * 
 * 
 * @author Administrator
 * 
 * For example,
   Given [5, 7, 7, 8, 8, 10] and target value 8,

   return [3, 4].
 * 
 * 
 *
 */

/**
 * 
 * 该题属于折半查找的变形
 * @author Administrator
 *
 */
public class Solution {
	
	
	public static void main(String[] args) {
		int arr[] = {5, 7, 7, 8, 8, 10};
		int r[] = new Solution().search_driver(arr, 7);
		for(int j=0;j<r.length;j++){
			System.out.println(r[j]);
		}
	}
	
	public void search(int[] arr,int target,int start,int end,List<Integer> list){
		if(start>end){
			return ;
		}
		int mid = (int)((start+end)/2);
		if(arr[mid]>target){
			search(arr, target, start, mid-1, list);
		}else if(arr[mid]<target){
			search(arr, target, mid+1, end, list);
		}else{
			list.add(mid);
			int i=mid-1;
			while(arr[i]==target && i>=start){
				list.add(i);
				i--;
			}
			int j=mid+1;
			while(arr[j]==target && j<=end){
				list.add(j);
				j++;
			}
		}
		
	}
	public int[] search_driver(int[] arr,int target){
		List<Integer> list = new ArrayList<Integer>();
		search(arr,target,0,arr.length-1,list);
		if(!list.isEmpty()){
			int result[] = new int[list.size()];
			int i=0;
			for(Integer temp:list){
				result[i] = temp;
				i++;
			}
			return result;
		}else{
			return new int[]{-1};
		}
	}

}
