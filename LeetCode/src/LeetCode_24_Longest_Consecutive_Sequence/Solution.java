package LeetCode_24_Longest_Consecutive_Sequence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;



/**
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,

Given [100, 4, 200, 1, 3, 2],

The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 * @author Administrator
 *
 */
public class Solution {
    public int longestConsecutive(int[] nums) {
    	Map<Integer,Boolean> map =  new HashMap<Integer,Boolean>();
    	for(int i=0;i<nums.length;i++){
    		map.put(nums[i], false);
    	}

    	Queue<Integer> queue = new LinkedList<Integer>();
    	
    	int num=1;
    	int max_dist = Integer.MIN_VALUE;
    	while(!queue.isEmpty() || !map.isEmpty()){
    		if(!queue.isEmpty()){
	    		Integer tmp = queue.remove();	    		
	    		
	    		if(map.containsKey(tmp-1)  || map.containsKey(tmp+1)){
	    			if(map.containsKey(tmp-1) && map.get(tmp-1)==false){
	    				num++;
	    				queue.add(tmp-1);
	    				map.remove(tmp-1);
	  
	    			}
	    			
	    			if(map.containsKey(tmp+1) && map.get(tmp+1)==false){
	    				num++;
	    				queue.add(tmp+1);
	    				map.remove(tmp+1);
	    			}
	    		}else{
	    			if(num>max_dist){
	    				max_dist = num;
	    			}
	    		}
    		}else{
    			Integer ho = 0;
    			for(Map.Entry<Integer, Boolean> entry:map.entrySet()){
    				ho = entry.getKey();
    				queue.add(ho);
    				break;
    			}
    			map.remove(ho);
    			num = 1;
    		}
    		
    				
    	}
    	
    	return max_dist;
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[]{100, 4, 200, 1, 3, 2};
		System.out.println(new Solution().longestConsecutive(nums));
	}
}
