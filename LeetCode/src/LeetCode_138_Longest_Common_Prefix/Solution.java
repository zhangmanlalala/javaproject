package LeetCode_138_Longest_Common_Prefix;

public class Solution {
	public static String LongestCommomPrefix(String[] str){
		String result ="";
		char car = 0 ;
		for(int j=0;;j++){
			for(int i=0;i<str.length-1;i++){
				if(str[i].length()<=j){
					return result;
				}else{
					car = str[0].charAt(j);
					if(str[i+1].charAt(j)!=car)
						return result;
				}
				
			}
			result+=String.valueOf(car);
		}
		
	}
	public static void main(String[] args) {
	   String[] str = {"abccde","abfre","abfegsh"};
	   System.out.println(LongestCommomPrefix(str));

	}

}
