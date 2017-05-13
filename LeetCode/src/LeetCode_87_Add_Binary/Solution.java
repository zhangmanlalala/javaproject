package LeetCode_87_Add_Binary;

public class Solution {
	public String addBinary(String a, String b) {
        int p = 0;
        int i=a.length()-1,j=b.length()-1;
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        while(i>=0 && j>=0){
        	
        	sum = a.charAt(i)-'0' + b.charAt(j)-'0'+p;
        	if(sum == 1){
        		sb.append('1');
        		 p = 0;
        	}else if(sum == 2){
        		sb.append('0');
        		p = 1;
        	}else if(sum == 3){
        		sb.append('1');
        		p = 1;
        	}
        	i--;
        	j--;
        	sum = 0;
        	
 //       	if(a.charAt(i) == '1'){
//        		if(b.charAt(j) == '1'){
//        			if(p == '0'){
//        				sb.append('0'); 
//        				p = '1';
//        			}else{
//        				sb.append('1');
//        			}
//        			
//        		}else{
//        			if(p == '0'){
//        				sb.append('1'); 
//        			}else{
//        				sb.append('0'); 
//        				p = '1';
//        			}
 //      		}
//        	}
        }
        while(i>=0){
        	sum = a.charAt(i)-'0' +p;
        	if(sum == 1){
        		sb.append('1');
        		p  = 0;
        	}else if(sum == 2){
        		sb.append('0');
        		p = 1;
        	}else if(sum == 3){
        		sb.append('1');
        		p = 1;
        	}
        	i--;
        	sum = 0;
        }
        
        while(j>=0){
        	sum = b.charAt(j)-'0'+p;
        	if(sum == 1){
        		sb.append('1');
        		 p =0;
        	}else if(sum == 2){
        		sb.append('0');
        		p = 1;
        	}else if(sum == 3){
        		sb.append('1');
        		p = 1;
        	}
        	j--;
        	sum = 0;
        }
        
        if(p == 1){
        	sb.append('1');
        }
        
        return sb.reverse().toString();
    }
	
	public static void main(String[] args) {
		System.out.println(new Solution().addBinary("101", "1"));
	}
}
