package _210_Course_Schedule_II;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to 
first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering 
of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to 
finish all courses, return an empty array.

For example:

2, [[1,0]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the
 correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]

There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 
2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is 
[0,1,2,3]. Another correct ordering is[0,2,1,3].
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
    	int[] map = new int[numCourses];
    	if(prerequisites == null || prerequisites.length==0 || numCourses<=0){
    		return map;
    	}
    	
    	for(int i=0;i<prerequisites.length;i++){
    		map[prerequisites[i][0]]++;
    	}
    	
    	Queue<Integer> queue = new LinkedList<Integer>();
    	for(int i=0;i<numCourses;i++){
    		if(map[i] == 0){
    			queue.add(i);
    		}
    	}
    	
    	int count = queue.size();
    	int result[] = new int[numCourses];
    	int i=0;
    	while(!queue.isEmpty()){
    		int temp = queue.remove();
    		result[i] = temp;
    		i++;
    		for(int j=0;j<prerequisites.length;j++){
    			if(temp == prerequisites[j][1]){
    				int node = prerequisites[j][0];
    				map[node]--;
    				if(map[node] == 0){
    					queue.add(node);//如果有环，换是不可能入队列的，环的任意节点的入度不可能为0；
    					count++;
    				}
    			}
    		}
    	}
    	
    	if(count == numCourses){
    		return result;
    	}else{
    		int arr[] = new int[]{};
    		return arr;
    	}
    }
}
