package LeetCode_19_Clone_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:

Nodes are labeled uniquely.
We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

    First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
    Second node is labeled as 1. Connect node 1 to node 2.
    Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.

Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 
 * 
 * 
 * @author Administrator
 *
 */

class UndirectedGraphNode { 
     int label; 
     List<UndirectedGraphNode> neighbors; 
     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); } 
} 
public class Solution {
	
	/**
	 * 广度优先搜索，设计队列的应用
	 * 节点有没有访问过的标记，HashMap的应用
	 * 
	 * @param node
	 * @return
	 */
	
	/**
	 * 
	 * 关于广度优先搜索的一个很重要的题
	 * @param node
	 * @return
	 */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        //广度优先搜索的常规技术，利用队列
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        
        //HashMap用来标记节点有没有访问过
        HashMap<UndirectedGraphNode,UndirectedGraphNode> hashMap = new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
        
        //先把第一个节点放进队列，完成初始化
        queue.add(node);
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        
        hashMap.put(node, clone);
        //开始遍历(while循环)
        while(!queue.isEmpty()){
        	UndirectedGraphNode curr = queue.remove();//弹出队首元素
        	UndirectedGraphNode currClone = hashMap.get(curr);
        	
        	//对于当前节点的邻居
        	for(UndirectedGraphNode item:curr.neighbors){
        		//如果在hashMap里，说明已经访问过了，建立指针关系即可
        		if(hashMap.containsKey(item)){
        			currClone.neighbors.add(hashMap.get(item));
        		}else{//如果没有在hashMap里，说明没有访问过，除了新建节点并建立指针关系外，
        			//还要再hashMap里放入新对，并将没访问过的节点入队
        			UndirectedGraphNode temp = new UndirectedGraphNode(item.label);
        			currClone.neighbors.add(temp);
        			queue.add(item);
        			hashMap.put(item, temp);
        			
        		}
        	}
        }
        
        
        return clone;
    }
}
