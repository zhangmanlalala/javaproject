package ������;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTree<T> {
	/**�������ĸ��ڵ�*/  
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
    
    /**�����������������*/  
    /**input.txt: - + a # # * # # / e # # f # # 
     * # ����ս�� 
     */  
    private Node<T> createBiTree(Node<T> node,Scanner scn){
    	String temp = scn.next();
    	if(temp.trim().equals("#")){
    		return null;
    	}else{
    		//����3�仰��1����֤�ܷ��ظ��ڵ� 2.��֤�����ӵ��²���Ľڵ�
    		node = new Node<T>((T)temp);//�ȴ���һ���м�ڵ㣬ʹ�õ����������
    		node.setLeft(createBiTree(node.getLeft(),scn));
    		node.setRight(createBiTree(node.getLeft(),scn));
    		return node;
    	}
    }
    
    /**����ݹ����������*/  
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
      
      
    /**����ǵݹ����������*/  
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
      
      
      
    /**����ݹ����������*/  
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
      
    /**����ǵݹ����������*/  
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
      
    /**����ݹ����������*/  
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
      
    /**����ǵݹ����������*/  
    public void nrPostOrderTraverse(){  
        Stack<Node<T>> stack = new Stack<Node<T>>();  
        Node<T> node = root;  
        Node<T> preNode = null;   //��¼֮ǰ�������ҽ��  
        while(node != null || !stack.isEmpty()){  
            while(node != null){  
                stack.push(node);  
                node = node.getLeft();  
            }  
            node = stack.peek(); //��ȡջ��Ԫ�أ��������Ƴ� 
              
            /**����ҽ��Ϊ�գ������ҽ��֮ǰ����������ӡ�����*/  
            if(node.getRight() == null || node.getRight() == preNode){  
                System.out.println(node.getValue());  
                node = stack.pop();  
                preNode = node;  
                node = null;  //�ҽڵ�Ϊ�գ��´ξͲ����ٲ���������
            }  
            else{  
                node = node.getRight();  
            }  
        }  
    }  
      
      
    /**��α���������*/  
    public void levelTraverse(){  
        levelTraverse(root);  
    }  
    private void levelTraverse(Node<T> node) {  
        Queue<Node<T>> queue = new ArrayDeque<Node<T>>();  
        queue.add(node);  
        while(!queue.isEmpty()){  
            node = queue.poll();  //��ȡ���Ƴ����е�ͷ
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
