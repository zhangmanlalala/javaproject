package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjMatrixGraph{

		protected Vertex[] vertexlist;//˳���洢ͼ�Ķ��㼯��
		
		//������ͼʣ�����
		protected int[][] adjmatrix; // ͼ���ڽӾ��� ��άͼ �洢����ÿ����������ƣ�A,B,C,D....��
		
		//������ͼ��ڵ㵽�ڵ�֮��ĵ���
		protected final int[][] prices; 
		
		public Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
		
		private int vertexNum;
		
		 
		public  int servicePrices = 0;
		
		public static List<ConsumePoint> consume = new ArrayList<ConsumePoint>();
		
		public static int totalBand = 0;//����������ѽڵ���Ҫ�Ĵ���
		
		public static int totalEdgeNum = 0;//����ܱ���
		
		public static int totalProvideBand = 0;//������еı����ṩ���ܴ���
		
		
		
		private static final int MAX_WEIGHT = Integer.MAX_VALUE / 2; //������Ȩֵ
		
		
		 // -���캯��1����ʼ��ͼ-----//  		  
		public AdjMatrixGraph(int n) {// nΪ�������Ŀ  
		  
			this.vertexlist = new Vertex[n];  
			  
			this.adjmatrix = new int[n][n];  
			this.prices = new int[n][n];  
			vertexNum = n; 
			

			//�Խ�����ȨֵΪ0�������ط�ȨֵΪ�����
			for (int i = 0; i < n; i++) { 		  
				for (int j = 0; j < n; j++) {					
					this.adjmatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;  // �Խ�����Ϊ0�������Ķ�Ϊ�����  
				}
			}
			
			for (int i = 0; i < n; i++) { 		  
				for (int j = 0; j < n; j++) {					
					this.prices[i][j] = (i == j) ? 0 : -1;  // �Խ�����Ϊ0�������Ķ�Ϊ��������-1��������� 
				}
			}
			    
		}  
		
		
		//��ʾ������ĸ���		   
		public int vertexCount() {  
		  
			return this.vertexNum;  
		  
		} 
		
		//����һ������
		public boolean insertVertex(int vertex) { // ����һ�����㣬������ɹ�������true  
			if(vertex < vertexCount()){  
				vertexlist[vertex] = new Vertex(vertex);;  
	            return true;  
	        }  
	        else{  
	            return false;  
	        }  		   
	    }  
		
		
		//����һ����,���Ǵ�������ͼ�ķ�ʽ
		 public boolean insertEdge(int i, int j, int weight,int price) {
	  
			// ����һ��ȨֵΪweight�ı�<vi,vj>�����ñ����У��򲻲��� �������ÿ���ߵĴ������� 
			  
			if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()  
			  
			&& i != j && adjmatrix[i][j] == MAX_WEIGHT) {  
			  
				// ���жϸñ���������ı���Ƿ��ڷ�Χ���ñߵ�ֵ�Ƿ�Ϊ���ֵ����ȷ������ӱߵ�ֵ�Ƿ���ڣ�  		  
				//this.adjmatrix[i][j] = weight;// ���Ȩֵ  
				this.adjmatrix[i][j] = weight;// ���Ȩֵ  
				this.adjmatrix[j][i] = weight;// ���Ȩֵ  
			 
				
			}  
			// ����һ��ȨֵΪweight�ı�<vi,vj>�����ñ����У��򲻲��� �������ÿ���ߵ��������� 
			if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()  
					  
					&& i != j && prices[i][j] == -1) {  
					  
						// ���жϸñ���������ı���Ƿ��ڷ�Χ���ñߵ�ֵ�Ƿ�Ϊ-1����ȷ������ӱߵ�ֵ�Ƿ���ڣ�  		  
						//this.adjmatrix[i][j] = weight;// ���Ȩֵ  
						this.prices[i][j] = price;// ��ӵ���  
						this.prices[j][i] = price;// ��ӵ���
					 
						
			}  
		  
			return false;  
		  
		}  

		 // ɾ���ߣ����ﲻ��Ҫ������Ҫ��ʵ��
		 public boolean removeEdge(int i, int j) { // ɾ���ߡ�vi,vj�������ɹ�������T  
		  
			 return false;
		   
		 }  
		 //ɾ������ͱߣ����ﲻ��Ҫ������Ҫ��ʵ��
		 public boolean removeVertex(int v){   // ɾ�����Ϊv�Ķ��㼰������ı�  		  
		   
			 	return false;  
		   
		 }  
		 
		 //�����붥��v����,�����д�������ж��㣬��Щ�����൱�ڴ�v�����Ķ���
		 
		 public List<Integer> getNaborPoint(int v){
			List<Integer> lst = new ArrayList<Integer>();
			 for(int j=0;j<adjmatrix.length;j++){
				 if(adjmatrix[v][j] != 0 && adjmatrix[v][j] != MAX_WEIGHT){
					 lst.add(j);
				 }
			 }
			 
			 
			 return lst;
		 }
		 
		 
		 public static AdjMatrixGraph initializeGraph(String[] data){
				if(data == null){
					System.out.println("����Ϊ�գ��������룡");
					return null;
				}
				//������һ��
				String[] str = data[0].split(" ");
				int totalVertex = Integer.parseInt(str[0]);
				//int consumeNum = Integer.parseInt(str[2]);
				final AdjMatrixGraph graph = new AdjMatrixGraph(totalVertex);
				AdjMatrixGraph.totalEdgeNum = Integer.parseInt(str[1]);//���ûʲô��
	
				
				//����������
				int serverPrice = Integer.parseInt(data[2]);
				graph.servicePrices = serverPrice;
				
				//�ӵ�5�п�ʼ�����ڵ�ͱ�
				int i=4;//i�������ĵ�i����
				int id1,id2,left_band,price;
				String[] str1;
				while(data[i]!=null && data[i].length()>0){
					
					//����һ����
					str1 = data[i].split(" ");
					id1 = Integer.parseInt(str1[0]);
					id2 =  Integer.parseInt(str1[1]);
					left_band = Integer.parseInt(str1[2]);
					price = Integer.parseInt(str1[3]);
					
					AdjMatrixGraph.totalProvideBand += left_band;
					
					//�����
					graph.insertEdge(id1,id2,left_band,price);
					
					//���붥��
					if(graph.vertexlist[id1] ==  null){
						graph.insertVertex(id1);
					}
					if(graph.vertexlist[id2] == null){
						graph.insertVertex(id2);
					}
					
					//ά��һ���ڽӱ�
					if(graph.map.containsKey(id1)){
						List<Integer> lt = graph.map.get(id1);
						lt.add(id2);
					}else{
						List<Integer> lst = new ArrayList<Integer>();
						lst.add(id2);
						graph.map.put(id1, lst);
					}
					
					if(graph.map.containsKey(id2)){
						List<Integer> lt = graph.map.get(id2);
						lt.add(id1);
					}else{
						List<Integer> lst = new ArrayList<Integer>();
						lst.add(id1);
						graph.map.put(id2, lst);
					}
					
					i++;
					
				}
							
				//�����ѽڵ���Ϣ
				i++;
				int need;
				while(data[i]!=null && data[i].length()>0){
				
						str1 = data[i].split(" ");
						id1 = Integer.parseInt(str1[0]);
						id2 =  Integer.parseInt(str1[1]);//id2��ʾ�����ѽڵ������Ķ���
						need = Integer.parseInt(str1[2]);
						ConsumePoint sp = new ConsumePoint(id1, id2, need);
						consume.add(sp);
						totalBand +=need;
						
						i++;
						if(i>=data.length){
							break;
						}
					
			     }
					
				
				
				return graph;
				
				
		}
	
		 	
		public static void main(String[] args) {  
			
		}		
			        

}  
		 	

class Vertex{    //�������ݽṹ�Ķ���

	public int number;  //����ı������
	
	public boolean wasVisited; 
	
	
	public Vertex(int number) {	
		this.number = number;
	}
	public boolean isWasVisited() {
		return wasVisited;
	}
	public void setWasVisited(boolean wasVisited) {
		this.wasVisited = wasVisited;
	}


}	


class ConsumePoint{
	public int id;  //����id
	public int linkedVertexId;  //��������id
	public int need;//������
	
	
	
	public ConsumePoint(int id, int linkedVertexId, int need) {
		this.id = id;
		this.linkedVertexId = linkedVertexId;
		this.need = need;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLinkedVertexId() {
		return linkedVertexId;
	}
	public void setLinkedVertexId(int linkedVertexId) {
		this.linkedVertexId = linkedVertexId;
	}
	public int getNeed() {
		return need;
	}
	public void setNeed(int need) {
		this.need = need;
	}
	 
}
