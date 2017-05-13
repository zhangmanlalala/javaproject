package com.cacheserverdeploy.deploy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Queue;


public class Graph {
	protected Vertex[] vertexlist;//顺序表存储图的顶点集合
	
	public static  int servicePrices = 0;
	
	public static List<ConsumePoint> consume = new ArrayList<ConsumePoint>();
	
	public static int totalBand = 0;//存放所有消费节点需要的带宽
	
	public static int totalEdgeNum = 0;//存放总边数
	
	public static int totalProvideBand = 0;//存放所有的边能提供的总带宽
	
	private static int vertexNum = 0;
	
	//用于排序的邻接表
	public static Map<Integer,Integer> map = new HashMap<Integer,Integer>();
 
	
	public static List<Edge> edges= new ArrayList<Edge>();
	public static List<List<Integer>> graph = new ArrayList<List<Integer>>();
	

	
	public static void addEdge(int from,int to,int cap,int cost){
		edges.add(new Edge(from,to,cap,0,cost));//主边
		edges.add(new Edge(to,from, 0,0,-cost)); //残余边
		int m = edges.size();
		graph.get(from).add(m-2);//存放真正的网络边
		graph.get(to).add(m-1);//存放残余的网络边		
	}
	
	public static void removeEdge(int from,int to){
		int m = edges.size();
		graph.get(from).remove((Integer)(m-2));//注意这里删除的是元素，而不是删除下标
		graph.get(to).remove((Integer)(m-1));//存放残余的网络边		
		edges.remove(m-1);
		edges.remove(m-2);
	}
	
	/**
	 * 
	 * @param s 代表源点  t代表汇点
	 * @param t
	 * @param flow
	 * @param cost
	 * @return
	 */
	public static int[] father ;//存放父节点
	public static int[] pos ;//pos中存放以某个节点为终点的边的剩余带宽
	public static int[] dis;//存放每个顶点到源点的最短路径
	public static int[] vis;//存放每个顶点是否进入队列
	
	public static int flow = 0;
	public static int cost = 0;
	
	/**
	 * 
	 * 
	 * @param s
	 * @param t
	 * @param lst 用于存储增广路径
	 * @return
	 */
	public static boolean isSuccess = false;
	public static boolean bellManford(int s,int t){
		//初始化vis和dis
		Arrays.fill(dis, Integer.MAX_VALUE);
		Arrays.fill(vis, 0);//上述两个数组的初始化
		
		Queue<Integer> queue = new LinkedList<Integer>();//队列用于存放顶点	
		queue.add(s);//往队列中加入初始点
		vis[s] = 1;//标记初始点入队列
		father[s] = -1;//表示s的父边的索引
		dis[s] = 0;//表示s到s的距离为0；
		pos[s] = Integer.MAX_VALUE;//为了求一条路径上的最小剩余带宽而设置的值
		
		while(!queue.isEmpty()){			
			int f = queue.peek();//队首元素出队列
			queue.poll();//弹出队首元素
			vis[f] = 0;//标记队首元素已经出队列
			
			for(int i=0;i<graph.get(f).size();i++){
				Edge tmp = edges.get(graph.get(f).get(i));//使用邻接表找以f为起点的所有边
				//如果他的容量大于流过他的带宽，并且如果边tmp的终点v到源点s的距离大于（起点f到终点的距离+tmp.cost）
				if(tmp.cap>tmp.flow && dis[tmp.v]>(dis[f] + tmp.cost)){//松弛操作
					
					dis[tmp.v] = dis[f] + tmp.cost;
					
					father[tmp.v] = graph.get(f).get(i);//记录父边
					
					pos[tmp.v] = Math.min(pos[f], tmp.cap-tmp.flow);//计算该条边和上一条边残余带宽的最小值
					
					if(vis[tmp.v] == 0){//如果变tmp的终点没有在队列中，则将其放入队列
						vis[tmp.v] = 1;
						queue.add(tmp.v);
					}
				}
			}				
		}
		
		if(dis[t] == Integer.MAX_VALUE){
			return false;
		}
		
		
		Graph.flow = Graph.flow+pos[t];  //总的流量带宽在上一次的基础上加上次增广路径的最小带宽
		Graph.cost +=dis[t]*pos[t];//dis[t]是将单价作为权重，并且将边上所有单价都加起来了，pos[t]是该条增光边上的最小带宽		
		//下面这一部分在找到增广路径后，更新残余网路
		//这里的u不代表顶点，t代表汇点，回过头来找刚刚的增广路径
		
		for(int u = t; u!=s ; u = edges.get(father[u]).u)  //u一直找u的父边的起点
	    {  
			int a = father[u];
			edges.get(a).flow += pos[t];  //更新正向边的已用流量
			int b = a^1;
	        edges.get(b).flow -= pos[t];  //更新残余边的残余容量
	    }  
		
		if(Graph.flow>=Graph.totalBand){
			Graph.isSuccess = true;
		}
		
	    return true;  
											
	}
	
