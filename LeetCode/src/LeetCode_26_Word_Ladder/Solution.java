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
	 * 再看看标准解法
	 * 单词变形，想到图
	 * BFS(广度优先搜索)保证最短路径
	 * 因为这里不需要输出路径，因此我们不需要保存前驱节点信息，需要保存的只是单词和距离，可以添加
	 * 一个node数据结构，也可以用两个队列来保持word和距离的对应关系
	 * 因为访问一个节点之后就不会再访问，则可以采取从字典里删除的方式来保证不会重复访问
	 * 因此，也不需要hash了
	 * 说到底，这是图的BFS问题
	 * 
	 */
	/**
	 * 
	 * 广度优先搜索的算法描述如下：
	 * (1)将起始节点放入队列的尾部
	 * (2)while(队列不为空){
	 * 		取得并删除队首节点
	 * 		处理该节点
	 * 		把node的未处理相邻接点加入到队列尾部
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
			String currWord = wordQueue.pop();//取得并删除队首节点
			Integer currDist = distQueue.pop();
			
			
			if(currWord.equals(end)){//处理该节点
				return currDist;
			}
			
			for(int i=0;i<currWord.length();i++){  //把全部未处理的相邻节点放入队列的尾部
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
	 * 我自己的解法失败了
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
