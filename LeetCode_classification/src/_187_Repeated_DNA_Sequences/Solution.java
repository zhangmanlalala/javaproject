package _187_Repeated_DNA_Sequences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 *   for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than 
once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

 * 
 * @author Administrator
 *
 */
public class Solution {
    public List<String> findRepeatedDnaSequences2(String s) {
       Set<String> set = new HashSet<String>();
       
       for(int i=10;i<=s.length()-10;i++){
    	   String temp = s.substring(i-10, i);
    	   String right = s.substring(i-9,s.length());
    	   if(right!=null && right.indexOf(temp)!=-1){
    		   set.add(temp);
    	   }
       }
       List<String> lst = new ArrayList<String>(set);
       return lst;
    }
    
    /**
     * 
     * 还是看标准解法
     */
    public List<String> findRepeatedDnaSequences(String s){
    	Set<String> seen = new HashSet<String>();
    	Set<String> repeated = new HashSet<String>();
    	for(int i=0;i+9<s.length();i++){
    		String ten = s.substring(i, i+10);
    		if(!seen.add(ten)){//代表这是一个重复的
    			repeated.add(ten);
    		}
    	}
    	
    	return new ArrayList<String>(repeated);
    }
}
