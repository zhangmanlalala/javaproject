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
			//如果相应位置相等，或者p是？，则两个下标全部后移
			if(pPos<p.length() && (s.charAt(sPos)==p.charAt(pPos) || p.charAt(pPos)=='?') ){
				pPos++;
				sPos++;
				continue;  //结束本次循环
			}
			
			//如果p是*，那么标记p上面*的位置，下标后移，同时标记s当前的位置，因为*可能与空匹配
			if(pPos<p.length() && (p.charAt(pPos)=='*')){
				star = pPos++;
				mark = sPos;            //事实上如果p中有两个相连的**号没意义
				continue;
			}
			
			//如果上面都不满足，则要p定位在*之后的位置，同时s后移，直到找到满足上面条件的
			if(star!=-1){
				pPos = star+1;
				sPos = ++mark;
				continue;
			}
			
			//如果上面都不行了，说明真的匹配不了
			return false;
		}
		//如果s遍历完了，p还有剩下的，就看剩下的是不是都是*
		while(pPos<p.length() && p.charAt(pPos)=='*'){ //既然走到这一步，说明s已经走完，不用在多此一举，判断一遍
			pPos++;
		}
		if(pPos==p.length()){
			return true;
		}else{
			return false;
		}
		
	}
}
