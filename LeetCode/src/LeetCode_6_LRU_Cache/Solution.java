package LeetCode_6_LRU_Cache;

import java.util.HashMap;

public class Solution {
	//双向链表节点
	class DoubleLinkedListNode{
		public int key;
		public int value;
		public DoubleLinkedListNode pre;
		public DoubleLinkedListNode next;
		public DoubleLinkedListNode(int key,int value){
			this.key = key;
			this.value = value;
		}
	}
	
	
	private int capacity;
	private int size;
	private HashMap<Integer,DoubleLinkedListNode> map = new HashMap<Integer,DoubleLinkedListNode>();
	private DoubleLinkedListNode head;
	private DoubleLinkedListNode tail;
	
	public Solution(int capacity){
		this.capacity = capacity;
		size = 0;
	}
	
	//get和set调用原子操作方法实现逻辑
	
	public int get(int key){
		if(map.containsKey(key)){
			DoubleLinkedListNode curr = map.get(key);
			removeNode(curr);
			setHead(curr);
			return curr.value;
		}else{
			return -1;
		}
	}
	
	public void set(int key,int value){
		if(map.containsKey(key)){
			DoubleLinkedListNode curr = new DoubleLinkedListNode(key,value);
			map.put(key, curr);
			setHead(curr);
			if(size<capacity){
				size++;
			}else{
				map.remove(tail.key);
				removeNode(tail);
			}
		}
	}
	
	//两个基本操作就是删除节点和把节点设置为头结点，所以把两个功能分出来写成两个方法
	private void removeNode(DoubleLinkedListNode node){
		DoubleLinkedListNode pre = node.pre;
		DoubleLinkedListNode next = node.next;
		
		if(pre != null){
			pre.next = next;
		}else{
			head = next;
		}
		
		if(next!=null){
			next.pre = pre;
		}else{
			tail = pre;
			if(tail!=null){
				tail.next = null;
			}
		}
	}
	
	private void setHead(DoubleLinkedListNode node){
		node.next = head;
		node.pre = null;
		
		if(head!=null){
			head.pre = node;
			
		}
		
		head = node;
		
		if(tail == null){
			tail = node;
		}
	}
 }
