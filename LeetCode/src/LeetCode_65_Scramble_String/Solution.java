package LeetCode_65_Scramble_String;
/**
 *  Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t

To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t

We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a

We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1. 
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 
 * 
 * ���Լ��ķ���û�����
 * @author Administrator
 *
 */
public class Solution {
	//��׼�ⷨһ�������õݹ�
	/**
	 *��s1��Ϊs11��s12,s2�ֳ�s21��s22������
	 *isScramble(s11,s21)&&isScramble(s12,s22)
	 *
	 *����
	 *isScramble(s11,s22)&&isScramble(s12,s21) 
	 * 
	 * ��ô�ָ���ǲ�֪��������Ҫʵ��ÿһ��λ�ã�ֻҪ��һ��λ�õķָ���У��Ϳ��Է���true
	 * 
	 * ���ڵݹ�ⷨ������Ҫ��֦����ͨ������֦����˼�Ǿ��ǰѲ�����ľ����ȥ����Ҫ�ٽ���ݹ���
	 * 
	 * ����:(�ص㣬���ڵݹ�ļ�֦)
	 * 1�����Ȳ����
	 * 2������֮�����ݲ����
	 * 3������һ���ܾ���õ����������s1.equals(s2)
	 */
	
	/**
	 * 
	 * ���ж��������ʱ��ʹ����Ͱ�����˼�룬�Ȱ�Ԫ�ط��ڲ�ͬ��Ͱ��
	 */
	
	
	public boolean isScramble(String s1,String s2){
		if(s1.length()!=s2.length()) return false;  //��֦���֣�Ҳ�ǵݹ���������ʹ������
		if(s1.equals(s2)) return true;
		int[] count = new int[26];
		int len = s1.length();
		for(int i=0;i<len;i++){
			count[s1.charAt(i)-'a']++;//�����õ�Ͱ�����˼�룬����֮�����ڽ��ַ�ת��Ϊ���֣�s1.charAt(i)-'a'
			count[s2.charAt(i)-'a']--;
		}
		//������ߵ����ݲ���ȣ�ֱ�ӷ���false;
		for(int i=0;i<26;i++){
			if(count[i]!=0){
				return false;
			}
		}
		
		
		for(int step=1;step<len;step++){
			String s11 = s1.substring(0, step);
			String s12 = s1.substring(step);
			
			String s21 = s2.substring(0, step);
			String s22 = s2.substring(step);
			
			if(isScramble(s11,s21) && isScramble(s12,s22)) return true;
			
			s21 = s2.substring(0,len-step);
			s22 = s2.substring(len-step);
			if(isScramble(s11,s22) && isScramble(s12,s21)) return true;
		}
		
		return false;
	}
}
