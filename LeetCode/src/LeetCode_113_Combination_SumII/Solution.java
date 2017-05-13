package LeetCode_113_Combination_SumII;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 
 * 
 * 
 * @author Administrator
 *
 */

public class Solution {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int target = 8;
		List<Integer> ls = new ArrayList<Integer>();
		ls.add(10);
		ls.add(1);
		ls.add(2);
		ls.add(7);
		ls.add(6);
		ls.add(1);
		ls.add(5);
		
		Set<String> set = solution.drive2(ls,target);
		for(String temp:set){
			System.out.println(temp);
	
		}

	}
	
	public Set<String> drive2(List<Integer> s,int target){
		s.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1>o2){
					return 1;
				}else if(o1<o2){
					return -1;
				}else{
					return 0;
				}
			}
		});
		
		List<Integer> sb = new ArrayList<Integer>();
		Set<String> set = new HashSet<String>();
		findAll2(s, 0, target, sb, set);
		return set;
	}
	
	public void findAll2(List<Integer> s,int k,int target,List<Integer> sb,Set<String> set){
		if(s.isEmpty() || k>=s.size()){
			return ;
		}
		for(int i=k;i<s.size();i++){
			sb.add(s.get(i));
			int te = 0;
			for(int j=0;j<sb.size();j++){
				te = te+sb.get(j);
			}
			if(te<target){
				findAll2(s, i+1, target, sb, set);
			}else if(te == target){
				String string = "";
				
				for(Integer tp:sb){
					string = string+tp+"";
				}
				if(!set.contains(string)){
					set.add((string));
				}
			}
			sb.remove(sb.size()-1);		
		}
	}

}
