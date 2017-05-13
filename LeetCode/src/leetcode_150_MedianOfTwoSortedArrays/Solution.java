package leetcode_150_MedianOfTwoSortedArrays;
/**
 * ���⣺There are two sorted arrays A and B of size m and n respectively. 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * @author Administrator
 *
 */

/**
 * ������Ŀ��Ҫ����ǲ��������������λ��������ʱ�临�Ӷ�Ϊ����ʱ�临�Ӷ�
 * ��ôһ���Ƕ��ֲ��ң��ؼ�����ô������ָ��
 * �����Ŀ���Ѱ��A,B�е�K�����(k��0��ʼ,����k+1��������Ҫ����)��ÿ�ΰ�AB���ȱ�������(K+1)�������䵽A��B�����У�
 * ����A�ֵ�a������B�ֵ�k-1-a����ע�������k��ָ�����±꣬�Ǵ�0��ʼ��
 * ����
 * aMidLen = k*aLen/(aLen+bLen);
 * bMidLen = k-aMidlen-1;
 * aMid = aStart+aMidLen; //����A�ָ��(�����ָ�㹲��aMid+1��Ԫ��)
 * bMid = bStart+bMidlen; //����B�ָ��(�����ָ�㹲��bMid+1��Ԫ��)�����߼���������k+1��Ԫ�أ��պ÷���Ҫ��
 * ��Ϊ�������ָ�㹲����k+1��Ԫ�أ���
 * ���A[aMid]>B[bMid],˵��B��bMid(����bMid)ǰ���Ԫ��һ����K��֮��
 * ��A��aMid֮���Ԫ��һ����K��֮���ˣ��������ֶ�û�е�k����������������������֣�
 * ��ʣ����������ҵ�(k-bMid)������� ͬʱKҪ��ȥBǰ��ĸ�������֮��ͬ��
 * �������Ϸ�ʽ���ݹ����
 * 
 * 
 * �ݹ��ս������ǣ�
 * 1����һ�����鳤��Ϊ0����ô������һ�������еĵ�K��Ԫ��
 * 2,k���0����ô���������鿪ͷ��С���Ǹ���
 * ���⣬
 * ����������Ԫ�ص��������飬��λ�������м���һ��
 * ����ż����Ԫ�ص��������飬��λ�����Ǿ����м�������ƽ����
 * @author Administrator
 *
 */
public class Solution {
	public double findMedianSortedArrays(int A[],int B[]){
		int m=A.length;
		int n=B.length;
		if((m+n)%2!=0)
			return (double) findKth(A,B,(m+n)/2,0,m-1,0,n-1);
		else
			return 0.5*(findKth(A,B,(m+n)/2,0,m-1,0,n-1)+findKth(A,B,(m+n)/2-1,0,m-1,0,n-1));
	}
	/**
	 * ���ֲ���Ѱ�����������е�K�����
	 * 
	 * @param A
	 * @param B
	 * @param k
	 * @param aStart
	 * @param aEnd
	 * @param bStart
	 * @param bEnd
	 * @return
	 */
	
	/**
	 * ���ֲ��ҷ����ݹ�˼��ĺ��ģ� (1)�ݹ��������
	 * 							(2)(���ݵĸ���)�ݹ��ж�����{
	 * 								�ݹ�ʵ�ʲ���
	 * 							}
	 * 							(3)�ݹ���Ҳ���
	 */
	 
	public int findKth(int A[],int B[],int k,int aStart,int aEnd,int bStart,int bEnd){
		//�ݹ��������
		int aLen= aEnd-aStart+1;
		int bLen= bEnd-bStart+1;
		if(aLen == 0)
			return B[bStart+k];
		if(bLen == 0)
			return A[aStart+k];
		if(k==0)
			return Math.min(A[aStart], B[bStart]);
		
		
		//��������k�ֳ������֣��������������ȷֵ�����Խ������
		int aMidLen = k*aLen/(aLen+bLen);
		int bMidLen = k-aMidLen-1;
		int aMid = aStart+aMidLen;     //����A�ָ��
		int bMid = bStart+bMidLen;  
		//���ݵĸ��£��ݹ��ж�����+�ݹ�ʵ�ʲ���
		if(A[aMid] >B[bMid]){
			k=k-(bMid-bStart+1);
			aEnd =aMid;//ע��aMid���ܱ��ų�Ϊ��k���Ԫ�أ��ʲ�������
			bStart =bMid+1;
		}else{
			k=k-(aMid-aStart+1);
			bEnd =bMid;
			aStart =aMid+1;
		}
		//�ݹ���Ҳ���
		return findKth(A,B,k,aStart,aEnd,bStart,bEnd);
	}
}
