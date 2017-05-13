package LeetCode_122_Next_Permutation;

import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Solution {

	public static void main(String[] args) {
		int[] arr = new int[5];
		arr[0] = 3;
		arr[1] = 7;
		arr[2] = 8;
		arr[3] = 6;
		arr[4] = 8;
		int[] newarr = new Solution().findNextPermutation(arr);
		for(int i=0;i<newarr.length;i++){
			
			System.out.println(newarr[i]);
		}

	}
	
	public int[] findNextPermutation(int[] arr){
		int i,j;
		int temp;
		int cur = 0;
		for(j=arr.length-1;j>0;j--){
			if(arr[j-1]>=arr[j]){
				
			}else{
				temp = arr[j];
				cur = j;
				for(i=arr.length-1;i>=j;i--){
					if(arr[j-1]>=arr[i]){
						continue;
					}else{
						if(arr[i]<temp){
							temp = arr[i];
							cur = i;
						}
					}
				}
				arr[cur] = arr[j-1];
				arr[j-1] = temp;
				break;
			}
		}
		if(j == 0){
			 Arrays.sort(arr);
		}
		return arr;
	}

}
