package LeetCode_3_Max_Points_on_a_Line;

import java.util.HashMap;

/**
 * 
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 
 * @author Administrator
 *
 */

class Point { 
     int x; 
    int y; 
     Point() { x = 0; y = 0; } 
    Point(int a, int b) { x = a; y = b; } 
} 
public class Solution {
	public int maxPoints(Point[] points){
		if(points == null || points.length == 0)
			return 0;
		int max=1;
		//遍历所有的点，计算从此点开始经过次点的直线上的最大点数
		//因为前面的点已经计算过
		for(int i=0;i<points.length;i++){
			max = Math.max(max, maxPoints(points,i));
		}
		
		return max;
	}
	
	//此函数计算经过points[start]的所有直线上的最大点数
	public int maxPoints(Point[] points,int start){
		int max = 1;
		int x1 = points[start].x;
		int y1 = points[start].y;
		//dup记录points[start]重合的点的个数
		
		int dup = 0;
		//hashmap记录某斜率对应的点数
		HashMap<Double,Integer> map =  new HashMap<Double,Integer>();
		
		for(int i=start+1;i<points.length;i++){
			int x2 = points[i].x;
			int y2 = points[i].y;
			//Java中0除以正数得到+0，0除以负数得到-0，加上0.0消除两者的区别
			double slope = (x1==x2)? Double.POSITIVE_INFINITY:(double)(y2-y1)/(double)(x2-x1)+0.0;
			if(x1==x2 && y1==y2){//处理相等的情况
				dup++;
				continue;
			}
			int count = 0;
			if(map.containsKey(slope)){
				count = map.get(slope)+1;
			}else{
				count = 2;
			}
			map.put(slope, count);
			max = Math.max(max, count);
		}
		return dup+max;
	}
	
}
