package LeetCode_75_Combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,

If n = 4 and k = 2, a solution is:
 * 
 * @author Administrator
 *
 */
public class Solution {
	public List<List<Integer>> combine(int n, int k) {
		List<Integer> list = new ArrayList<Integer>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(n==0 || k==0){
			return null;
		}
		
		combination(n,k,1,list,result);
		return result;
		
		
    }
	
	public void combination(int n,int k,int start,List<Integer> list,List<List<Integer>> result){
		/**
		 * 
		 * �������������У��뵽���ݣ���ô����DFS,ע��ָ��ֳ�
		 */
		if(list.size() == k || k==0){
			if(list.size() == k){
				List<Integer> res = new ArrayList<Integer>(list);

				result.add(res);
			}
			return ;
		}
		for(int i=start;i<=n;i++){
			//������
			list.add(i);
			
			//��������
			combination(n,k,i+1,list,result);
			//�ָ��ֳ�
			list.remove(list.size()-1);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new Solution().combine(4, 3));
	}
}
