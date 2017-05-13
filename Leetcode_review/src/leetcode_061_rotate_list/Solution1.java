package leetcode_061_rotate_list;


/**
 * 
 * �������ͣ���һ������ĵ�����k���ڵ�
 * @author Administrator
 *
 */
//������ʹ��˫ָ�뷨����һ��ָ������k-1����Ȼ������ָ��һ���ߣ���һ��ָ���ߵ�β�ڵ�ʱ���ڶ���ָ��ָ��ľ��ǵ�����
//k��λ��ע
//ע������壺k�п��ܴ��ڽڵ�����
public class Solution1 {
	
	public static ListNode findKthToTail(ListNode head,int k){		
		if(head == null || head.next == null){
			return head;
		}	
		
		ListNode slow = head,fast = head;		
		for(int i=0;i<k-1;i++){//��ָ������k-1��
			fast = fast.next;
			if(fast == null){//��ֹk��ֵ���ڽڵ�����
				break;
			}
			
		}
		
		if(fast == null){
			return null;
		}
		while(fast.next!=null){//����ָ���ߵ��ն˽ڵ�ʱ��ͣ��
			fast = fast.next;
			slow = slow.next;
		}
		
		return slow;		
	}
}
