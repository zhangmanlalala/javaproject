package leetcode_038_count_and_say;
/**
 * 
 * The count-and-say sequence is the sequence of integers beginning as follows:
	1, 11, 21, 1211, 111221, ...

	1 is read off as "one 1" or 11.
	11 is read off as "two 1s" or 21.
	21 is read off as "one 2, then one 1" or 1211.

	Given an integer n, generate the nth sequence.

	Note: The sequence of integers will be represented as a string. 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public String countAndSay(int n) {
        
        String pre = "1";
        int num = 0;

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<n;i++){
        	int j=1,first=0;
        	while(j<pre.length()){
        		if(pre.charAt(j) == pre.charAt(j-1)){
        			j++;
        		}else{
        			num = j-first;
        			sb.append(num).append(pre.charAt(first));
        			first = j;
        			j++;
        		}
        	}
        	
        	num = j-first;
			sb.append(num).append(pre.charAt(first));
			
			pre = sb.toString();
			sb.delete(0, sb.length());
		}
        	
        	
        	return pre;
       }
    
    public static void main(String[] args) {
		System.out.println(new Solution().countAndSay(4));
	}
    
    
}
