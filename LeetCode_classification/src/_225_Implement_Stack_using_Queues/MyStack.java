package _225_Implement_Stack_using_Queues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the following operations of a stack using queues.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    empty() -- Return whether the stack is empty.

Notes:

    You must use only standard operations of a queue -- which means only push to back,
     peek/pop from front, size, and is empty operations are valid.
    Depending on your language, queue may not be supported natively. You may simulate a 
    queue by using a list or deque (double-ended queue), as long as you use only 
    standard operations of a queue.
    You may assume that all operations are valid (for example, no pop or top operations
     will be called on an empty stack).

Update (2015-06-11):
The class name of the Java function had been updated to MyStack instead of Stack.  
 * 
 * @author Administrator
 *
 */
public class MyStack {
	Queue<Integer> queue;
	
	public MyStack(){
		queue = new LinkedList<Integer>();
	}
	
    // Push element x onto stack.
	Queue<Integer> copy =  new LinkedList<Integer>();;
    public void push(int x) {
    	if(queue.size() == 0){
    		queue.add(x);
    		return;
    	}
    	copy.add(x);
        while(queue.size()>0){
        	copy.add(queue.remove());
        }
        while(copy.size()>0){
        	queue.add(copy.remove());
        }
        
    }

    // Removes the element on top of the stack.
    public void pop() {
    	if(!empty()){
    		queue.remove();
    	}
    }

    // Get the top element.
    public int top() {
    	if(!empty()){   		
    		return queue.peek();
    	}else{
    		return -1;
    	}
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
}
