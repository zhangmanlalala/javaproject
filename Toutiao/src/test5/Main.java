package test5;

import java.util.Scanner;

public class Main {
	
     public static void main(String[] args) {
    	 Scanner sc = new Scanner(System.in);
    	 String line = sc.nextLine();
    	 String pattern = sc.nextLine();
    	 sc.close();
    	 
    	 if(isMatch(line,pattern)){
    		 System.out.println(1);
    	 }else{
    		 System.out.println(0);
    	 }
    	 
    	 
    	 
	}
     
 	public static boolean isMatch(String s,String p){
		int sPos=0,pPos=0;
		int star=-1,mark=-1;
		while(sPos<s.length()){
			//�����Ӧλ����ȣ�����p�ǣ����������±�ȫ������
			if(pPos<p.length() && (s.charAt(sPos)==p.charAt(pPos) || p.charAt(pPos)=='?') ){
				pPos++;
				sPos++;
				continue;  //��������ѭ��
			}
			
			//���p��*����ô���p����*��λ�ã��±���ƣ�ͬʱ���s��ǰ��λ�ã���Ϊ*�������ƥ��
			if(pPos<p.length() && (p.charAt(pPos)=='*')){
				star = pPos++;
				mark = sPos;            //��ʵ�����p��������������**��û����
				continue;
			}
			
			//������涼�����㣬��Ҫp��λ��*֮���λ�ã�ͬʱs���ƣ�ֱ���ҵ���������������
			if(star!=-1){
				pPos = star+1;
				sPos = ++mark;
				continue;
			}
			
			//������涼�����ˣ�˵�����ƥ�䲻��
			return false;
		}
		//���s�������ˣ�p����ʣ�µģ��Ϳ�ʣ�µ��ǲ��Ƕ���*
		while(pPos<p.length() && p.charAt(pPos)=='*'){ //��Ȼ�ߵ���һ����˵��s�Ѿ����꣬�����ڶ��һ�٣��ж�һ��
			pPos++;
		}
		if(pPos==p.length()){
			return true;
		}else{
			return false;
		}
		
	}
}
