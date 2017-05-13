package test6;



import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Solution {
	   public static void main(String[] args) {

	        ArrayList<Integer> inputs = new ArrayList<Integer>();
	        Scanner in = new Scanner(System.in);
	        String line = in.nextLine();
	        if(line != null && !line.isEmpty()) {
	            int res = resolve(line.trim());
	            System.out.println(String.valueOf(res));
	        }
	    }

	    // write your code here
	    public static int resolve(String expr) {
	       Stack<Integer> ss = new Stack<Integer>();
	       String[] temp = expr.split(" ");
	       int i=0;
	       while(i<temp.length){
	    	   String c = temp[i];
	    	   if(c.equals("*")){
	    		   int a = 0,b = 0;
	    		   if(!ss.isEmpty()){
	    			   a = ss.pop();	   
	    		   }else{
	    			   return -1;
	    		   }
	  
	    		   if(!ss.isEmpty()){
	    			   b = ss.pop();
	    		   }else{
	    			   return -1;
	    		   }
	    		   
	    		   ss.push(a*b);
	    	   }else if(c.equals("^")){
	    		   int a = 0;
	    		   if(!ss.isEmpty()){
	    			   a = ss.pop();
	    		   }else{
	    			   return -1;
	    		   }
	    		   a++;
	    		   ss.push(a);
	    		   
	    	   }else if(c.equals("+")){
	    		   int a = 0,b = 0;
	    		   if(!ss.isEmpty()){
	    			   a = ss.pop();	   
	    		   }else{
	    			   return -1;
	    		   }
	    		   
	    		   if(!ss.isEmpty()){
	    			   b = ss.pop();
	    		   }else{
	    			   return -1;
	    		   }
	    		   
	    		   ss.push(a+b);
	    	   }else{
	    		   ss.add(Integer.parseInt(c));
	    		   if(ss.size()>16){
	    			   return -2;
	    		   }
	    	   }
	    	   
	    	   i++;
	       }
	       
	       if(!ss.isEmpty()){
	    	   return ss.peek();
	       }else{
	    	   return -1;
	       }
	       
	    }
}
