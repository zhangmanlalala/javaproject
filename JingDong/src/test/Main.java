package test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
/*	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int n = Integer.parseInt(line);
		line = sc.nextLine();
		sc.close();
		
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0;i<n;i++){
			
			if(line.charAt(i) >= '1' && line.charAt(i)<='9'){
				int x = line.charAt(i)-'0';
				
				int j=i-x;
				if(j<0){
					j=0;
				}
				for(;j<=i+x && j<line.length();j++){
					if(line.charAt(j) == 'X'){
						set.add(j);
					}
				}
				
			}
		}
		System.out.println(set.size());
		
	}*/
/*	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		String[] str = line.split(" ");
		int n = Integer.parseInt(str[0]);
		int k = Integer.parseInt(str[1]);
		
		if(n<k){
			System.out.println(0);
			return ;
		}else if(n==k){
			System.out.println(1);
			return ;
		}
		
		if(k==1){
			int i=n;
			for(;i>=0;i--){
				int num = i + i/2;
				if(num<=n){
					break;
				}
			}
			
			System.out.println(i);
			return ;
		}
		
		int mod = n/k;
		int yu = n%k;
		if(yu >= mod/2){
			System.out.println(mod);
		}else{
			System.out.println(mod-1);
		}
		
		int j=n;
		for(;j>=0;j--){
			int num = j/k+j/2;
			if(num<=n){
				break;
			}
		}
		System.out.println(j);
		
		
	}*/
}
