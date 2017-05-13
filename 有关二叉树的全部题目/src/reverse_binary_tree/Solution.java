package reverse_binary_tree;

import java.util.Stack;

/**
 * 
 * 
 * �������ķ�ת
 * @author Administrator
 *
 */

class TreeNode{
	int val;
	public TreeNode(int val) {
		// TODO Auto-generated constructor stub
		this.val = val;
	}	
	TreeNode left;
	TreeNode right;
}
public class Solution {
	
	
	/**
	 * 
	 * ���Ҳ����ȷ��
	 * @param root
	 * @return
	 */
	public TreeNode reverseBinaryTree(TreeNode root){
		
		if(root == null){
			return null;
		}else if(root.right == null && root.left == null){
			return root;
		}
		
		TreeNode leftNode = root.left;
		TreeNode rightNode = root.right;//�Ȱ����ҽڵ㱣�治Ȼ�ᱻ����
		root.left = reverseBinaryTree(rightNode);
		root.right = reverseBinaryTree(leftNode);
		
		return root;
		
	}
	/**
	 * 
	 * ���͵Ķ������ݹ飬ÿ���ڵ㶼��һ�������⣬��ջ��˼ά
	 * @param root
	 * @return
	 */
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
        	return null;
        }
        
        //���ú�������
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        
        
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        
        return root;
  
    }
    
    /**
     * 
     * ��ջ���������ݹ��Ϊ�ǵݹ����
     */
    public TreeNode invertTree1(TreeNode root) {
    	
    	
    	Stack<TreeNode> ss = new Stack<TreeNode>();
    	TreeNode node = root,pre = root;
    	
    	while(node!=null || !ss.isEmpty()){
    		
    		while(node!=null){
    			ss.push(node);
    			node = node.left;
    		}
    		
        	
        	if(!ss.isEmpty()){
        		
        		node = ss.peek();
        		if(node.right == null || node.right == pre){//����ҽڵ�Ϊ�գ������ҽڵ�֮ǰ������
        			node = ss.pop(); //��ջ
        			
        			TreeNode rightNode = node.right;
        			node.right = node.left;
        			node.left = rightNode;
        			
        			pre = node; //�Ѿ��������Ľڵ㸳ֵ
        			node = null;//
        			
        		}else{
        			node = node.right;
        		}
        		
        		
        	}
    	}
    	
    	return root;

    }
}
