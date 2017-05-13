package leetcode_056_merge_intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
	
	
	
    public List<Interval> merge(List<Interval> intervals) {
    	
    	//List<Interval> result = new ArrayList<Interval>();
        
    	//Interval first = intervals.get(0);
    	Collections.sort(intervals,new Comparator<Interval>(){

			@Override
			public int compare(Interval arg0, Interval arg1) {
				// TODO Auto-generated method stub
				return arg0.start-arg1.start;
			}
    		
    	});
    	
    	if(intervals==null || intervals.size()<=1){
    		return intervals;
    	}
    	int i=1;
    	while(i<intervals.size()){
    		
    		if(intervals.get(i).start <=intervals.get(i-1).end){
    			intervals.get(i).start = intervals.get(i-1).start;
    			if(intervals.get(i-1).end>intervals.get(i).end){
    				intervals.get(i).end = intervals.get(i-1).end;
    			}
    			intervals.remove(i-1);
    		}else{
    			i++;
    		}
        	
        }
    	
    	return intervals;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}



class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
}
