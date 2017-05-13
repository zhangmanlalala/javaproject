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
	 * ʹ�ð�ʽɸѡ������һ��������
	 * ˼�룺
	 * ��С��N����������ѡ��һ����С������(2),���������ı�����������������
	 * Ȼ����������������
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
	 * �ж�ĳ��������N�ǲ��������ķ���
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
