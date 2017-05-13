package LeetCode_55_Interleaving_String;
/**
 *  Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false. 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * ���Լ��Ľⷨ���ݹ�ʱ�临�ӶȺܸߣ�ͨ����
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public boolean isInterleave2(String s1, String s2, String s3) {
        
		
		if(s1.length()+s2.length() !=s3.length()){
			return false;
		}
		
		if(s3.length() == 0 && s1.length()==0 && s2.length()==0){
			return true;
		}
		
		char ch3 = s3.charAt(s3.length()-1);
		
		if(s1.length()>0 && s2.length()>0){
			char ch1 = s1.charAt(s1.length()-1);
			char ch2 = s2.charAt(s2.length()-1);
			if( ch3!=ch1 && ch3!=ch2 ){//���һ��Ԫ�ض�����ȣ�ֱ�ӷ���false
				return false;
			}else if(ch3!=ch1){//ch3 == ch2
				return isInterleave(s1,s2.substring(0, s2.length()-1),s3.substring(0, s3.length()-1));
			}else if(ch3!=ch2){//ch3 == ch1
				return isInterleave(s1.substring(0, s1.length()-1),s2,s3.substring(0, s3.length()-1));
			}else{//ch3 == ch2 && ch3 == ch1
				return isInterleave(s1,s2.substring(0, s2.length()-1),s3.substring(0, s3.length()-1)) || isInterleave(s1.substring(0, s1.length()-1),s2,s3.substring(0, s3.length()-1));
			}
		}else if(s1.length()>0){
			char ch1 = s1.charAt(s1.length()-1);
			if( ch3!=ch1 ){//���һ��Ԫ�ض�����ȣ�ֱ�ӷ���false
				return false;
			}else{
				return isInterleave(s1.substring(0, s1.length()-1),s2,s3.substring(0, s3.length()-1));
			}
		}else if(s2.length()>0){
			char ch2 = s2.charAt(s2.length()-1);
			if(ch3!=ch2){
				return false;
			}else{
				return isInterleave(s1,s2.substring(0, s2.length()-1),s3.substring(0, s3.length()-1));
			}
		}else{
			return false;
		}
    }
	/**
	 * 
	 * 
	 * ��׼�ⷨ����̬�滮���ҳ������⣬�ҳ�״̬ת�Ʒ������������Ľ�
	 * �����⣺��mat[i][j]��ʾs1��ͷ��ʼ����Ϊi�ִ���s2��ͷ��ʼ����Ϊj���ִ��ܷ񽻲��s3�ϳ���Ϊ(i+j)���Ӵ�
	 * ���ǿ��Կ�������������������ͬ�ʣ�ֻ�ǹ�ģ��ͬ�����Ǳ�׼�Ķ�̬�滮
	 * 
	 * ��������״̬ת�Ʒ���
	 * mat[i][j] = mat[i-1][j] &&(s1.charAt(i) == s3.charAt(i+j-1))  || mat[i][j-1] &&(s2.charAt(j) == s3.charAt(i+j-1))
	 * 
	 * 
	 * ���������ǳ�ʼ��
	 * mat[0][0]Ӧ����true�������ʼ��mat[i][0]��mat[0][j]
	 * @param args
	 */
	
	public boolean isInterleave(String s1, String s2, String s3) {
		if(s1 == null || s2 == null || s3==null){
			return false;
		}
		
		if(s3.length()!=s1.length()+s2.length()){
			return false;
		}
		
		boolean mat[][] = new boolean[s1.length()+1][s2.length()+1];
		mat[0][0] = true;//��ʼ��
		
		for(int i=1;i<s1.length()+1;i++){
			if(s1.charAt(i-1) == s3.charAt(i-1) && mat[i-1][0])
				mat[i][0] =true;
			else{
				break;
			}
		}
		
		for(int j=1;j<s2.length()+1;j++){
			if(s2.charAt(j-2) == s3.charAt(j-1) && mat[0][j-1])
				mat[0][j] = true;
			else
				break;
		}
		
		
		for(int i=1;i<s1.length()+1;i++){
			for(int j=1;j<s2.length()+1;j++){
				if(s1.charAt(i-1) == s3.charAt(i+j-1) && mat[i-1][j])
					mat[i][j] = true;
				if(s2.charAt(j-1) == s3.charAt(i+j-1) && mat[i][j-1])
					mat[i][j] = true;
			}
		}
		
		return mat[s1.length()][s2.length()];
		
		
		//�ܱ�׼�Ķ�̬�滮���ҳ�״̬ת�Ʒ������������Ľ�
		
	}
	
	public static void main(String[] args) {
		String s1 = "aabcc";
		String s2 = "dbbca";
		//String s3 = "aadbbcbcac";
		String s3 = "aadbbbaccc";
		//System.out.println(s1.substring(0, 0));
		System.out.println(new Solution().isInterleave(s1, s2, s3));
	}
}
