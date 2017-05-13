package 二叉查找树;

public class BinarySearchTree <AnyType extends Comparable<? super AnyType>>{
	
	/**
	 * 
	 * 需要深究
	 * @author Administrator
	 *
	 * @param <AnyType>
	 */
	//定义节点为内部类
	private static class BinaryNode<AnyType>{
		AnyType element;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;
		public BinaryNode(AnyType theElement) {
			this(theElement,null,null);
		}
		public BinaryNode(AnyType theElement,BinaryNode<AnyType> lt,BinaryNode<AnyType> rt){
			this.element =theElement;
			this.left = lt;
			this.right = rt;
		}
	}
	
	private BinaryNode<AnyType> root;
	
	public BinarySearchTree(){
		root = null;
	}
	
	public void makeEmpty(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root==null;
	}
	
	
	public boolean contains(AnyType x){
		return contains(x,root);
	}
	
	public AnyType findMax() throws Exception{
		if(isEmpty()){
			throw new Exception();
		}
		return findMax(root).element;
	}
	public AnyType findMin() throws Exception{
		if(isEmpty()){
			throw new Exception();
		}
		return findMin(root).element;
	}
	
	
	public void insert(AnyType x){
		root = insert(x,root);
		
	}
	public void remove(AnyType x){
		root = remove(x,root);
	}
	private boolean contains(AnyType x,BinaryNode<AnyType> t){
		if(t==null){
			return false;
		}
		int compareResult = x.compareTo(t.element);
		if(compareResult<0){
			return contains(x,t.left);
		}else if(compareResult>0){
			return contains(x,t.right);
			
		}else{
			return true;
		}
	}
	private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t){
		if(t==null){
			return null;
		}
		if(t.left!=null){
			return findMin(t.left);
		}else{
			return t;
		}
	}
	
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t){
		if(t==null){
			return null;
		}
		if(t.right!=null){
			return findMin(t.right);
		}else{
			return t;
		}
	}
	
	/**
	 * 二叉查找树插入操作的经典算法与contains()方法类似
	 * 
	 * 记住两点  1)插入操作是在叶子节点之后进行的
	 * 		 2)记住t.left = insert(x,t.left)这句代码的作用
	 * 
	 * @param x
	 * @param t
	 * @return
	 */
	
	private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){
		if(t ==null){
			return new BinaryNode(x,null,null);	
		}
		int result1 = x.compareTo(t.element);
		if(result1<0){
			t.left = insert(x,t.left);//这句话有两个作用:1)保证最后能返回根节点
												// 2)保证能把插入的节点连接起来
		}else if(result1>0){
			t.right = insert(x,t.right);//递归查找操作
		}else{
			//do nothing;
		}
		
		
		return t;  //很经典
	}
	
	
	/**
	 * 二叉树删除操作的经典算法
	 * 
	 * t.left = remove(x,t.left);  递归查找操作
	 * 两个作用，
	 * 
	 * 
	 */

	private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
		if(t==null){
			return t;
		}
		int result = x.compareTo(t.element);
		if(result<0){
			t.left = remove(x,t.left);//这句话有两个作用:1)保证最后能返回根节点
												// 2)保证能把插入的节点连接起来
		}else if(result>0){
			t.right = remove(x,t.right);//递归查找操作
		}else{
			 if(t.left!=null && t.right!=null) {
				 t.element=findMin(t.right).element;  //递归实际操作
				 t.right = remove(t.element,t.right);//递归查找操作
				 
		     }else{
		    	t = (t.left!=null) ?t.left:t.right;
		     }
		}
		
		
		return t;  //很经典
	}
	
	
	/**
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	
}
