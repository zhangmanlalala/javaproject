package _222_Count_Complete_Tree_Nodes;
/**
 * 
 *Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, 
is completely filled, and all nodes in the last level are as far left 
as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Subscribe to see which companies asked this question
 
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
	/**
	 * 
	 * ����ȫ���������Ľڵ���
	 * @param root
	 * @return
	 */
	
	/**
	 * 
	 * ��׼�𰸣������ĳ�ڵ�һֱ����ĸ߶�==һֱ���ҵĸ߶ȣ���ô�Ըýڵ�Ϊroot������һ������ȫ������
	 * ����ȫ�������Ľڵ����������ù�ʽ���2^h-1.����߶Ȳ���ȣ���ݹ����return countNode(left)+countNode
	 * (right)+1,���Ӷ���O(h^2)
	 * 
	 * 
	 * @param root
	 * @return
	 */
	/**
	 * 
	 * ���ڶ�����������Ҫ�뵽�ݹ�ȥ������ÿһ���ӽڵ���˵����Ҳ�����Ǹ��ڵ�
	 * @param root
	 * @return
	 */
    public int countNodes(TreeNode root) {
       if(root == null) return 0;
       
       int l = getLeft(root) + 1 ;
       int r = getRight(root) + 1 ;
       if(l==r){ //���ĳ���ڵ�һֱ�����һֱ���ҵĸ߶���ȣ���ýڵ�Ϊroot������һ����һ����ȫ������
    	   return (2<<(l-1)) -1;
       }else{
    	   //�ݹ���ɣ���������һ���ڵ㣬���������Ľڵ����+�������Ľڵ����+1==�ܵĽڵ����
    	   return countNodes(root.left) + countNodes(root.right)+1;
       }
    }
    
    
    private int getLeft(TreeNode root){
    	int count = 0;
    	while(root.left!=null){
    		root = root.left;
    		++count;
    	}
    	
    	return count;
    }
    
    
    private int getRight(TreeNode root){
    	int count = 0;
    	while(root.right!=null){
    		root = root.right;
    		++count;
    	}
    	
    	return count;
    }
}
