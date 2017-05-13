package LeetCode_33_Pascals_Triangle_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *Given a triangle, find the minimum path sum from top to bottom. 
 *Each step you may move to adjacent numbers on the row below.
For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).  
 * 
 * 
 * @author Administrator
 *
 */
/**
 * 
 * 假设要求第n行的，前面n-1行有一个计算结果，保存在ArrayList中，接下来就能得到第n行的所有计算结果
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        
    	if(triangle.size()==0 || triangle==null){
    		return 0;
    	}
    	List<Integer> lst = new ArrayList<Integer>();	
    	lst.add(triangle.get(0).get(0));
    	
    	for(int i=1;i<triangle.size();i++){
    		List<Integer> latest = new ArrayList<Integer>();
    		List<Integer> tmp = triangle.get(i);
    		
    		latest.add(lst.get(0)+tmp.get(0));
    		for(int j=1;j<tmp.size()-1;j++){
    			if(lst.get(j-1)<lst.get(j)){
    				latest.add(lst.get(j-1)+tmp.get(j));
    			}else{
    				latest.add(lst.get(j)+tmp.get(j));
    			}
    		}
    		latest.add(lst.get(lst.size()-1)+tmp.get(tmp.size()-1));
    		lst = latest;
    		
    	}
    	Object[] a = lst.toArray();
    	Arrays.sort(a);
    	return (Integer)a[0];
    }
}
    
  