	public static void searchFinalRoute(Map<List<Integer>,Integer> result,List<Integer> serverLocation){		
		//result.clear();
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(0);//将超级原点加入路径
		
		List<Integer> server = graph.get(0);
		for(int j=0;j<serverLocation.size();j++){
			Edge edge = edges.get(server.get(j));
			lst.add(edge.v);
			dfs(lst,result,edge,Graph.vertexNum-1,edge.flow);
			lst.remove(lst.size()-1);
		}
	}
	
	
	public static void dfs(List<Integer> lst,Map<List<Integer>,Integer> result,Edge edge,int t,int minBand){
		
		if(edge.cap==0  || edge.flow==0 || minBand==0){//反向边直接跳出或者已经没流量了，跳出
			return ;
		}
		if(edge.v == t){//如果终点是汇点
			edge.flow = edge.flow-minBand;
			//lst.add(t);
			List<Integer> lstCopy = new ArrayList<Integer>(lst);
			result.put(lstCopy,minBand);		
			//lst.remove(lst.size()-1);//保护现场
			return;
		}
		List<Integer>  linkedEdge = graph.get(edge.v);
		
		
		for(int i=0;i<linkedEdge.size();i++){
				
			Edge tmp = edges.get(linkedEdge.get(i));//使用邻接表找以f为起点的所有边		
			minBand = Math.min(minBand,edge.flow);//这句话完全没问题了
			if(tmp.flow>0 && tmp.cap>0 && minBand>0){//tmp.flow>0 && edge.flow>0
				int tempMin = Math.min(minBand,tmp.flow);//这句话完全没问题了
				lst.add(tmp.v);
				
				dfs(lst,result,tmp,t,tempMin);
				
				edge.flow = edge.flow - tempMin;
				minBand = minBand - tempMin;//这句话感觉很重要呀，就是不知道对不对
				lst.remove(lst.size()-1);
				//minBand = Math.max(minBand, edge.flow);//恢复现场
							
			}
		}
		
		
		
		
		
	}
	
		
	public static void clear(){
		//清除上一次迭代每条边的flow信息
		for(Edge edge:edges){
			edge.flow = 0;
		}
		
		//每次循环前更新这两个参数
		Graph.flow = 0;
		Graph.cost = 0;
		
		//清除上一次迭代中pos和father两个数组的元素信息
		Arrays.fill(Graph.pos,0);
		Arrays.fill(Graph.father, 0);	
	}
	
	    
	     
