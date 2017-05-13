package LeetCode_89_Minimum_PathSum;

/**
 *Given a m x n grid filled with non-negative numbers, 
 *find a path from top left to bottom right which minimizes 
 *the sum of all numbers along its path.
 Note: You can only move either down or right at any point in time. 
 * 
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 
 * ��һ�����̻�һ�����Ž�Ľṹ����
 * �ڶ������ݹ�ض������Ž��ֵ
 * ��������������Ž��ֵ��һ��ʹ���Ե����ϵķ�������ÿһ������Ľ���������ֵ���
 * @author Administrator
 *
 */

/**
 * 
 * ���ǹ�����ά�ֵ䣬ÿһ�����ӵ�ֵ����¼
 * @author Administrator
 *
 */
public class Solution {
	
	
	public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if( m==1 && n==1){
        	return grid[0][0];
        }
        
        int result[][] = new int[m][n];
        
        for(int i=m-1;i>=0;i--){
        	if(i == m-1){
        		result[m-1][n-1] = grid[m-1][n-1];
        	}else{        		
        		result[i][n-1] = grid[i][n-1]+result[i+1][n-1];
        	}       	
        	for(int j=n-2;j>=0;j--){
        		if(i==m-1){
        			result[i][j] = grid[i][j]+result[i][j+1];
        		}else{
        			int path1 = grid[i][j]+result[i][j+1];
        			int path2 = grid[i][j]+result[i+1][j];
        			result[i][j] = Math.min(path1, path2);
        		}
        	}
        }
        
        return result[0][0];
    } 
	
	/**
	 * 
	 * ���Ǳ�׼��
	 * @param grid
	 * @return
	 */
	
	/**
	 * 
	 * �ý⹹����һά�ֵ䣬��¼��ǰ�е����ֵ����
	 * @param grid
	 * @return
	 */
	public int minPathSum2(int[][] grid) {
		if(grid==null || grid.length==0 || grid[0].length==0)  
            return 0;  
        int row = grid.length;  
        int col = grid[0].length;  
        int[] dp = new int[col];  
        //���˵�һ������������������������min  
        for(int i=1; i<col; i++)  
            dp[i] = Integer.MAX_VALUE;  
              
        for(int i=0; i<row; i++){  
            //��һ��ֻ��һ·���¼�����  
            dp[0] = dp[0] + grid[i][0];  
              
            for(int j=1; j<col; j++)  
                //�����dp[j]��¼��ʵ��������һ�д�λ�õ�����ֵ  
                dp[j] = Math.min( dp[j-1], dp[j]) + grid[i][j];  
        }  
        return dp[col-1];  
    }  

	
	public static void main(String[] args) {
		 int[][] grid = {
                 {2,0,0},
                 {0,1,1},
                 {0,0,0}
					};
		System.out.println(new Solution().minPathSum(grid));
	}
}
