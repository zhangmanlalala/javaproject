package leetcode_148_AddTwoNumbers;

public class Better_solution {
	
	/**
	 * 自己建立一个单向链表
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static ListNode add(ListNode l1,ListNode l2){
		
		ListNode l3 =null;
		ListNode l4 =null;
		int flag=0;
		while(l1!=null){
			if(l2!=null){
				int c = l1.x+l2.x+flag;			
				if(c>=10){	
					if(l3==null){
						l3 = new ListNode(c-10);
					}else{
						l3.next = new ListNode(c-10);
						l3 = l3.next;
					}
					
					flag=1;
				}else{
					if(l3==null){
						l3 = new ListNode(c);
					}else{
						l3.next = new ListNode(c);
						l3 = l3.next;
					}
					
					flag=0;
				}
				l1 = l1.next;
				l2 = l2.next;
				if(l4==null){
					l4 = l3;
				}
			
			}else{
				int c = l1.x+flag;
				if(c>=10){
					l3.next = new ListNode(c-10);
					l3 = l3.next;
					flag=1;
				}else{
					l3.next = new ListNode(c);
					l3 = l3.next;
					flag=0;
				}
				l1 = l1.next;
			}
		}
		
		while(l2!=null){
			int c = l2.x+flag;
			if(c>=10){
				l3.next = new ListNode(c-10);
				l3 = l3.next;
				flag=1;
			}else{
				l3.next = new ListNode(c);
				l3 = l3.next;
				flag=0;
			}
			l2 = l2.next;
			
		}
		if(flag==1){
			l3.next = new ListNode(1);
		}
		
		return l4;
	}
	
	
	/**
	 * 
	 * 测试使用
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode l1 = new ListNode(8);
		l1.next = new ListNode(9);
		l1.next.next = new ListNode(7);
		
		ListNode l2 = new ListNode(8);
		l2.next = new ListNode(9);
		l2.next.next = new ListNode(7);
		l2.next.next.next = new ListNode(1);
		l2.next.next.next.next = new ListNode(2);
	
		ListNode l = add(l1,l2);
		while(l!=null){
			System.out.print(l.x);
			l=l.next;
		}
	}
	
	
	
}


class ListNode{
	int x;
	ListNode next;
	public ListNode(int x){
		this.x = x;
		next = null;
	}
}