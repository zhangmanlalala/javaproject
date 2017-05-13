package 网易测试;

import java.util.Scanner;

public class Main1 {
	
	
	public static void main(String[] args) {
		
/*		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		String[] temp = line.split(" ");
		StringBuilder sb = new StringBuilder();
		int revX1 = Integer.parseInt(sb.append(temp[0]).reverse().toString());
		sb.delete(0, sb.length());
		int revY1 = Integer.parseInt(sb.append(temp[1]).reverse().toString());
		sb.delete(0, sb.length());
		
		int z = revX1+revY1;
		sb.append(z);
		
		System.out.println(Integer.parseInt(sb.reverse().toString()));*/
		
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int n = Integer.parseInt(line);
		
		int result = 0;
		if( n ==1){
			System.out.println(1);
		}else if(n==2){
			System.out.println(2);
		}
		
		result = 2;
		for(int i=3;i<=n;i++){
			if(i%2 !=0 ){//代表是奇数
				result +=i;
				continue;
			}
			
			for(int j=i/2;j>=1;j--){
				if((i%j == 0) && (j%2 !=0)){
					result +=j;
					break;
				}
			}
		}
		
		System.out.println(result);
				
		
		
		
		
		
	}
	
}
