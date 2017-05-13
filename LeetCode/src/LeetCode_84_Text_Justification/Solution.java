package LeetCode_84_Text_Justification;

import java.util.ArrayList;
import java.util.List;

import sun.applet.Main;


/**
 *Given an array of words and a length L, format the text such that each line has exactly L characters 
 *and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a 
line do not divide evenly between words, the empty slots on the left will be assigned more spaces than 
the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16. 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	 public List<String> fullJustify(String[] words, int maxWidth) {
	        StringBuilder s = new StringBuilder();
	        List<String> list = new ArrayList<String>();
	        int sumLen = 0;
	        int wordLen = words[0].length();
	        int spaceNum = 0;
	        int start = 0;
	        int i=0;
	        boolean lastLine = false;
	        while(i<words.length){	        	
	        	while(sumLen+wordLen+spaceNum<=maxWidth){	        		
	        		sumLen = sumLen+wordLen;
	        		i++;
	        		spaceNum++;
	        		if(i==words.length){
	        			lastLine = true;
	        			break;
	        		}else{
	        			wordLen = words[i].length();
	        		}
	        	}
	        	spaceNum = maxWidth - sumLen;
	        	int everySpace = 0;
	        	int arr[];
	        	if(i-start-1>=1){
	        		arr = new int[i-start-1];
	        		int p = 0;
	        		while(p<spaceNum){
		        		for(int k=0;k<i-start-1;k++){
		        			if(p<spaceNum){
		        				arr[k] = arr[k]+1;
		        				p++;
		        			}else{
		        				break;
		        			}
		        		}
	        		}
	        		
	        	}else{
	        		arr = new int[1];
	        		arr[0] = spaceNum;
	        	}
	        	
	        	
	        	if(start == i-1){//只有一个单词的情况
	        		s.append(words[start++]);
	        		for(int h=0;h<arr[0];h++){
	        			s.append(" ");
	        		}
	        		
	        	}else if(start == i-2){//有两个单词的情况	
	        		if(!lastLine){
		        		s.append(words[start++]);
		        		for(int h=0;h<arr[0];h++){
		        			s.append(" ");
		        		}
		        		s.append(words[start++]);
	        		}else{
	        			s.append(words[start++]);
	        			s.append(" ");
	        			s.append(words[start++]);
	        			for(int h=0;h<maxWidth-sumLen-1;h++){
		        			s.append(" ");
		        		}
	        		}
		        	
	        	}else{
	        		if(!lastLine){
		        		int m=start;
		        		for( ;m<=i-2;m++){
		        			s.append(words[m]);			        		
			        		for(int g=0;g<arr[m-start];g++)
			        			s.append(" ");
		        		}
		        		s.append(words[m++]);
	        		}else{
	        			int m=start;
		        		for( ;m<=i-2;m++){
		        			s.append(words[m]);
		        			s.append(" ");
		        		}
		        		s.append(words[m++]);
		        		for(int h=0;h<maxWidth-sumLen-(i-start-1);h++){
		        			s.append(" ");
		        		}
	        		}
	        		
	        	}
	        	
	        	list.add(s.toString());
	        	s.delete(0, maxWidth);
	        	
	        	start = i;
	        	spaceNum = 0;
	        	sumLen = 0;
	        }
	        
	        return list;
	        
	 }
	 
	 public static void main(String[] args) {
		String[] words = {"My","momma","always","said,","\"Life","was","like","a","box","of","chocolates.","You","never","know","what","you're","gonna","get."} ;
		List<String> list = new Solution().fullJustify(words, 20);
		
		for(String temp:list){
			System.out.println(temp);
		}
	}
}
