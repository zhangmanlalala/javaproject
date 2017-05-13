package leetcode_060_permutation_sequence;

/**
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
	public  String result=null;
	public  int num = 0;
	
    public String getPermutation(int n, int k) {
    	
    
    	StringBuilder sb = new StringBuilder();
    	boolean isUsed[] = new boolean[n+1];
    	
    	dfs(n,k,sb,isUsed);
    	
    	return result;
    	
    }
    
    
    
    public void dfs(int n,int k,StringBuilder sb, boolean isUsed[]){
    	
    	if(result!=null){
    		return ;
    	}
    	
    	if(sb.length() == n){
    		num++;
    		if(num == k){
    			result = sb.toString();
    		} 		
    		return ;
    		
    	}
    	
    	
    	for(int i=1;i<=n;i++){
    		
    		
    		if(isUsed[i] == false){
    			sb.append(i);
    			isUsed[i] = true;
    			
    			dfs(n,k,sb,isUsed);
    			
    			
    			sb.deleteCharAt(sb.length()-1);
    			isUsed[i] = false;
    		}
    	}
    	
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().getPermutation(2, 1));
	}
}
