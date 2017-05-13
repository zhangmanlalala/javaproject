package leetcode_232_Implement_Queue_using_Stacks;

import java.util.Stack;

public class MyQueue {
    // Push element x to the back of queue.
	Stack<Integer> stack;
	public MyQueue(){
		stack = new Stack<Integer>();
	}
	
	Stack<Integer> copy = new Stack<Integer>();
    public void push(int x) {
    	
        while(!stack.isEmpty()){
        	copy.push(stack.pop());
        }
        copy.push(x);
        while(!copy.isEmpty()){
        	stack.push(copy.pop());
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(!empty()){
        	stack.pop();
        }
    }

    // Get the front element.
    public int peek() {
        if(!empty()){
        	return stack.peek();
        }else{
        	return -1;
        }
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.isEmpty();
    }
}
