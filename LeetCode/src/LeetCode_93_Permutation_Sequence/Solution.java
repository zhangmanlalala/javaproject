package LeetCode_93_Permutation_Sequence;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
	By listing and labeling all of the permutations in order,
	We get the following sequence (ie, for n = 3):
	"123"
	"132"
	"213"
	"231"
	"312"
	"321"
	Given n and k, return the kth permutation sequence.
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	public String driveFindAll(int n,int k){
		StringBuilder sb = new StringBuilder();
		List<Integer> index = new ArrayList<Integer>();
		List<String> re = new ArrayList<String>();
		findAll(n,sb,index,re);
		return re.get(k);
	}
	
	public void findAll(int n,StringBuilder sb,List<Integer> index,List<String> re){
		if(sb.length()==n){
			return;
		}
		if(n==1){
			 re.add("1");
			 return;
		}else if(n<1){
			return;
		}else{
			for(int i=1;i<=n;i++){
				if(index.contains(i)) 
					continue;
				sb.append(i);
				index.add(i);
				findAll(n,sb,index,re);
				if(sb.length()==n){
					re.add(sb.toString());
				}
				sb.deleteCharAt(sb.length()-1);
				index.remove(index.size()-1);
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(new Solution().driveFindAll(3, 2));
	}
}
