package LeetCode_141_Container_With_Most_Water;
/**
 * Given n non-negative integers a1, a2, ..., an, where each represents
 *  a point at coordinate (i, ai). n vertical lines are drawn such that 
 *  the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 *  which together with x-axis forms a container, such that the container
 *  contains the most water.
 * 
 * @author Administrator
 *分析，维持两个指针，分别从左右向中间收缩，同时计算装水量，然后更新最大装水量
 *如果在收缩过程中，碰到的高度还没有之前的高度高，那么计算出来的装水量一定比之前的小，所以可以忽略
 */
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static int maxArea(int[] height){
		int i ,j,lh,rh,area,tmp;
		i=0;
		j=height.length-1;
		lh=height[i];
		rh = height[j];
		area = 0;
		tmp = 0;
		while(i<j){
			tmp = Math.min(lh, rh)*(j-i);
			if(tmp>area){
				area = tmp;
			}
			if(lh<rh){
				while(i<j && height[i]<=lh){
					i++;
				}
				if(i<j){
					lh = height[i];
				}
			}else{
				while(i<j && height[j]<=rh){
					j--;
				}
				if(i<j){
					rh = height[j];
				}
			}
		}
		return area;
		
		
	}

}
