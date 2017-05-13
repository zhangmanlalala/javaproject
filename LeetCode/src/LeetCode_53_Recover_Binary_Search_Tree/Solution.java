package LeetCode_53_Recover_Binary_Search_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *  Two elements of a binary search tree (BST) are swapped by mistake.
	Recover the tree without changing its structure.
	Note:
	A solution using O(n) space is pretty straight forward. Could you devise a constant space solution? 
 * 
 * @author Administrator
 *
 */
public class Solution {
	public void recoverTree2(TreeNode root) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		drive(root,list);
		TreeNode temp = list.get(0);
		temp.val = list.get(1).val;
		list.get(1).val = temp.val;
    }
	
	public void drive(TreeNode root,List<TreeNode> list){
		if(list.size()==2){
			return ;
		}
		
		if(root == null){
			return ;
		}
		//使用后续遍历，左右中，依次检查
		drive(root.left,list);
		
		drive(root.right,list);
		
		if(!isValidBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE)){
			list.add(root.left);
		}
		

	}
	public boolean isValidBST(TreeNode node, int min, int max){  
        if(node == null)  
            return true;  
        if(node.val>min && node.val<max && isValidBST(node.left, min, node.val)   
            && isValidBST(node.right, node.val, max) )  
            return true;  
        else  
            return false;  
    }  
	
	/**
	 * 
	 * 自己的想法搞不定，还是来看标准解法吧
	 * 
	 * 
	 */
	
	/**
	 * 
	 * 分析：
	 *这道题要明确二叉查找树的性质：中序遍历是有序的
	 *因此，我们需要做的是，找到那两个不在自己位置上的元素并交换值，这可以通过比较相邻两个元素来实现
	 *
	 *分为两种情况：
	 *1，两个异常元素相邻，只会出现一次逆序
	 *2，两个异常元素不相邻，会出现两次逆序，第一次逆序前面的是异常，第二次逆序后面的是异常(这个不知道怎么证明，但是通过举例可以看出来)
	 *所以，对树进行中序遍历，并记录异常元素指针即可
	 */
	
	TreeNode pre;
	TreeNode first;
	TreeNode second;
	
	
	public void recoverTree(TreeNode root){
		pre = first = second;
		inorder(root);
		if(first !=null && second !=null){
			int temp = first.val;
			first.val = second.val;
			second.val = temp;
		}
	}
	
	//很经典，不知道咋想到的
	/**
	 * 
	 * 采用的是中序遍历
	 * @param root
	 */
	public void inorder(TreeNode root){
		if(root == null) return;
		inorder(root.left);
		if(pre == null)
			pre = root;
		else{
			if(pre.val>root.val){
				//first等于null说明第一个异常还没找到
				//先照顾第一个异常，是逆序对的前一个
				if(first == null)
					first = pre;
				//这里让second == root，把相邻不相邻两种情况统一了
				//如果相邻，后面不会再有逆序对，second不会再被赋值
				//如果不相邻，再碰到逆序对的时候，second会被更新
				second = root;
			}
			
			pre = root;
		}
		
		inorder(root.right);
		
	}
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
 
