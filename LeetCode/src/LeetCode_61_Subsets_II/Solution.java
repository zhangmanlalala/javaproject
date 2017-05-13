package LeetCode_61_Subsets_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 *Given a collection of integers that might contain duplicates, nums, return all possible subsets.
	Note: The solution set must not contain duplicate subsets.
	For example,
	If nums = [1,2,2], a solution is:
	
	[
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	]
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * �ҳ����п��ܣ�dfs�����㷨��ע�Ᵽ���ֳ�
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
         Arrays.sort(nums);//���������ԭַ�����
         Set<String> set = new HashSet<String>();
         List<List<Integer>> lst = new ArrayList<List<Integer>>();
         List<Integer> temp = new ArrayList<Integer>();
         for(int k=1;k<=nums.length;k++){
        	 
        	 dfs(nums,0,k,set,temp,lst);
         }
         lst.add(new ArrayList<Integer>());
         return lst;
         
    }
	
	
	public void dfs(int[] nums,int start,int num,Set<String> set,List<Integer> temp,List<List<Integer>> lst){

		if(temp.size()==num){
			if(!set.contains(temp.toString())){
				set.add(temp.toString());
				List<Integer> list1 = new ArrayList<Integer>();//���Ǹ���list�ķ�ʽ
				list1.addAll(temp);
				lst.add(list1);
			}
			return;
//			
		}
		
		for(int i=start;i<nums.length;i++){
			temp.add(nums[i]);
			
			//��ǰ����
			dfs(nums,i+1,num,set,temp,lst);
			
			//�ָ��ֳ�
			temp.remove(temp.size()-1);
			
		}
		
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,2};
		List<List<Integer>> lst = new Solution().subsetsWithDup(nums); 
		for(List<Integer> t:lst){
			System.out.println(t.toString());
		}
	}
	
}
