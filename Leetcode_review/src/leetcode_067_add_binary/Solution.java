package leetcode_067_add_binary;

public class Solution {
    public String addBinary(String a, String b) {
    	if(a == null){
    		return b;
    	}else if(b==null){
    		return a;
    	}
    	
    	int i = a.length()-1,j = b.length()-1;
    	
    	int carry = 0;
    	StringBuilder sb = new StringBuilder();
    	while(i>=0 && j>=0){
    	    
    		if(a.charAt(i)=='1' && b.charAt(j) == '1'){
    			if(carry == 1){
    				sb.append("1");
    				carry = 1;
    			}else{
    				sb.append("0");
    				carry = 1;
    			}
    		}else if(a.charAt(i) == '0' && b.charAt(j)=='0'){
    			if(carry == 1){
    				sb.append("1");
    				carry = 0;
    			}else{
    				sb.append("0");
    				carry = 0;
    			}
    		}else{
    			if(carry == 1){
    				sb.append("0");
    				carry = 1;
    			}else{
    				sb.append("1");
    				carry = 0;
    			}
    			
    		}
    		
    		i--;
    		j--;
    	}
   	
    	
    	if(i>=0){
    		while(i>=0){
    			if(a.charAt(i) == '1'){
    				if(carry == 1){
    					sb.append("0");
    					carry = 1;
    				}else{
    					sb.append("1");
    					carry = 0;
    				}
    			}else{
    				if(carry == 1){
    					sb.append("1");
    					carry = 0;
    				}else{
    					sb.append("0");
    					carry = 0;
    				}
    			}
    			
    			i--;
    		}
    		if(carry == 1){
    			sb.append("1");
    		}
    	}else{
    		while(j>=0){
    			if(b.charAt(j)=='1'){
    				if(carry == 1){
    					sb.append("0");
    					carry = 1;
    				}else{
    					sb.append("1");
    					carry = 0;
    				}
    			}else{
    				if(carry == 1){
    					sb.append("1");
    					carry = 0;
    				}else{
    					sb.append("0");
    					carry = 0;
    				}
    			}
    			
    			j--;
    		}
    		if(carry == 1){
    			sb.append("1");
    		}
    	}
    	
    	
    	return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().addBinary("1", "111"));
	}
}
