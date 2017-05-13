package Leet_Code100_Maximum_Subarray;

import java.util.List;


/**
 * 
 * Find the contiguous subarray within an array (containing at least one number) which 
 * has the largest sum.
	For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
	the contiguous subarray [4,−1,2,1] has the largest sum = 6.
	
	click to show more practice.	
	More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and 
conquer approach, which is more subtle.
 * @author Administrator
 *
 */
public class Solution {

	
	 public int[] cross_max(int A[],int p,int r,int q){
		 int left_sum = Integer.MIN_VALUE;
		 int left_idx = 0;
		 int sum = 0;
		 for(int i=r;i>=p;i--){
			 sum = sum+A[i];
			 if(sum>left_sum){
				 left_idx = i;
				 left_sum = sum ;
			 }
		 }
		 
		 int right_sum = Integer.MIN_VALUE;
		 int right_idx = 0;
		 sum=0;
		 for(int j=r+1;j<=q;j++){
			 sum = sum+A[j];
			 if(sum>right_sum){
				 right_idx = j;
				 right_sum = sum;
			 }
		 }
		 
		 int result[] = new int[3];
		 result[0] = left_idx;
		 result[1] = right_idx;
		 result[2] = left_sum+right_sum;
		 return result;
	 }
	 
	 public int[] devide(int[] A,int p,int q){
		 if(p>=q){
			 return new int[]{p,q,A[p]};
		 }else{
			 int r = (p+q)/2;
			 int[] arr1 = devide(A,p,r);
			 int[] arr2 = devide(A,r+1,q);
			 int[] arr3 = cross_max(A,p,r,q);
			 if(arr1[2]>=arr2[2] && arr1[2]>=arr3[2]){
				 return arr1;
			 }else if(arr2[2]>=arr1[2] && arr2[2]>=arr3[2]){
				 return arr2;
			 }else{
				 return arr3;
			 }
		 }
		 
	 }
	 public static void main(String[] args) {
		int[] A = new int[]{-2,1,-3,4,-1,2,1,-5,4};
		int[] result = new Solution().devide(A, 0, A.length-1);
		for(int i=result[0];i<=result[1];i++){
			System.out.println(A[i]);
		}
	}
}
