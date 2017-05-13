package ÍøÒ×²âÊÔ;

import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		
		int arr[] = new int[]{8,6};
		
		dfs(arr,0,0,n);
		
		if(a!=0){
			System.out.println(a);
		}else{
			System.out.println(-1);
		}
		
	}
	
	public static int a = 0;
	
	public static void dfs(int arr[],int sum,int num,int n){
		
		if(a != 0){
			return ;
		}
		
		if(sum == n){
			a = num;
			return ;
		}else if(sum>n){
			return ;
		}
		
		
		for(int i=0;i<arr.length;i++){
			sum +=arr[i];
			num++;
			
			dfs(arr,sum,num,n);
			
			sum -=arr[i];
			num--;
		}
		
	}
}
