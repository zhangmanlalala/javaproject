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
 * �������������ĸ���
 * @author Administrator
 *
 */

/**
 * 
 * �������ݹ���⣬ÿһ����Ϊ���������������ݹ�������������������������Ϲ�ϵ����������Ӧ������ˣ���ÿ������Ϊ��������ӹ�ϵ
 * @author Administrator
 *
 */
public class Solution {
	 public int numTrees(int n) {
	        return numTrees(1,n);
	 }
	 
	 
	 /**
	  * 
	  * �ݹ�ⷨ�����Ǻܺ����
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
	  * ��̬�滮�ⷨ���Ҿ�����������㷨
	  * ��n=0������
	  * ��n=1,��һ����
	  * ��n=2,���ڵ�ȡ1(������0���ڵ㣬������1���ڵ�)+���ڵ�ȡ2(������1���ڵ㣬������0���ڵ�)
	  * ��n=3�����ڵ�ȡ1(��0����2)+���ڵ�ȡ2(��1,��1)+���ڵ�ȡ3(��2����0)
	  * .
	  * .
	  * .
	  * ����Ҫ��˼�룬���ڵ������ͬ���乹�ɵĶ���������ĸ�����һ������������ģ����ڵ��ֵʵ�����޹صġ�
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
		 //��̬�滮���Ե�������⣬��������ĽⱣ�����ֵ���(һ���ֵ䣬���߶����ֵ�)
		 for(int i=2;i<=n;i++){
			 for(int j=1;j<=i;j++){
				 total[i] = total[i]+total[j-1]*total[i-j];
			 }
		 }
		 
		 return total[n];
		 
	 }
}
