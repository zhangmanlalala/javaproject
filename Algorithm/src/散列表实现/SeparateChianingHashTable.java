package ɢ�б�ʵ��;

import java.util.LinkedList;
import java.util.List;
/**
 * ������������ֻ��ʵ����comparable�ӿڵĶ�����й���һ��
 * ��ɢ�б��еĶ�������ṩhashcode()������equals����
 * @author Administrator
 *
 * @param <AnyType>
 */
public class SeparateChianingHashTable<AnyType> {
	//����
	private List<AnyType>[] thelists;
	private static final int DEFAULT_TABLE_SIZE =101;
	private int currentSize;
	/**
	 * ���췽����ʹ��Ĭ�ϵı���ʼ��������hashtable
	 */
	public SeparateChianingHashTable(){
		this(DEFAULT_TABLE_SIZE);
	}
	/**
	 * 
	 * �ص㷽�����μ�
	 * ���췽��,��ʼ��������hashtable
	 * @param size
	 */
	public SeparateChianingHashTable(int size){
		thelists = new LinkedList[nextPrime(size)];//ע����һ��ֻ������������һ�����飬��û�г�ʼ�����޷�ʹ��
		//����û�к���һ������ɿ�ָ�쳣
		//����ע���ָ���ջ�Լ����еĽǶ�ȥ���������δ���
		for(int i=0;i<thelists.length;i++){
			thelists[i] = new LinkedList<AnyType>();//thelists[i]�д�ŵ�����һ��LinkedList�ĵ�ַ
		}
	}
	/**
	 * �������
	 * @param x
	 */
	
	public void insert(AnyType x){
		List<AnyType> whichList = thelists[myhash(x)];
		if(!whichList.contains(x)){
			whichList.add(x);
			//currentSize()Ҫ����1��
			//rehash()������
			if(++currentSize>thelists.length){
				rehash();
			}
		}
	}
	/**
	 * ɾ������
	 * @param x
	 */
	public void remove(AnyType x){
		List<AnyType> whichList = thelists[myhash(x)];
		if(whichList.contains(x)){
			whichList.remove(x);
			currentSize++;
		}
	}
	/**
	 * �����Ƿ������Ԫ��
	 * @param x
	 * @return
	 */
	public boolean contains(AnyType x){
		List<AnyType> whichList = thelists[myhash(x)];
		return whichList.contains(x);
	}
	//��table�е��з�����ÿ��LinkedList��Ϊ��
	public void makeEmpty(){
		for(int i=0;i<thelists.length;i++){
			thelists[i].clear();
		}
		this.currentSize=0;
	}
	
	
	
	private void rehash(){
		//thelists���൱��ָ��
		List<AnyType> changelists[] = thelists;
		//����һ���µ�hashtable������
		thelists=new LinkedList[nextPrime(2*DEFAULT_TABLE_SIZE+1)];
		for(int i=0;i<thelists.length;i++){
			thelists[i] = new LinkedList<AnyType>();//thelists[i]�д�ŵ�����һ��LinkedList�ĵ�ַ
		}
		currentSize=0;
		//����ע���ָ���ջ�Լ����еĽǶ�ȥ���������δ���
		for(int i=0;i<changelists.length;i++){
			
			
				for(AnyType any:changelists[i]){//����ʹ����ǿforѭ������linkedlist
			
				this.insert(any);//thelists[i]�д�ŵ�����һ��LinkedList�ĵ�ַ
				}
			
		}
		changelists=null;
		
	}
	/**
	 * myhsah������hash��ת����Ϊ�����±�
	 * һ������hashcode()��������ö����hash�룬���Ǹ���Horner�������һ��37�Ķ���ʽ
	 * @param x
	 * @return
	 */
	private int myhash(AnyType x){
		int hashValue =x.hashCode();
		hashValue = hashValue%thelists.length;
		if(hashValue<0){
			hashValue +=thelists.length; 
		}
		return hashValue;
	}
	//���ڷ���n����һ������
	private static int nextPrime(int n){
		if(n%2==0){
			n++;
		}
		for(;!isPrime(n);n+=2){
			;
		}
		return n;
			
	}
	//�����ж�n�ǲ���Ϊ����
	private static boolean isPrime(int n){
		if(n == 2 || n ==3){  
            return true;  
        }  
        if(n == 1 || n%2 == 0){  
            return false;  
        }  
        return true;  
	}
	
	
	
	
	/**
	 * ���������Ϊ����ʹ��
	 */
	public static void main(String[] args) {
		SeparateChianingHashTable<String> myhashTable = new SeparateChianingHashTable<String>();
		myhashTable.insert("zengjichen");
		myhashTable.insert("zhangman");
		myhashTable.insert("gaoqi");
		System.out.println(myhashTable.currentSize);
		System.out.println(myhashTable.contains("gaoqi"));
	}
}


/**
 * ����ٳ�һ�����Է���ɢ���еĶ����һ������
 */
class Employee{
	private String name;
	private double salary;
	private int seniority;
	public boolean equals(Object rhs){
		return rhs instanceof Employee  && this.name.equals(((Employee)rhs).name);
	}
	public int hashcode(){
		return name.hashCode();
	}
}


