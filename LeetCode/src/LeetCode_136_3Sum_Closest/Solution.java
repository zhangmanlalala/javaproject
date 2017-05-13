package LeetCode_136_3Sum_Closest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {

	}
	/**
	 * 我实现的
	 * @param array
	 * @param target
	 * @return
	 */
	public int ThreeSumClosed (int[] array,int target){
		
		Arrays.sort(array);//调用方法对array数组排序，排序后能减少复杂度
		
		//用三个下标来控制，并且保证下标的大小顺序
		int len = array.length;
		int j =0;
		int k =0;
		int lastDif = 0;
		int closedDif = array[0]+array[1]+array[2]-target;
		for(int i=0;i<len-2;i++){
			j = i+1;
			k = len-1;
			while(j<k){
				lastDif = array[i] + array[j]+array[k]-target;				
				if(lastDif==0){
					closedDif = lastDif;
					break;
				}else if(lastDif>0){
					if(Math.abs(lastDif)<Math.abs(closedDif))
						closedDif = lastDif;
						k--;				
				}else{
					if(Math.abs(lastDif)<Math.abs(closedDif))
						closedDif = lastDif;
					    j++;
				}
			}
		}
		return closedDif;
		
	}
	
	/**
	 * 别人实现的
	 */
	public int threeSumClosest(int[] num, int target) {  
        int min = Integer.MAX_VALUE;  
        int result = 0;  
        Arrays.sort(num);  
        for(int i=0; i<num.length-2; i++){  
            int j=i+1;  
            int k=num.length-1;  
            while(j<k){  
                int sum = num[i]+num[j]+num[k];  
                int diff = Math.abs(sum-target);  
                if(diff < min){  
                    min = diff;  
                    result = sum;  
                }  
                if(sum <= target)  
                    j++;  
                else  
                    k--;  
            }  
        }  
        return result;  
    }  

}
