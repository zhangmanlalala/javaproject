package LeetCode_63_Gray_Code;

import java.util.ArrayList;
import java.util.List;
/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the 
sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2

Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 
	 * 格雷码，我自己做出来的
	 * @param n
	 * @return
	 */
	 public List<Integer> grayCode(int n) {
	        List<Integer> list1 = new ArrayList<Integer>();
	        if(n==0){
	        	list1.add(0);
	        	return list1;
	        }
	        List<Integer> list2 = new ArrayList<Integer>();
	        
	        for(int i=1;i<=n;i++){
	        	if(i==1){
        			list1.add(0);
        			list1.add(1);
        			continue;
        		}
	        	int num1 = (int) Math.pow(2, i);
	        	list2 = list1;
	        	int num2 = (int) Math.pow(2, i-1);
	        	for(int j=num1-1;j>=num2;j--){
	        		list2.add(num2+list1.get(j-num2));
	        	}
	        	list1 = list2;
	
	        }
	        
	        return list1;
	 }
	 
	 public static void main(String[] args) {
		System.out.println(new Solution().grayCode(3).toString());
	}
}
