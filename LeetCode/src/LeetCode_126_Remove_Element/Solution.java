package LeetCode_126_Remove_Element;

import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int removeElement(int[] arr,int value){
		if(arr.length==0){
			return -1;
		}
		int j = arr.length-1;
		int i=0;
		int len = arr.length;
		while(i<len){
			if(arr[i]==value){
				arr[i]=arr[j];
				j--;
				len--;
			}else{
				i++;
			}
		}
		
		arr = Arrays.copyOf(arr, len);
		return arr.length;
	}

}
