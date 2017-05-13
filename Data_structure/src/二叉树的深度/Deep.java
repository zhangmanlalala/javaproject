package �����������;

import java.util.LinkedList;



public class Deep {
	//�ݹ�ʵ��2  
	  public int findDeep1(Node root)  
	  {  
	      
	      if(root == null)  
	      {  
	          return 0;  
	      }  
	      else  
	      {  
	       int lchilddeep = findDeep1(root.getLeft());//�������������  
	       int rchilddeep = findDeep1(root.getRight());//�������������  
	       return lchilddeep > rchilddeep ? lchilddeep + 1 : rchilddeep + 1;//����������������Ƚϴ���Ǹ���һ���������������  
	      }  
	  }  
	    
	    
	  //�ǵݹ�ʵ��  
	  public int findDeep2(Node root)  
	  {  
	     if(root == null)  
	         return 0;  
	      
	     Node current = null;  
	     LinkedList<Node> queue = new LinkedList<Node>();  
	     queue.offer(root);  
	     int cur,last;  
	     int level = 0;  
	     while(!queue.isEmpty())  
	     {  
	         cur = 0;//��¼�����Ѿ������Ľڵ����  
	         last = queue.size();//�������굱ǰ���Ժ󣬶�����Ԫ��ȫ����һ���Ԫ�أ����еĳ�������һ��Ľڵ�ĸ���  
	         while(cur < last)//����û�б������������һ���ڵ�ʱѭ��  
	         {  
	             current = queue.poll();//����һ��Ԫ��  
	             cur++;  
	             //�ѵ�ǰ�ڵ�����ҽڵ���ӣ�������ڵĻ���  
	             if(current.getLeft() != null)  
	             {  
	                 queue.offer(current.getLeft());  
	             }  
	             if(current.getRight() != null)  
	             {  
	                 queue.offer(current.getRight());  
	             }  
	         }  
	         level++;//ÿ������һ��level+1  
	     }  
	     return level;  
	  }  
	 
}


class Node<T>{  
    private T value;  
    private Node<T> left;  
    private Node<T> right;  
      
    public Node(){  
    }  
    public Node(Node<T> left, Node<T> right, T value){  
        this.left = left;  
        this.right = right;  
        this.value = value;  
    }  
    public Node(T value){  
        this(null, null, value);  
    }  
      
    public Node<T> getLeft(){  
        return this.left;  
    }  
    public void setLeft(Node<T> left){  
        this.left = left;  
    }  
    public Node<T> getRight(){  
        return this.right;  
    }  
    public void setRight(Node<T> right){  
        this.right = right;  
    }  
    public T getValue(){  
        return this.value;  
    }  
    public void setValue(T value){  
        this.value = value;  
    }  
}  

