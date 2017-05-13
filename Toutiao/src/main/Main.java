package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int m = Integer.parseInt(line);
		
		List<Integer> lst = new ArrayList<Integer>();
		for(int i=0;i<m;i++){
			line = sc.nextLine();
			lst.add(Integer.parseInt(line));
		}
		
		StringBuilder sb = new StringBuilder();
		line = sc.nextLine();
		int n = Integer.parseInt(line);
		int temp = 0;
		for(int i=0;i<n;i++){
			line = sc.nextLine();
			temp = Integer.parseInt(line);
			if(lst.contains(temp)){
				sb.append(temp+" ");
			}		
			
		}	
		sc.close();
		
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
			
		
	}
	
}
