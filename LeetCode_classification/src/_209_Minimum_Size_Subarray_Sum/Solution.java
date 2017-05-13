package _209_Minimum_Size_Subarray_Sum;
/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a 
 * subarray of which the sum �� s. If there isn't one, return 0 instead.
	For example, given the array [2,3,1,2,4,3] and s = 7,
	the subarray [4,3] has the minimal length under the problem constraint.  
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 
	 * ��̬�滮���ҵ������⣬���״̬ת�Ʒ���
	 * 
	 * �ⷨ����
	 * @param s
	 * @param nums
	 * @return
	 */
    public int minSubArrayLen2(int s, int[] nums) {
        if(nums == null || nums.length==0){
        	return 0;
        }
    	
    	int result[] = new int[nums.length+1];
    	result[0] = 0;

    	int sum = 0;
    	for(int i=1;i<=nums.length;i++){
    		sum = 0;
    		if(result[i-1] == 0){
    			int j=i-1;
    			for(;j>=0;j--){
    				sum = sum+nums[j];
    				if(sum>=s){
    					break;
    				}
    			}
    			if(j<0){
    				result[i] = 0;
    			}else{
    				result[i] = (i-1-j)+1;
    			}
    			
    		}else{
    			int j=i-1;
    			for(;j>=0;j--){
    				sum = sum+nums[j];
    				if(sum>=s){
    					break;
    				}
    			}
    			
    			result[i] = Math.min(result[i-1], i-1-j+1);
    		}
    	}
    	
    	return result[nums.length];
    	
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[]{2,3,1,2,4,3};
		System.out.println(new Solution().minSubArrayLen(7, nums));
	}
    
    /**
     * 
     * ˼·2��˫ָ�뷨,����ָ��֮��ά��һ�������飬ʹ���>=s,�ƶ�����ָ�롣
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length==0){
        	return 0;
        }
    	int pre = 0;
    	int last = 0;
    	int sum = nums[0];
    	int len = Integer.MAX_VALUE;
    	while(last<nums.length){
    		
    		if(sum>=s){
    			len = Math.min(len, last-pre+1);
    			sum = sum-nums[pre];//�Ӻ��м�ȥ��ǰ���һ��
    			pre++;//��һ��ָ������ƶ�һλ
    		}else{
    			last++;
    			if(last<nums.length){
    				sum = sum+nums[last];
    			}
    		}
    	}
    	
    	if(len<Integer.MAX_VALUE){
    		return len;
    	}else{
    		return 0;
    	}
    }
}
