package 将字符串转化成整数;


//分析：这里要考虑两个特殊情况
//(1)负数的情况
//(2)溢出的情况
public class Solution {	
	public static int strToInt(String str){
		if(str == null || str.length() == 0){
			return 0;
		}
		int sign = 1;
		if(str.charAt(0) == '-'){//处理str为负的情况
			sign = -1;
			str = str.substring(1);
		}	
		
		int num = 0;	
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)>'9' || str.charAt(i)<'0'){
				return 0;//无法转化
			}
			num = num*10 + str.charAt(i)-'0';
			if(sign == -1){
				if(num+1>Integer.MAX_VALUE){
					return 0;//溢出
				}
			}else{
				if(num>Integer.MAX_VALUE){
					return 0;//溢出
				}
			}
		}
		
		if(sign == -1){
			return -num;
		}else{
			return num;
		}
		
		
	}
}
