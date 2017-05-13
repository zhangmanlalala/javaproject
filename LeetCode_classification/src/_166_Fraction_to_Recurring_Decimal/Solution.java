package _166_Fraction_to_Recurring_Decimal;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

    Given numerator = 1, denominator = 2, return "0.5".
    Given numerator = 2, denominator = 1, return "2".
    Given numerator = 2, denominator = 3, return "0.(6)".

 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 关键是确定发生循环的位置，使用map存所有的余数，当余数已经出现过时，即发生了循环
	 * 
	 * 注意：Overflow，符号的处理，分开两个函数，使代码更容易读
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	
	//很经典
    public String fractionToDecimal(int numerator, int denominator) {
        long num = numerator;
        long den = denominator;
        
        boolean neg = num*den<0;
        num = Math.abs(num);
        den = Math.abs(den);
        
        String res = neg?"-"+Long.toString(num/den):Long.toString(num/den);
        long remainder = num%den;
        
        return (remainder==0)?res:(res+"."+getDec(remainder,den));
    }
    
    
    private StringBuilder getDec(long remainder,long den){
    	Map<Long,Integer> map = new HashMap<Long,Integer>();
    	int i=0;
    	StringBuilder sb = new StringBuilder();
    	while(remainder!=0 && !map.containsKey(remainder)){
    		map.put(remainder, i);//map中保存余数，当余数出现重复时，跳出循环
    		++i;
    		remainder *= 10;
    		sb.append(Long.toString(remainder/den));
    		remainder %=den;
    		
    	}
    	
    	
    	if(remainder!=0){
    		sb.insert((int)map.get(remainder), '(');
    		sb.append(')');
    		
    		
    	}
    	
    	return sb;
    }
}
