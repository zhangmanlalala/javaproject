package LeetCode_18_Gas_Station;
/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.  
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * �ҵĽ��ʱ����Խ����
	 * @param gas
	 * @param cost
	 * @return
	 */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int remain[] = new int[gas.length];
        for(int i=0;i<gas.length;i++){
        	remain[i] = gas[i] - cost[i];
        }
        int sum = 0;
        boolean flag = true;
        for(int i=0;i<gas.length;i++){
        	sum = remain[i];
        	flag = true;
        	if(remain[i] <0){
        		continue;
        	}else{
        		
        		for(int j=i+1;j<gas.length;j++){
        			sum = sum+remain[j];
        			if(sum<0){
        				flag = false;
        				break;
        			}
        		}
        		
        		if(flag){
        			int j=0;
	        		for(;j<i;j++){
	        			sum = sum+remain[j];
	        			if(sum<0){
	        				break;
	        			}
	        		}
	        		if(j == i){
	        			return i;
	        		}
        		}
        		
        		
        	}
        }
        
        return -1;
    }
    /**
     * ��׼�ⷨ���ܾ���
     * ������

1, ��ÿһ��վ���������diff[i]��

2, ��0��ʼ���Խ�����ӣ���iʱ���������leftGasС��0��˵����������һ���ڵ��ˣ���ͷ�ڵ��Ϊi+1,��

3, ����ӵĹ����У�����ά��һ��sum,��¼�ܵĽ��࣬����ܵĽ��ൽ���С��0��˵�����۴��ĸ�վ�㿪ʼ����û�а취�ƻ�һ�ܡ�

�������⣺

1��Ϊʲôͷ�����i+1�����Ǳ��ԭ��ͷ��㣨����Ϊs, s<i������ǰ�ڵ��е������ڵ��أ�

��Ϊ��Ȼ��sֱ��i,leftGas��С��0����ô����ѡȡs��i�м����һ���ڵ㣨��Ϊk������ô���ǿ��Կ϶�����s��k��leftGasһ����С��0����ô��k��i��һ����С��0�����ԣ�k������������ֻ�ܴ�i+1��ʼ���ԣ�

2�� Ϊʲôֻ��Ҫ����������β������Ҫ�����»ع�ͷ�������أ�

��Ϊ������һ������sum��¼������������������е�diff�������Ľ����С��0��˵�����۴��ĸ�վ�㿪ʼ�����޷���������circle;

�෴��������sum��С��0��˵��һ�����ڣ���Ȼǰ���Ѿ�����ĳ�Σ���ĳ���Σ����Ϊ���������������һ��ȷ���Ŀ�ʼ�㵽����ĩβ��һ��һ�����ǰ���ֵ���в�����
     */
    
    public int canCompleteCircuit2(int[] gas, int[] cost) {  
        int n = gas.length;  
        int[] diff = new int[n];  
        for(int i=0; i<n; i++){  
            diff[i] = gas[i]-cost[i];  
        }  
        int start = 0, sum = 0, leftGas = 0;  
        for(int i=0; i<n; i++){  
            sum = sum + diff[i];  
            leftGas = leftGas + diff[i];  
            if(leftGas < 0){  
                start = i+1;  //��Ȼǰ��ĵ�i��leftgasС��0���������sum>0����ô�ض���Ӻ��濪ʼ�������������
                leftGas = 0;  //�����i+1;�ܾ���
            }  
        }  
        if(sum<0){  
            return -1;  
        }else{  
            return start;  
        }  
    }  
   
}
