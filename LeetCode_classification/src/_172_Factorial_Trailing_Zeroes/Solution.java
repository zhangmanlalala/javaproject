package _172_Factorial_Trailing_Zeroes;
/**
 *Given an integer n, return the number of trailing zeroes in n!. 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int trailingZeroes(int n) {
        int cnt = 0;
        while (n != 0)
        {
            n /= 5;//����5ȡ����
            cnt += n;
        }
        return cnt;
    }
}
