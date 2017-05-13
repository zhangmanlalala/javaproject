package LeetCode_146_ZigZagConversion;

/**
 * 
 *The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for
 * better legibility) 
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
   Write the code that will take a string and make this conversion given
    a number of rows:
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * ע�������nrows����������������
 * @author Administrator
 *
 */
public class Solution {

	/**
	 * 
	 * ���Լ���ʵ��
	 * @param str
	 * @param nRows
	 * @return
	 */
	public static String convert(String str,int nRows){
		char car[] = new char[str.length()] ;
		if(str==null)
			return null;
		if(nRows==1){
			return str;
		}else if(nRows>1){
			int index = 0;
			for(int i=1;i<=nRows;i++){
				if(i != (nRows+1)/2 ){//�ж��ǲ����м���
					for(int j=i;j<=str.length();j=j+nRows+1){
						car[index] = str.charAt(j-1);
						index++;
					}
				}else{
					for(int j =i;j<=str.length();j=j+i){
						car[index] = str.charAt(j-1);
						index++;
					}
				}
			}
		}
		
		return new String(car,0,car.length);
	}
	
	
	/**
	 * 
	 * ����ʹ��
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "PAYPALISHIRING";
		System.out.println(convert(str,5));
	}
}
