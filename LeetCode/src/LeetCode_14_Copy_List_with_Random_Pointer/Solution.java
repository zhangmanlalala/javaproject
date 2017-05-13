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
	 * ��׼�ⷨ�������ƵĽڵ����ԭ���ڵ�ĺ��棬����ԭ���ڵ�random��ָ�ڵ�ĸ��ƽڵ�(��random.next)���ǵ�ǰ���ƽڵ��random��Ҫָ��Ľڵ�
	 * @param head
	 * @return
	 */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null){
        	return null;
        }
        RandomListNode pointer = head;
        //��һ��������ÿ���ڵ㣬���ڸýڵ�������
        while(pointer!=null){
        	RandomListNode copy = new RandomListNode(pointer.label);
        	copy.next = pointer.next;
        	pointer.next = copy;
        	pointer = pointer.next.next;
        }
        //�ڶ���������randomָ�룬�����ƽڵ�randomָ����ָ�ڵ�ĸ��ƽڵ�(��һ�½ڵ�)
        //���Ǹ��ƽڵ�random��Ӧ��ָ��ĵط�
        //ע�⴦��random��ָΪnull�����
        pointer = head;
        while(pointer !=null){
        	if(pointer.random == null){
        		pointer.next.random = null;
        	}else{
        		pointer.next.random = pointer.random.next;
        	}
        	
        	pointer = pointer.next.next;
        }
        
        //���������������������
        //��node.next = node.next.next�ķ������Խ���
        //ע������ָ������
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
