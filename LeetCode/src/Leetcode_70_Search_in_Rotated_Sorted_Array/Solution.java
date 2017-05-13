package Leetcode_70_Search_in_Rotated_Sorted_Array;

public class Solution {
	
	/**
	 * 
	 * 我自己的方法，有的冗余
	 * @param nums
	 * @param target
	 * @return
	 */
	public int search(int[] nums, int target) {
		 if(nums.length==0 || nums==null){
			 return -1;
		 }
		 return searchTarget(nums,0,nums.length-1,target);
    }
	
	
	public int searchTarget(int[] nums,int start,int end,int target){
		if(start == end){
			if(nums[start] == target){
				return start;
			}else{
				return -1;
			}
		}
		if(start<end){
			int mid = (start+end)/2;
			if(nums[start]<=nums[mid]){
				int idx1 = binarySearch(nums,start,mid,target);
				if(idx1>-1){
					return idx1;
				}else{
					return searchTarget(nums,mid+1,end,target);
				}
			}else{
				int idx2 = binarySearch(nums,mid,end,target);
				if(idx2>-1){
					return idx2;
				}else{
					
					return searchTarget(nums,start,mid-1,target);
				}
			}
		}
		
		return -1;
		

	}
	
	public int binarySearch(int[] nums,int start,int end,int target){
		if(start == end){
			if(nums[start] == target){
				return start;
			}else{
				return -1;
			}
		}else if(start<end){
			int m = (start+end)/2;
			if(nums[m]<target){
				return binarySearch(nums,m+1,end,target);	
			}else if(nums[m]>target){
				return binarySearch(nums,start,m-1,target);
			}else{
				return m;
			}
		}
		
		return -1;

	}
	
	/**
	 * 
	 * 
	 * 标准方法：带条件的二分查找法
	 * 
	 * @param args
	 */
	public int search2(int[] A, int target) {  
        return search3(A, target, 0, A.length-1);  
    }  
    public int search3(int[] A, int target, int start, int end){  
        if(start>end)  
            return -1;  
        int mid=(start+end)/2;  
        if(target == A[mid])  
            return mid;  
        if(A[mid] >= A[end]){//左边有序  
            if(target < A[mid] && target>=A[start])  
                return search3(A, target, start, mid-1);  
            else  
                return search3(A, target, mid+1, end);  
        }else{//右边有序  
            if(target > A[mid] && target<=A[end])  
                return search3(A, target, mid+1, end);  
            else  
                return search3(A, target, start, mid-1);  
        }  
    }  
	
	
	public static void main(String[] args) {
		int[] nums = {4,5,6,7,0,1,2};
		System.out.println(new Solution().search(nums, 2));
	}
}
