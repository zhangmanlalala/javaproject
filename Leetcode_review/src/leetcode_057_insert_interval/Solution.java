package leetcode_057_insert_interval;

import java.util.List;

/**
 * 
 *Given a set of non-overlapping intervals, insert a new interval 
 *into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].  
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
	
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        
    	if(intervals==null){
    		return intervals;
    	}else if(intervals.size()==0){
    		intervals.add(newInterval);
    		return intervals;
    		
    	}
    	
    	
    	int i=0;
    	while(i<intervals.size()){
    		
    		if(intervals.get(i).end >=newInterval.start){
    			break;
    			
    		}else{
    			i++;
    		}
    	}
    	if(i==intervals.size()){//说明没找到
    		intervals.add(newInterval);
    		return intervals;
    		
    	}
    	
    	int j=intervals.size()-1;
    	while(j>=0){
    		
    		if(intervals.get(j).start<=newInterval.end ){
    			break;
    		}else{
    			j--;
    		}
    	}
    	
    	if(j==-1){
    		intervals.add(0,newInterval);
    		return intervals;
    	}
    	
    	
    	
    	if(intervals.get(i).start<newInterval.start){
    		newInterval.start = intervals.get(i).start;
    	}
    	
    	if(intervals.get(j).end>newInterval.end){
    		newInterval.end = intervals.get(j).end;
    	}
    	
    	for(int m=i;m<=j;m++){
    		intervals.remove(i);
    	}
    	
    	intervals.add(i,newInterval);
    	
    	return intervals;
    	
    }
    
    public static void main(String[] args) {
		
	}
}


class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
}
