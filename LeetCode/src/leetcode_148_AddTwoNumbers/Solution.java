package leetcode_148_AddTwoNumbers;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain
 * a single digit. Add the two numbers and return it as a linked list.

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 0 -> 8
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 我自己的尝试
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<Integer> add(List<Integer> list1,List<Integer> list2){
		int list1_length = list1.size();
		int list2_length = list2.size();
		List<Integer> list3 = new LinkedList<Integer>();
		int flag=0;
		for(int i=0;i<Math.min(list1_length, list2_length);i++){
			int c=list1.get(i)+list2.get(i)+flag;
			if(c>=10){
				list3.add(c-10);
				flag=1;
			}
			else{
				list3.add(c);
				flag=0;
			}
		}
		if(list1_length<list2_length){
			for(int i=list1_length;i<list2_length;i++){
				int d = list2.get(i)+flag;
				if(d>=10){
					list3.add(d-10);
					flag=1;
				}else{
					list3.add(d);
					flag=0;
				}
				
			}
			if(flag==1){
				list3.add(1);
			}
		}
		return list3;
	}
	
	public static void main(String[] args) {
		List<Integer> list1 = new LinkedList<Integer>();
		List<Integer> list2 = new LinkedList<Integer>();
		list1.add(8);
		list1.add(5);
		list1.add(7);
		list2.add(6);
		list2.add(3);
		list2.add(6);
		list2.add(8);
		List<Integer> list3 = add(list1,list2);
		for(int i=0;i<list3.size();i++){
			
			System.out.print(list3.get(i));
		}
	}
}
