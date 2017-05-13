package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjMatrixGraph{

		protected Vertex[] vertexlist;//顺序表存储图的顶点集合
		
		//该无向图剩余带宽
		protected int[][] adjmatrix; // 图的邻接矩阵 二维图 存储的是每个顶点的名称（A,B,C,D....）
		
		//该无向图存节点到节点之间的单价
		protected final int[][] prices; 
		
		public Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
		
		private int vertexNum;
		
		 
		public  int servicePrices = 0;
		
		public static List<ConsumePoint> consume = new ArrayList<ConsumePoint>();
		
		public static int totalBand = 0;//存放所有消费节点需要的带宽
		
		public static int totalEdgeNum = 0;//存放总边数
		
		public static int totalProvideBand = 0;//存放所有的边能提供的总带宽
		
		
		
		private static final int MAX_WEIGHT = Integer.MAX_VALUE / 2; //设出最大权值
		
		
		 // -构造函数1：初始画图-----//  		  
		public AdjMatrixGraph(int n) {// n为顶点的数目  
		  
			this.vertexlist = new Vertex[n];  
			  
			this.adjmatrix = new int[n][n];  
			this.prices = new int[n][n];  
			vertexNum = n; 
			

			//对角线上权值为0，其他地方权值为无穷大
			for (int i = 0; i < n; i++) { 		  
				for (int j = 0; j < n; j++) {					
					this.adjmatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;  // 对角线上为0，其他的都为无穷大。  
				}
			}
			
			for (int i = 0; i < n; i++) { 		  
				for (int j = 0; j < n; j++) {					
					this.prices[i][j] = (i == j) ? 0 : -1;  // 对角线上为0，其他的都为无穷大，这个-1代表无穷大 
				}
			}
			    
		}  
		
		
		//显示出顶点的个数		   
		public int vertexCount() {  
		  
			return this.vertexNum;  
		  
		} 
		
		//插入一个顶点
		public boolean insertVertex(int vertex) { // 插入一个顶点，若插入成功，返回true  
			if(vertex < vertexCount()){  
				vertexlist[vertex] = new Vertex(vertex);;  
	            return true;  
	        }  
	        else{  
	            return false;  
	        }  		   
	    }  
		
		
		//插入一条边,这是创建无向图的方式
		 public boolean insertEdge(int i, int j, int weight,int price) {
	  
			// 插入一条权值为weight的边<vi,vj>，若该边已有，则不插入 ，这个存每条边的带宽限制 
			  
			if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()  
			  
			&& i != j && adjmatrix[i][j] == MAX_WEIGHT) {  
			  
				// 先判断该边两个顶点的编号是否在范围，该边的值是否为最大值，来确定所添加边的值是否存在；  		  
				//this.adjmatrix[i][j] = weight;// 添加权值  
				this.adjmatrix[i][j] = weight;// 添加权值  
				this.adjmatrix[j][i] = weight;// 添加权值  
			 
				
			}  
			// 插入一条权值为weight的边<vi,vj>，若该边已有，则不插入 ，这个存每条边的流量单价 
			if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()  
					  
					&& i != j && prices[i][j] == -1) {  
					  
						// 先判断该边两个顶点的编号是否在范围，该边的值是否为-1，来确定所添加边的值是否存在；  		  
						//this.adjmatrix[i][j] = weight;// 添加权值  
						this.prices[i][j] = price;// 添加单价  
						this.prices[j][i] = price;// 添加单价
					 
						
			}  
		  
			return false;  
		  
		}  

		 // 删除边，这里不需要，有需要在实现
		 public boolean removeEdge(int i, int j) { // 删除边〈vi,vj〉，若成功，返回T  
		  
			 return false;
		   
		 }  
		 //删除顶点和边，这里不需要，有需要再实现
		 public boolean removeVertex(int v){   // 删除序号为v的顶点及其关联的边  		  
		   
			 	return false;  
		   
		 }  
		 
		 //返回与顶点v相邻,并且有带宽的所有顶点，这些顶点相当于从v出发的顶点
		 
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
					System.out.println("数据为空：请检查输入！");
					return null;
				}
				//解析第一行
				String[] str = data[0].split(" ");
				int totalVertex = Integer.parseInt(str[0]);
				//int consumeNum = Integer.parseInt(str[2]);
				final AdjMatrixGraph graph = new AdjMatrixGraph(totalVertex);
				AdjMatrixGraph.totalEdgeNum = Integer.parseInt(str[1]);//这个没什么用
	
				
				//解析第三行
				int serverPrice = Integer.parseInt(data[2]);
				graph.servicePrices = serverPrice;
				
				//从第5行开始解析节点和边
				int i=4;//i代表读入的第i条边
				int id1,id2,left_band,price;
				String[] str1;
				while(data[i]!=null && data[i].length()>0){
					
					//解析一条边
					str1 = data[i].split(" ");
					id1 = Integer.parseInt(str1[0]);
					id2 =  Integer.parseInt(str1[1]);
					left_band = Integer.parseInt(str1[2]);
					price = Integer.parseInt(str1[3]);
					
					AdjMatrixGraph.totalProvideBand += left_band;
					
					//插入边
					graph.insertEdge(id1,id2,left_band,price);
					
					//插入顶点
					if(graph.vertexlist[id1] ==  null){
						graph.insertVertex(id1);
					}
					if(graph.vertexlist[id2] == null){
						graph.insertVertex(id2);
					}
					
					//维护一个邻接表
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
							
				//读消费节点信息
				i++;
				int need;
				while(data[i]!=null && data[i].length()>0){
				
						str1 = data[i].split(" ");
						id1 = Integer.parseInt(str1[0]);
						id2 =  Integer.parseInt(str1[1]);//id2表示与消费节点相连的顶点
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
		 	

class Vertex{    //顶点数据结构的定义

	public int number;  //顶点的编号属性
	
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
	public int id;  //本身id
	public int linkedVertexId;  //相连顶点id
	public int need;//需求量
	
	
	
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
