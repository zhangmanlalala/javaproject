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
	 * 这道题要求时间复杂度为O(log (m+n)).所以是二分查找法，这道题实际转化为在A，B两个有序数组中寻找第k大的数
	 * 方法是在两个数组上同时进行二分查找法
	 * (1)确定k的大小(k是索引从0开始),共k+1个数
	 * (2)按照数组A和B的长度比例，从A和B中共选取K个数
	 * A中选取的个数为aMidLen = k*aLen(aLen+bLen)
	 * B中选取的个数为bMidLen = k-1-aMidLen 
	 * aMid = aStart+aMidLen
	 * bMid = bStart+bMidLen   可以计算刚好选取了k+1个数
	 * 
	 * (3)比较A[aMid]和B[bMid]之间的关系，如果A[aMid]>b[bMid]则说明aMid之后的数都在k之外，舍弃
	 * bMid之前的数都在k之内，舍弃，在剩下的数组中寻找第k-(bMid-bStart+1)大的数；如果A[aMid]<b[bMid]同理，如果A[mid]==B[bMid]
	 * 递归结束
	 * 
	 * (4)递归结束条件或者触底情况，k==0或者有一个数组的长度为0
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
    		bstart = bMid+1;//bMid已经从k中减去了
    		
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
