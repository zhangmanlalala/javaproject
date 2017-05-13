package _207_Course_Schedule;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to 
first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible 
for you to finish all courses?

For example:

2, [[1,0]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 So it is possible.

2, [[1,0],[0,1]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0, 
and to take course 0 you should also have finished course 1. So it is impossible. 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
	/**
	 * ���������壺�������޻�ͼ�еĶ��������Է�ʽ����ÿһ���ߵ���������������г������յ�֮ǰ��
	 * ��������������������ȷ��һ��������ϵ���У����﷢����˳�����磬���ճ������У����ܽ���Ŀ��ֳ�A,B,C��D4������Ŀ
	 * ����ɣ�����A������B��D��C������D��Ϊ�˼��������Ŀ�Ľ���˳�򣬿��Զ������ϵ��������������ó�һ���������У�������
	 * ǰ������������Ҫ����ɵ�����
	 * 
	 * ����Ӧ�ã�ѡ��
	 * 
	 * ע�⣺�õ������򲢲���Ψһ��
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	
	/**
	 * 
	 * ���������ʵ�֣�
	 * BFS������������������ڶ������Ĳ�α���
	 * �ؼ���Ѱ�����Ϊ0�Ķ��㣬Ҫά��һ�����Ϊ0�ļ���(����)
	 * 
	 * ��򵥵ķ�ʽ���Ƚ����Ϊ0�Ķ�����ڶ����У������в�Ϊ��ʱ��ɾ��һ������v��ͬʱ�����붥��v�ڽӵĶ������ȣ�������
	 * ���Ϊ0�Ķ���ŵ������У�ѭ�����У�������ӵ�˳��������������˳�򡣸��㷨��ʱ�临�Ӷ�ΪO(V+E)
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] map = new int[numCourses];
        for(int i=0;i<prerequisites.length;i++){
        	map[prerequisites[i][1]]++;//����������ж�������
        }
        
        Queue<Integer> que = new LinkedList<Integer>();
        
        for(int i=0;i<map.length;i++){
        	if(map[i] == 0) que.add(i);//�Ƚ����Ϊ0�Ķ��������
        }
        
        int count = que.size();
        while(!que.isEmpty()){
        	int k = que.remove();
        	for(int i=0;i<prerequisites.length;i++){
        		if(k == prerequisites[i][0]){
        			int l = prerequisites[i][1];
        			map[l]--;
        			if(map[l] == 0){
        				que.add(1);
        				++count;
        			}
        					
        		}
        	}
        }
        
        return count == numCourses;//����л�����count��ֵһ��С��numCourses���л��Ķ�����һ������������е�
    }
}
