package LeetCode_26_Word_Ladder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 
 *  Given two words (beginWord and endWord), and a dictionary's word list, 
 *  find the length of shortest transformation sequence from beginWord to endWord, such that:
    Only one letter can be changed at a time
    Each intermediate word must exist in the word list
	For example,
	
	Given:
	beginWord = "hit"
	endWord = "cog"
	wordList = ["hot","dot","dog","lot","log"]
	
	As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
	return its length 5. 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * �ٿ�����׼�ⷨ
	 * ���ʱ��Σ��뵽ͼ
	 * BFS(�����������)��֤���·��
	 * ��Ϊ���ﲻ��Ҫ���·����������ǲ���Ҫ����ǰ���ڵ���Ϣ����Ҫ�����ֻ�ǵ��ʺ;��룬�������
	 * һ��node���ݽṹ��Ҳ��������������������word�;���Ķ�Ӧ��ϵ
	 * ��Ϊ����һ���ڵ�֮��Ͳ����ٷ��ʣ�����Բ�ȡ���ֵ���ɾ���ķ�ʽ����֤�����ظ�����
	 * ��ˣ�Ҳ����Ҫhash��
	 * ˵���ף�����ͼ��BFS����
	 * 
	 */
	/**
	 * 
	 * ��������������㷨�������£�
	 * (1)����ʼ�ڵ������е�β��
	 * (2)while(���в�Ϊ��){
	 * 		ȡ�ò�ɾ�����׽ڵ�
	 * 		����ýڵ�
	 * 		��node��δ�������ڽӵ���뵽����β��
	 * }
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	
	public int ladderLength2(String start, String end, HashSet<String> dict) {  
		if(dict.isEmpty()){
			return 0;
		}
		LinkedList<String> wordQueue = new LinkedList<String>();
		LinkedList<Integer> distQueue = new LinkedList<Integer>();
		wordQueue.add(start);
		distQueue.add(1);
		
		while(!wordQueue.isEmpty()){
			String currWord = wordQueue.pop();//ȡ�ò�ɾ�����׽ڵ�
			Integer currDist = distQueue.pop();
			
			
			if(currWord.equals(end)){//����ýڵ�
				return currDist;
			}
			
			for(int i=0;i<currWord.length();i++){  //��ȫ��δ��������ڽڵ������е�β��
				char[] charArr = currWord.toCharArray();
				for(char ch='a';ch<='z';ch++){
					charArr[i] = ch;
					
					String newWord = new String(charArr);
					if(dict.contains(newWord)){
						wordQueue.add(newWord);
						distQueue.add(currDist+1);
						dict.remove(newWord);
					}
				}
			}
		}
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * ���Լ��Ľⷨʧ����
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    	if(wordList==null){
    		return 0;
    	}
    	if(isNear(beginWord,endWord)){
    		return 2;
    	}

    	if(wordList.contains(beginWord)){
    		wordList.remove(beginWord);
    	}
    	if(wordList.contains(endWord)){
    		wordList.remove(endWord);
    	}
    	ladderLength(beginWord,endWord,wordList,0);
    	if(min_num>0){
    		return min_num+2;
    	}else{
    		return min_num;
    	}
    }
    private int min_num = 0;
    private void ladderLength(String beginWord, String endWord,Set<String> wordList ,int num){
    	
    	if(wordList == null){
    		return ;
    	}
    	if(isNear(beginWord,endWord)){
    		if(min_num == 0){
				min_num = num;
			}else{
    			if(num<min_num){
    				min_num = num;
    			}
			}
			return ;
    	}
    	List<String> begin = new ArrayList<String>();
    	List<String> end = new ArrayList<String>();
    	for(String str:wordList){
    		if(isNear(str,beginWord) && isNear(str,endWord)){
    			num++;
    			if(min_num == 0){
    				min_num = num;
    			}else{
	    			if(num<min_num){
	    				min_num = num;
	    			}
    			}
    			return ;
    		}else if(isNear(str,beginWord)){
    			begin.add(str);
    		}else if(isNear(str,endWord)){
    			end.add(str);
			}
    	}
    	
    	if(begin.isEmpty() || end.isEmpty()){
    		return ;
    	}else{
    		for(int i=0;i<begin.size();i++){
    			
    			for(int j=0;j<end.size();j++){
    				Set<String> set = new HashSet<String>(wordList);
    				set.remove(begin.get(i));
    				set.remove(end.get(j));
    				ladderLength(begin.get(i),end.get(j),set,num+2);
    			}
    		}
    	}
    	
    }
    			
    public static void main(String[] args) {
    	HashSet<String> set = new HashSet<String>();
		Collections.addAll(set,"miss","dusk","kiss","musk","tusk","diss","disk","sang","ties","muss");
		System.out.println(new Solution().ladderLength2("kiss", "tusk", set));
	}	
    	

    private boolean isNear(String s1,String s2){
    	if(s1.length()!=s2.length()){
    		return false;
    	}
    	int num = 0;
    	for(int i=0;i<s1.length();i++){
    		if(num>1){
    			break;
    		}
    		if(s1.charAt(i) != s2.charAt(i)){
    			num++;
    		}
    	}
    	if(num<=1){
    		return true;
    	}else{
    		return false;
    	}
    }
}
