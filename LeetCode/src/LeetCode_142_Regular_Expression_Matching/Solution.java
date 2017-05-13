package LeetCode_142_Regular_Expression_Matching;

public class Solution {
	
	public boolean isMatch(String s,String p){
		if(p.length()==0){
			return s.length()==0;
		}
		//p�ĵڶ�λ����*
		if(p.length() ==1 || p.charAt(1)!='*'){
			if(s.length()<1 || (p.charAt(0)!='.' && s.charAt(0)!=p.charAt(0)))
				return false;
			//��ô��Ҫ��һλ���(��Ȱ���ƥ����������),��ƥ��ʣ��
			return isMatch(s.substring(1),p.substring(1));
		}else{//p�ĵڶ�λ��*�������ƥ��ǰi��
			int len = s.length();
			int i = -1;
			//i<0��ƥ��0��������ƥ������
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
