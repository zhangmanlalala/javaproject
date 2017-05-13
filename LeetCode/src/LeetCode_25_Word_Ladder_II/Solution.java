package LeetCode_25_Word_Ladder_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, 
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:

    Only one letter can be changed at a time
    Each intermediate word must exist in the word list

For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"] 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 标准解法
	 * 分析：
	 * 看到单词换字母，想到的是图，一个单词每换一个字母，就生成一个相邻节点
	 * 看到最短路径，想到的的是BFS(广度优先搜素)，BFS可以确保搜索到的一定是最短路径
	 * 要输出最短路径，BFS之后就要从后往前原路返回
	 * 要输出所有最短路径，则每一个节点都要维护一个所有前驱结点的list
	 * 要判断是不是前驱结点，则每一个节点要维护一个距离
	 * 
	 * BFS的话，就要标记是否访问过，这个应该用Hash，应为需要通过word访问Node，所以应该使用HashMap
	 * 基于以上考虑，一个节点要保存所代表的word，要保存离开始节点的距离，要保存一个前驱结点的list
	 * g感觉这个题有点难
	 * 
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	 class Node{  
	        public int dist;  
	        public String str;  
	        public LinkedList<Node> prev;  
	          
	        public Node(int dist, String str){  
	            this.dist = dist;  
	            this.str = str;  
	            this.prev = new LinkedList<Node>();  
	        }  
	          
	        public void addPrev(Node pNode){  
	            prev.add(pNode);  
	        }  
	    }  
	 public List<List<String>> findLadders(String start, String end, Set<String> dict) {  
	        //因为对dict里的word建立图关系，所以要把end加到字典里先  
	        dict.add(end);  
	        //Node可以存放更多信息，利用HashMap可以最快通过word访问到相对应的node  
	        Map<String, Node> map = new HashMap<String, Node>();  
	        //广度优先遍历当然用到队列  
	        Queue<String> queue = new LinkedList<String>();  
	          
	        Node startNode = new Node(1, start);  
	        queue.offer(start);  
	        map.put(start, startNode);  
	          
	        List<List<String>> ret = new ArrayList<List<String>>();  
	        //开始遍历  
	        while(!queue.isEmpty()){  
	            //出队当前节点  
	            String str = queue.poll();  
	            //找到了，开始生成路径，不需要再寻找下去  
	            if(str.equals(end)){  
	                getPaths(map.get(end), map, new ArrayList<String>(), ret);  
	                return ret;  
	            }  
	            //尝试str变体的所有可能,每一个位置都尝试所有26个字母  
	            for(int i=0; i<str.length(); i++){  
	                for(int j=0; j<26; j++){  
	                    //生成新的单词  
	                    String newStr = replace(str, i, (char)('a'+j));  
	                    //如果生成的新单词不再字典里，就不再考虑了  
	                    if(dict.contains(newStr)){  
	                        //在字典里，并且没有访问过  
	                        if(!map.containsKey(newStr)){  
	                            Node node = map.get(str);  
	                            //新建节点，距离比前驱加1  
	                            Node newNode = new Node(node.dist+1, newStr);  
	                            newNode.prev = new LinkedList<Node>();  
	                            //加入前驱节点  
	                            newNode.prev.add(node);  
	                            //建立Hash关系  
	                            map.put(newStr, newNode);  
	                            //入队，参加BFS  
	                            queue.offer(newStr);  
	                        }else{  
	                            //已经访问过，则要检查能不能建立图关系  
	                            Node node = map.get(newStr);  
	                            Node prevNode = map.get(str);  
	                            //如果满足距离关系，应该加一条边，即加入前驱list  
	                            if(node.dist == prevNode.dist+1){  
	                                node.addPrev(prevNode);  
	                            }  
	                        }  
	                    }  
	                }  
	            }  
	        }  
	        return ret;  
	    }  
	    //替换字符生成新单词的辅助函数  
	    private String replace(String str, int index, char c){  
	        StringBuilder sb = new StringBuilder(str);  
	        sb.setCharAt(index, c);  
	        return sb.toString();  
	    }  
	    //根据图关系，生成路径  
	    private void getPaths(Node end, Map<String, Node> map, ArrayList<String> curPath, List<List<String>> paths){  
	        if(end == null){  
	            paths.add(curPath);  
	            return;  
	        }  
	        curPath.add(0, end.str);  
	        if(!end.prev.isEmpty()){  
	            //递归  
	            for(Node prevNode : end.prev){  
	                //记住每一次都要重新new一个  
	                getPaths(prevNode, map, new ArrayList<String>(curPath), paths);  
	            }  
	        }else{  
	            getPaths(null, map, curPath, paths);  
	        }  
	    }  
}
