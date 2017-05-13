package ���ַ���ת��������;


//����������Ҫ���������������
//(1)���������
//(2)��������
public class Solution {	
	public static int strToInt(String str){
		if(str == null || str.length() == 0){
			return 0;
		}
		int sign = 1;
		if(str.charAt(0) == '-'){//����strΪ�������
			sign = -1;
			str = str.substring(1);
		}	
		
		int num = 0;	
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)>'9' || str.charAt(i)<'0'){
				return 0;//�޷�ת��
			}
			num = num*10 + str.charAt(i)-'0';
			if(sign == -1){
				if(num+1>Integer.MAX_VALUE){
					return 0;//���
				}
			}else{
				if(num>Integer.MAX_VALUE){
					return 0;//���
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
