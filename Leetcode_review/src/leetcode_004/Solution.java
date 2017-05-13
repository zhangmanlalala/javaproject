package leetcode_004;
/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be
 O(log (m+n)).

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0

Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * �����Ҫ��ʱ�临�Ӷ�ΪO(log (m+n)).�����Ƕ��ֲ��ҷ��������ʵ��ת��Ϊ��A��B��������������Ѱ�ҵ�k�����
	 * ������������������ͬʱ���ж��ֲ��ҷ�
	 * (1)ȷ��k�Ĵ�С(k��������0��ʼ),��k+1����
	 * (2)��������A��B�ĳ��ȱ�������A��B�й�ѡȡK����
	 * A��ѡȡ�ĸ���ΪaMidLen = k*aLen(aLen+bLen)
	 * B��ѡȡ�ĸ���ΪbMidLen = k-1-aMidLen 
	 * aMid = aStart+aMidLen
	 * bMid = bStart+bMidLen   ���Լ���պ�ѡȡ��k+1����
	 * 
	 * (3)�Ƚ�A[aMid]��B[bMid]֮��Ĺ�ϵ�����A[aMid]>b[bMid]��˵��aMid֮���������k֮�⣬����
	 * bMid֮ǰ��������k֮�ڣ���������ʣ�µ�������Ѱ�ҵ�k-(bMid-bStart+1)����������A[aMid]<b[bMid]ͬ�����A[mid]==B[bMid]
	 * �ݹ����
	 * 
	 * (4)�ݹ�����������ߴ��������k==0������һ������ĳ���Ϊ0
	 * @param nums1
	 * @param nums2
	 * @return
	 */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	if(nums1==null){
    		return nums2[nums2.length/2];
    	}else if(nums2 == null){
    		return nums1[nums1.length/2];
    	}
    	int m=nums1.length;
		int n=nums2.length;
		if((m+n)%2!=0)
			return (double) findMedianSortedArrays(nums1,0,m-1,nums2,0,n-1,(m+n)/2);
		else
			return 0.5*(findMedianSortedArrays(nums1,0,m-1,nums2,0,n-1,(m+n)/2)+findMedianSortedArrays(nums1,0,m-1,nums2,0,n-1,(m+n)/2-1));
    }
    
    public int findMedianSortedArrays(int[] nums1,int astart,int aend,int[] nums2,int bstart,int bend,int k){
    	if(astart>aend){
    		return nums2[k+bstart];
    	}
    	if(bstart>bend){
    		return nums1[k+astart];
    	}
    	if(k==0){
    		return Math.min(nums1[astart], nums2[bstart]);
    	}
    	
    	int aLen = aend-astart+1;
    	int bLen = bend-bstart+1;
    	
    	int aMidLen = k*aLen/(aLen+bLen);
    	int bMidLen = k-1-aMidLen ;
    	
    	int aMid = astart+aMidLen;
    	int bMid = bstart+bMidLen;
    	
    	if(nums1[aMid]>nums2[bMid]){
    		k = k-(bMid-bstart+1);
    		aend = aMid;
    		bstart = bMid+1;//bMid�Ѿ���k�м�ȥ��
    		
    	}else if(nums1[aMid]<nums2[bMid]){
    		k = k-(aMid-astart+1);
    		bend = bMid;
    		astart = aMid+1;
    	}else{
    		return nums1[aMid];
    	}
    	
    	return findMedianSortedArrays(nums1,astart,aend,nums2,bstart,bend,k);
    	
    }
    
    
    public static void main(String[] args) {
		new Solution().findMedianSortedArrays(new int[]{1,2}, new int[]{1,2});
	}
}
