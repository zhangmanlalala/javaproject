package _226_Invert_Binary_Tree;
/**
 * 
 *Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9

to

     4
   /   \
  7     2
 / \   / \
9   6 3   1

Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew),
but you can��t invert a binary tree on a whiteboard so fuck off. 
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
    public TreeNode invertTree(TreeNode root) {
    	
    	if(root==null){
    		return root;
    	}
        //��Ȼ��ʹ�ú�������
    	root.left = invertTree(root.left);//��֤�ܷ��ظ��ڵ㣬��֤�����ӵ��²���Ľڵ�
    	root.right = invertTree(root.right);
    	//ÿһ���ڵ㶼��һ��������
    	TreeNode temp = root.right;
    	root.right = root.left;
    	root.left = temp;
    	
    	return root;

    	
    }
}
