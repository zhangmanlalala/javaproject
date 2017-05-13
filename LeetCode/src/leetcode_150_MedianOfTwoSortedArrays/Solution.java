package leetcode_150_MedianOfTwoSortedArrays;
/**
 * 问题：There are two sorted arrays A and B of size m and n respectively. 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * @author Administrator
 *
 */

/**
 * 由于题目中要求的是查找两个数组的中位数，并且时间复杂度为对数时间复杂度
 * 那么一定是二分查找，关键是怎么找这个分割点
 * 这个题目变成寻找A,B中第K大的数(k从0开始,共有k+1个数据需要分配)，每次按AB长度比例，将(K+1)个数分配到A，B数组中，
 * 假设A分到a个，则B分到k-1-a个。注意这里的k是指数组下标，是从0开始的
 * 即：
 * aMidLen = k*aLen/(aLen+bLen);
 * bMidLen = k-aMidlen-1;
 * aMid = aStart+aMidLen; //这是A分割点(包含分割点共有aMid+1个元素)
 * bMid = bStart+bMidlen; //这是B分割点(包含分割点共有bMid+1个元素)，两者加起来共有k+1个元素，刚好符合要求
 * 因为包括两分割点共有有k+1个元素，则：
 * 如果A[aMid]>B[bMid],说明B中bMid(包括bMid)前面的元素一定在K个之内
 * 且A中aMid之后的元素一定在K个之外了，这两部分都没有第k大的数，可以舍弃这两部分，
 * 在剩余的数据中找第(k-bMid)大的数， 同时K要减去B前面的个数；反之，同理
 * 按照以上方式，递归二分
 * 
 * 
 * 递归终结条件是：
 * 1，有一个数组长度为0，那么就是另一个数组中的第K个元素
 * 2,k变成0，那么就是两数组开头较小的那个。
 * 另外，
 * 对于奇数个元素的有序数组，中位数就是中间那一个
 * 对于偶数个元素的有序数组，中位数就是就是中间两个的平均。
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
	 * 二分查找寻找两个数组中第K大的数
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
	 * 二分查找法：递归思想的核心： (1)递归结束条件
	 * 							(2)(数据的更新)递归判断条件{
	 * 								递归实际操作
	 * 							}
	 * 							(3)递归查找操作
	 */
	 
	public int findKth(int A[],int B[],int k,int aStart,int aEnd,int bStart,int bEnd){
		//递归结束条件
		int aLen= aEnd-aStart+1;
		int bLen= bEnd-bStart+1;
		if(aLen == 0)
			return B[bStart+k];
		if(bLen == 0)
			return A[aStart+k];
		if(k==0)
			return Math.min(A[aStart], B[bStart]);
		
		
		//按比例将k分成两部分，按比例分配避免等分的数组越界问题
		int aMidLen = k*aLen/(aLen+bLen);
		int bMidLen = k-aMidLen-1;
		int aMid = aStart+aMidLen;     //这是A分割点
		int bMid = bStart+bMidLen;  
		//数据的更新：递归判断条件+递归实际操作
		if(A[aMid] >B[bMid]){
			k=k-(bMid-bStart+1);
			aEnd =aMid;//注意aMid不能被排除为第k大的元素，故不能舍弃
			bStart =bMid+1;
		}else{
			k=k-(aMid-aStart+1);
			bEnd =bMid;
			aStart =aMid+1;
		}
		//递归查找操作
		return findKth(A,B,k,aStart,aEnd,bStart,bEnd);
	}
}
