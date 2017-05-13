package LeetCode_142_Regular_Expression_Matching;

public class Solution {
	
	public boolean isMatch(String s,String p){
		if(p.length()==0){
			return s.length()==0;
		}
		//p的第二位不是*
		if(p.length() ==1 || p.charAt(1)!='*'){
			if(s.length()<1 || (p.charAt(0)!='.' && s.charAt(0)!=p.charAt(0)))
				return false;
			//那么需要第一位相等(相等包括匹配任意的情况),再匹配剩余
			return isMatch(s.substring(1),p.substring(1));
		}else{//p的第二位是*，则可以匹配前i个
			int len = s.length();
			int i = -1;
			//i<0是匹配0个，可以匹配任意
			while(i<len && (i<0 || p.charAt(0)=='.' || p.charAt(0)==s.charAt(i))){
				if(isMatch(s.substring(i+1),p.substring(2)))
					return true;
				i++;
			}
			return false;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
