package _204_Count_Primes;
/**
 * 
 *Count the number of prime numbers less than a non-negative number, n. 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 使用埃式筛选法构建一张素数表
	 * 思想：
	 * 从小于N的正整数中选择一个最小的素数(2),所有是它的倍数的数都不是素数
	 * 然后填满这张素数表
	 * @param n
	 * @return
	 */
	public int countPrimes(int n) {
		boolean[] notPrime = new boolean[n];
		int count = 0;
		for(int i=2;i<n;i++){
			if(notPrime[i] == false){
				count++;
				for(int j=i+i;j<n;j=j+i){
					notPrime[j] = true;
				}
			}
		}
		
		return count;
	}
	
	
	/**
	 * 
	 * 判断某个正整数N是不是素数的方法
	 */
	
	
	public boolean isPrime(int N){
		if(N<2){
			return false;
		}
		
		for(int i=2;i*i<=N;i++){
			if(N%i == 0){
				return false;
			}
		}
		
		return true;
	}
}
