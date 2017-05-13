package _157_Read_N_Characters_Given_Read4;
/**
 * 
 *  Read N Characters Given Read4 Total Accepted: 960 Total Submissions: 3775

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that readsn characters from the file.

Note:
The read function will only be called once for each test case.
 * @author Administrator
 *
 */
/**
 * 
 * 分析：
 * 这也是一道带锁题，题目的意思是给定一个函数read4(char[] buf) 的函数，每次可以最多读4个字节的字符，然后要求通过给定的函数来实现一个函数，
 * 能按照要求读出n个字符。

这道题目是http://blog.csdn.net/foolnote/article/details/50626162的简化版，因为题目说目标函数只会被调用一次，这就大大简化了
题目难度。所以我们的思路就很简单，在函数体里循环调用read4()函数。直到读出我们想要的字符数为止。这里要注意一下，有可能在我们读足要想的字符数之前，
read4()函数就已经扫到文件尾部位置了。
 * @author Administrator
 *
 */
public class Solution {
	public int read4(char[] buf){
		return 4;
	}
	public int read(char[] buf,int n){
		if(n==0){
			return 0;
		}
		
		int read = 0;
		char[] buffer4 = new char[4];
		while(true){
			int r = read4(buffer4);
			for(int i=0;i<r && read<n;i++){
				buf[read++] = buffer4[i];
			}
			
			if(r!=4){//如果r不等于4，那么说明已经读到文件末尾了，跳出来
				break;
			}
		}
		
		return read;
	}
}
