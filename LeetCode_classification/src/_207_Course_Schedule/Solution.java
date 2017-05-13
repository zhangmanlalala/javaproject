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
	 * 拓扑排序定义：将有向无环图中的顶点以线性方式排序，每一条边的起点在线性序列中出现在终点之前。
	 * 拓扑排序：拓扑排序常用来确定一个依赖关系集中，事物发生的顺序。例如，在日常工作中，可能将项目拆分成A,B,C和D4个子项目
	 * 来完成，但是A依赖于B和D，C依赖于D。为了计算这个项目的进行顺序，可以对这个关系集进行拓扑排序得出一个线性序列，这排在
	 * 前面的任务就是需要先完成的任务。
	 * 
	 * 典型应用：选课
	 * 
	 * 注意：得到的排序并不是唯一的
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	
	/**
	 * 
	 * 拓扑排序的实现：
	 * BFS广度优先搜索，类似于二叉树的层次遍历
	 * 关键是寻找入度为0的顶点，要维护一个入度为0的集合(队列)
	 * 
	 * 最简单的方式：先将入度为0的顶点放在队列中，当队列不为空时，删除一个顶点v，同时更新与顶点v邻接的顶点的入度，将其中
	 * 入度为0的顶点放到队列中，循环进行，顶点出队的顺序就是拓扑排序的顺序。该算法的时间复杂度为O(V+E)
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] map = new int[numCourses];
        for(int i=0;i<prerequisites.length;i++){
        	map[prerequisites[i][1]]++;//计算出了所有定点的入度
        }
        
        Queue<Integer> que = new LinkedList<Integer>();
        
        for(int i=0;i<map.length;i++){
        	if(map[i] == 0) que.add(i);//先将入度为0的顶点入队列
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
        
        return count == numCourses;//如果有环，则count的值一定小于numCourses，有环的顶点是一定不可能入队列的
    }
}
