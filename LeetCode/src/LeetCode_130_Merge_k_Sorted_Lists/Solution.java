package LeetCode_130_Merge_k_Sorted_Lists;

import java.util.LinkedList;
import java.util.List;

/**
 * Merge k sorted linked lists and return it as one sorted list. 
 * Analyze and describe its complexity.
 * @author Administrator
 *
 *
 *分析：采用分治策略
 *
 *1 把原问题划分为几个规模较小的不相交的的类似于原问题的子问题
 *分为：与原问题相同的子问题，与原问题不一样之问题
 *2 求解各个之问题
 *与原问题相同的子问题：递归情况
 *与原问题不一样的子问题：递归结束条件和触底情况(前进的时候做)
 *
 *3 合并各个子问题的解(merge)   (回溯的时候做)
 *
 *
 *辅助方法：写出递归表达式
 *		  ：画出递归树
 */
public class Solution {
	public LinkedList<Comparable> mergeLinkedList(LinkedList[] arr,int start,int end){
		if(arr.length==0){
			return null;
		}
		if(start==end){
			LinkedList<Comparable> list = arr[start];
			return list;
		}
		while(start<end){
			int cen = (int)((start+end)/2);
			LinkedList<Comparable> l1 = mergeLinkedList(arr,start,cen);
			LinkedList<Comparable> l2 = mergeLinkedList(arr,cen+1,end);
			return merge(l1,l2);
		}
		return null;	
		
	}
	public LinkedList<Comparable> merge(LinkedList<Comparable> l1,LinkedList<Comparable> l2){
		LinkedList<Comparable> result = new LinkedList<Comparable>();
		int i=0;
		int j=0;
		while(i<l1.size() && j<l2.size()){
			if(l1.get(i).compareTo(l2.get(j))==-1){
				result.add(l1.get(i));
				i++;
			}else{
				result.add(l2.get(j));
				j++;
			}
		}
		if(i<l1.size()){
			for(;i<l1.size();i++){
				result.add(l1.get(i));
				i++;
			}
		}
		if(j<l2.size()){
			for(;j<l2.size();j++){
				result.add(l2.get(j));
				j++;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		LinkedList[] arr = new LinkedList[4];
		for(int i=0;i<arr.length;i++){
			arr[i] = new LinkedList<Comparable>();
		}
		arr[0].add(1);
		arr[0].add(3);
		arr[0].add(5);
		
		arr[1].add(1);
		arr[1].add(3);
		arr[1].add(5);
		
		arr[2].add(1);
		arr[2].add(3);
		arr[2].add(5);
		
		arr[3].add(1);
		arr[3].add(3);
		arr[3].add(5);
		
		
		LinkedList<Comparable> lt= new Solution().mergeLinkedList(arr, 0, arr.length-1);
		for(Comparable temp:lt){
			System.out.println(temp);
		}
	}
}
