package LeetCode_123_Substring_with_Concatenation_of_All_Words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	
	public List<Integer> subStringCon(String s,List<String> list){
		if(s==null || s=="" || list.isEmpty()){
			return null;
		}
		Map<String,Integer> map_change=new HashMap<String,Integer>();
		Map<String,Integer> map=new HashMap<String,Integer>();
		List<Integer> store = new ArrayList<Integer>();
		int i = 1;
		for(String str:list){
			map_change.put(str, i);
			i++;
		}
		String temp=null;
		int len = list.get(0).length();
		int j=0;
		for(int pre = 0;pre<=s.length()-len; ){
			temp = s.substring(pre, pre+len);
			if(map_change.containsKey(temp) && !map.containsKey(temp)){
				pre = pre+len;
				map.put(temp, j);
				j++;
				if(j==list.size()){
					j = 0;
					store.add(pre-len*list.size());				
				}
			}else{
				pre = pre+1;
				map.clear();
			}
		}
		return store;
		
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("foo");
		list.add("bar");
		String s = "barfoothefoobarmanfoobarmanbarfoo";
		List<Integer> st = new Solution().subStringCon(s, list);
		for(Integer t:st){
			System.out.println(t);
		}
	}
}
