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
	 * ����������λ����
	 * �����������ͱ����������ǿ�����������������������ģ������������
	 * onesÿһλ���1(��3)����һ�ε������
	 * twosÿһλ���1(��3)����2�ε������
	 * ÿ��ones��twosĳһλ����1��ʱ��˵����Ϊ1���������Σ���ʱ��Ҫ��ones��twos��Ӧλ����0����0��
	 * �����������ǽ�ones��twos��λ����ȡ����֮��ֱ���ones��twos��λ�󽻼���
	 * ���������ones���ǽ��
	 */
	
	/**
	 * 
	 * û��ô����
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
	 * �ⷨ����ͨ�ýⷨ
	 * int ���ݹ���32λ��������32�������洢��N��Ԫ���и���������λ��1���ֵĴ���������ٽ���ģ������
	 * ���Ϊ1����ô˵����һλ��Ҫ��Ԫ�ض����Ʊ�ʾ��Ϊ1����λ��
	 */
	public int singleNumber3(int A[]){
		int len = A.length;
		int bitnum[] = new int[32];
		int res = 0;
		for(int i=0;i<32;i++){
			for(int j=0;j<len;j++){
				if(((A[j]>>i)&1)>0)//�����������Ҫ
					bitnum[i] = bitnum[i] + 1;
			}
			res |= (bitnum[i]%3)<<i;
		}
		
		return res;
	}
	/**
	 * ʱ�䣺O(32*N)������һ��ͨ�õĽⷨ������ѳ���3�θ�Ϊ k �Σ���ôֻ��ģk�����ˡ�
	 */
}
