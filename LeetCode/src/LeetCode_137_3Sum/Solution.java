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
	 * ��д��ʵ�ֻ����ظ�
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
	 * Ksum������Ҫ�о����е�����������У���֤3��Ԫ���±������Ϳ�����
	 * ��Ҫע����ǣ�ӦΪs�п��ܴ����ظ���Ԫ�أ����Լ��㱣֤�������±����򣬻���
	 * �����ҵ��ظ��Ľ����������Ҫһ��HashSet(���򲻿��ظ�)
	 * @param args
	 */
	public List<List<Integer>> ThreeSumDemo (int[] array){
		List<List<Integer>> endList = new ArrayList<List<Integer>>();
		
		Set<List<Integer>> set = new HashSet<List<Integer>>();
		Arrays.sort(array);//���÷�����array��������������ܼ��ٸ��Ӷ�
		
		//�������±������ƣ����ұ�֤�±�Ĵ�С˳��
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
					//��Ϊnum���ܴ����ظ�Ԫ�أ�����Ҫ��set�ж�һ��
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
