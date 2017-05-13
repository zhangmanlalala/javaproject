package leetcode_006;
/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on
 *  a given number of rows like this: (you may want to display this pattern 
 *  in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);

convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".  
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 答案一直不对,那是因为自己理解错了
	 * @param s
	 * @param numRows
	 * @return
	 */
    public String convert(String s, int numRows) {
    	if(s.length()<=2 || s.length()<=numRows){
    	    return s;
    	}
    	StringBuilder sb = new StringBuilder();
    	int longRows = numRows/2+1;
    	if(numRows%2 == 0){//偶数的情况
    		
    		for(int i=1;i<=numRows;i++){  
    			
    	  		for(int j=i;j<=s.length();j=j+numRows){
	    			sb.append(s.charAt(j-1));
	    		}
        		
    		}
    		
//    		for(int j=numRows+1;j<=s.length();j=j*2){
//				sb.append(s.charAt(j-1));
//			} 
//
//    		for(int i=numRows/2+1;i<=numRows;i++){
//        		
//    	  		for(int j=i;j<=s.length();j=j+numRows+1){
//	    			sb.append(s.charAt(j-1));
//	    		}
//        		
//    		}
    		
    	}else{
	    	for(int i=1;i<=numRows;i++){
	    		
	    		if(i == longRows){ 			
	    			for(int j=i;j<=s.length();j=j+longRows){
		    			sb.append(s.charAt(j-1));
		    		}	    			
	    		}else{
		    		for(int j=i;j<=s.length();j=j+numRows+1){
		    			sb.append(s.charAt(j-1));
		    		}
	    		}
	    	}
    	}

    	
    	return sb.toString();
    }
    
    
    /**
     * 
     * 看标准答案
     * @param args
     */
    public String convert2(String s, int numRows) {
    	char[] c = s.toCharArray();
    	int len = c.length;
    	StringBuilder[] sb = new StringBuilder[numRows];
    	for(int i=0;i<sb.length;i++){
    		sb[i] = new StringBuilder();
    	}
    	
    	int i=0;
    	while(i<len){
    		for(int idx = 0;idx<numRows && i<len;idx++){
    			sb[idx].append(c[i++]);
    		}
    		
    		for(int idx = numRows-2;idx>=1 && i<len;idx--){
    			sb[idx].append(c[i++]);
    		}
    	}
    	for(int idx=1;idx<sb.length;idx++){
    		sb[0].append(sb[idx].toString());
    	}
    	return sb[0].toString();
    	
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().convert("ABCD", 2));
	}
}
