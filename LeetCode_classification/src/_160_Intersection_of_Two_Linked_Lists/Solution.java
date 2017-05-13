package _160_Intersection_of_Two_Linked_Lists;
/**
 * 
 * 
 * @author Administrator
 *
 */

/**
 *Write a program to find the node at which the intersection of two singly 
 *linked lists begins.
	For example, the following two linked lists:
	
	A:          a1 → a2
	                   ↘
	                     c1 → c2 → c3
	                   ↗            
	B:     b1 → b2 → b3
	
	begin to intersect at node c1. 
 * 
 * @author Administrator
 *
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        
        ListNode a = headA;
        ListNode b = headB;
        
        //if a & b have different len, then we will stop the loop after second iteration
        //小明从A走，小陈从B走，小明走完A后从B开始走，小陈走完B后从A开始走，他们俩走相同的步数
        //如果这两条路有交际，则小明和小陈一定会相遇
        while( a != b){
        	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;    
        }
        
        return a; 
    }
}
