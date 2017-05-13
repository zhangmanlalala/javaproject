package leetcode_010;
/**
 * 
 *Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") �� false
isMatch("aa","aa") �� true
isMatch("aaa","aa") �� false
isMatch("aa", "a*") �� true
isMatch("aa", ".*") �� true
isMatch("ab", ".*") �� true
isMatch("aab", "c*a*b") �� true
 
 * @author Administrator
 *
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        if(p.length()==0)  
            return s.length()==0;  
        //p�ĵڶ�λ����*  
        if(p.length()==1 || p.charAt(1) != '*'){  
            if(s.length()<1 || (p.charAt(0)!='.' && s.charAt(0)!=p.charAt(0)))  
                return false;  
            //��ô��Ҫ��һλҪ���(��Ȱ���.ƥ����������)����ƥ��ʣ��  
            return isMatch(s.substring(1), p.substring(1));  
        }else{//p�ĵڶ�λ��*�������ƥ��ǰi��  
            int len=s.length();  
            int i=-1;  
            //i<0��ƥ��0����.����ƥ������  
            while(i<len && (i<0 || p.charAt(0)=='.' || p.charAt(0)==s.charAt(i))){  
                if(isMatch(s.substring(i+1), p.substring(2)))  
                    return true;  
                i++;  
            }  
            return false;  
        }  
    }
}
