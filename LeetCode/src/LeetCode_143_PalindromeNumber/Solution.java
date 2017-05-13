package LeetCode_143_PalindromeNumber;

public class Solution {
	
	public static boolean isPalindromeNumber(int num){
		if(num<0)
		return false;
		int original = num;
		int newNum = 0;
		int h=0;
		while(num!=0){
			h = num%10;
			num = num/10;
			newNum = newNum*10+h;
		}
		if(newNum==original){
			return true;
		}else {
			return false;
		}
		
	}
	public static void main(String[] args) {

	}

}
