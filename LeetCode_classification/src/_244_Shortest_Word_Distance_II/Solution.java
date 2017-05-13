package _244_Shortest_Word_Distance_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *  This is a follow up of Shortest Word Distance. The only difference is 
 *  now you are given the list of words and your method will be called repeatedly 
 *  many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements
 a method that takes two words word1 and word2 and return the shortest distance 
 between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

You may assume that word1 does not equal to word2, and word1 and word2 
are both in the list.
 * @author Administrator
 *
 */
public class Solution {
	private Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
	public Solution(String[] words){
		for(int i=0;i<words.length;i++){
			List<Integer> positions = map.get(words[i]);
			if(positions == null){
				positions = new ArrayList<>();
				map.put(words[i], positions);
			}
			positions.add(i);
		}
	}
	
	public int shortest(String word1,String word2){
		int distance = Integer.MAX_VALUE;
		List<Integer> positions1 = map.get(word1);
		List<Integer> positions2 = map.get(word2);
		int i=0;
		int j=0;
		while(i<positions1.size() && j<positions2.size()){
			//下面才是最经典的地方
			if(positions1.get(i)<positions2.get(j)){
				if(positions2.get(j)-positions1.get(i) <distance) distance = positions2.get(j)-positions1.get(i);
				i++;
			}else{
				if(positions1.get(i) - positions2.get(j) < distance) distance = positions1.get(i) - positions2.get(j);
				j++;
			}
		}
		
		return distance;
	}
}
