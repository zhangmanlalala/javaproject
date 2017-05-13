package LeetCode_141_Container_With_Most_Water;
/**
 * Given n non-negative integers a1, a2, ..., an, where each represents
 *  a point at coordinate (i, ai). n vertical lines are drawn such that 
 *  the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 *  which together with x-axis forms a container, such that the container
 *  contains the most water.
 * 
 * @author Administrator
 *������ά������ָ�룬�ֱ���������м�������ͬʱ����װˮ����Ȼ��������װˮ��
 *��������������У������ĸ߶Ȼ�û��֮ǰ�ĸ߶ȸߣ���ô���������װˮ��һ����֮ǰ��С�����Կ��Ժ���
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
