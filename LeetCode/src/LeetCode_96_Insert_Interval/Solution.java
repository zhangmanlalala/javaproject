package LeetCode_96_Insert_Interval;

import java.util.ArrayList;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

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
	
	public ArrayList<Interval> mergeIntervals(ArrayList<Interval> list,Interval interval){
		if(list.size()==0){
			list.add(interval);
			return list;
		}
		int first=-1;
		int last=-1;
		for(int i=0;i<list.size();i++){
			if(first!=-1 && last!=-1){
				break;
			}
			if(interval.start>=list.get(i).start && interval.start<=list.get(i).end){
				first = i;
			}
			if(interval.end>=list.get(i).start && interval.end<=list.get(i).end){
				last = i;
			}
		}
		if(first==last && first==-1 ){
			list.add(interval);
			return list;
		}else if(first==last && first != -1){
			return list;
		}else{
			if(first!=-1 && last==-1){
				list.get(first).end = interval.end;
			}else if(first==-1 && last!=-1){
				list.get(last).start = interval.start;
			}else{
				list.get(first).end = interval.end;
				for(int j=first+1;j<=last;j++ ){
					list.remove(j);
				}
			}
			return list;
		}
	}
}

class Interval{
	int start;
	int end;
	public Interval(){
		this.start = 0;
		this.end = 0;
	}
	public Interval(int start,int end){
		this.start = start;
		this.end = end;
	}
}
