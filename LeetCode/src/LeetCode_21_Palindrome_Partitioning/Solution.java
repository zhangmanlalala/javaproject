package LeetCode_21_Palindrome_Partitioning;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 
	 * �����������㷨���þ�û���ˣ������ˣ�������ô��
	 * @param s
	 * @return
	 */
    public List<List<String>> partition(String s) {
    	List<List<String>> result = new ArrayList<List<String>>();
    	if(s==null){
    		return result;
    	}
    	List<String> lst = new ArrayList<String>();
    	partition(s.trim(),lst,result);
    	return result;
    }
    /**
     * 
     * ����˼���ǣ���һ���ַ��������֣��Ͳ������������������ʣ�µ����ַ�����Ϊ�����⣬�ֿ��Է�Ϊ������������ݹ����
     * dfs�����㷨(forѭ���ӵݹ�)����ؼ�����Ҫ�ָ��ֳ�
     * @param s
     * @param lst
     * @param result
     */
    private void partition(String s,List<String> lst,List<List<String>> result){
    	
    	
    	if(s.length()==0){   		
    		List<String> tp = new ArrayList<String>();
    		tp.addAll(lst);
    		result.add(tp);
    		return ;
    	}

    	
 
    	
    	for(int i=1;i<=s.length();i++){
    		String temp = s.substring(0, i);
    		if(isPalindrome(temp)){
    			lst.add(temp);
    
    			partition(s.substring(i,s.length()),lst,result);
    			
    			lst.remove(lst.size()-1);
    			
    		}
    	}
    }
    
    private boolean isPalindrome(String s){
    	if(s.length()==1){
    		return true;
    	}
    	int i=0;
    	int j=s.length()-1;
    	while(i<j){
    		if(s.charAt(i)==s.charAt(j)){
    			i++;
    			j--;
    		}else{
    			break;
    		}
    	}
    	if(i<j){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    
    public static void main(String[] args) {
//		List<String> lst = new ArrayList<String>();
//		Collections.addAll(lst, "abc","def");
		List<List<String>> result = new Solution().partition("aab");
		System.out.println("ab".substring(2, 2).length());
		for(List<String> temp:result){
			
			System.out.println(temp.toString());
		}
	}
}
