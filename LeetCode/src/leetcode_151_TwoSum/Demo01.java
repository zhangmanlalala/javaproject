package leetcode_151_TwoSum;

import java.util.HashMap;
import java.util.Map;

public class Demo01 {
	//˼·һ
	/**
	 * �������е�ֵ��������Ȼ��������ָ�룬һ��������߿�ʼ������
	 * һ�������ұ߿�ʼ�����ƶ�һֱ�ҵ�����֮��Ϊtarget
	 * @param array
	 * @param target
	 * @return
	 */
	public static int[] SumTwo1(int[] array,int target){
		int copyArray[] = new int[array.length];
		for(int i=0;i<array.length;i++){
			copyArray[i]=array[i];
		}
		int temp=0;
		//ð������
		boolean sorted=true;
		for(int i=0;i<array.length-1;i++){
			sorted=true;			
			for(int j=0;j<array.length-i-1;j++){
				if(array[j]>array[j+1]){
					temp = array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
					sorted=false;
				}
			}
			if(sorted){
				break;
			}
		}
		
		int i=0;
		int j=array.length-1;
		while(array[i]+array[j]!=target){
			if(array[i]+array[j]<target){
				i++;
			}else{ 
				j--;
			}
			if(i==j){
				break;
			}
			
		}
		if(i!=j){
			return new int[]{i+1,j+1};
		}else{
			return null;
		}
		
		
	}
		
	//˼·������ٷ�	
	public static int[] SumTwo2(int[] array,int target){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<array.length-1;i++){
			
			for(int j=i+1;j<array.length;j++){
				if(array[i]+array[j]==target){
					return new int[]{i+1,j+1};
					
				}
			}
		}
		return null;
	}
	
	
	//˼·��
	/**
	 * 1.��map��ԭ�����е�ÿ�����ݼ����±��װ����������������Ϊ�����±���Ϊֵ��������Ҫ
	 * 2.��ԭ������ÿ�����ݵĲ�������target��ȥ�����ݣ�����map��Ѱ��ÿ�����ݲ���
	 * @param array
	 * @param target
	 * @return
	 */
	public static int[] SumTwo3(int[] array,int target){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		int index[] = new int[2];
		int complement=0;
		Integer indx=null;
		for(int i=0;i<array.length;i++){
			map.put(array[i], i);
		}
		
		for(int i=0;i<array.length;i++){
			complement = target-array[i];
			indx = map.get(complement);
			
			if(indx!=null && indx!=i){//(����������ǳ���Ҫ)
				if(i>indx){
					index[0]=indx;
					index[1]=i;
				}else{
					index[0]=i;
					index[1]=indx;
				}
				return index;
			}
		}
		return null;
	}
	
	
	
	public static void main(String[] args) {
		int array[] =new int[]{1,15,9,21,36,18};
//		System.out.println("index1:"+SumTwo1(array,30)[0]+"-----index2:"+SumTwo1(array,30)[1]);
		System.out.println("index1:"+SumTwo2(array,30)[0]+"-----index2:"+SumTwo2(array,30)[1]);
		System.out.println("index1:"+SumTwo3(array,30)[0]+"-----index2:"+SumTwo3(array,30)[1]);
	}
}
