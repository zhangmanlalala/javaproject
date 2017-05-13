package 二叉树的深度;

import java.util.LinkedList;



public class Deep {
	//递归实现2  
	  public int findDeep1(Node root)  
	  {  
	      
	      if(root == null)  
	      {  
	          return 0;  
	      }  
	      else  
	      {  
	       int lchilddeep = findDeep1(root.getLeft());//求左子树的深度  
	       int rchilddeep = findDeep1(root.getRight());//求右子树的深度  
	       return lchilddeep > rchilddeep ? lchilddeep + 1 : rchilddeep + 1;//左子树和右子树深度较大的那个加一等于整个树的深度  
	      }  
	  }  
	    
	    
	  //非递归实现  
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
	         cur = 0;//记录本层已经遍历的节点个数  
	         last = queue.size();//当遍历完当前层以后，队列里元素全是下一层的元素，队列的长度是这一层的节点的个数  
	         while(cur < last)//当还没有遍历到本层最后一个节点时循环  
	         {  
	             current = queue.poll();//出队一个元素  
	             cur++;  
	             //把当前节点的左右节点入队（如果存在的话）  
	             if(current.getLeft() != null)  
	             {  
	                 queue.offer(current.getLeft());  
	             }  
	             if(current.getRight() != null)  
	             {  
	                 queue.offer(current.getRight());  
	             }  
	         }  
	         level++;//每遍历完一层level+1  
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

