package test1;

import java.util.Scanner;

public class Main {
	
	/**
	 * 
	 * 
	 * ��������������⣬����ⷨ
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
  
        //���������е�ֵ���Ǹ�ֵ�����
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
