package example3;

public class Solution {
	
   public boolean searchMatrix(int[][] matrix, int target) {
	   if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
		   return false;
	   }
	   int row = matrix.length;
	   int col = matrix[0].length;
	   for(int i=0;i<row;i++){
		   
		   if(matrix[i][0]<=target && matrix[i][col-1]>=target){
			   if(binarySearch(matrix,target,i)){
				   return true;
			   }
			   
		   }else if(matrix[i][0]>target){
			   return false;
		   }
		   
	   }
	   
	   return false;
	   
	   
	   
   }
   
   public boolean binarySearch(int[][] matrix,int target,int row){
	   int i=0,j=matrix[0].length;
	   
	   while(i<=j){
		   
		   int mid = (i+j)/2;
		   if(matrix[row][mid]>target){
			   j = mid-1;
		   }else if(matrix[row][mid]<target){
			   i = mid+1;
		   }else{
			   return true;
		   }
	   }
	   
	  return false;
   }
   
	/**
	 * 分析：

	二分查找，关键在于怎么在二维数组上面进行一维二分查找。

	长度为 m*n

	mid元素是matrix[mid/n][mid%n]

	这样就可以进行二分了。
	 * 标准解法
	 */
   public boolean searchMatrix2(int[][] matrix, int target) {
	   if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
		   return false;
	   }
	   
	   int m = matrix.length;
	   int n = matrix[0].length;
	   
	   int i=0,j=m*n-1;
	   
	   while(i<=j){
		   int mid = (i+j)/2;
		   
		   int midNum = matrix[mid/n][mid%n];
		   if(midNum>target){
			   j = mid-1;
		   }else if(midNum<target){
			   i = mid+1;
		   }else{
			   return true;
		   }
		   
	   }
	   
	   return false;
   
   }
}
