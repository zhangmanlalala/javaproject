package LeetCode_98_Jump_Game;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.
For example:
A = [2,3,1,1,4], return true.
A = [3,2,1,0,4], return false.
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	public boolean jumpGame(int[] arr){
		int maxStep = arr[0];
		int pos = arr[0];
		int j=0;
		int i=0;
		while(j<arr.length){
			for(;i<=pos;i++){
				if(i>=arr.length-1){
					return true;
				}else if(arr[i]+i>maxStep){
					maxStep = arr[i]+i;
				}
			}
			if(maxStep<=pos){
				return false;
			}else{
				pos = maxStep;
				j = i;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[] A = new int[]{2,3,1,1,4};
		int[] B = new int[]{3,2,1,0,4};
		System.out.println(new Solution().jumpGame(B));
	}
}
