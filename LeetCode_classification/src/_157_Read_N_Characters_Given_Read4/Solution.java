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
 * ������
 * ��Ҳ��һ�������⣬��Ŀ����˼�Ǹ���һ������read4(char[] buf) �ĺ�����ÿ�ο�������4���ֽڵ��ַ���Ȼ��Ҫ��ͨ�������ĺ�����ʵ��һ��������
 * �ܰ���Ҫ�����n���ַ���

�����Ŀ��http://blog.csdn.net/foolnote/article/details/50626162�ļ򻯰棬��Ϊ��Ŀ˵Ŀ�꺯��ֻ�ᱻ����һ�Σ���ʹ�����
��Ŀ�Ѷȡ��������ǵ�˼·�ͺܼ򵥣��ں�������ѭ������read4()������ֱ������������Ҫ���ַ���Ϊֹ������Ҫע��һ�£��п��������Ƕ���Ҫ����ַ���֮ǰ��
read4()�������Ѿ�ɨ���ļ�β��λ���ˡ�
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
			
			if(r!=4){//���r������4����ô˵���Ѿ������ļ�ĩβ�ˣ�������
				break;
			}
		}
		
		return read;
	}
}
