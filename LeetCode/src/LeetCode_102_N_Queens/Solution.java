package LeetCode_102_N_Queens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 *  such that no two queens attack each other
 * 
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 
 * 分析找出所有的可能，用回溯算法，注意保护现场
 * @author Administrator
 *
 */
public class Solution {
	
	
	public void queens(int n,List<Integer> index,StringBuilder sb,Set<String> set){
		if(n<=1){
			return;
		}else if(index.size()==n){
			return;
		}
		
		for(int i=0;i<n;i++){
			if(index.contains(i)){
				continue;
			}
			sb.append(i);
			index.add(i);
			queens(n,index,sb,set);
			if(index.size()==n){
				set.add(sb.toString());
			}
			sb.deleteCharAt(sb.length()-1);
			index.remove(index.size()-1);
		}
	}
	
	public Set<String> drive(int n){
		List<Integer> index = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		Set<String> set = new HashSet<String>();
		queens(n,index,sb,set);
		return set;
	}
	
	
	public static void main(String[] args) {
		Set<String> set = new Solution().drive(3);
		for(String temp:set){
			System.out.println(temp);
		}
	}
}
