package _243_Shortest_Word_Distance;
/**
 * 
 *Total Accepted: 1754 Total Submissions: 4239 Difficulty: Easy

Given a list of words and two words word1 and word2, return the shortest distance
 between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both 
in the list. 
 * @author Administrator
 *
 */
public class Solution {
	 public int shortestDistance(String[] words, String word1, String word2) {  
		 
		 if(words==null || words.length<2){
			 return 0;
		 }
		 int pos1 = -1;
		 int pos2 = -1;
		 int distance = words.length;
		 for(int i=0;i<words.length;i++){
			 if(words[i].equals(word1)){
				 pos1 = i;
				 if(pos2!=-1 &&  pos1-pos2<distance) distance = pos1-pos2;
				 
			 }else if(words[i].equals(word2)){
				 pos2 = i;
				 if(pos1!=-1 && pos2-pos1<distance) distance = pos2 - pos1;
			 }
		 }

		 return distance;
	 }
}
