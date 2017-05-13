package 动态规划部分;
/**
 * 
 * 钢条切割问题
 * @author Administrator
 *
 */
public class Slice_steel {
	public static int pathNum(int m,int n){
		if(m-1==0 || n-1==0){
			return 1;
		}else if(m-1<0 || n-1<0){
			return -1;
		}
		int result[][] = new int[m][n];
					
		result[0][0] = 0;
		for(int i=0;i<=m-1;i++){
			result[i][0] = 1;
			result[0][0] = 0;
			for(int j=1;j<=n-1;j++){
				if(i == 0){
					result[i][j] = 1;
				}else{
					result[i][j] = result[i-1][j]+result[i][j-1];
				}
			}
		}
		return result[m-1][n-1];
		
		
	}
}