	 public static void initializeGraph(String[] data){
			if(data == null){
				System.out.println("数据为空：请检查输入！");
				return ;
			}
			
			//解析第一行
			String[] str = data[0].split(" ");
			Graph.vertexNum = Integer.parseInt(str[0])+2;//之所以加2是因为要加上一个超级源点和一个超级汇点
			Graph.totalEdgeNum = Integer.parseInt(str[1]);//这个没什么用
			int consumeNum = Integer.parseInt(str[2]);	
			
			//创建邻接表，必须要加上超级源点和超级汇点所占的空间
			for (int i = 0; i < Graph.vertexNum; i++){
				Graph.graph.add(new ArrayList<Integer>());
			}
			Graph.father = new int[vertexNum];//存放某个节点的父边
			Graph.pos = new int[vertexNum];//存放某个节点的父边还剩余的带宽
			Graph.dis = new int[vertexNum];//存放每个节点到超级源点的距离
			Graph.vis = new int[vertexNum];//存放每个节点是否入队列
			
			//解析第三行
			int serverPrice = Integer.parseInt(data[2]);
			Graph.servicePrices = serverPrice;
			
			//从第5行开始解析节点和边
			int i=4;//i代表读入的第i条边
			int id1,id2,left_band,price;
			String[] str1;
			while(data[i]!=null && data[i].length()>0){
				
				//解析一条边
				str1 = data[i].split(" ");
				id1 = Integer.parseInt(str1[0]);//起点
				id2 =  Integer.parseInt(str1[1]);//终点
				left_band = Integer.parseInt(str1[2]);//容量
				price = Integer.parseInt(str1[3]);//价格
				
				Graph.totalProvideBand += left_band;
				//这是一个牵一发而动全身的改变，后面切记
				id1 = id1+1;//让每个节点的编号加1，从而给编号为0的超级源点腾出位置
				id2 = id2+1;	
				//插入边,将一条边拆分为两条边，正向边和它的残余边
				Graph.addEdge(id1,id2,left_band,price);
				//反向边和它的残余边
				Graph.addEdge(id2,id1,left_band,price);
				
			}
						
			//读消费节点信息
			i++;
			int need;
			while(data[i]!=null && data[i].length()>0){
			
					str1 = data[i].split(" ");
					id1 = Integer.parseInt(str1[0]);
					id2 =  Integer.parseInt(str1[1]);//id2表示与消费节点相连的顶点
					id2 = id2+1;//前面将每个顶点的编号加1给超级源点腾出位置，这里也要进行加1做出相应的改变
					need = Integer.parseInt(str1[2]);
					ConsumePoint sp = new ConsumePoint(id1, id2, need);
					consume.add(sp);
					totalBand +=need;
					
					i++;
					if(i>=data.length){
						break;
					}
				
		     }
							
	}
	 
	 
		public static List<String> minCost(){
			int t = Graph.vertexNum-1;//现在顶点的编号为1到Graph.vertexNum
			int s = 0;  
			//注意这里先加入汇点的边，再加入源点的边，因为汇点的路径是不用变的，源点路径需要一直随服务器更新而更新
			//同时,建立一个消费者节点和它相连顶点的映射
			Map<Integer,Integer> consumeMap = new HashMap<Integer,Integer>();
			for(ConsumePoint cp:Graph.consume){
				Graph.addEdge(cp.linkedVertexId, t, cp.need, 0);
				consumeMap.put(cp.linkedVertexId, cp.id);
			}	

			List<String> finalResult  = new ArrayList<String>();

		       
			
			
			
			//下面是一个SPFA模板
			
		    //初始化服务器的位置,选用全部的节点作为服务器的位置
	    	List<Integer> serverLocation = Graph.initializeServerLocation(consumeMap);//初始化服务器的位置

			//将服务器和超级源点构成的边加入整个网络
			for(int m=0;m<serverLocation.size();m++){
				Graph.addEdge(s, serverLocation.get(m), Integer.MAX_VALUE, 0);	
			}
			while(bellManford(s,t) && !Graph.isSuccess){
				
			}
			//将之前服务器和超级源点构成的边先从整个网络删除，一定要在这里删除，注意
			for(int m=serverLocation.size()-1;m>=0;m--){
				Graph.removeEdge(s, serverLocation.get(m));
				
			}							
			//执行相关清理工作，待下次循环使用
			Graph.isSuccess = false;			
			Graph.clear();	
			
			
				
					
				
				
				
	   
			
			
			//计算最后的实际路径
			for(int m=0;m<serverLocation.size();m++){
				Graph.addEdge(s, serverLocation.get(m), Integer.MAX_VALUE, 0);	
			}		
			while(bellManford(s,t) && !Graph.isSuccess){		
				
			}								
			//使用DFS保存实际路径
			Map<List<Integer>,Integer> result = new HashMap<List<Integer>,Integer>();
			finalResult.clear();
			Graph.searchFinalRoute(result,serverLocation);
			Graph.findTrueRoute(finalResult, result, consumeMap);																		
			
			return finalResult;	
					
	    }
		
		public static List<Integer> initializeServerLocation(Map<Integer,Integer> consumeMap){	
		 	List<Integer> serverLocation = new ArrayList<Integer>();	 			
			for(Map.Entry<Integer, Integer> temp:consumeMap.entrySet()) { 		
			    serverLocation.add(temp.getKey());	
			}	
				
			return serverLocation;
		} 
		
		public static void findTrueRoute(List<String> finalResult,Map<List<Integer>,Integer> result,Map<Integer,Integer> consumeMap){
			StringBuilder sb = new StringBuilder();
			finalResult.add(result.size()+"");

			for(Map.Entry<List<Integer>, Integer> entry:result.entrySet()){
				
				List<Integer> cun = entry.getKey();
				int bd = entry.getValue();
				for(int g=1;g<cun.size()-1;g++){
					int xia = (cun.get(g)-1);//下标要减1
					sb.append(xia+" ");
				}
				sb.append(consumeMap.get(cun.get(cun.size()-2))+" ");
				sb.append(bd);
		
				finalResult.add(sb.toString());
				
				sb.delete(0, sb.length());
				
			}
			
		}
		
		
		
		 public  static String[] execute(String[] data){
			  //String[] data = Graph.readData("C:/Users/Administrator/Desktop/Primary/case0.txt");
			  Graph.initializeGraph(data);
			  List<String> finalResult = Graph.minCost();	
			  String[] res= new String[finalResult.size()+1];
			  res[0] = finalResult.get(0);
			  res[1] = "";
			  
			  for(int n=2;n<res.length;n++){
				  res[n] = finalResult.get(n-1);
				  
			  }
					  
			  return res;
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

class Edge{
	int u;//代表起点
	int v;//代表终点
	int cost;//该条边的单价
	int cap;//该条边的总容量
	int flow;//该条边已使用的流量
	
	int next;

	public Edge(int u, int v,int cap,int flow,int cost) {
		this.u = u;
		this.v = v;
		this.cap = cap;
		this.flow = flow;
		this.cost = cost;
	}
	
	
}
