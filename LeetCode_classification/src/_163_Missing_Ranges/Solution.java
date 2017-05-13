package _163_Missing_Ranges;

import java.util.ArrayList;
import java.util.List;

/**
 * Missing Ranges Total Accepted: 510 Total Submissions: 2300

Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].


[����]

O(N) ɨһ�鼴��, ����LOWER,

[ע��]

����ѭ��, ����һ��iʱ, �ڲ�ѭ��Ҫע���жϱ߽�����. 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public List<String> findMissingRanges(int[] A,int lower,int upper){
		if(A==null) return null;
		
		List<String> res = new ArrayList<String>();
		for(int i=0;i<A.length;i++){
			while(i<A.length && A[i]==lower){//�ܾ��䣬����lower��ֵ��
				lower++;
				i++;
			}
			
			if(i>A.length) break;
			
			if(A[i] == lower+1){
				res.add(String.valueOf(lower));
			}else{
				res.add(""+lower+"->"+(A[i]-1));
			}
			
			lower = A[i]+1;
		}
		
		if(lower == upper){
			res.add(String.valueOf(lower));
		}else if(lower<upper){
			res.add(""+lower+"->"+upper);
		}
		
		return res;
		
	}
}
