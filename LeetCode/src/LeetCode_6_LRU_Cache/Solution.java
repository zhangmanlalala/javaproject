package LeetCode_6_LRU_Cache;

import java.util.HashMap;

public class Solution {
	//˫������ڵ�
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
	
	//get��set����ԭ�Ӳ�������ʵ���߼�
	
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
	
	//����������������ɾ���ڵ�Ͱѽڵ�����Ϊͷ��㣬���԰��������ֳܷ���д����������
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
