package didi;

import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		String numStr = sc.nextLine();
		sc.close();
		String[] str = line.split(" ");
		//int num = Integer.parseInt(numStr);
		
		StringBuilder sb = new StringBuilder();
		
		while(numStr.length()>0){
			int comle = numStr.charAt(0)-'0';
			numStr = numStr.substring(1);
			sb.append(getString(comle));
		}
		String str1 = sb.toString();
		
		int maxLen = Integer.MIN_VALUE;
		int idx = 0;
		for(int i=0;i<str.length;i++){
			int len = largestCommonSubstring(str1.toLowerCase(),str[i].toLowerCase());
			if(len>maxLen){
				maxLen = len;
				idx = i;
			}
		}
		System.out.println(str[idx]);
		
		
		
		
	}
	
	public static String getString(int num){
		
		if(num==2){
			return "abc";
		}else if(num==3){
			return "def";
		}else if(num == 4){
			return "ghi";
		}else if(num ==5){
			return "jkl";
		}else if(num == 6){
			return "mno";
		}else if(num == 7){
			return "pqrs";
		}else if(num == 8){
			return "tuv";
		}else if(num == 9){
			return "wxyz";
		}else{
			return "";
		}
		
	}
	
	
	public static int largestCommonSubstring(String str1,String str2){
		
		
		if(str1==null || str1.length() == 0 || str2==null || str2.length()==0){
			return 0;
		}
		
		char a = str1.charAt(str1.length()-1);
		char b = str2.charAt(str2.length()-1);
		
		if(a == b){
			return (1+largestCommonSubstring(str1.substring(0, str1.length()-1),str2.substring(0, str2.length()-1)));
		}else{
			int num1 = largestCommonSubstring(str1,str2.substring(0,str2.length()-1));
			int num2 = largestCommonSubstring(str1.substring(0, str1.length()-1),str2);
			return Math.max(num1, num2);
		}
		
		
		
	}
}
