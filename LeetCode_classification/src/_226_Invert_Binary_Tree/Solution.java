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
but you can’t invert a binary tree on a whiteboard so fuck off. 
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
        //显然是使用后序辨遍历
    	root.left = invertTree(root.left);//保证能返回根节点，保证能链接到新插入的节点
    	root.right = invertTree(root.right);
    	//每一个节点都是一个子问题
    	TreeNode temp = root.right;
    	root.right = root.left;
    	root.left = temp;
    	
    	return root;

    	
    }
}
