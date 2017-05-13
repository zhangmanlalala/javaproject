package LeetCode_23_Sum_Root_to_left_Numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3

The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25. 
 * 
 * 
 * @author Administrator
 *
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
	public int sumNumbers(TreeNode root) {
		if(root ==null){
			return 0;
		}
		//使用先序遍历,用费递归的形式
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;
		TreeNode pre = root;
		int sum = 0;
		while(!stack.isEmpty() || root!=null){
			
			while(node!=null){
				stack.push(node);
				node = node.left;
			}
			
			if(!stack.isEmpty()){
				TreeNode temp = stack.peek();
				if(temp.right==null || temp.right==pre){
					if(temp.left==null && temp.right==null){//叶子节点
						List<TreeNode> lst = new ArrayList<TreeNode>(stack);
						int num = 0;
						for(TreeNode cal:lst){
							num = num*10+cal.val;
						}
						sum = sum+num;
					}
					node = stack.pop();
					pre = node;
					node = null;
					
				}else{
					node = temp.right;
				}
			}
			
		}
		
		return sum;
        
    }
	
	/**
	 * 
	 * 标准解法
	 * 
	 */
	public int sum = 0;
	public void postOrder(TreeNode root,int num){//先遍历
		
		if(root == null){
			return;
		}
		
		if(root.left==null && root.right==null){
			num = num*10+root.val;
			sum = sum+num;
			return;
		}
		num = num*10+root.val;
		postOrder(root.left,num);
		postOrder(root.right,num);
		
		
		
	}
}
