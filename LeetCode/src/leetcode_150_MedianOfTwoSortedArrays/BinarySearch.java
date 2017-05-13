package leetcode_150_MedianOfTwoSortedArrays;
/**
 * 二分查找法：递归思想的核心： (1)递归结束条件
 * 							(2)递归判断条件{
 * 								递归实际操作
 * 							}
 * 							(3)递归查找操作
 * 							
 * @author Administrator
 *
 */
public class BinarySearch {
	/**
	 * 二分查找法while循环实现
	 * 
	 * 假定数组为有序的
	 * 返回-1代表没找到
	 */
	
	public static int binarySearch1(int array[],int target){
		int startPos=0;
		int endPos=array.length-1;
		int midPos =(startPos+endPos)/2;
		while(startPos<=endPos){
			if(array[midPos]<target){
				startPos =midPos+1;
				midPos = (startPos+endPos)/2;
			}else if(array[midPos]>target){
				endPos = midPos-1;
				midPos = (startPos+endPos)/2;
			}else{
				break;
			}
		}
		
		if(startPos<=endPos){
			return midPos;
		}else{
			return -1;
		}
	}
	
	
	/**
	 * 二分查找法递归实现
	 * @return
	 * 
	 * 假定数组为有序的
	 * 返回-1代表没找到
	 */
	public static int binarySearch2(int array[],int target,int startPos,int endPos){
		if(array==null || startPos>endPos){
			return -1;
		}
		int midPos =(startPos+endPos)/2;
		if(array[midPos]<target){
			startPos=midPos+1;		
		}else if(array[midPos]>target){
			endPos=midPos-1;
		}else{
			return midPos;
		}
		return binarySearch2(array, target, startPos, endPos);
		
	}
	/**
	 * 
	 * 测试使用
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		int array[] =new int[]{1,8,15,26,39,87};
		System.out.println(binarySearch1(array,15));
		System.out.println(binarySearch2(array,39,0,array.length));
	}
	
}
