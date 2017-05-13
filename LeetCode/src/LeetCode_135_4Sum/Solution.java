package LeetCode_135_4Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		
	}
	
	public static List<List<Integer>> fourSum(int[] array,int target){
		List<List<Integer>> endList = new ArrayList<List<Integer>>();
		Set<List<Integer>> set = new HashSet<List<Integer>>();
		
		Arrays.sort(array);
		int len = array.length;
		int k =0;
		int m =0;
		int dif =0;
		for(int i=0;i<=len-4;i++){
			for(int j=i+1;j<=len-3;j++){
				k = j+1;
				m = len-1;
				while(k<m){
					dif = array[i]+array[j]+array[k]+array[m]-target;
					if(dif==0){
						List<Integer> startList = new ArrayList<Integer>();
						startList.add(array[i]);
						startList.add(array[j]);
						startList.add(array[k]);
						startList.add(array[m]);
						if(!set.contains(startList)){
							set.add(startList);
							endList.add(startList);
						}
						k--;
						j++;
					}else if(dif>0){
						k--;
					}else{
						j++;
					}
				}
			}
		}
		return endList;
	}

}
