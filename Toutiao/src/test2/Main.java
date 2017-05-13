package test2;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Set<String> set = new HashSet<String>();
		
		String line = null;
		int num = 0;
		while(sc.hasNextLine()){
			line =sc.nextLine();
			if(line.equals("0")){
				break;
			}
			if(!set.contains(line)){
				set.add(line);
				num++;
			}
		}
		sc.close();
		
		System.out.println(num);
		
		
	}
}
