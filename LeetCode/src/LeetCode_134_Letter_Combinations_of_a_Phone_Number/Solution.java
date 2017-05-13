package LeetCode_134_Letter_Combinations_of_a_Phone_Number;

import java.util.ArrayList;
import java.util.List;


/**
 * Given a digit string, return all possible letter combinations that 
 * the number could represent
 * Input:Digit string "23"  
   Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].  
 * @author Administrator
 *
 */

/**
 * �绰���������Ŀ
 * �������������У�Ӧ���뵽�����㷨����DFS
 * 
 * dfs  ���ص���forѭ�����ϵݹ�
 * @author Administrator
 *
 */
public class Solution {

	public List<String> letterCombinations(String digits){
	    //���ѡ
	String[] ss  ={" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	List<String> res = new ArrayList<String>();
	dfs(ss,digits.length(),digits,res,new StringBuilder());
	return res;
	}
	public void dfs(String[] ss,int remain, String digital,List<String> res,StringBuilder sb){
		if(remain==0){
			res.add(sb.toString());
			return;
		}
		
		String a = ss[digital.charAt(0)-'0'];
		for(int i=0;i<a.length();i++){
			sb.append(a.charAt(i));
			dfs(ss,remain-1,digital.substring(1),res,sb);
			sb.deleteCharAt(sb.length()-1);//�ָ��ֳ��������һ���ַ�ȥ��
		}
				
		
	
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		String digital = "23";
		System.out.println((new Solution().letterCombinations(digital)).toString());
	}
}
