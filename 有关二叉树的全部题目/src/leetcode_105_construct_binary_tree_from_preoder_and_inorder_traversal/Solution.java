package leetcode_105_construct_binary_tree_from_preoder_and_inorder_traversal;

public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if(preorder == null || inorder == null){
    		return null;
    	}
    	
    	return buildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    
    
    public TreeNode buildTree(int[] preorder,int start1,int end1,int[] inorder,int start2,int end2){
    	
    	if(start1>end1 || start2>end2){
    		return null;
    	}
    	
    	
    	TreeNode node = new TreeNode(preorder[start1]);
    	int j = start2;
    	for(;j<=end2;j++){
    		if(inorder[j] == preorder[start1]){
    			break;
    		}
    	}
    	int numLeft = j-start2;//算出node的左子树有几个节点 
    	node.left = buildTree(preorder,start1+1,start1+numLeft,inorder,start2,j-1);
    	node.right = buildTree(preorder,start1+numLeft+1,end1,inorder,j+1,end2);
    	
    	return node;
    }
}


class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
