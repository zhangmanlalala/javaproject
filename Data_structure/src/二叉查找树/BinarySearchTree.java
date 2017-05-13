package ���������;

public class BinarySearchTree <AnyType extends Comparable<? super AnyType>>{
	
	/**
	 * 
	 * ��Ҫ�
	 * @author Administrator
	 *
	 * @param <AnyType>
	 */
	//����ڵ�Ϊ�ڲ���
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
	 * �����������������ľ����㷨��contains()��������
	 * 
	 * ��ס����  1)�����������Ҷ�ӽڵ�֮����е�
	 * 		 2)��סt.left = insert(x,t.left)�����������
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
			t.left = insert(x,t.left);//��仰����������:1)��֤����ܷ��ظ��ڵ�
												// 2)��֤�ܰѲ���Ľڵ���������
		}else if(result1>0){
			t.right = insert(x,t.right);//�ݹ���Ҳ���
		}else{
			//do nothing;
		}
		
		
		return t;  //�ܾ���
	}
	
	
	/**
	 * ������ɾ�������ľ����㷨
	 * 
	 * t.left = remove(x,t.left);  �ݹ���Ҳ���
	 * �������ã�
	 * 
	 * 
	 */

	private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
		if(t==null){
			return t;
		}
		int result = x.compareTo(t.element);
		if(result<0){
			t.left = remove(x,t.left);//��仰����������:1)��֤����ܷ��ظ��ڵ�
												// 2)��֤�ܰѲ���Ľڵ���������
		}else if(result>0){
			t.right = remove(x,t.right);//�ݹ���Ҳ���
		}else{
			 if(t.left!=null && t.right!=null) {
				 t.element=findMin(t.right).element;  //�ݹ�ʵ�ʲ���
				 t.right = remove(t.element,t.right);//�ݹ���Ҳ���
				 
		     }else{
		    	t = (t.left!=null) ?t.left:t.right;
		     }
		}
		
		
		return t;  //�ܾ���
	}
	
	
	/**
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	
}
