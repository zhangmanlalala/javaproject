package _228_Summary_Ranges;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].  
 * 
 * @author Administrator
 *
 */
public class Solution {
    public List<String> summaryRanges(int[] nums) {
    	List<String> lst = new ArrayList<String>();
    	if(nums == null){
    		return lst;
    	}
        int pre = nums[0];
        int last = nums[0];
        String temp;
        for(int i=1;i<nums.length;i++){
        	if(nums[i] - last == 1){
        		last = nums[i];
        	}else{
        		temp = null;
        		if(last == pre){
        			temp = pre+"";
        		}else{
        			temp = pre+"->"+last;
        		}
        		lst.add(temp);
        		
        		pre = nums[i];
        		last = nums[i];
        	}
        }
        
        temp = null;
		if(last == pre){
			temp = pre+"";
		}else{
			temp = pre+"->"+last;
		}
		lst.add(temp);
		
		return lst;
    }
    
    public static void main(String[] args) {
    	int nums[] = new int[]{0,1,2,4,5,7};
		System.out.println(new Solution().summaryRanges(nums).toString());
	}
}
