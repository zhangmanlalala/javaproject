package ɢ�б�ʵ��;


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
	 * ע��̽��ɢ�б���ִ��һ���ɾ����������Ϊ��Ӧ�ĵ�Ԫ�����Ѿ������ͻ��Ҫɾ����Ԫ�ؿ���
	 * �������˱𴦣�������ö���ɾ����ʹ��һ��ɾ����־����Ϊfalseʱ����ʾ��ɾ����
	 * @param x
	 */
	public void remove(AnyType x){
		int currentPos = findPos(x);
		if(isActive(currentPos)){
			array[currentPos].isActive=false;
		}
	}
	
	//��̬�ڲ���
	/**
	 * ɢ�б�����������;���HashEntry���ͣ�����һ���ڲ�����ڷ�װʹ��
	 * �����Map�ڲ�Ƕ�׵�Entry<keyType,ValueType>�ӿ��е����ƣ�map�е�ÿ��Ԫ�ض�
	 * ����Entry<keyType,ValueType>��ʾ
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
	 *isActive()�����ж�currentPos���ĵ�Ԫ�Ƿ񱻼���
	 *���isActive(currentPos)����true����˵��currentPos���ĵ�Ԫ��ֵ������
	 *���isActive(currentPos)����false����˵��currentPos���ĵ�ԪΪnull����ֵ��ɾ��
	 * 
	 * @param currentPos
	 * @return
	 */
	private boolean isActive(int currentPos){
		return array[currentPos] !=null && array[currentPos].isActive;
	}
	/**
	 * 
	 * �ú����ҵ�Ԫ��x�İ�ƽ��̽�ⷨ�ҵ��Ľ����ͻ��λ�ӣ�
	 * ����������裺 (1) ����x��hashcode�ҵ���ʼ��currentPos
	 * 			    (2) ��currenttPos����Ԫ�ز�Ϊ�ղ�����x����ȣ���currentPosһֱ������
	 * 					�����ķ�ʽΪ���ƹ�ʽ��f(i) =f(i-1)+2*i-1,����֤����f(i)=i*i;
	 * 				(3) ��󷵻�currentPos���ô���Ԫ��ҪôΪ�գ�Ҫô��x��ȡ�
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
