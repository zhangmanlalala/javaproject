package LeetCode_34_Pascals_Triangle;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * Given rowIndex, generate the first rowIndex of Pascal's triangle.

	For example, given rowIndex = 5,
	Return
	
	[
	     [1],
	    [1,1],
	   [1,2,1],
	  [1,3,3,1],
	 [1,4,6,4,1]
	]
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
	public List<Integer> generate(int rowIndex) {
		
		List<Integer> pre = new ArrayList<Integer>();
        if(rowIndex < 0){
			return pre;
		}
        pre.add(1);
        if(rowIndex == 0){
        	return pre;
        }
        
        List<Integer> tmp = new ArrayList<Integer>();
        tmp.add(1);
        tmp.add(1);

		for(int i=3;i<=rowIndex+1;i++){
			List<Integer> latest = new ArrayList<Integer>();
			latest.add(1);
			for(int j=1;j<tmp.size();j++){
				latest.add(tmp.get(j-1)+tmp.get(j));
			}
			latest.add(1);
			tmp = latest;
			if(i==rowIndex){
				return latest;
			}	
		}
		return tmp;

    }
}
