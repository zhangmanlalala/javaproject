package LeetCode_107_Permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import LeetCode_107_Permutations.Solution;

/**
 *Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1]. 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public Set<String> drivePermut(List<Integer> list){
		StringBuilder sb = new StringBuilder();
		List<Integer> index = new ArrayList<Integer>();
		Set<String> set = new HashSet<String>();
		permutations(list,index,sb,set);
		return set;
	}
	public void permutations(List<Integer> list,List<Integer> index,StringBuilder sb,Set<String> set){
		
		if(list.isEmpty() || index.size()==list.size()){
			return ;
		}
		
		for(int i=0;i<list.size();i++){
			if(index.contains(i)){
				continue;
			}
			sb.append(list.get(i));
			index.add(i);
			permutations(list,index,sb,set);
			if(sb.length()==list.size()){				
				set.add(sb.toString());
			}
			sb.deleteCharAt(sb.length()-1);
			index.remove(index.size()-1);
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(2);
		Set<String> set = new Solution().drivePermut(list);
		
		for(String temp:set){
			System.out.println(temp);
		}
	}
}