package LeetCode_137_3Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
	/**
	 * 我写的实现会有重复
	 * @param array
	 * @return
	 */
	public static Map<Integer,Integer[]> ThreeSum(int[] array){
		int len = array.length;
		Map<Integer,Integer[]> result = new HashMap<Integer,Integer[]>();
		Map<Integer,Integer> map_arr = new HashMap<Integer,Integer>();
		for(int i=0;i<len;i++){
			map_arr.put(i, array[i]);
		}
		int sum2 = 0;
		int sup = 0;
		int fit_num = 1;
		for(int i=0;i<len-2;i++){
			for(int j=i+1;j<len-1;j++){
				sum2 = array[i]+array[j];
				sup =0-sum2;
				if(map_arr.containsValue(sup)){
					for(int k=j+1;k<len;k++){
						if(k==i || k==j){
							continue;
						}else if(map_arr.get(k)==sup){
							Integer[] num = new Integer[3];
							num[0] =array[i];
							num[1] =array[j];
							num[2] = sup;
							result.put(fit_num, num);
							fit_num++;
						}
					}
				}
			}
		}
		return result;
		
	}
	
	/**
	 * Ksum的问题要列举所有的情况，过程中，保证3个元素下标的有序就可以了
	 * 需要注意的是，应为s中可能存在重复的元素，所以即便保证了三个下标有序，还是
	 * 可能找到重复的结过，所以需要一个HashSet(无序不可重复)
	 * @param args
	 */
	public List<List<Integer>> ThreeSumDemo (int[] array){
		List<List<Integer>> endList = new ArrayList<List<Integer>>();
		
		Set<List<Integer>> set = new HashSet<List<Integer>>();
		Arrays.sort(array);//调用方法对array数组排序，排序后能减少复杂度
		
		//用三个下标来控制，并且保证下标的大小顺序
		int len = array.length;
		int j =0;
		int k =0;
		int sum = 0;
		for(int i=0;i<len-2;i++){
			j = i+1;
			k = len-1;
			while(j<k){
				sum = array[i] + array[j]+array[k];
				if(sum==0){
					List<Integer> elementList = new ArrayList<Integer>();
					elementList.add(array[i]);
					elementList.add(array[j]);
					elementList.add(array[k]);
					//因为num可能存在重复元素，所以要用set判断一下
					if(!set.contains(elementList)){
						set.add(elementList);
						endList.add(elementList);
					}
					j++;
					k--;
				}else if(sum>0){				
						k--;				
				}else{
					    j++;
				}
			}
		}
		return endList;
		
	}
	public static void main(String[] args) {
		int[] array = {-1,0,1,2,-1,-4};
		Integer[] se;
		Map<Integer,Integer[]> result = ThreeSum(array);
		Iterator<Integer> it = result.keySet().iterator();
		while(it.hasNext()){
			se = result.get(it.next());
			System.out.println("("+se[0]+","+se[1]+","+se[2]+")");
		}
	}

}
