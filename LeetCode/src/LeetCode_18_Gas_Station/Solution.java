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
	 * 我的解答时间又越界了
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
     * 标准解法：很经典
     * 分析：

1, 对每一个站，计算结余diff[i]；

2, 从0开始，对结余相加，到i时，如果结余leftGas小于0，说明开不到下一个节点了，则头节点变为i+1,；

3, 在相加的过程中，另外维护一个sum,记录总的结余，如果总的结余到最后小于0，说明无论从哪个站点开始，都没有办法绕环一周。

两个问题：

1，为什么头结点变成i+1而不是变成原来头结点（假设为s, s<i）到当前节点中的其他节点呢？

因为既然从s直到i,leftGas才小于0，那么我们选取s到i中间的任一个节点（设为k），那么我们可以肯定，从s到k的leftGas一定不小于0，那么从k到i就一定会小于0，所以，k不满足条件，只能从i+1开始尝试；

2， 为什么只需要遍历到数组尾而不需要再重新回过头来考察呢？

因为我们有一个变量sum记录整体的情况，如果把所有的diff加起来的结果都小于0，说明无论从哪个站点开始，都无法遍历整个circle;

相反，如果最后sum不小于0，说明一定存在，既然前面已经出现某段（或某几段）相加为负的情况，则从最后一个确定的开始点到数组末尾的一段一定会对前面的值进行补偿。
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
                start = i+1;  //既然前面的到i的leftgas小于0，如果最后的sum>0，那么必定会从后面开始补偿，所以起点
                leftGas = 0;  //变成了i+1;很经典
            }  
        }  
        if(sum<0){  
            return -1;  
        }else{  
            return start;  
        }  
    }  
   
}
