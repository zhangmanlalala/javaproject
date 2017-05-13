package _165_Compare_Version_Numbers;
/**
 *Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37 
 * @author Administrator
 *
 */
public class Solution {
    public int compareVersion(String version1, String version2) {
        if(version1 == null || version2 ==null) return 0;  
        
        String[] v1 = version1.split("\\.");  
        String[] v2 = version2.split("\\.");  
          
        int n1 = v1.length;  
        int n2 = v2.length;  
          
        int i=0;  
          
        while(i<n1 || i<n2) {  
            int x1 = i<n1 ? Integer.parseInt(v1[i]) : 0;  
            int x2 = i<n2 ? Integer.parseInt(v2[i]) : 0;  
            if(x1 > x2) return 1;  
            else if(x1 < x2) return -1;  
            else ++i;  
        }  
          
        return 0;  
    }
}
