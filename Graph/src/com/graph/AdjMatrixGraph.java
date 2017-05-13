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
		//求无向图的最短路径 ，这个算法很好，不需要大的改动
		 /**
		  * 
		  * @param W1   输入单价矩阵
		  * @param start  代表起始点
		  * @param end  代表终点
		  * @return
		  */	 
		/* public static int[] dijkstra(int[][] W1, int start, int end) {  
			 	
		        boolean[] isLabel = new boolean[W1[0].length];// 是否标号  
		        int[] indexs = new int[W1[0].length];// 所有标号的点的下标集合，以标号的先后顺序进行存储，实际上是一个以数组表示的栈  
		        int i_count = -1;//栈的顶点  
		        int[] distance = W1[start].clone();// v0到各点的最短距离的初始值  
		        int index = start;// 从初始点开始  
		        int presentShortest = 0;//当前临时最短距离  
		 
		        indexs[++i_count] = index;// 把已经标号的下标存入下标集中  
		        isLabel[index] = true;  
		        
		          
		        while (i_count<W1[0].length) {  
		            // 第一步：标号v0,即w[0][0]找到距离v0最近的点  
		 
		            int min = Integer.MAX_VALUE;  
		            for (int i = 0; i < distance.length; i++) {  
		                if (!isLabel[i] && distance[i] != -1 && i != index) {  
		                    // 如果到这个点有边,并且没有被标号  
		                    if (distance[i] < min) {  
		                        min = distance[i];  
		                        index = i;// 把下标改为当前下标  
		                    }  
		                }  
		            }  
		            if (index == end) {//已经找到当前点了，就结束程序  
		                break;  
		            }  
		            isLabel[index] = true;//对点进行标号  
		            indexs[++i_count] = index;// 把已经标号的下标存入下标集中  
		            if (W1[indexs[i_count - 1]][index] == -1 
		                    || presentShortest + W1[indexs[i_count - 1]][index] > distance[index]) {  
		                // 如果两个点没有直接相连，或者两个点的路径大于最短路径  
		                presentShortest = distance[index];  
		            } else {  
		                presentShortest += W1[indexs[i_count - 1]][index];  
		            }  
		 
		            // 第二步：将distance中的距离加入vi  
		            for (int i = 0; i < distance.length; i++) {  
		                // 如果vi到那个点有边，则v0到后面点的距离加  
		                if (distance[i] == -1 && W1[index][i] != -1) {// 如果以前不可达，则现在可达了  
		                    distance[i] = presentShortest + W1[index][i];  
		                } else if (W1[index][i] != -1 
		                        && presentShortest + W1[index][i] < distance[i]) {  
		                    // 如果以前可达，但现在的路径比以前更短，则更换成更短的路径  
		                    distance[i] = presentShortest + W1[index][i];  
		                }  
		 
		            }  
		        }  
		        //如果全部点都遍历完，则distance中存储的是开始点到各个点的最短路径  
		       // return distance[end] - distance[start]; 
		        //List<Integer> lst = new ArrayList<Integer>(indexs);

		        return indexs;
	   } */
		 
	/*	public List<List<Integer>> searchRoute(Map<Integer,List<Integer>> map,int start,List<Integer> end){
			
			List<List<Integer>> result = new ArrayList<List<Integer>>();
			List<Integer> lst = new ArrayList<Integer>();
			
			this.vertexlist[start].wasVisited = true;
			lst.add(start);
			dfs(this.map,result,start,end,lst);
			
			return result;
 			
			
		}
		
		
		public void dfs(Map<Integer,List<Integer>> map,List<List<Integer>> result,int start,List<Integer> end,List<Integer> lst){
			
			
			if(lst.size()>0 && end.contains(lst.get(lst.size()-1)) ){
				List<Integer> re = new ArrayList<Integer>(lst);
				result.add(re);
				return ;
			}
			
			if(lst.size() >=10 || result.size()>=50){
				return ;
			}
			
			
			List<Integer> temp = map.get(start);

			for(int i=0;i<temp.size();i++){
				
					if(this.vertexlist[temp.get(i)].wasVisited == false){
						lst.add(temp.get(i));	
						this.vertexlist[temp.get(i)].wasVisited= true;
					
						dfs(map,result,temp.get(i),end,lst);
						
						lst.remove(lst.size()-1);
						this.vertexlist[temp.get(i)].wasVisited= false;
					}
				
				
				
			}
			
		}
		 
		 //初始化图
		
		 
		
		 public static void recursive(AdjMatrixGraph graph,List<Map.Entry<Integer, List<Integer>>> infoIds){
			 	
			    //由于serverLocation位置一直在变化，所以每次赋值前，将上一次的值清空
			 	if(graph.serverLocation.size()>0){
			 		graph.serverLocation.clear();
			 	}
			 	
			 	
			 	//返回最小服务器数，并且更新服务器位置
			 	int serverNumMin = minServerNum(graph,infoIds);
			 	
			 	//返回找到的服务器的最大供应量
				Map<Integer,Integer> maxProvide = findServerLocation(graph);
				
				System.out.println("the need of the Server number:   "+graph.serverLocation.size());
				System.out.println("the location of server:  "+graph.serverLocation.toString());
				System.out.println("=============================================");
				System.out.println("=============================================");
				System.out.println("=============================================");
				
		
				
				//统计每个服务器实际的供应量
				Map<Integer,Integer> serverRealProvide = new HashMap<Integer,Integer>();
				for(int i=0;i<consume.size();i++){		
					
					System.out.println("=======================================================");
		
					Map<List<Integer>,Integer> route = AdjMatrixGraph.findRoute(consume.get(i),graph,1,serverRealProvide);
					for(Map.Entry<List<Integer>, Integer> entry : route.entrySet()){
						System.out.println("选择的路径："+entry.getKey().toString() + "   该路径消耗的带宽 ： "+entry.getValue());
					}
					
				}
				
				for(int i=0;i<graph.serverLocation.size();i++){
					int serverId = graph.serverLocation.get(i);
					
					System.out.println("服务器编号：" + serverId + " 向外提供的实际带宽 ：  " +serverRealProvide.get(serverId) + "最大能提供的带宽" + maxProvide.get(serverId) );
					double ratio = 1.0*serverRealProvide.get(serverId)/maxProvide.get(serverId); 
					System.out.println("利用率 ：" + ratio);
				}
				
			 
		 }
		 
		 //能返回服务器的最大供应量 
		 public static Map<Integer,Integer> findServerLocation(AdjMatrixGraph graph){		
			 
			 	Map<Integer,Integer> maxProvide = new HashMap<Integer,Integer>();
			 
			 	int i=0;			
						
				int eachSum;
				for (; i < graph.serverLocation.size(); i++) {  
				    int location =graph.serverLocation.get(i); 
				    
				    List<Integer> relation =  graph.map.get(location);
				    
				    eachSum = 0;
				    for(Integer tp:relation){
				    	eachSum  += graph.adjmatrix[location][tp];
				    	
				    }
				    
				    maxProvide.put(location, eachSum);
				    System.out.println(location+ "服务器节点的最大供应量== " +eachSum);
				    

				    
				}		
				return maxProvide;
						
		 }	 
		 
		 *//**
		  * 返回最小服务器数，并且更新服务器位置
		  * 
		  * @param graph
		  * @param infoIds
		  * @return
		  *//*
		 public static int minServerNum(AdjMatrixGraph graph,List<Map.Entry<Integer, List<Integer>>> infoIds){		 				
				int i = 0,sum = 0;
				for (; i < infoIds.size(); i++) {  
				    Entry<Integer,List<Integer>> ent=infoIds.get(i); 
				    int sortedVertex = ent.getKey();
				    List<Integer> relation = ent.getValue();
				    for(Integer tp:relation){
				    	sum = sum+graph.adjmatrix[sortedVertex][tp];
				    	
				    }
				    System.out.println(sortedVertex+ "==" +ent.getValue().size()); 
				    System.out.println("================" + sum);
				    
				    graph.serverLocation.add(sortedVertex);
				    
				    if(sum>=1.2*totalBand){
				    	break;
				    }
				    
				}
				
				return i+1;
		 }
		 
		 public static List<Map.Entry<Integer, List<Integer>>> sortVertexIndegree(AdjMatrixGraph graph){
			 	int i=0 ;			
				List<Map.Entry<Integer, List<Integer>>> infoIds = new ArrayList<Map.Entry<Integer, List<Integer>>>(  
						graph.map.entrySet());  
				System.out.println("--------------排序前--------------");  
				for (i = 0; i < infoIds.size(); i++) {  
				    String id = infoIds.get(i).toString();  
				    System.out.println(id);  
				}  
				// 对节点入度个数排序
				Collections.sort(infoIds, new Comparator<Map.Entry<Integer, List<Integer>>>() {  
				    public int compare(Map.Entry<Integer, List<Integer>> o1,  
				            Map.Entry<Integer, List<Integer>> o2) {  
				        return ( o2.getValue().size()-o1.getValue().size());  
				    }  
				});  
				
				
				System.out.println("------total band-----" + totalBand);			
				System.out.println("--------------排序后--------------"); 
				i = 0;
				for (; i < infoIds.size(); i++) {  
				    Entry<Integer,List<Integer>> ent=infoIds.get(i); 
				    int sortedVertex = ent.getKey();	    
				    System.out.println(sortedVertex+ "==" +ent.getValue().toString());
				}
				
				return infoIds;
		 }
		 
		 public static Map<List<Integer>,Integer> findRoute(ConsumePoint consumePoint,AdjMatrixGraph graph,double ratio,Map<Integer,Integer> serverRealProvide){
			 	Map<List<Integer>,Integer> haveFindRoute = new HashMap<List<Integer>,Integer>();
			 
				//找消费节点consume_id到各个服务器的路径，排序前          
				//System.out.println("找到的路径如下：=========");
				List<List<Integer>> result = graph.searchRoute(graph.map,consumePoint.linkedVertexId,graph.serverLocation);
				for(List<Integer> tmp:result){
					System.out.println(tmp.toString());
				}
				//正在进行排序
				result.sort(new Comparator<List<Integer>>(){

					@Override
					public int compare(List<Integer> o1, List<Integer> o2) {
						int sum1 = 0; 
						for(int j=1;j<o1.size();j++){
							sum1 += graph.prices[o1.get(j-1)][o1.get(j)];
						}
						int sum2 = 0;
						for(int j=1;j<o2.size();j++){
							sum2  += graph.prices[o2.get(j-1)][o2.get(j)];
						}
						if(sum1 == sum2){
							return 0 ;
							
						}else if(sum1>sum2){
							return 1;
						}else{
							return -1;
						}
					}

					
				});
				
				//排序后
				System.out.println("找到的路径个数如下：=========" + result.size());
				System.out.println("排序后的路径如下：===========");
				for(List<Integer> tmp:result){
					System.out.println(tmp.toString());
				}
				
				
				System.out.println("当前消费节点：" + consumePoint.id + " 所需带宽：" +consumePoint.need+"============");
				

				
				//得到第一条路径
				int present_band = 0;
				for(int m=0;m<result.size();m++){
					List<Integer> route1 = result.get(m);
					
					//获取服务器id
					int serverId = route1.get(route1.size()-1);
					
					//找单条路上最大能提供的带宽
					int route1_provide = Integer.MAX_VALUE;
					for(int j=1;j<route1.size();j++){
						int s1 = graph.adjmatrix[route1.get(j-1)][route1.get(j)];
						if( s1 <route1_provide){
							route1_provide = s1;
						}
						if(route1_provide == 0){
							break;
						}
					}
					if(route1_provide == 0){
						continue;
					}else if(route1_provide>5){
						route1_provide = (int)(ratio*route1_provide);//每次只用掉一定的比例，不用完，给后面留点余地
					}
					
					present_band +=route1_provide;
					
					
					if(present_band>=consumePoint.need){//减掉最后一次需要的
						int last_time_need = route1_provide-(present_band-consumePoint.need);
						//加入该路径
						haveFindRoute.put(route1, last_time_need);
						
						if(serverRealProvide.containsKey(serverId)){
							int band_sum = last_time_need+serverRealProvide.get(serverId);
							serverRealProvide.put(serverId, band_sum);
						}else{
							serverRealProvide.put(serverId, last_time_need);
						}
						System.out.println("已经能满足需求：-----------------------");
						System.out.println("单条路径上的最小带宽：" +route1_provide);
						System.out.println("实际用掉的带宽：  " +last_time_need);
						System.out.println("已经用掉的路径：  " +route1.toString());
						
						//用完之后减掉
						for(int j=1;j<route1.size();j++){
							graph.adjmatrix[route1.get(j-1)][route1.get(j)] -= last_time_need ;				
						}
						break;
						
						
					}else{//减掉全部
						
						haveFindRoute.put(route1, route1_provide);
						
						if(serverRealProvide.containsKey(serverId)){
							int band_sum = route1_provide+serverRealProvide.get(serverId);
							serverRealProvide.put(serverId, band_sum);
						}else{
							serverRealProvide.put(serverId, route1_provide);
						}
						//用完之后减掉
						for(int j=1;j<route1.size();j++){
							graph.adjmatrix[route1.get(j-1)][route1.get(j)] -= route1_provide ;				
						}
							
						//System.out.println("单条路径上的最小带宽：" +route1_provide);
						//System.out.println("已经用掉的路径：  " +route1.toString());
					}
					
					
				}
				
				
				
				
				
				
				
				
				return haveFindRoute;
				
				
		 }
		 
		 */
		 
	/*	 public static void main(String[] args) throws FileNotFoundException {  

			FileReader fileReader;
			fileReader = new FileReader("C:\\Users\\Administrator\\Desktop\\test2.txt");
			Scanner sc = new Scanner(fileReader);
			
			
			String line1 = sc.nextLine();
			String[] str = line1.split(" ");		
			//int totalVertex = Integer.parseInt(str[0]);
			int totalLine = Integer.parseInt(str[1]);//这个没什么用
			int consumeNum = Integer.parseInt(str[2]);
			String[] data = new String[totalLine+consumeNum+5];
			data[0] = line1;
			
			//开始读入到数组中
			int lineNum = 1;
			while(sc.hasNextLine()){
				
				line1 = sc.nextLine();
				data[lineNum] = line1;
				
				lineNum++;
				if(lineNum>=totalLine+consumeNum+5){
					break;
				}
			}
			
			AdjMatrixGraph graph = AdjMatrixGraph.initializeGraph(data);
			
			//d对每个顶点按入度数量排序，入度大的排在前面
			List<Map.Entry<Integer, List<Integer>>> infoIds = AdjMatrixGraph.sortVertexIndegree(graph);
			
			//对消费节点按需求量排序
			System.out.println("排序前");
			for(ConsumePoint tp:consume){
				System.out.println(tp.id);
			}
			consume.sort(new Comparator<ConsumePoint>(){

				@Override
				public int compare(ConsumePoint o1, ConsumePoint o2) {
					// TODO Auto-generated method stub
					if(o1.need>o2.need){
						return -1;
					}else if(o1.need<o2.need){
						return 1;
					}else{
						return 0;
					}
				}
				
			});			
			System.out.println("排序后");
			for(ConsumePoint tp:consume){
				System.out.println(tp.id);
			}
			
			*/
			
			
			        

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
	
	
	//构造器2
	
	
	


}	

/*//我把这种从A到B和从B到A的两个方向封装在一个桥对象中
class Edge{
	//由于是无向图，边没有方向，所以，起点和终点换个位置也是一样；后面注意怎么改进
	//后来又想了，这其实不是一个无向图，这是一个双向图，相当于从A到B，和从B到A都是可以的，并且流量互不影响
	public int start;//边的起始位置
	
	public int dest;  //边的终点位置
	
	public int left_band;  //边的剩余带宽
	
	public int price;//流量费单价
	
	

	public Edge(int start, int dest, int left_band, int price) {
		this.start = start;
		this.dest = dest;
		this.left_band = left_band;
		this.price = price;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public int getLeft_band() {
		return left_band;
	}

	public void setLeft_band(int left_band) {
		this.left_band = left_band;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
*/


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
