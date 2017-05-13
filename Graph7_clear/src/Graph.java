import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import java.util.Scanner;

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

	public static boolean bellManford(int s,int t,List<Integer> lst,List<Integer> band,Map<Integer,Integer> serverProvide,Map<Integer,Integer> consumeGet){
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
		int consumeId = edges.get(father[t]).u;
		if(consumeGet.containsKey(consumeId)){
			int comple = consumeGet.get(consumeId);
			consumeGet.put(consumeId, comple+pos[t]);
		}else{
			consumeGet.put(consumeId, pos[t]);
		}
		
		
		for(int u = t; u!=s ; u = edges.get(father[u]).u)  //u一直找u的父边的起点
	    {  
			lst.add(u);//lst存储增广路径
			int a = father[u];
			edges.get(a).flow += pos[t];  //更新正向边的已用流量
			int b = a^1;
	        edges.get(b).flow -= pos[t];  //更新残余边的残余容量
	    }  
		
		int serverId = lst.get(lst.size()-1);
		if(serverProvide.containsKey(serverId)){	
			int com = serverProvide.get(serverId);
			serverProvide.put(serverId, com+pos[t])	;
		}else{
			serverProvide.put(serverId, pos[t]);
		}
		
		lst.add(s);//将超级源点加入增广路径
		if(Graph.flow<Graph.totalBand){
			band.add(pos[t]);//将对应的带宽保存起来
		}else if(Graph.flow==Graph.totalBand){//如果最后一次带宽大于Graph.totalBand这里要进行处理
			band.add(pos[t]);//将对应的带宽保存起来
			Graph.isSuccess = true;
		}else{//则最后一条加入进来的增广边不用耗费它所能耗费的全部带宽
			int last_time_need = pos[t]-(Graph.flow-Graph.totalBand);
			band.add(last_time_need);//将对应的带宽保存起来
			Graph.isSuccess = true;
		}	
	    return true;  
											
	}
	
	
	public static int nearbyNode(int serverId){
		
		int num = graph.get(serverId).size();
		int idx = -1;
		int max = Integer.MIN_VALUE;
		
		for(int i=0;i<num;i++){
			Edge tmp = edges.get(graph.get(serverId).get(i));//使用邻接表找以f为起点的所有边
			if(tmp == null){
				continue;
			}
			int linkedNode = tmp.v;
			if(linkedNode == 0 || linkedNode==Graph.vertexNum-1){//不能往里面加入超级源点和超级汇点
				continue;
			}
			if(!Graph.map.containsKey(linkedNode)){
				continue;
			}
			int linkedNodeWeight = Graph.map.get(linkedNode);
			if(linkedNodeWeight>max){
				max = linkedNodeWeight;
				idx = linkedNode;
			}
			
		}
		
		return idx;//idx==-1时代表没找到
			
	}
	//成功之后更新服务器位置的方法，减少服务器的个数
	public static List<Integer> updateServerLocation(List<Integer> serverLocation,Map<Integer,Integer> serverProvide,List<Integer> discard,List<Integer> unComplement){
		int number  = serverLocation.size();
		serverLocation.clear();		
		//对每个服务器的供应量进行排序
		List<Map.Entry<Integer, Integer>> sortedServerProvide = new ArrayList<Map.Entry<Integer, Integer>>(serverProvide.entrySet());
		Collections.sort(sortedServerProvide,new Comparator<Map.Entry<Integer, Integer>>(){

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue()-o1.getValue();
			}
			
		});
		
		//先从从利用率高的服务器节点中选取一部分
		int discardNum = 5;
		int i=0;
		for(;i<sortedServerProvide.size()-discardNum;i++){
			int key = sortedServerProvide.get(i).getKey();
			if(!serverLocation.contains(key) && !discard.contains(key)){
				serverLocation.add(key);		
			}
		}
		
		List<Integer> temp = new ArrayList<Integer>();
		//将剔除的节点放入容器中标记起来
		for(;i<sortedServerProvide.size();i++){
			int key = sortedServerProvide.get(i).getKey();
			temp.add(key);
			if(!discard.contains(key)){
				discard.add(key);
				
			}
		}
		
		for(int m=0;m<temp.size()-3;m++){
			int in = Graph.nearbyNode(temp.get(m));
			if(!serverLocation.contains(in) && !discard.contains(in)){
				serverLocation.add(in);		
			}
		}
		
		//先将未满足的加入
		int j=0;
		for(;j<unComplement.size() && j<number;j++){
			int id = unComplement.get(j);
			if(!serverLocation.contains(id)){
				serverLocation.add(id);
			}
		}
		
		
		return serverLocation;
		
		
	
}
	public static int minCost = Integer.MAX_VALUE;
	public static void anasys(Map<Integer,Integer> consumeGet,Map<Integer,Integer> consumeMap,List<Integer> unComplement){

		for(Map.Entry<Integer, Integer> entry:consumeGet.entrySet()){
			int key = entry.getKey();
			int value1 = entry.getValue();
			int value2 = Graph.consume.get(consumeMap.get(key)).need;
			
			if(value1<value2){
				unComplement.add(key);
			}
		}
		
	}
	
	public static List<String> minCost(){
		
		//初始化服务器的位置，并且维护一个服务器位置概率池
		List<Map.Entry<Integer, Integer>> infoIds = Graph.sortVertexIndegree();//对节点带宽排序

		int s = 0;
		int t = Graph.vertexNum-1;//现在顶点的编号为1到Graph.vertexNum
		
		//注意这里先加入汇点的边，再加入源点的边，因为汇点的路径是不用变的，源点路径需要一直随服务器更新而更新
		//同时,建立一个消费者节点和它相连顶点的映射
		Map<Integer,Integer> consumeMap = new HashMap<Integer,Integer>();
		for(ConsumePoint cp:Graph.consume){
			Graph.addEdge(cp.linkedVertexId, t, cp.need, 0);
			consumeMap.put(cp.linkedVertexId, cp.id);
		}
		
		//寻找候选服务器节点
		List<Map.Entry<Integer, Integer>> candidateInfo = Graph.selectCandidateLocation(consumeMap,infoIds);		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<String> finalResult  = new ArrayList<String>();
		List<Integer> lst = new ArrayList<Integer>();
		List<Integer> band = new ArrayList<Integer>();
		
		//保存每个服务对外提供的带宽
	    Map<Integer,Integer> serverProvide = new HashMap<Integer,Integer>();
	    Map<Integer,Integer> consumeGet = new HashMap<Integer,Integer>();
	     
	    //初始化服务器的位置
    	List<Integer> serverLocation = Graph.initializeServerLocation(candidateInfo);//初始化服务器的位置
	   
    	int recursiveNum = 50;    	
    	List<Integer> unComplement = new ArrayList<Integer>();
    	List<Integer> discard = new ArrayList<Integer>();	
    	
		int times = 0;
	
		while(times<=recursiveNum){					
			//将服务器和超级源点构成的边加入整个网络
			for(int m=0;m<serverLocation.size();m++){
				Graph.addEdge(s, serverLocation.get(m), Integer.MAX_VALUE, 0);	
			}
			
			serverProvide.clear();
			consumeGet.clear();
			result.clear();
			lst.clear();
			band.clear();
	
			while(bellManford(s,t,lst,band,serverProvide,consumeGet) && !Graph.isSuccess){
				List<Integer> tp = new ArrayList<Integer>(lst);
				result.add(tp);
				lst.clear();
	
			}
			//将最后一次成功的增广路径加进来,之前一直忘记加了
			if(lst.size()>0){
				List<Integer> tep = new ArrayList<Integer>(lst);
				result.add(tep);
			}
					
			//将之前服务器和超级源点构成的边先从整个网络删除，一定要在这里删除，注意
			for(int m=serverLocation.size()-1;m>=0;m--){
				Graph.removeEdge(s, serverLocation.get(m));
			}							
			
										
			//更新服务器		
			if(Graph.isSuccess){
				int serverNumber = serverLocation.size();
				int total = Graph.cost+serverNumber*Graph.servicePrices;

				if(total<Graph.minCost){
					
					StringBuilder sb = new StringBuilder();
					Graph.minCost = total;
					finalResult.clear();
					finalResult.add(result.size()+"");
					
					for(int k=0;k<result.size();k++){
						List<Integer> cun = result.get(k);
						for(int g=cun.size()-2;g>=1;g--){
							int xia = (cun.get(g)-1);//下标要减1
							sb.append(xia+" ");
						}
						sb.append(consumeMap.get(cun.get(1))+" ");
						sb.append(band.get(k));
						
						
						finalResult.add(sb.toString());
						
						sb.delete(0, sb.length());
						
					}
					
					
					
				}

			}			
			if(times%10 ==0){
				discard.clear();
			}
			
			
		
			//先进行分析，再删除网路中的多余边
			Graph.anasys(consumeGet,consumeMap,unComplement);			
			serverLocation = updateServerLocation(serverLocation,serverProvide,discard,unComplement);
			Graph.isSuccess = false;
			
			
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
			
			times++;
			
		}	
    	
		//要去掉的
		if(Graph.minCost<Integer.MAX_VALUE){
		
			System.out.println("寻找成功，得到的最小费用为："+Graph.minCost);
		}else{
			System.out.println("寻找失败=====================");
		}
		
		return finalResult;
		
		
    }
		
	
	  public  static void execute() throws IOException{
		  String[] data = Graph.readData("C:/Users/Administrator/Desktop/Primary/case0.txt");
		  Graph.initializeGraph(data);
		  List<String> finalResult = Graph.minCost();	
		  for(String tp:finalResult){
			  System.out.println(tp);
		  }
	  }
	    
	
	
	
	
	 
	 

	public static String[] readData(String path) throws FileNotFoundException{
			FileReader fileReader = new FileReader(path);
			
			Scanner sc = new Scanner(fileReader);
					
			String line1 = sc.nextLine();
			String[] str = line1.split(" ");		
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
			sc.close();
			return data;
			
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
				
				int everybandPrice = left_band/price;
				//维护一个邻接表
				if(Graph.map.containsKey(id1)){
					int band = Graph.map.get(id1);
					Graph.map.put(id1, band+everybandPrice);
				}else{
					Graph.map.put(id1, everybandPrice);
				}
				
				if(Graph.map.containsKey(id2)){
					int band = Graph.map.get(id2);
					Graph.map.put(id2, band+everybandPrice);
				}else{
					Graph.map.put(id2, everybandPrice);
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
	 
	 public static void clear(int n){
		 for(int i=0;i<=n;i++){
			 graph.get(i).clear();
		 }
		 edges.clear();
	 }
	 
	 public static List<Integer> initializeServerLocation(List<Map.Entry<Integer, Integer>> candidateInfo){	
	 	List<Integer> serverLocation = new ArrayList<Integer>();	 	
		int i = 0;
		for (; i < candidateInfo.size(); i++) { 
			
		    Entry<Integer,Integer> ent=candidateInfo.get(i); 
		    int sortedVertex = ent.getKey();
		   // int sortedVertexBand = ent.getValue();  
		    serverLocation.add(sortedVertex);	

		}	
			
		return serverLocation;
	}
	 /**
	  * 计算每个顶点的入度
	  * @param graph
	  * @return
	  */
	 public static List<Map.Entry<Integer, Integer>> sortVertexIndegree(){
		 	int i=0 ;			
			List<Map.Entry<Integer, Integer>> infoIds = new ArrayList<Map.Entry<Integer, Integer>>(  
					Graph.map.entrySet());  
			// 对节点入度个数和带宽比例排序
			Collections.sort(infoIds, new Comparator<Map.Entry<Integer, Integer>>() {  
			    public int compare(Map.Entry<Integer, Integer> o1,  
			            Map.Entry<Integer, Integer> o2) {  
			        return ( o2.getValue()-o1.getValue()); 			    			    	
			    	
			    	
			    }  
			});  
			
			return infoIds;
	 }
	 
	 public static List<Map.Entry<Integer, Integer>> selectCandidateLocation(Map<Integer,Integer> consumeMap,List<Map.Entry<Integer, Integer>> infoIds){
		 Map<Integer,Integer> candidateServerLocation = new HashMap<Integer,Integer>();
			
			for(int i=0;i<infoIds.size();i++){
				int key = infoIds.get(i).getKey();
				if(consumeMap.containsKey(key)){
					int consumeId = consumeMap.get(key);
					int weight =(int) (infoIds.get(i).getValue()*0.5 + 0.5*Graph.consume.get(consumeId).need);
					candidateServerLocation.put(key, weight);
				}
			}
			
			List<Map.Entry<Integer, Integer>> candidateInfo = new ArrayList<Map.Entry<Integer, Integer>>(candidateServerLocation.entrySet());
			
			Collections.sort(candidateInfo,new Comparator<Map.Entry<Integer, Integer>>(){

				@Override
				public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
					// TODO Auto-generated method stub
					return (o2.getValue()-o1.getValue());
				}
				
			});
			
			
			return candidateInfo;
	 }
	 
	 
	 
	 public static void main(String[] args) throws IOException {  
		 Graph.execute();
				    
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
