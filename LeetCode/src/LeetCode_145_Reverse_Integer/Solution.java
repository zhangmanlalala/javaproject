package LeetCode_145_Reverse_Integer;

public class Solution {
       /**
        * 我自己的思路，有瑕疵
        * @param num
        * @return
        */
		public static int solute(int num){
			String str = String.valueOf(num);
			int num_inverse =0;
			if(num>=0){
				int b = 0;
				for(int i=str.length()-1;i>=0;i--){
				    b = (int)(str.charAt(i))-48;
					if(b==0 && num_inverse==0){
						continue;
					}
					num_inverse = (int) (num_inverse+b*Math.pow(10,i));
				}
			}else{
				int b = 0;
				for(int i=str.length()-1;i>=1;i--){
				    b = (int)(str.charAt(i))-48;
					if(b==0 && num_inverse==0){
						continue;
					}
					num_inverse = (int) (num_inverse+b*Math.pow(10,i-1));
				}
				
				num_inverse = -num_inverse;
			}
			return num_inverse;
		}
		/**
		 * 
		 * 正确的思路
		 * @param num
		 * @return
		 */
		
		public static int solute2(int num){
			boolean isPositive ;
			if(num>=0){
				isPositive = true;
			}else{
				isPositive = false;
				num = Math.abs(num);
			}
			int res=0;
			int inverse_num=0;
			while(num!=0){
				res = num%10;//得到余数
				num = num/10;//得到商     很经典的方法
				inverse_num = inverse_num*10+res;	
			}
			return isPositive? inverse_num:(-inverse_num);
			
		}
		
		
		
		/**
		 * 
		 * 判断一个整数是几位数的常用方法1
		 * @param args
		 */
		
		public static int judgeNum1(Integer a){
			
			int b = a.toString().length();
			int c = String.valueOf(a).length();
			System.out.println(b);
			System.out.println(c);
			return b;
		}
		/**
		 * 
		 * 判断一个整数是几位数的常用方法2
		 * @param args
		 */
		public static int judgeNum2(Integer a){
			int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,    
		            99999999, 999999999, Integer.MAX_VALUE };    
		     
		    for(int i = 0;; i++){ 
	            if (a <= sizeTable[i])    
	                return i + 1; 
	          
		    }    
		}
		public static void main(String[] args) {
			/*Integer a  = -19920508;
			int b = a.toString().length();
			int c = String.valueOf(a).length();//这是判断一位整数是几位数的中方法
			String str = String.valueOf(a);
			System.out.println(b);
			System.out.println(str);
			System.out.println(Integer.parseInt("0031"));*/
			System.out.println(solute(2365400));
			System.out.println(solute2(2365400));
			System.out.println(Integer.valueOf("12"));
			System.out.println(judgeNum2(2653));
		}
}
