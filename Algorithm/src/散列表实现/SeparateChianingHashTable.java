package 散列表实现;

import java.util.LinkedList;
import java.util.List;
/**
 * 就像二叉查找树只对实现了comparable接口的对象进行工作一样
 * 该散列表中的对象必须提供hashcode()方法和equals方法
 * @author Administrator
 *
 * @param <AnyType>
 */
public class SeparateChianingHashTable<AnyType> {
	//属性
	private List<AnyType>[] thelists;
	private static final int DEFAULT_TABLE_SIZE =101;
	private int currentSize;
	/**
	 * 构造方法，使用默认的表长初始化这样的hashtable
	 */
	public SeparateChianingHashTable(){
		this(DEFAULT_TABLE_SIZE);
	}
	/**
	 * 
	 * 重点方法，牢记
	 * 构造方法,初始化这样的hashtable
	 * @param size
	 */
	public SeparateChianingHashTable(int size){
		thelists = new LinkedList[nextPrime(size)];//注意这一步只是声明了这样一个数组，还没有初始化，无法使用
		//若果没有后面一步会造成空指异常
		//这里注意从指针和栈以及队列的角度去理解下面这段代码
		for(int i=0;i<thelists.length;i++){
			thelists[i] = new LinkedList<AnyType>();//thelists[i]中存放的是新一个LinkedList的地址
		}
	}
	/**
	 * 插入操作
	 * @param x
	 */
	
	public void insert(AnyType x){
		List<AnyType> whichList = thelists[myhash(x)];
		if(!whichList.contains(x)){
			whichList.add(x);
			//currentSize()要自增1；
			//rehash()见后面
			if(++currentSize>thelists.length){
				rehash();
			}
		}
	}
	/**
	 * 删除操作
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
	 * 查找是否包含改元素
	 * @param x
	 * @return
	 */
	public boolean contains(AnyType x){
		List<AnyType> whichList = thelists[myhash(x)];
		return whichList.contains(x);
	}
	//把table中的列方向上每个LinkedList置为空
	public void makeEmpty(){
		for(int i=0;i<thelists.length;i++){
			thelists[i].clear();
		}
		this.currentSize=0;
	}
	
	
	
	private void rehash(){
		//thelists就相当于指针
		List<AnyType> changelists[] = thelists;
		//建立一个新的hashtable分两步
		thelists=new LinkedList[nextPrime(2*DEFAULT_TABLE_SIZE+1)];
		for(int i=0;i<thelists.length;i++){
			thelists[i] = new LinkedList<AnyType>();//thelists[i]中存放的是新一个LinkedList的地址
		}
		currentSize=0;
		//这里注意从指针和栈以及队列的角度去理解下面这段代码
		for(int i=0;i<changelists.length;i++){
			
			
				for(AnyType any:changelists[i]){//这里使用增强for循环遍历linkedlist
			
				this.insert(any);//thelists[i]中存放的是新一个LinkedList的地址
				}
			
		}
		changelists=null;
		
	}
	/**
	 * myhsah方法将hash码转换成为数组下标
	 * 一般对象的hashcode()方法计算该对象的hash码，都是根据Horner法则计算一个37的多项式
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
	//用于返回n的下一个素数
	private static int nextPrime(int n){
		if(n%2==0){
			n++;
		}
		for(;!isPrime(n);n+=2){
			;
		}
		return n;
			
	}
	//用于判断n是不是为素数
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
	 * 下面代码作为测试使用
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
 * 下面举出一个可以放在散列中的对象的一个例子
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


