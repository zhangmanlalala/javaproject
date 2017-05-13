package LeetCode_103_Pow_x_n;



/**
 * Implement pow(x, n).

	分析：

	这道题一个一个相乘过不了。

	分治可以解决。
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
	public int pow(int x,int n){
		if(n==1){  //递归结束条件和触底情况
			return x;
		}
		int temp = pow(x,n/2);
		return temp*temp;	//写在后面表示回溯的时候进行计算	
	}
	
	public int drive(int x,int n){
		if(n==0){
			return 1;
		}else if(n%2==0){
			return pow(x,n);
		}else{
			return x*pow(x,n-1);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new Solution().drive(2, 4));
	}
}
