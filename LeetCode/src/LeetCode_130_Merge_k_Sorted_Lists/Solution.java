package LeetCode_130_Merge_k_Sorted_Lists;

import java.util.LinkedList;
import java.util.List;

/**
 * Merge k sorted linked lists and return it as one sorted list. 
 * Analyze and describe its complexity.
 * @author Administrator
 *
 *
 *���������÷��β���
 *
 *1 ��ԭ���⻮��Ϊ������ģ��С�Ĳ��ཻ�ĵ�������ԭ�����������
 *��Ϊ����ԭ������ͬ�������⣬��ԭ���ⲻһ��֮����
 *2 ������֮����
 *��ԭ������ͬ�������⣺�ݹ����
 *��ԭ���ⲻһ���������⣺�ݹ���������ʹ������(ǰ����ʱ����)
 *
 *3 �ϲ�����������Ľ�(merge)   (���ݵ�ʱ����)
 *
 *
 *����������д���ݹ���ʽ
 *		  �������ݹ���
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
