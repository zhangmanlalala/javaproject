package LeetCode_12_Word_Break_II;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, add spaces in 
 * s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given

 s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"]. 
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 
 * ������һ�����������У�Ӧ���뵽DFS�������㷨����Ӧ��ע��ָ��ֳ�
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * �����׼�𰸵Ĳ�ྐྵȻ������û�ж��ܲ��ָܷ�
	 * @param s
	 * @param wordDict
	 * @return
	 */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> lst = new ArrayList<String>();
        if(s==null || s.length()==0) return lst;  
        //�����ж�s�ܲ��ܸպñ��ָ�  
        //�����ö�̬�滮�ķ�����table[i]��¼[0,i)���������Ľ�  
        //[0,i)���Էָ�Ϊtrue,����Ϊfalse  
        boolean[] table = new boolean[s.length()+1];  
        table[0] = true;  
        for(int i=1; i<=s.length(); i++){  
            for(int k=0; k<i; k++){  
                //[0,k)���Էָͬʱ[k,i)���ֵ��˵��[0,i�����Էָ�  
                //��¼�м��������ظ����������  
                table[i] = table[k] && wordDict.contains(s.substring(k,i));  
                if(table[i] == true)  
                    break;  
            }  
        }  
        //���s���ܱ��ָ��û�б�Ҫ���������ȥ  
        if(!table[s.length()]) return lst;  
          
        StringBuilder sb = new StringBuilder();
        dfs(s,wordDict,sb,lst);
        return lst;
    }
    
    
    public void dfs(String s,Set<String> wordDict,StringBuilder sb,List<String> lst){
    	
    	if(s.length()==0){
    		String ss = sb.toString();
    		lst.add(ss.substring(0,ss.length()-1));
    	}
    	
    	for(int i=1;i<=s.length();i++){
    		String temp = s.substring(0, i);
    		if(wordDict.contains(temp)){
    			sb.append(temp);
    			sb.append(" ");
    			
    			dfs(s.substring(i,s.length()),wordDict,sb,lst);
    			
    			sb.delete(sb.length()-temp.length()-1, sb.length());
    		}
    	}
    }
    
    public static void main(String[] args) {
    	
    	Set<String> wordDict = new HashSet<String>();
    	Collections.addAll(wordDict, "cat", "cats", "and", "sand", "dog");
    	
		System.out.println(new Solution().wordBreak2("catsanddog", wordDict).toString());
	}
   
    
    /**
     * 
     * �ҵ��㷨ʱ����Խ���ˣ����ǿ���׼�㷨��
     * 
     * �������������˼·����������ԣ��ӵ�һ���ַ���ʼ��飬�ܷ����ֵ����ҵ��Ӵ�
     * ��������ҵ�������⣬�ڴ�����λ�ÿ�ʼ�ң�֪����󣬸պý��������ҵ�һ����
     * ��Ϊ���ܴ�ĳ��λ�ÿ�ʼ���ҵ�������ͬ�ĵ��ʣ�����Ҫ����Ѱ�Ҳ�ͬ�Ľ�
     * 
     * �����ɣ�
     * 1�����ж������ַ����ܲ��ܸպ÷ָ��Ϊ���ظ����⣬���Ƕ�̬�滮
     * 2���õݹ���������ݣ�����һ�����ʣ��ݹ�������⣬֮���ٽ�ȥ������ʣ��Ա�֤���Ա����Դ�
     * �㿪ʼ�����е���
     * 
     */
    
    
    public List<String> wordBreak2(String s, Set<String> dict) {  
    	List<String> res = new ArrayList<String>();
    	if(s==null || s.length()==0) return res;
    	
    	//�����ж�s�ܲ��ܸպ÷ָ�
    	//����ʹ�ö�̬�滮��table[i]��¼[0,i)���������
    	//[0,i)���Էָ�Ϊtrue��false
    	
    	boolean[] table = new boolean[s.length()+1];
    	table[0] = true;
    	for(int i=1;i<s.length();i++){
    		for(int k=0;k<i;k++){
    			//[0,k)���Էָͬʱ[k,i)���ֵ��˵��[0,i)���Էָ�
    			//��¼�м��������ظ����������
    			table[i] = table[k] && dict.contains(s.substring(k, i));
    			if(table[i]==true){
    				break;
    			}
    		}
    	}
    	
    	//���s���ָܷ��û��Ҫ���������ȥ
    	if(!table[s.length()]) return res;
    	
    	
    	StringBuilder sb = new StringBuilder();
    	dfs2(s, 0, sb, res, dict);  
        return res;
    }
    
    private void dfs2(String s, int start, StringBuilder sb, List<String> res, Set<String> dict){  
        //�ݹ��ս�  
        if(start >= s.length()){  
            //ע��Ҫ�½�һ���ַ���  
            res.add(new String(sb));  
            return;  
        }  
        for(int i=start+1; i<=s.length(); i++){  
            String sub = s.substring(start, i);  
            if(dict.contains(sub)){  
                int currLen = sb.length();  
                if(currLen != 0){  
                    sb.append(" ");  
                }  
                sb.append(sub);  
                dfs2(s, i, sb, res, dict);  
                sb.delete(currLen, sb.length());  
            }  
        }  
    }  
    
    
}
