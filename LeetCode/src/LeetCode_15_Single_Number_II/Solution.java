package LeetCode_15_Single_Number_II;
/**
 * Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?  
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 
	 * 分析：考查位运算
	 * 运用三个整型变量，把他们看作二进制数，用这三个数模拟三进制运算
	 * ones每一位存放1(余3)出现一次的情况；
	 * twos每一位存放1(余3)出现2次的情况；
	 * 每当ones和twos某一位都是1的时候，说明该为1出现了三次，这时候要把ones和twos相应位置清0，清0的
	 * 具体做法就是讲ones和twos按位求交再取反，之后分别于ones和twos按位求交即可
	 * 这样，最后ones就是结果
	 */
	
	/**
	 * 
	 * 没怎么看懂
	 * @param A
	 * @return
	 */
	public int singleNumber(int[] A){
		int ones = 0,twos=0,temp = 0;
		for(int i=0;i<A.length;i++){
			twos = twos|(ones & A[i]);
			ones = ones^A[i];
			temp = ~(ones & twos);
			ones = ones & temp;
			twos = twos & temp;
		}
		
		return ones;
	}
	
	
	/**
	 * 
	 * 
	 * 解法二，通用解法
	 * int 数据共有32位，可以用32个变量存储着N个元素中各个二进制位上1出现的次数，最后再进行模三操作
	 * 如果为1，那么说明这一位是要找元素二进制表示中为1的那位，
	 */
	public int singleNumber3(int A[]){
		int len = A.length;
		int bitnum[] = new int[32];
		int res = 0;
		for(int i=0;i<32;i++){
			for(int j=0;j<len;j++){
				if(((A[j]>>i)&1)>0)//这个条件很重要
					bitnum[i] = bitnum[i] + 1;
			}
			res |= (bitnum[i]%3)<<i;
		}
		
		return res;
	}
	/**
	 * 时间：O(32*N)，这是一个通用的解法，如果把出现3次改为 k 次，那么只需模k就行了。
	 */
}
