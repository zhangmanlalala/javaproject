package 二叉树;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTree<T> {
	/**二叉树的根节点*/  
    private Node<T> root;  
      
    public BinaryTree(){}  
    public BinaryTree(Node<T> root){  
        this.root = root;  
    }  
    
    public void createBiTree(){  
        Scanner scn = null;  
          
        try {  
            scn = new Scanner(new File("input.txt"));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
          
        this.root = createBiTree(root, scn);  
    }  
    
    /**先序遍历创建二叉树*/  
    /**input.txt: - + a # # * # # / e # # f # # 
     * # 代表空结点 
     */  
    private Node<T> createBiTree(Node<T> node,Scanner scn){
    	String temp = scn.next();
    	if(temp.trim().equals("#")){
    		return null;
    	}else{
    		//下面3句话，1，保证能返回根节点 2.保证能链接到新插入的节点
    		node = new Node<T>((T)temp);//先创建一个中间节点，使用的是先序遍历
    		node.setLeft(createBiTree(node.getLeft(),scn));
    		node.setRight(createBiTree(node.getLeft(),scn));
    		return node;
    	}
    }
    
    /**先序递归遍历二叉树*/  
    public void preOrderTraverse(){  
        preOrderTraverse(root);  
    }  
    private void preOrderTraverse(Node<T> node) {  
        if(node != null){  
            System.out.println(node.getValue());  
            preOrderTraverse(node.getLeft());  
            preOrderTraverse(node.getRight());  
        }  
    }  
      
      
    /**先序非递归遍历二叉树*/  
    public void nrPreOrderTraverse(){  
        Stack<Node<T>> stack = new Stack<Node<T>>();  
        Node<T> node = root;  
        while(node != null || !stack.isEmpty()){  
            while(node != null){  
                System.out.println(node.getValue());  
                stack.push(node);  
                node = node.getLeft();  
            }  
            node = stack.pop();  
            node = node.getRight();  
        }  
    }  
      
      
      
    /**中序递归遍历二叉树*/  
    public void inOrderTraverse(){  
        inOrderTraverse(root);  
    }  
    private void inOrderTraverse(Node<T> node) {  
        if(node != null){  
            inOrderTraverse(node.getLeft());  
            System.out.println(node.getValue());  
            inOrderTraverse(node.getRight());  
        }  
    }  
      
    /**中序非递归遍历二叉树*/  
    public void nrInOrderTraverse(){  
        Stack<Node<T>> stack = new Stack<Node<T>>();  
        Node<T> node = root;  
        while(node != null || !stack.isEmpty()){  
            while(node != null){  
                stack.push(node);  
                node = node.getLeft();  
            }  
            node = stack.pop();  
            System.out.println(node.getValue());  
            node = node.getRight();  
        }  
    }  
      
    /**后序递归遍历二叉树*/  
    public void postOrderTraverse(){  
        postOrderTraverse(root);  
    }  
    private void postOrderTraverse(Node<T> node) {  
        if(node != null){  
            postOrderTraverse(node.getLeft());  
            postOrderTraverse(node.getRight());  
            System.out.println(node.getValue());  
        }  
    }  
      
    /**后序非递归遍历二叉树*/  
    public void nrPostOrderTraverse(){  
        Stack<Node<T>> stack = new Stack<Node<T>>();  
        Node<T> node = root;  
        Node<T> preNode = null;   //记录之前遍历的右结点  
        while(node != null || !stack.isEmpty()){  
            while(node != null){  
                stack.push(node);  
                node = node.getLeft();  
            }  
            node = stack.peek(); //获取栈顶元素，但不慌移除 
              
            /**如果右结点为空，或者右结点之前遍历过，打印根结点*/  
            if(node.getRight() == null || node.getRight() == preNode){  
                System.out.println(node.getValue());  
                node = stack.pop();  
                preNode = node;  
                node = null;  //右节点为空，下次就不用再查找右子树
            }  
            else{  
                node = node.getRight();  
            }  
        }  
    }  
      
      
    /**层次遍历二叉树*/  
    public void levelTraverse(){  
        levelTraverse(root);  
    }  
    private void levelTraverse(Node<T> node) {  
        Queue<Node<T>> queue = new ArrayDeque<Node<T>>();  
        queue.add(node);  
        while(!queue.isEmpty()){  
            node = queue.poll();  //获取并移除队列的头
            if(node != null){  
                System.out.println(node.getValue());  
                queue.add(node.getLeft());  
                queue.add(node.getRight());  
            }  
        }  
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
