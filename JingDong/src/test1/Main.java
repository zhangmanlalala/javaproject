package test1;

import java.util.Scanner;

public class Main {
	
	/**
	 * 
	 * 
	 * 最大子数组子问题，经典解法
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		String[] str = line.split(" ");
		int[] num = new int[str.length];
		for(int i=0;i<str.length;i++){
			num[i] = Integer.parseInt(str[i]);
		}
		
		
		int curSum = 0;  
        int maxSum = 0;  
        int len = num.length;  
  

        for (int i = 0; i < len; i++) {  
            curSum += num[i];  
            if (curSum < 0) {  
                curSum = 0;  
            }  
            if (curSum > maxSum) {  
                maxSum = curSum;  
            }  
        }  
  
        //数组中所有的值都是负值的情况
        if (maxSum == 0) {  
            for (int i = 0; i < len; i++) {  
                if (i == 0) {  
                    maxSum = num[i];  
                }  
                if (num[i] > maxSum) {  
                    maxSum = num[i];  
                }  
            }  
        } 
        System.out.println(maxSum);
 
	}
	
}
