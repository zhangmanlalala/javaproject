package LeetCode_78_Search_a_2D_Matrix;


/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. 
 * This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,
 * 
 * 
 * @author Administrator
 *
 */
/**
 * 
 * Consider the following matrix:

	[java] view plain copy
	��CODE�ϲ鿴����Ƭ�������ҵĴ���Ƭ

    [  
      [1,   3,  5,  7],  
      [10, 11, 16, 20],  
      [23, 30, 34, 50]  
    ]  
 * 
 * @author Administrator
 *
 */

public class Solution {
	
	/**
	 * 
	 * ���Լ���ʵ��
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;
		int i=0;
        for(;i<m;i++){
        	if(target>=matrix[i][0] && target<=matrix[i][n-1]){
        		break;
        	}
        }
        //���ֲ��ҷ�
        if(i<m){
        	int j=0;
        	int k=n-1;
        	while(j<=k){
        		int mid=(j+k)/2;
        		if(matrix[i][mid] >target){
        			k = mid-1;
        		}else if(matrix[i][mid]<target){
        			j=mid+1;
        		}else{
        			return true;
        		}
        	}
        	if(j<=k){
        		return true;
        	}else{
        		return false;
        	}
        }else{
        	return false;
        }
    }
	
	/**
	 * ������

	���ֲ��ң��ؼ�������ô�ڶ�ά�����������һά���ֲ��ҡ�

	����Ϊ m*n

	midԪ����matrix[mid/n][mid%n]

	�����Ϳ��Խ��ж����ˡ�
	 * ��׼�ⷨ
	 */
	public boolean searchMatrix2(int[][] matrix, int target){

		//������  
        if(matrix==null || matrix.length==0 || matrix[0].length==0)  
            return false;  
        int m = matrix.length;  
        int n = matrix[0].length;  
        //������ת����һά������ж���  
        int start = 0;  
        int end = m*n-1;  
        while(start <= end){  
            int mid = (start+end)/2;  
            int val = matrix[mid/n][mid%n];//�����ǹؼ�  
            if(val == target)  
                return true;  
            else if(val < target)  
                start = mid+1;  
            else  
                end = mid-1;  
        }  
        return false;  
		
	}
	/**
	 * 
	 * �ֱ𽫶��ֲ����������ϣ���������
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix3(int[][] matrix, int target){
		int m = matrix.length;
	    int n = matrix[0].length;
	    
	    int left=0, right=m-1;
	    while(left <= right){   
	        int mid = (left + right)/2;
	        if(target == matrix[mid][0]) return true;
	        if(target > matrix[mid][0])
	            left = mid+1;
	        else
	            right = mid-1;   
	    }

	    int targetLine = left>right?left-1: left;//be careful
	    left=0;
	    right=n-1;
	    while(left <= right){
	        int mid = (left+right)/2;
	        if(target == matrix[targetLine][mid]) return true;
	        if(target < matrix[targetLine][mid])
	            right = mid-1;
	        else
	            left = mid+1;
	    }
	    return false;
	}
	public static void main(String[] args) {
		int matrix[][] =    {  
		                      {1,  3,  5,  7},  
		                      {10, 11, 16, 20},  
		                      {23, 30, 34, 50}  
							} ; 
		System.out.println(new Solution().searchMatrix(matrix, 3));
	}
}
