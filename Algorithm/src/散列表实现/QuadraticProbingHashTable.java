package 散列表实现;


public class QuadraticProbingHashTable<AnyType> {
	public QuadraticProbingHashTable(){
		this(DEFAULT_TABLE_SIZE );
	}
	public QuadraticProbingHashTable(int size){
		allocateArray(size);
		makeEmpty();
	}
	public void makeEmpty(){
		currentSize=0;
		for(int i=0;i<array.length;i++){
			array[i] =null;
		}
	}
	
	public boolean contains(AnyType x){
		int currentPos = findPos(x);
		return isActive(currentPos);
	}
	public void insert(AnyType x){
		//insert x as active;
		int currentPos = findPos(x);
		if(isActive(currentPos))
			return;
		array[currentPos] = new HashEntry<AnyType>(x,true);
		
		if(++currentSize>array.length /2)
			rehash();
	}
	
	/**
	 * 注意探测散列表不能执行一般的删除操作，因为相应的单元可能已经引起冲突，要删除的元素可能
	 * 被存在了别处，这里采用惰性删除：使用一个删除标志，当为false时，表示被删除。
	 * @param x
	 */
	public void remove(AnyType x){
		int currentPos = findPos(x);
		if(isActive(currentPos)){
			array[currentPos].isActive=false;
		}
	}
	
	//静态内部类
	/**
	 * 散列表中数组的类型就是HashEntry类型，定义一个内部类便于封装使用
	 * 这个跟Map内部嵌套的Entry<keyType,ValueType>接口有点类似，map中的每个元素都
	 * 能用Entry<keyType,ValueType>表示
	 * 
	 * 
	 * @author Administrator
	 *
	 * @param <AnyType>
	 */
	private static class HashEntry<AnyType>{
		public AnyType element;
		public boolean isActive;
		
		public HashEntry(AnyType e){
			this(e,true);
		}
		public HashEntry(AnyType e,boolean i){
			element =e;
			isActive = i;
		}
	}
	
	private static final int DEFAULT_TABLE_SIZE =11;
	
	private HashEntry<AnyType>[] array;
	private int currentSize;
	
	private void allocateArray(int arraySize){
		array = new HashEntry[arraySize];
	}
	
	/**
	 *isActive()用来判定currentPos处的单元是否被激活
	 *如果isActive(currentPos)返回true，则说明currentPos处的单元有值被激活
	 *如果isActive(currentPos)返回false，则说明currentPos处的单元为null或者值被删除
	 * 
	 * @param currentPos
	 * @return
	 */
	private boolean isActive(int currentPos){
		return array[currentPos] !=null && array[currentPos].isActive;
	}
	/**
	 * 
	 * 该函数找到元素x的按平方探测法找到的解决冲突的位子，
	 * 解决方案步骤： (1) 根据x的hashcode找到初始的currentPos
	 * 			    (2) 当currenttPos处的元素不为空并且与x不相等，则currentPos一直自增，
	 * 					自增的方式为递推公式：f(i) =f(i-1)+2*i-1,可以证明：f(i)=i*i;
	 * 				(3) 最后返回currentPos，该处的元素要么为空，要么和x相等。
	 * @param x
	 * @return
	 */
	private int findPos(AnyType x){
		int offset = 1;
		int currentPos = myhash(x);
		while(array[currentPos]!=null && !array[currentPos].element.equals(x)){
			currentPos +=offset;   //Compute ith probe
			offset +=2;
			if(currentPos>=array.length)
				currentPos -=array.length;
		}
		
		return currentPos;
	}
	private void rehash(){
		HashEntry<AnyType>[] oldArray = array;
		allocateArray(nextPrime(2*oldArray.length));
		currentSize=0;
		
		for(HashEntry<AnyType> temp:oldArray){
			if(temp!=null && temp.isActive){
				insert(temp.element);
			}
		}
	}
	
	private int myhash(AnyType x){
		int hashValue = x.hashCode();
		hashValue %= array.length;
		if(hashValue<0){
			hashValue +=array.length; 
		}
		return hashValue;
	}
	
	private static int nextPrime(int n){
		if(n%2==0){
			n++;
		}
		for(;!isPrime(n);n+=2){
			;
		}
		return n;
	}
	private static boolean isPrime(int n){
		if(n == 2 || n ==3){  
            return true;  
        }  
        if(n == 1 || n%2 == 0){  
            return false;  
        }  
        return true;  
	}
}
