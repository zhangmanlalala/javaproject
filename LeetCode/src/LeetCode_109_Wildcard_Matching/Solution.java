package LeetCode_109_Wildcard_Matching;


/**
 * '?' Matches any single character.  
'*' Matches any sequence of characters (including the empty sequence).  
The matching should cover the entire input string (not partial).  
  
The function prototype should be:  
bool isMatch(const char *s, const char *p)  
  
Some examples:  
isMatch("aa","a") → false  
isMatch("aa","aa") → true  
isMatch("aaa","aa") → false  
isMatch("aa", "*") → true  
isMatch("aa", "a*") → true  
isMatch("ab", "?*") → true  
isMatch("aab", "c*a*b") → false 
 * 
 * @author Administrator
 *
 */


/**
 * 通配符匹配。
	？可以匹配一个字符；	
	* 可以匹配0个，1个或多个字符；	
	在s 和 p 上维持两个游标 sPos 和 pPos, 记录当前位置。
	
	当 sPos 和 pPos 字符相等，或者 pPos 字符为 ?, 认为是匹配的，两个游标都后移；
	
	当 pPos 字符为 *， 它可能匹配0个或多个字符，所以 pPos 后移（*可能匹配0个字符），同时记录*的位置star和s当前的位置mark，以备后面回溯回来重新匹配（*可能匹配过个字符）；
	
	如果以上都不满足，并且之前出现过*，那么我们要回溯回去，看看是不是*需要匹配多个字符。
	
	如果以上都不行，说明不能匹配。
	
	到最后，可能s走完了，p还没走完，这时，如果p剩余的都是*，说明可以匹配。
 * 
 * 
 * @author Administrator
 *
 */


//很经典的字符串匹配算法，设置两个游标，经典经典
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean isMatch(String s,String p){
		int sPos=0,pPos=0;
		int star=-1,mark=-1;
		while(sPos<s.length()){
			//如果相应位置相等，或者p是？，则两个下标全部后移
			if(pPos<p.length() && (s.charAt(sPos)==p.charAt(pPos) || p.charAt(pPos)=='?') ){
				pPos++;
				sPos++;
				continue;  //结束本次循环
			}
			
			//如果p是*，那么标记p上面*的位置，下标后移，同时标记s当前的位置，因为*可能与空匹配
			if(pPos<p.length() && (p.charAt(pPos)=='*')){
				star = pPos++;
				mark = sPos;            //事实上如果p中有两个相连的**号没意义
				continue;
			}
			
			//如果上面都不满足，则要p定位在*之后的位置，同时s后移，直到找到满足上面条件的
			if(star!=-1){
				pPos = star+1;
				sPos = ++mark;
				continue;
			}
			
			//如果上面都不行了，说明真的匹配不了
			return false;
		}
		//如果s遍历完了，p还有剩下的，就看剩下的是不是都是*
		while(pPos<p.length() && p.charAt(pPos)=='*'){ //既然走到这一步，说明s已经走完，不用在多此一举，判断一遍
			pPos++;
		}
		if(pPos==p.length()){
			return true;
		}else{
			return false;
		}
		
	}

}
