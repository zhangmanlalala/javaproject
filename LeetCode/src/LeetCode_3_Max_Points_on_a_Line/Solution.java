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
		//�������еĵ㣬����Ӵ˵㿪ʼ�����ε��ֱ���ϵ�������
		//��Ϊǰ��ĵ��Ѿ������
		for(int i=0;i<points.length;i++){
			max = Math.max(max, maxPoints(points,i));
		}
		
		return max;
	}
	
	//�˺������㾭��points[start]������ֱ���ϵ�������
	public int maxPoints(Point[] points,int start){
		int max = 1;
		int x1 = points[start].x;
		int y1 = points[start].y;
		//dup��¼points[start]�غϵĵ�ĸ���
		
		int dup = 0;
		//hashmap��¼ĳб�ʶ�Ӧ�ĵ���
		HashMap<Double,Integer> map =  new HashMap<Double,Integer>();
		
		for(int i=start+1;i<points.length;i++){
			int x2 = points[i].x;
			int y2 = points[i].y;
			//Java��0���������õ�+0��0���Ը����õ�-0������0.0�������ߵ�����
			double slope = (x1==x2)? Double.POSITIVE_INFINITY:(double)(y2-y1)/(double)(x2-x1)+0.0;
			if(x1==x2 && y1==y2){//������ȵ����
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
