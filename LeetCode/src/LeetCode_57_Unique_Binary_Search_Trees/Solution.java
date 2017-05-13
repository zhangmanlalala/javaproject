package LeetCode_57_Unique_Binary_Search_Trees;
/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 * 
 * @author Administrator
 *
 */

/**
 * 
 * 计算二叉查找树的个数
 * @author Administrator
 *
 */

/**
 * 
 * 分析，递归求解，每一个作为根，对左右子树递归求两个子树个数，左右是组合关系，所以总数应该是相乘，而每个数作为根，是相加关系
 * @author Administrator
 *
 */
public class Solution {
	 public int numTrees(int n) {
	        return numTrees(1,n);
	 }
	 
	 
	 /**
	  * 
	  * 递归解法，不是很好理解
	  * @param start
	  * @param end
	  * @return
	  */
	 public int numTrees(int start,int end){
		 if(start>=end){
			 return 1;
		 }
		 
		 int total = 0;
		 for(int i=start;i<=end;i++){
			 total = total+numTrees(start,i-1)*numTrees(i+1,end);
		 }
		 return total;
		 
	 }
	 
	 /**
	  * 
	  * 动态规划解法，我决定理解这种算法
	  * 当n=0，空树
	  * 当n=1,就一颗树
	  * 当n=2,根节点取1(左子树0个节点，右子树1个节点)+根节点取2(左子树1个节点，右子树0个节点)
	  * 当n=3，根节点取1(左0，右2)+根节点取2(左1,右1)+根节点取3(左2，右0)
	  * .
	  * .
	  * .
	  * 最重要的思想，当节点个数相同，其构成的二叉查找树的个数是一样，都是升序的，跟节点的值实际是无关的。
	  * 
	  * 
	  */
	 
	 public int numTrees2(int n){
		 if(n<0){
			 return 0;
		 }
		 int[] total = new int[n+1];
		 
		 total[0] = 1;
		 total[1] = 1;
		 //动态规划，自底向上求解，将子问题的解保存在字典中(一级字典，或者二级字典)
		 for(int i=2;i<=n;i++){
			 for(int j=1;j<=i;j++){
				 total[i] = total[i]+total[j-1]*total[i-j];
			 }
		 }
		 
		 return total[n];
		 
	 }
}
