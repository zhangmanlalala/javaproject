package LeetCode_62_Decode_Ways;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

[java] view plain copy

    'A' -> 1  
    'B' -> 2  
    ...  
    'Z' -> 26  

Given an encoded message containing digits, determine the total number of ways to decode it.

For example,

Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * ��̬�滮���Ե����ϣ�ʹ������forѭ�����������������ڶ�ά������
	 * @param s
	 * @return
	 */
	public int numDecodings(String s) {
        /**
         * ��̬�滮���������Ż��������õ�֮�⣬����������Ҳ���õ�����������������ǣ�
         * 1.�ַ���
         * 2.�󷽷�������
         * 
         * �ʹ�����ԣ�����������¥�ݷ�ʽ�����⣨��¥�����������Ķ�̬�滮���⣩
         * ��num[i]�Ǵ�0��ʼ����Ϊi���ִ� �Ľ����������������������������ǰ��һλ��2λ���ַ�ʽ��ֱ���Ͼ���
         * num[i] = num[i-1] + num[i-2]
         * ������⣬��Ҫ�жϺϷ������⣬���������1λ�Ļ���������0���������λ�Ļ���ǰ��һλ������0ͬʱ��λ����ֵҪ��1��26֮��
         * �����������������ǲ������ģ��Ͳ��ü���������Ľ����
         * 
         */
		
		if(s == null || s.length()==0 || s.charAt(0)=='0')
			return 0;
		int[] number = new int[s.length()+1];
		number[0] = number[1] = 1;
		int tmp;
		for(int i=2;i<=s.length();i++){
			//one���1λ����������1λ�ǺϷ��ģ�one=number[i-1],����one=0
			//two���2λ����������2λ�ǺϷ��ģ�two=number[i-2],����two=0
			int one=0,two=0;
			
			tmp = Integer.parseInt(s.substring(i-1, i));
			if(tmp!=0)
				one = number[i-1];
			if(s.charAt(i-2)!='0'){
				tmp = Integer.parseInt(s.substring(i-2,i));//���ǰ��ַ���ת��Ϊ����
				if(tmp>=1 && tmp<=26)
					two = number[i-2];
			}
			
			number[i] = one+two;
		}
		
		return number[s.length()];
    }
}
