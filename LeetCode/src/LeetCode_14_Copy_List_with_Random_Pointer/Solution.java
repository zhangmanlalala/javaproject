package LeetCode_14_Copy_List_with_Random_Pointer;
/**
 * 
 *  A linked list is given such that each node contains 
 *  an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list. 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 标准解法：将复制的节点接在原来节点的后面，这样原来节点random所指节点的复制节点(即random.next)就是当前复制节点的random所要指向的节点
	 * @param head
	 * @return
	 */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null){
        	return null;
        }
        RandomListNode pointer = head;
        //第一步，复制每个节点，接在该节点正后面
        while(pointer!=null){
        	RandomListNode copy = new RandomListNode(pointer.label);
        	copy.next = pointer.next;
        	pointer.next = copy;
        	pointer = pointer.next.next;
        }
        //第二步，处理random指针，被复制节点random指针所指节点的复制节点(即一下节点)
        //便是复制节点random所应该指向的地方
        //注意处理random所指为null的情况
        pointer = head;
        while(pointer !=null){
        	if(pointer.random == null){
        		pointer.next.random = null;
        	}else{
        		pointer.next.random = pointer.random.next;
        	}
        	
        	pointer = pointer.next.next;
        }
        
        //第三部，把两条链表解耦
        //用node.next = node.next.next的方法可以解耦
        //注意最后空指针的情况
        pointer = head;
        RandomListNode newHead = head.next;
        RandomListNode newPointer = newHead;
        while(pointer!=null){
        	pointer.next = pointer.next.next;
        	newPointer.next = (pointer.next==null)? null:newPointer.next.next;
        	
        	pointer = pointer.next;
        	newPointer = newPointer.next;
        }
        return newHead;
    }
}



class RandomListNode {
     int label;
     RandomListNode next, random;
     RandomListNode(int x) { this.label = x; }
}
