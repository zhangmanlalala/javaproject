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
	 * ��׼�ⷨ
	 * ������
	 * �������ʻ���ĸ���뵽����ͼ��һ������ÿ��һ����ĸ��������һ�����ڽڵ�
	 * �������·�����뵽�ĵ���BFS(�����������)��BFS����ȷ����������һ�������·��
	 * Ҫ������·����BFS֮���Ҫ�Ӻ���ǰԭ·����
	 * Ҫ����������·������ÿһ���ڵ㶼Ҫά��һ������ǰ������list
	 * Ҫ�ж��ǲ���ǰ����㣬��ÿһ���ڵ�Ҫά��һ������
	 * 
	 * BFS�Ļ�����Ҫ����Ƿ���ʹ������Ӧ����Hash��ӦΪ��Ҫͨ��word����Node������Ӧ��ʹ��HashMap
	 * �������Ͽ��ǣ�һ���ڵ�Ҫ�����������word��Ҫ�����뿪ʼ�ڵ�ľ��룬Ҫ����һ��ǰ������list
	 * g�о�������е���
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
	        //��Ϊ��dict���word����ͼ��ϵ������Ҫ��end�ӵ��ֵ�����  
	        dict.add(end);  
	        //Node���Դ�Ÿ�����Ϣ������HashMap�������ͨ��word���ʵ����Ӧ��node  
	        Map<String, Node> map = new HashMap<String, Node>();  
	        //������ȱ�����Ȼ�õ�����  
	        Queue<String> queue = new LinkedList<String>();  
	          
	        Node startNode = new Node(1, start);  
	        queue.offer(start);  
	        map.put(start, startNode);  
	          
	        List<List<String>> ret = new ArrayList<List<String>>();  
	        //��ʼ����  
	        while(!queue.isEmpty()){  
	            //���ӵ�ǰ�ڵ�  
	            String str = queue.poll();  
	            //�ҵ��ˣ���ʼ����·��������Ҫ��Ѱ����ȥ  
	            if(str.equals(end)){  
	                getPaths(map.get(end), map, new ArrayList<String>(), ret);  
	                return ret;  
	            }  
	            //����str��������п���,ÿһ��λ�ö���������26����ĸ  
	            for(int i=0; i<str.length(); i++){  
	                for(int j=0; j<26; j++){  
	                    //�����µĵ���  
	                    String newStr = replace(str, i, (char)('a'+j));  
	                    //������ɵ��µ��ʲ����ֵ���Ͳ��ٿ�����  
	                    if(dict.contains(newStr)){  
	                        //���ֵ������û�з��ʹ�  
	                        if(!map.containsKey(newStr)){  
	                            Node node = map.get(str);  
	                            //�½��ڵ㣬�����ǰ����1  
	                            Node newNode = new Node(node.dist+1, newStr);  
	                            newNode.prev = new LinkedList<Node>();  
	                            //����ǰ���ڵ�  
	                            newNode.prev.add(node);  
	                            //����Hash��ϵ  
	                            map.put(newStr, newNode);  
	                            //��ӣ��μ�BFS  
	                            queue.offer(newStr);  
	                        }else{  
	                            //�Ѿ����ʹ�����Ҫ����ܲ��ܽ���ͼ��ϵ  
	                            Node node = map.get(newStr);  
	                            Node prevNode = map.get(str);  
	                            //�����������ϵ��Ӧ�ü�һ���ߣ�������ǰ��list  
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
	    //�滻�ַ������µ��ʵĸ�������  
	    private String replace(String str, int index, char c){  
	        StringBuilder sb = new StringBuilder(str);  
	        sb.setCharAt(index, c);  
	        return sb.toString();  
	    }  
	    //����ͼ��ϵ������·��  
	    private void getPaths(Node end, Map<String, Node> map, ArrayList<String> curPath, List<List<String>> paths){  
	        if(end == null){  
	            paths.add(curPath);  
	            return;  
	        }  
	        curPath.add(0, end.str);  
	        if(!end.prev.isEmpty()){  
	            //�ݹ�  
	            for(Node prevNode : end.prev){  
	                //��סÿһ�ζ�Ҫ����newһ��  
	                getPaths(prevNode, map, new ArrayList<String>(curPath), paths);  
	            }  
	        }else{  
	            getPaths(null, map, curPath, paths);  
	        }  
	    }  
}
