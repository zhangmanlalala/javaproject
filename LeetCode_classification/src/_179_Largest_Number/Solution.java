package _179_Largest_Number;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
 * 
 * @author Administrator
 *
 */

public class Solution {
    public String largestNumber(int[] nums) {
        String[] str = new String[nums.length];
        
    	for(int i=0;i<nums.length;i++){
    		str[i] = String.valueOf(nums[i]);
    	}
    	Arrays.sort(str,new Comparator<String>(){

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub	
				int i=0;
				String str1 = arg0+arg1;
				String srt2 = arg1+arg0;
				while(i<str1.length()){
					if(str1.charAt(i)==srt2.charAt(i)){
						i++;
					}else if(str1.charAt(i)>srt2.charAt(i)){
						return 1;
					}else{
						return -1;
					}
				}
				return 0;
				
			
			}
    		
    	});
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i=str.length-1;i>=0;i--){
    		sb.append(str[i]);
    	}
    	if(sb.toString().charAt(0) =='0'){
    		return "0";
    	}else{
    		return sb.toString();
    	}
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[]{3, 30, 34, 5, 9};
		System.out.println(new Solution().largestNumber(nums));
	}
}
