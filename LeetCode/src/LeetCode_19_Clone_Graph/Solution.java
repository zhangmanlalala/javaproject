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
	 * ���������������ƶ��е�Ӧ��
	 * �ڵ���û�з��ʹ��ı�ǣ�HashMap��Ӧ��
	 * 
	 * @param node
	 * @return
	 */
	
	/**
	 * 
	 * ���ڹ������������һ������Ҫ����
	 * @param node
	 * @return
	 */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        //������������ĳ��漼�������ö���
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        
        //HashMap������ǽڵ���û�з��ʹ�
        HashMap<UndirectedGraphNode,UndirectedGraphNode> hashMap = new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
        
        //�Ȱѵ�һ���ڵ�Ž����У���ɳ�ʼ��
        queue.add(node);
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        
        hashMap.put(node, clone);
        //��ʼ����(whileѭ��)
        while(!queue.isEmpty()){
        	UndirectedGraphNode curr = queue.remove();//��������Ԫ��
        	UndirectedGraphNode currClone = hashMap.get(curr);
        	
        	//���ڵ�ǰ�ڵ���ھ�
        	for(UndirectedGraphNode item:curr.neighbors){
        		//�����hashMap�˵���Ѿ����ʹ��ˣ�����ָ���ϵ����
        		if(hashMap.containsKey(item)){
        			currClone.neighbors.add(hashMap.get(item));
        		}else{//���û����hashMap�˵��û�з��ʹ��������½��ڵ㲢����ָ���ϵ�⣬
        			//��Ҫ��hashMap������¶ԣ�����û���ʹ��Ľڵ����
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
