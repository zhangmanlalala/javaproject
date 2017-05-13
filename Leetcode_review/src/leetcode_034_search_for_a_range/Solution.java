package leetcode_034_search_for_a_range;

public class Solution {

	
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1,-1};
        if(nums == null || nums.length==0){
        	return result;
        }
    	int i=0,j=nums.length-1;
    	while(i<=j){
    		int mid = (i+j)/2;
    		if(nums[mid] == target){
    			result[0] = mid;
    			while(result[0]-1>=0){
    				if(nums[result[0]-1] == target){
    					result[0] = result[0]-1;
    				}else{
    					break;
    				}
    			}
    			
    			result[1] = mid;
    			while(result[1]+1<nums.length){
    				if(nums[result[1]+1] == target){
    					result[1] = result[1]+1;
    				}else{
    					break;
    				}
    			}
    		}else if(nums[mid]>target){
    			j = mid-1;
    		}else{
    			i = mid+1;
    		}
    	}
    	
    	return result;
    }
    public int[] searchRange2(int[] nums, int target) {
    	int[] result = new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE};
        if(nums == null || nums.length==0){
        	return new int[]{-1,-1};
        }
        binarySearch(nums,0,nums.length-1,target,result);
        
        if(result[0]!=Integer.MAX_VALUE){
        	return result;
        }else{
        	return new int[]{-1,-1};
        }
    }
    //真正的二份查找法
    private void binarySearch(int[] nums,int left,int right,int target,int result[]){
    	if(left>right){
    		return ;
    	}
    	
    	int mid = (left+right)/2;
    	
    	if(nums[mid] == target){
    		if(result[0]>mid){
    			result[0] = mid;
    		}
    		
    		if(result[1]<mid){
    			result[1] = mid;
    		}
    		
    		binarySearch(nums,left,mid-1,target,result);
    		
    		binarySearch(nums,mid+1,right,target,result);
    		
    	}else if(nums[mid]>target){
    		binarySearch(nums,left,mid-1,target,result);
    		
    	}else{
    		binarySearch(nums,mid+1,right,target,result);
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
