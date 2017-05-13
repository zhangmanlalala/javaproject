package LeetCode_7_Binary_Tree_Postorder_Traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *Given a binary tree, return the postorder traversal of its nodes' values.

	For example:
	Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3

return [3,2,1].  
 * O
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
    public List<Integer> postorderTraversal(TreeNode root) {
        
    	List<Integer> lst = new ArrayList<Integer>();
    	Stack<TreeNode> stack  = new Stack<TreeNode>();
    	TreeNode pre = root;
    	TreeNode node = root; 
    	
    	while(!stack.isEmpty() || node!=null){
    		
    		while(node!=null){
    			stack.push(node);
    			node = node.left;
    		}
    		
    		if(!stack.isEmpty()){
    			node = stack.peek();
    			//�����ǰ�ڵ���ҽڵ�Ϊ�գ������ҽڵ�֮ǰ���ʹ�����ֱ�ӷ��ʵ�ǰ�ڵ�
    			if(node.right == null || node.right == pre){
    				lst.add(node.val);
    				node = stack.pop();
    				pre = node;
    				node = null;
    			}else{
    				node = node.right;//���ߣ��ȱ���temp���ҽڵ�
    			}
    		}
    	}
    	
    	return lst;
    	
    }
}
