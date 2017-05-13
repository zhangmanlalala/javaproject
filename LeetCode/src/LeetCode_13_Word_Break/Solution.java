package LeetCode_13_Word_Break;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Given a string s and a dictionary of words dict, determine if s can be 
 * segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".  
 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
    	String[] arr =  new String[wordDict.size()];
    	int i=0;
    	for(String temp:wordDict){
    		arr[i] = temp;
    		i++;
    	}

       Arrays.sort(arr, new Comparator<String>(){

		@Override
		public int compare(String o1, String o2) {
			if(o1.length()>o2.length()){
				return 1;
			}else if(o1.length()<o2.length()){
				return -1;
			}else{
				return 0;
			}
		}});
       
       i = arr.length-1;
       while(s.length()!=0 && i>=0){
    	   int idx = s.indexOf(arr[i]);
    	   if(idx !=-1){
    		   s = s.substring(0,idx)+s.substring(idx+arr[i].length(),s.length());
    		   i--;
    	   }else{
    		   break;
    	   }
       }
       if(i==-1 && s.length()==0){
    	   return true;
       }else{
    	   return false;
       }
    }
    
    /**
     * 
     * ��׼�ⷨ����̬�滮
     * ��򵥵��뷨���ֽ����⣬�������Ǵ�ͷ��ʼ��һ�����ʣ������ʣ�µ��ַ������Ա��ָ���ܵ��ַ����Ϳ��Ա��ָ�
     * �ɴ˲�������ĵݹ�ⷨ���������Ƿ��֣����жϺ�����ַ����ܷ�ָ�Ĺ����У�����˴������ظ������⣬ֻҪ�ظ������⣬
     * ��Ӧ���뵽��̬�滮����̬�滮�Ļ���˼·������һ���ֵ��¼�м������Ա������ѯ
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, Set<String> wordDict) {
    	boolean[] table = new boolean[s.length()+1];
    	table[0] = true;
    	
    	for(int i=1;i<s.length();i++){
    		for(int k=0;k<i;k++){
    			table[i] = table[k] && wordDict.contains(s.substring(k, i));
    			if(table[i])
    				break;
    		}
    	}
    	
    	return table[s.length()];
    }
    public static void main(String[] args) {
    	Set<String> wordDict = new HashSet<String>();
    	Collections.addAll(wordDict, "a","b","bbb","bbbb");
		System.out.println(new Solution().wordBreak2("bb", wordDict));
	}
       
       

	


}
