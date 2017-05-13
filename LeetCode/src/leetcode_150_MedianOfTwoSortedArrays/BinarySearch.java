package leetcode_150_MedianOfTwoSortedArrays;
/**
 * ���ֲ��ҷ����ݹ�˼��ĺ��ģ� (1)�ݹ��������
 * 							(2)�ݹ��ж�����{
 * 								�ݹ�ʵ�ʲ���
 * 							}
 * 							(3)�ݹ���Ҳ���
 * 							
 * @author Administrator
 *
 */
public class BinarySearch {
	/**
	 * ���ֲ��ҷ�whileѭ��ʵ��
	 * 
	 * �ٶ�����Ϊ�����
	 * ����-1����û�ҵ�
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
	 * ���ֲ��ҷ��ݹ�ʵ��
	 * @return
	 * 
	 * �ٶ�����Ϊ�����
	 * ����-1����û�ҵ�
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
	 * ����ʹ��
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		int array[] =new int[]{1,8,15,26,39,87};
		System.out.println(binarySearch1(array,15));
		System.out.println(binarySearch2(array,39,0,array.length));
	}
	
}
