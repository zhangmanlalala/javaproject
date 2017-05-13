package LeetCode_115_Count_and_Say;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().countSay(7));

	}
	public int countSay(int n){
		Stack<Integer> s = new Stack<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		if(n == 1){
			return 1;
		}
		int former = 1;
		int temp = 1;
		int rem = 0;
		int count ;
		for(int k=1;k<n;k++){	
			while(temp!=0){
				rem = temp%10;
				s.push(rem);
				temp = temp/10;
			}
			former = s.pop();
			count = 1;
			if(s.isEmpty()){
				list.add(count);
				list.add(former);
			}else{
				while(!s.isEmpty()){		
					temp = s.pop();
					if(temp != former){
						list.add(count);
						list.add(former);						
						former = temp;
						count = 1;						
					}else if(temp == former){
						count = count+1;						
					}
					if(s.isEmpty()){
						list.add(count);
						list.add(former);
					}
					
				}
			}
			temp = 0;
			for(int i=0;i<list.size();i++){
				temp = temp*10+list.get(i);
			}
			list.clear();
			
		}
		return temp;
	}

}
