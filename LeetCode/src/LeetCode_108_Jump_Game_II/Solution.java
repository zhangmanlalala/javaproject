package LeetCode_108_Jump_Game_II;


/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
	Each element in the array represents your maximum jump length at that position.
	Your goal is to reach the last index in the minimum number of jumps.
	For example:
	Given array A = [2,3,1,1,4]
	The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * 
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 出现了贪心的最优化问题，不容易。
	基本思路就是，
	确定当前能到的最大下标 temp之后，游标 i 继续往后走直到temp，在这个过程中，比较max 和 A[i] + i, 以更新下一步最大下标max，
	当 i 走到temp的时候，说明要走下一步了，而下一步能走到的最大是max。
 * 
 * @author Administrator
 *
 */
public class Solution {
	public int jump(int[] A){
		if(A==null || A.length<=1)
			return 0;
		//temp记录当前最大下标
		//max记录下一步的最大下标
		int max = 0;
		int temp = A[0];
		int i=0;
		int num=0;
		while(i<A.length){
			while(i<temp){
				if(i>=A.length){
					return num;
				}
				i++;
				if(i+A[i]>max){
					max=i+A[i];
				}
			}
			num++;
			temp = max;
		}
		return num;
	}
}
