package 网易测试;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//测试题一
		/*Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int[] arr = new int[n];
		String str = sc.nextLine();
		String[] temp  = str.split(" ");
		for(int i=0;i<n;i++){
			arr[i] = Integer.parseInt(temp[i]);
		}

		int i=0;			
				
		int j = n-1;
		int result = 0;
		while(i<j){
			
			if(arr[i] == arr[j]){
				i++;
				j--;
			}else if(arr[i]<arr[j]){
				arr[i+1] = arr[i]+arr[i+1];
				i++;
				result++;
			}else{
				arr[j-1] = arr[j-1]+arr[j];
				j--;
				result++;
			}
		}
		
		System.out.println(result);*/
		
		
		//测试题2
		
/*		Scanner sc = new Scanner(System.in);	
		int n = Integer.parseInt(sc.nextLine());
		
		double av = Math.sqrt(n);
		double x=0,y = 0;
		int result = 0;
		for(int i=0;i<=av;i++){
			x = Math.sqrt(n-Math.pow(i, 2));
			y = (int) x;
			if(x==y){
				if(y==0 || i==0){
					result +=2;
				}else{
					result +=4;
				}
			}
		}
		
		System.out.println(result);*/
		
		//测试用例3
		//使用动态规划
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] temp = str.split(" ");
		int start = Integer.parseInt(temp[0]);
		int end = Integer.parseInt(temp[1]);
		
		dfs(0,start,end);
		if(a!=Integer.MAX_VALUE){
			System.out.println(a);
		}else{
			System.out.println(-1);
		}
		
		
	}
	
	public static int a = Integer.MAX_VALUE;
	public static void dfs(int result,int start,int end){
		
		if(a!=Integer.MAX_VALUE){
			return ;
		}
		
		if(start == end){
			if(a>result){
				a = result;
			}
			return ;
		}else if(start>end){
			return ;
		}
		
		for(int i=start/2;i>=2;i--){
			
			if(start%i == 0){
				result++;
				dfs(result,start+i,end);
				result--;
			}
		}
		
	}

}
