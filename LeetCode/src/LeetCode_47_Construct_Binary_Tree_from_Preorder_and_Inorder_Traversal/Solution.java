package LeetCode_47_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;

/**
 * ���������������������Ľ��������������
 * ԭ�����������ÿ���ڵ��൱�ڸ��ڵ㣬Ȼ������������Ľ�����ҵ�ÿһ��ǰ��ĸ��ڵ�����ҽڵ�
 * �ܾ���
 * 
 * @author Administrator
 *
 */



public class Solution {
	static int num = 0;
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder.length == 0 || inorder.length == 0){
			return null;
		}
		
		return buildTree(null,preorder,inorder,0,inorder.length);
    }
	
	/**
	 * 
	 * ����1���ڵصݹ����涨����һ��ȫ�ֱ������ݹ������ÿһ�α仯������������������֣����Ҳ��ܻ��ݵ�Ӱ��
	 * @param node
	 * @param preorder
	 * @param inorder
	 * @param start2
	 * @param end2
	 * @return
	 */
	public TreeNode buildTree(TreeNode node,int[] preorder,int[] inorder,int start2,int end2){
		if(start2>end2 ){
			return null;
		}
		if(num>=preorder.length)
			return null;
		int i= start2;
		for(;i<=end2;i++){
			if(inorder[i] == preorder[num]){
				break;
			}
		}
		node = new TreeNode(preorder[num]);
		num++;
		
		node.left = buildTree(node.left,preorder,inorder,start2,i-1);
		node.right = buildTree(node.right,preorder,inorder,i+1,end2);
		
		return node;
	}
	
	/**
	 * ���������ܾ��䣬������������ô�����������
	 */
	public TreeNode buildTree(TreeNode node,int[] preorder,int prestart,int[] inorder,int start2,int end2){
		if(start2>end2 ){
			return null;
		}
		if(prestart>=preorder.length)
			return null;
		int i= start2;
		for(;i<=end2;i++){
			if(inorder[i] == preorder[prestart]){
				break;
			}
		}
		node = new TreeNode(preorder[prestart]);
		
		node.left = buildTree(node.left,preorder,prestart+1,inorder,start2,i-1);
		node.right = buildTree(node.right,preorder,prestart+1+i-start2,inorder,i+1,end2);//�ܾ���
		//prestart+1+i-start2�Ƕ����һ�����������Ͻڵ�ĸ������������Ǵ�prestart+1+i-start2��ʼ��
		
		return node;
	}
	
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
