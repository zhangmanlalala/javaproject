package LeetCode_97_Merge_Intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * Given a collection of intervals, merge all overlapping intervals.
  For example,
  Given [1,3],[2,6],[8,10],[15,18],

  return [1,6],[8,10],[15,18].
 * 
 * @author Administrator
 *
 */
public class Solution {
	
		public ArrayList<Interval> merge(ArrayList<Interval> intervals){
			ArrayList<Interval> res = new ArrayList<Interval>();
			if(intervals == null){
				return res;
			}
			Collections.sort(intervals, new Comparator<Interval>(){

				@Override
				public int compare(Interval o1, Interval o2) {
					if(o1.start > o2.start){
						return 1;
					}else if(o1.start == o2.start){
						return 0;
					}else{
						return -1;
					}
				}
				
			});
			
			int i=0;
			while(i < intervals.size()){
				int j = i+1;
				int end = intervals.get(i).end;
				while(j<intervals.size() && intervals.get(j).start<=end){
					end = Math.max(end, intervals.get(j).end);
					j++;
				}
				res.add(new Interval(intervals.get(i).start,end));
				i = j;
			}
			return res;
			
		}


}

class Interval{
	int start;
	int end;
	Interval(){
		start = 0;
		end = 0;
	}
	Interval(int start,int end){
		this.start = start;
		this.end = end;
	}
}