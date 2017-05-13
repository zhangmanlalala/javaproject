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
 * 我自己的方法没解出来
 * @author Administrator
 *
 */
public class Solution {
	//标准解法一：还是用递归
	/**
	 *将s1分为s11和s12,s2分成s21和s22，则有
	 *isScramble(s11,s21)&&isScramble(s12,s22)
	 *
	 *或者
	 *isScramble(s11,s22)&&isScramble(s12,s21) 
	 * 
	 * 怎么分割，我们不知道，所以要实验每一个位置，只要有一个位置的分割可行，就可以返回true
	 * 
	 * 对于递归解法来讲，要减枝才能通过，减枝的意思是就是把不合理的尽早除去，不要再进入递归了
	 * 
	 * 比如:(重点，关于递归的减枝)
	 * 1，长度不相等
	 * 2，排序之后内容不相等
	 * 3，还有一种能尽早得到结果，比如s1.equals(s2)
	 */
	
	/**
	 * 
	 * 在判断内容相等时，使用了桶排序的思想，先把元素放在不同的桶中
	 */
	
	
	public boolean isScramble(String s1,String s2){
		if(s1.length()!=s2.length()) return false;  //减枝部分，也是递归结束条件和触底情况
		if(s1.equals(s2)) return true;
		int[] count = new int[26];
		int len = s1.length();
		for(int i=0;i<len;i++){
			count[s1.charAt(i)-'a']++;//这里用到桶排序的思想，巧妙之处在于将字符转化为数字，s1.charAt(i)-'a'
			count[s2.charAt(i)-'a']--;
		}
		//如果两者的内容不相等，直接返回false;
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
