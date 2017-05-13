package test4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String line = sc.nextLine();
		
		int num = Integer.parseInt(line);
		int[][] arr = new int[num][2];
		
		int i=0;
		while(sc.hasNextLine()){
			line = sc.nextLine();
			String[] str = line.split(" ");
			arr[i][0] = Integer.parseInt(str[0]);
			arr[i][1] = Integer.parseInt(str[1]);
			
			i++;
			if(i>=num){
				break;
			}	
			
		}
		
		sc.close();
		
		int result[] = new int[num];
		result[0] = 1;
		
		List<Integer> lstwidth = new ArrayList<Integer>();
		List<Integer> lstHigh = new ArrayList<Integer>();
		for(int j=1;j<num;j++){
			
			i=j;
			for(;i>=0;i--){
				if((arr[j][0]<arr[i][0] && arr[j][1]<arr[i][1]) || (arr[j][0]>arr[i][0] && arr[j][1]>arr[i][1])){
					break;
				}
			}
			
			if(i>=0 && !lstwidth.contains(arr[j][0]) && !lstHigh.contains(arr[j][1])){
				lstwidth.add(arr[j][0]);
				lstHigh.add(arr[j][1]);
				result[j] = result[j-1]+1;
			}else{
				result[j] = result[j-1];
			}
			
			
		}
		
		System.out.println(result[num-1]);

	}

}
