package _245_Shortest_Word_Distance_III;
/**
 * Question:

This is a follow up of Shortest Word Distance. The only difference is now word1 could 
be the same as word2. 
Given a list of words and two words word1 and word2, return the shortest distance 
between these two words in the list. 
word1 and word2 may be the same and they represent two individual words in the list. 
For example, 
Assume that words = ["practice", "makes", "perfect", "coding", "makes"]. 
Given word1 = ¡°makes¡±, word2 = ¡°coding¡±, return 1. 
Given word1 = "makes", word2 = "makes", return 3. 

Note: 

You may assume word1 and word2 are both in the list. 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	 public int shortestDistance(String[] words, String word1, String word2) {  
		 int distance = Integer.MAX_VALUE;
		 if(word1 == word2){
			 int first = -1;
			 int last = -1;
			 for(int i=0;i<words.length;i++){
				 if(words[i].equals(word1)){
					 
					 if(first==-1 && last == -1){
						 last = i;
					 }else{
						 first = last;
						 last = i;
						 distance = Math.min(distance, last-first);
					 }
				 }
			 }
			 return distance;
			 
		 }else{
			 
			 int m = -1;
			 int n = -1;
			 for(int i=0;i<words.length;i++){
				 if(words[i].equals(word1)){
					 m = i;
					 if(n!=-1 && m-n< distance) distance = m-n;
				 }else if(words[i].equals(word2)){
					 n = i;
					 if(m!=-1 && n-m<distance) distance = n-m;
				 }
			 }
			 
			 return distance;
		 }
	 }
}
