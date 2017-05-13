package LeetCode_114_Combination_Sum;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7,
A solution set is:
[7]
[2, 2, 3] 
 * 
 * @author Administrator
 *
 */

/**
 * 
 * 看到返回所有情况，应该想到递归dfs，注意dfs要注意恢复现场 ，dfs的构成：循环加递归
 * @author Administrator
 *
 */

public class Solution {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int target = 7;
		List<Integer> ls = new ArrayList<Integer>();
		ls.add(2);
		ls.add(3);
		ls.add(6);
		ls.add(7);
		List<String> list = solution.drive(ls,target);
		for(String temp:list){
			
			System.out.println(temp);
		}
		
	}
	
	public List<String> drive(List<Integer> s,int target){
		StringBuilder sb = new StringBuilder();
	
		List<String> list = new ArrayList<String>();
		findAll(s,0,target,sb,list);
		return list;

	}
	public void findAll(List<Integer> s,int start,int target,StringBuilder sb ,List<String> list){
		if(s.isEmpty() || start>=s.size()){
			return;
		}
		for(int k=start; k<s.size();k++){
			sb.append(s.get(k));
			int te = 0;
			for(int i=0;i<sb.length();i++){
				te = te+sb.toString().charAt(i)-48;
			}
			if(te<target){
				findAll(s,k,target,sb,list);
			}else if(te == target){
				list.add(sb.toString());
			}
			sb.deleteCharAt(sb.length()-1);
		}
	}

}
