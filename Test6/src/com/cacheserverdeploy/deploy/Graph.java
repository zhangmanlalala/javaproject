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
	public static Map<Integer,Integer> map2 = new HashMap<Integer,Integer>();
	public static Map<Integer,Integer> map3 = new HashMap<Integer,Integer>();
 
	
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

	public static boolean bellManford(int s,int t,Map<Integer,Integer> serverProvide,Map<Integer,Integer> consumeGet){
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
		
		
		
		int serverId = 0;
		
		for(int u = t; u!=s ; u = edges.get(father[u]).u)  //u一直找u的父边的起点
	    {  
			int a = father[u];
			edges.get(a).flow += pos[t];  //更新正向边的已用流量
			int b = a^1;
	        edges.get(b).flow -= pos[t];  //更新残余边的残余容量
	        
	        serverId = u;
	    }  
		
		if(serverProvide.containsKey(serverId)){	
			int com = serverProvide.get(serverId);
			serverProvide.put(serverId, com+pos[t])	;
		}else{
			serverProvide.put(serverId, pos[t]);
		}
		//如果满足就跳出来
		if(Graph.flow>=Graph.totalBand){
			Graph.isSuccess = true;
		}	
	    return true;  
											
	}
	//重载方法
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
	
	//重载方法
	public static boolean bellManford(int s,int t,Map<Integer,Integer> serverProvide,Map<Integer,Integer> serverSpend,Map<Integer,Integer> consumeGet){
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
		
		
		
		int serverId = 0;
		
		for(int u = t; u!=s ; u = edges.get(father[u]).u)  //u一直找u的父边的起点
	    {  
			int a = father[u];
			edges.get(a).flow += pos[t];  //更新正向边的已用流量
			int b = a^1;
	        edges.get(b).flow -= pos[t];  //更新残余边的残余容量
	        
	        serverId = u;
	    }  
		
		if(serverProvide.containsKey(serverId)){	
			int com = serverProvide.get(serverId);
			int spend = serverSpend.get(serverId);
			serverProvide.put(serverId, com+pos[t])	;
			serverSpend.put(serverId, (spend+dis[t]*pos[t]));
		}else{
			serverProvide.put(serverId, pos[t]);
			serverSpend.put(serverId, (dis[t]*pos[t]));
		}
		
	
		//如果满足就跳出来
		if(Graph.flow>=Graph.totalBand){
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
			if(!Graph.map3.containsKey(linkedNode)){
				continue;
			}
			int linkedNodeWeight = Graph.map3.get(linkedNode);//获取serverId附近性价比最高的节点
			if(linkedNodeWeight>max){
				max = linkedNodeWeight;
				idx = linkedNode;
			}
			
		}
		
		return idx;//idx==-1时代表没找到
			
	}
	public static int[] nearbyNode0(int serverId){
		
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
			if(!Graph.map3.containsKey(linkedNode)){
				continue;
			}
			int linkedNodeWeight = Graph.map3.get(linkedNode);//获取serverId附近性价比最高的节点
			if(linkedNodeWeight>max){
				max = linkedNodeWeight;
				idx = linkedNode;
			}
			
		}
		if(idx!=-1){
			return new int[]{idx,max};//idx==-1时代表没找到
			
		}else{
			return null;
		}
		
			
	}
	
	public static List<Integer> nearbyNode2(int serverId){
			List<Integer> nearBy = new ArrayList<Integer>();
			
			int num = graph.get(serverId).size();
			int idx1 = -1;
			int idx2 = -1;
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
				if(!Graph.map3.containsKey(linkedNode)){
					continue;
				}
				int linkedNodeWeight = Graph.map3.get(linkedNode);//获取serverId附近性价比最高的节点
				if(linkedNodeWeight>max){
					max = linkedNodeWeight;
					idx2 = idx1;
					idx1 = linkedNode;
				}
				
			}
			
			if(idx1!=-1){
				nearBy.add(idx1);
			}
			if(idx2!=-1){
				nearBy.add(idx2);
			}
			return nearBy;//idx==-1时代表没找到
				
		}
	
	public static List<Integer> nearbyNode3(int serverId){
		List<Integer> nearBy = new ArrayList<Integer>();
		Map<Integer,Integer> mapTemp = new HashMap<Integer,Integer>();
		
		int num = graph.get(serverId).size();
		
		for(int i=0;i<num;i++){
			Edge tmp = edges.get(graph.get(serverId).get(i));//使用邻接表找以f为起点的所有边
			if(tmp == null){
				continue;
			}
			int linkedNode = tmp.v;
			if(linkedNode == 0 || linkedNode==Graph.vertexNum-1){//不能往里面加入超级源点和超级汇点
				continue;
			}
			if(!Graph.map3.containsKey(linkedNode)){
				continue;
			}
			int linkedNodeWeight = Graph.map3.get(linkedNode);//获取serverId附近性价比最高的节点
			mapTemp.put(linkedNode, linkedNodeWeight);
			
		}
		
		List<Map.Entry<Integer, Integer>> stemp = new ArrayList<Map.Entry<Integer,Integer>>(mapTemp.entrySet());
		Collections.sort(stemp,new Comparator<Map.Entry<Integer, Integer>>(){

			@Override
			public int compare(Entry<Integer, Integer> arg0, Entry<Integer, Integer> arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue()-arg0.getValue();
			}
			
		});
		
		for(int i=0;i<stemp.size();i++){
			int key = stemp.get(i).getKey();//我们需要的是key
			nearBy.add(key);
		}

		return nearBy;//idx==-1时代表没找到
			
	}
	
	public static List<Integer> nearbyNode4(int serverId){//获取更多的附近的节点
		List<Integer> nearBy = new ArrayList<Integer>();
		Map<Integer,Integer> mapTemp = new HashMap<Integer,Integer>();
		
		int num = graph.get(serverId).size();
		
		for(int i=0;i<num;i++){
			Edge tmp = edges.get(graph.get(serverId).get(i));//使用邻接表找以f为起点的所有边
			if(tmp == null){
				continue;
			}
			int linkedNode = tmp.v;
			if(linkedNode == 0 || linkedNode==Graph.vertexNum-1){//不能往里面加入超级源点和超级汇点
				continue;
			}
			if(!Graph.map3.containsKey(linkedNode)){
				continue;
			}
			int linkedNodeWeight = Graph.map3.get(linkedNode);//获取serverId附近性价比最高的节点
			mapTemp.put(linkedNode, linkedNodeWeight);
			
		}
		
		
		
		
		List<Map.Entry<Integer, Integer>> stemp = new ArrayList<Map.Entry<Integer,Integer>>(mapTemp.entrySet());
		Collections.sort(stemp,new Comparator<Map.Entry<Integer, Integer>>(){

			@Override
			public int compare(Entry<Integer, Integer> arg0, Entry<Integer, Integer> arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue()-arg0.getValue();
			}
			
		});
		
		for(int i=0;i<stemp.size();i++){
			int key = stemp.get(i).getKey();//我们需要的是key
			nearBy.add(key);
		}
		
		//我们之前一个最大的问题是只找到周围的，周围的周围也可以试一下啊
		int size = nearBy.size();
		
		for(int m=0;m<size;m++){
			List<Integer> rt = Graph.nearbyNode2(nearBy.get(m));//这是只获得两个临近的邻近的
			for(int b=0;b<rt.size();b++){
				
				int p = rt.get(b);
				if(!nearBy.contains(p) && p!=serverId){//如果bearBy之前不包含p,
					nearBy.add(p);
				}
			}
			
			
		}
		return nearBy;//idx==-1时代表没找到
			
	}
	
	//第5个nearBy扩大了搜素范围
	public static List<Integer> nearbyNode5(int serverId){//获取更多的附近的节点
		List<Integer> nearBy = new ArrayList<Integer>();
		Map<Integer,Integer> mapTemp = new HashMap<Integer,Integer>();
		
		int num = graph.get(serverId).size();
		
		for(int i=0;i<num;i++){
			Edge tmp = edges.get(graph.get(serverId).get(i));//使用邻接表找以f为起点的所有边
			if(tmp == null){
				continue;
			}
			int linkedNode = tmp.v;
			if(linkedNode == 0 || linkedNode==Graph.vertexNum-1){//不能往里面加入超级源点和超级汇点
				continue;
			}
			if(!Graph.map3.containsKey(linkedNode)){
				continue;
			}
			int linkedNodeWeight = Graph.map3.get(linkedNode);//获取serverId附近性价比最高的节点
			mapTemp.put(linkedNode, linkedNodeWeight);
			
		}
		for(Map.Entry<Integer, Integer> entry:mapTemp.entrySet()){
			int key = entry.getKey();
			nearBy.add(key);
		}
		
		//相当于扩大了搜素范围
		for(int i=0;i<nearBy.size();i++){
			int[] ne = Graph.nearbyNode0(nearBy.get(i));
			if(ne!=null){
				int key = ne[0];
				int value = ne[1];
				if(!mapTemp.containsKey(key)){
					mapTemp.put(key, value);
				}
			}
		}
				
		List<Map.Entry<Integer, Integer>> stemp = new ArrayList<Map.Entry<Integer,Integer>>(mapTemp.entrySet());
		Collections.sort(stemp,new Comparator<Map.Entry<Integer, Integer>>(){

			@Override
			public int compare(Entry<Integer, Integer> arg0, Entry<Integer, Integer> arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue()-arg0.getValue();
			}
			
		});
		
		nearBy.clear();
		for(int i=0;i<stemp.size();i++){
			int key = stemp.get(i).getKey();//我们需要的是key
			nearBy.add(key);
		}
		
		return nearBy;
		

			
	}
	
	//针对高级和初级的
	public static void updateServerLocationSuper(List<Integer> serverLocation,List<Integer> sortedKey,Map<Integer,Integer> discard,List<Integer> unComplement){
		int number  = serverLocation.size();
		serverLocation.clear();	
		int discardNum = 4;		
		if(Graph.vertexNum>550){
			discardNum = 6;
		}
		//先从利用率高的服务器节点中选取一部分
		int i=0;
		for(;i<sortedKey.size()-discardNum;i++){
			int key = sortedKey.get(i);
			if(discard.containsKey(key) && discard.get(key)>1){
				continue;
			}
			if(!serverLocation.contains(key)){
				serverLocation.add(key);		
			}
		}
		
		List<Integer> temp = new ArrayList<Integer>();
		//将剔除的节点放入容器中标记起来
		for(;i<sortedKey.size();i++){
			int key = sortedKey.get(i);
			temp.add(key);
			if(discard.containsKey(key)){
				int n = discard.get(key);
				discard.put(key, n+1);
			}else{
				discard.put(key, 1);
			}
		}
		
		for(int m=0;m<temp.size()-2;m++){
			int in = Graph.nearbyNode(temp.get(m));
			if(in == -1){
				continue;
			}
			if(discard.containsKey(in) && discard.get(in)>0){//初级这里是0
				continue;//出现过3次都被抛弃了，那就不要他了
			}
			if(!serverLocation.contains(in)){
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
		
				
	}
		
	/////已针对中级的
	public static void updateServerLocation(List<Integer> serverLocation,List<Integer> sortedKey,Map<Integer,Integer> discard,List<Integer> unComplement){
		int number  = serverLocation.size();
		serverLocation.clear();	
		
		//先从利用率高的服务器节点中选取一部分
		int discardNum = 4;		
		if(Graph.vertexNum>550){
			discardNum = 6;
		}
		int i=0;
		for(;i<sortedKey.size()-discardNum;i++){
			int key = sortedKey.get(i);
			if(discard.containsKey(key) && discard.get(key)>1){
				continue;
			}
			if(!serverLocation.contains(key)){
				serverLocation.add(key);		
			}
		}
		
		List<Integer> temp = new ArrayList<Integer>();
		//将剔除的节点放入容器中标记起来
		for(;i<sortedKey.size();i++){
			int key = sortedKey.get(i);
			temp.add(key);
			if(discard.containsKey(key)){
				int n = discard.get(key);
				discard.put(key, n+1);
			}else{
				discard.put(key, 1);
			}
		}
		
		for(int m=0;m<temp.size()-2;m++){
			List<Integer> inRoung = Graph.nearbyNode5(temp.get(m));
			int in = inRoung.get(0);//获取大范围内的性价比最高的
			if(discard.containsKey(in) && discard.get(in)>0){//初级这里是0
				continue;//出现过3次都被抛弃了，那就不要他了
			}
			if(!serverLocation.contains(in)){
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
		
				
	}
	
	
	
	
/*	
	public static void upateServerLocationDemo3(List<Integer> serverLocation){
		
		//将为满足要求的节点先加入服务器
		int len = Graph.serverProvideCopy.size();
		//int idx = 1;//等于0或者1要进行斟酌
		for(int i=0;i<len;i++){
			
			int key = serverProvideCopy.get(i);
			//获取它附近的且性价比较高的节点
			List<Integer> nearBy = Graph.nearbyNode3(key);//获取多一点服务器节点
			for(Integer tp:nearBy){
				if(!serverLocation.contains(tp)){//由第二步保证了某个服务器节点的附近应该不会再有服务器节点
					serverLocation.add(tp);
					//idx++;
				}	
			}
		}			
		
	}*/
	
	public static void upateServerLocationDemo3(List<Integer> serverLocation,List<Map.Entry<Integer, Double>> sortQuality,List<Integer> drop,int serverIdx){
		
		//将为满足要求的节点先加入服务器
		while(serverIdx<sortQuality.size()){
			int key = sortQuality.get(serverIdx).getKey();
			//获取它附近的且性价比较高的节点
			List<Integer> nearList = Graph.nearbyNode5(key);
			int near = nearList.get(0);//这个就是大范围内的性价比最高的
			if(near!=-1 && !serverLocation.contains(near) && !drop.contains(key)){//由第二步保证了某个服务器节点的附近应该不会再有服务器节点
				serverLocation.remove((Integer)key);
				serverLocation.add(near);
				drop.add(key);
				break;
			}
			serverIdx++;
		}			
		
	}
	//先用上面的试试看
/*	public static void upateServerLocationDemo3(List<Integer> serverLocation,List<Map.Entry<Integer, Double>> sortQuality,List<Integer> drop,int serverIdx){
		
		//将为满足要求的节点先加入服务器
		while(serverIdx<sortQuality.size()){
			int key = sortQuality.get(serverIdx).getKey();
			//获取它附近的且性价比较高的节点
			int near  = Graph.nearbyNode(key);
			if(near!=-1 && !serverLocation.contains(near) && !drop.contains(key)){//由第二步保证了某个服务器节点的附近应该不会再有服务器节点
				serverLocation.remove((Integer)key);
				serverLocation.add(near);
				drop.add(key);
				break;
			}
			serverIdx++;
		}			
		
	}*/
	
	
	public static List<Map.Entry<Integer, Double>> ratioSort(Map<Integer,Integer> serverProvide,Map<Integer,Integer> serverSpend){
		Map<Integer,Double> quality = new HashMap<Integer,Double>();
		for(Map.Entry<Integer, Integer> entry:serverProvide.entrySet()){
			
			int key = entry.getKey();
			int provide = entry.getValue();
			int expend = serverSpend.get(key);
			double ratio = provide/(expend+0.0001);
			quality.put(key, ratio);
		}
		
		List<Map.Entry<Integer, Double>> sortQuality = new ArrayList<Map.Entry<Integer,Double>>(quality.entrySet());
		
		
		//将sortQuality按照从大到小排序
		Collections.sort(sortQuality,new Comparator<Map.Entry<Integer, Double>>(){

			@Override
			public int compare(Entry<Integer, Double> arg0, Entry<Integer, Double> arg1) {
				// TODO Auto-generated method stub
				double a = arg0.getValue()-arg1.getValue();
				if(a>0){
					return 1;//以前的版本是从小到大排序
				}else if(a<0){
					return -1;
				}else{
					return 0;
				}
			}
			
		});
		
		return sortQuality;
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
	
	public static int loopNum = 0;
	public static List<Integer> serverProvideCopy = new ArrayList<Integer>();
	public static List<String> minCost(){
		long start  = System.currentTimeMillis() ;	
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
		//保存每个服务对外提供的带宽
	    Map<Integer,Integer> serverProvide = new HashMap<Integer,Integer>();
		Map<Integer,Integer> serverSpend = new HashMap<Integer,Integer>();
	    Map<Integer,Integer> consumeGet = new HashMap<Integer,Integer>();
	       
	    //初始化服务器的位置,选用全部的节点作为服务器的位置
    	List<Integer> serverLocation = Graph.initializeServerLocation(consumeMap);//初始化服务器的位置
	    
	    List<Integer> unComplement = new ArrayList<Integer>();
    	Map<Integer,Integer> discard = new HashMap<Integer,Integer>();
    	
    	int standard = Graph.consume.size()/2;
    	///////////////////////
    	//第一层循环，找大概服务器的位置
    	if(Graph.vertexNum>550 || Graph.vertexNum<230){ //高级的和初级的从这里走
	    	int totalLoop1 = 120;
	    	int g1 = 0;
			while(serverLocation.size()>=standard  && g1<totalLoop1){								
				/////////////////////
				/////////////////////
				//将服务器和超级源点构成的边加入整个网络
				for(int m=0;m<serverLocation.size();m++){
					Graph.addEdge(s, serverLocation.get(m), Integer.MAX_VALUE, 0);	
				}
				//清理资源
				serverProvide.clear();
				consumeGet.clear();
				while(bellManford(s,t,serverProvide,consumeGet) && !Graph.isSuccess){
					
				}
				//将之前服务器和超级源点构成的边先从整个网络删除，一定要在这里删除，注意
				for(int m=serverLocation.size()-1;m>=0;m--){
					Graph.removeEdge(s, serverLocation.get(m));
					
				}	
				///////////////////////
				///////////////////////												
				//统计该次循环成功与否，如果成功，返回该次的费用，同时统计全局最优的费用,并且找到路径
				if(Graph.isSuccess){
					int serverNumber = serverLocation.size();
					int total = Graph.cost+serverNumber*Graph.servicePrices;
					if(total<Graph.minCost){					
						Graph.minCost = total;	
						//保存此时的服务器位置
						Graph.serverProvideCopy.clear();//先清理，再使用
						//保存该次循环的较优数据，以备下一层循环使用
						Graph.serverProvideCopy.addAll(serverLocation);
					}
				}		
				//对上面的结果进行分析，看哪个消费节点没满足条件，并且对每个服务器的供给量进行分析
				//unComplement.clear();//每次将这个清除掉
				Graph.anasys(consumeGet,consumeMap,unComplement);	
				List<Integer> sortedKey = mapSort(serverProvide);
				
				//如果执行完下面这一句，服务器节点个数少了，那么此后这个条件以后的都不再执行
				Graph.updateServerLocationSuper(serverLocation,sortedKey,discard,unComplement);							
				//执行相关清理工作，待下次循环使用
				Graph.isSuccess = false;			
				Graph.clear();	
				g1++;	
				
				
				//计时器
				long now = System.currentTimeMillis();
				if(now-start >=88000){
					break;
				}
			}
			
/*			long now1 = System.currentTimeMillis();
			System.out.println("第一层循环已经用的时间"+(now1-start));
			System.out.println("第一层循环的次数"+g1);
			System.out.println("第一层循环最后一次选用的服务器个数"+serverLocation.size());
			System.out.println("第一层循环最佳情况下选用的服务器个数"+Graph.serverProvideCopy.size());
			System.out.println("总费用为："+Graph.minCost);	*/
    	}else{

	    	int totalLoop1 = 120;
	    	int g1 = 0;
			while(serverLocation.size()>=standard  && g1<totalLoop1){								
				/////////////////////
				//将服务器和超级源点构成的边加入整个网络
				for(int m=0;m<serverLocation.size();m++){
					Graph.addEdge(s, serverLocation.get(m), Integer.MAX_VALUE, 0);	
				}
				//清理资源
				serverProvide.clear();
				consumeGet.clear();
				while(bellManford(s,t,serverProvide,consumeGet) && !Graph.isSuccess){
					
				}
				//将之前服务器和超级源点构成的边先从整个网络删除，一定要在这里删除，注意
				for(int m=serverLocation.size()-1;m>=0;m--){
					Graph.removeEdge(s, serverLocation.get(m));
					
				}	
				////////////////////////									
				//统计该次循环成功与否，如果成功，返回该次的费用，同时统计全局最优的费用,并且找到路径
				if(Graph.isSuccess){
					int serverNumber = serverLocation.size();
					int total = Graph.cost+serverNumber*Graph.servicePrices;
					if(total<Graph.minCost){					
						Graph.minCost = total;	
						//保存此时的服务器位置
						Graph.serverProvideCopy.clear();//先清理，再使用
						//保存该次循环的较优数据，以备下一层循环使用
						Graph.serverProvideCopy.addAll(serverLocation);
					}
				}		
				//对上面的结果进行分析，看哪个消费节点没满足条件，并且对每个服务器的供给量进行分析
				//unComplement.clear();//每次将这个清除掉
				Graph.anasys(consumeGet,consumeMap,unComplement);	
				List<Integer> sortedKey = mapSort(serverProvide);
				
				//如果执行完下面这一句，服务器节点个数少了，那么此后这个条件以后的都不再执行
				Graph.updateServerLocation(serverLocation,sortedKey,discard,unComplement);							
				//执行相关清理工作，待下次循环使用
				Graph.isSuccess = false;			
				Graph.clear();	
				g1++;	
				
				
				//计时器
				long now = System.currentTimeMillis();
				if(now-start >=88500){
					break;
				}
			}
			
			/*long now1 = System.currentTimeMillis();
			System.out.println("第一层循环已经用的时间"+(now1-start));
			System.out.println("第一层循环的次数"+g1);
			System.out.println("第一层循环最后一次选用的服务器个数"+serverLocation.size());
			System.out.println("第一层循环最佳情况下选用的服务器个数"+Graph.serverProvideCopy.size());
			System.out.println("总费用为："+Graph.minCost);	*/
    	}
		
		
		//第二层循环，服务器个数不变，纯替换看看
		if(Graph.vertexNum<500){
			serverLocation.clear();
			serverLocation.addAll(Graph.serverProvideCopy);
			
			List<Map.Entry<Integer, Double>> sortQuality = null;
			int g3 = 0,serverSize = Graph.serverProvideCopy.size();
			int totalLoop3 = 100,serverIdx = -1;//注意为啥要从-1开始，这个单步已经验证过了，没啥问题
			List<Integer> drop = new ArrayList<Integer>();
			
			while(g3<totalLoop3 && serverIdx<serverSize){
			
				long now4 = System.currentTimeMillis();
				if(now4-start >=87500){
					break;
				}
				
				//将服务器和超级源点构成的边加入整个网络
				for(int m=0;m<serverLocation.size();m++){
					Graph.addEdge(s, serverLocation.get(m), Integer.MAX_VALUE, 0);	
				}
				
				serverProvide.clear();
				serverSpend .clear();
				consumeGet.clear();
				while(bellManford(s,t,serverProvide,serverSpend,consumeGet) && !Graph.isSuccess){		
				
				}	
				//将之前服务器和超级源点构成的边先从整个网络删除，一定要在这里删除，注意
				for(int m=serverLocation.size()-1;m>=0;m--){
					Graph.removeEdge(s, serverLocation.get(m));
				}	
				///////////////////////
				///////////////////////
				
				if(sortQuality == null){
					sortQuality = Graph.ratioSort(serverProvide, serverSpend);
				
				}
				////////////////////////
				//统计该次循环成功与否，如果成功，返回该次的费用，同时统计全局最优的费用
				if(Graph.isSuccess){
					int serverNumber = serverLocation.size();
					int total = Graph.cost+serverNumber*Graph.servicePrices;
					if(total<Graph.minCost){					
						Graph.minCost = total;	
						//保存此时的服务器位置
						Graph.serverProvideCopy.clear();//先清理，再使用
						//保存该次循环的较优数据，以备下一层循环使用
						Graph.serverProvideCopy.addAll(serverLocation);
								
						//如果成功更新服务器的位置
						Graph.upateServerLocationDemo3(serverLocation,sortQuality,drop,serverIdx);
					}else{
				
						serverLocation.clear();
						serverLocation.addAll(Graph.serverProvideCopy);//serverLocation恢复到上次最佳的状态
						serverIdx++;					
						Graph.upateServerLocationDemo3(serverLocation,sortQuality,drop,serverIdx);
				
					}
				}else{
						
					serverLocation.clear();
					serverLocation.addAll(Graph.serverProvideCopy);//serverLocation恢复到上次最佳的状态
					serverIdx++;
				
					Graph.upateServerLocationDemo3(serverLocation,sortQuality,drop,serverIdx);
				
				
				}
				
				//执行相关清理工作，待下次循环使用
				Graph.isSuccess = false;			
				Graph.clear();	
				g3++;
			}
			
/*			long now4 = System.currentTimeMillis();
			System.out.println("第二层循环已经用的时间"+(now4-start));
			System.out.println("第二层循环的次数"+g3);
			System.out.println("第二层循环的最小费用："+Graph.minCost);*/
		}
	
																					
		/////////////////////////
		///////////////////////
		////////////////////
		//第三层循环，找精确服务器的数量	
		int times = Graph.serverProvideCopy.size()-1;		
		int g2 = 0;
		int totalLoop2 = 220;	
		while(times>=0 && g2<totalLoop2){
			
			long now2 = System.currentTimeMillis();
			if(now2-start >=87300){
				break;
			}
			
			serverLocation.clear();
			serverLocation.addAll(serverProvideCopy);
			serverLocation.remove(times);	
			/////////////////////
			/////////////////////
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
			///////////////////////
			///////////////////////
			////////////////////////
			//统计该次循环成功与否，如果成功，返回该次的费用，同时统计全局最优的费用
			if(Graph.isSuccess){
				int serverNumber = serverLocation.size();
				int total = Graph.cost+serverNumber*Graph.servicePrices;
				if(total<Graph.minCost){					
					Graph.minCost = total;	
					//保存此时的服务器位置
					Graph.serverProvideCopy.clear();//先清理，再使用
					//保存该次循环的较优数据，以备下一层循环使用
					Graph.serverProvideCopy.addAll(serverLocation);
				}
			}
		
			times--;//运行到减少服务器的个数的时候，才开始计算减少times				
			//执行相关清理工作，待下次循环使用
			Graph.isSuccess = false;			
			Graph.clear();	
			g2++;
		}
		
		
/*		long now = System.currentTimeMillis();
		System.out.println("第三层循环已经用的时间"+(now-start));
		System.out.println("第三层循环的次数"+g2);
		System.out.println("第三层循环的最优服务器个数："+serverProvideCopy.size());			
		System.out.println("第三层循环后的最小费用为："+Graph.minCost);*/
	
		//////////////////////////////
		//////////////////////////////
		//第四层循环，用它所有的周边服务器去试一试。只是验证我的观点也行啊
		if(Graph.vertexNum<=500){
			List<Integer> loop4ServerCopy = new ArrayList<Integer>(serverProvideCopy);
			serverLocation.clear();
			serverLocation.addAll(loop4ServerCopy);
			List<Integer> nearByServer = null;
			int overall = 0;
			
			int contemporyMin = Integer.MAX_VALUE;
			int times2 = Graph.serverProvideCopy.size()-1;		
			int g4 = 0;
			int totalLoop4 = 250;	
			while(times2>=0 && g4<totalLoop4){
				
				long now2 = System.currentTimeMillis();
				if(now2-start >=87500){
					break;
				}
				
				/////////////////////
				/////////////////////
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
				///////////////////////
				///////////////////////
				////////////////////////
				//统计该次循环成功与否，如果成功，返回该次的费用，同时统计全局最优的费用
				if(Graph.isSuccess){
					int serverNumber = serverLocation.size();
					int total = Graph.cost+serverNumber*Graph.servicePrices;
					if(total<contemporyMin){	
						
						contemporyMin = total;
						loop4ServerCopy.clear();
						loop4ServerCopy.addAll(serverLocation);
						
						if(contemporyMin<Graph.minCost){	
							Graph.minCost = contemporyMin;
							//保存此时的服务器位置
							Graph.serverProvideCopy.clear();//先清理，再使用
							//保存该次循环的较优数据，以备下一层循环使用
							Graph.serverProvideCopy.addAll(serverLocation);	
						}
					}
				}
					
			
				
				if(nearByServer==null){
					int serverId = serverLocation.get(times2);
					nearByServer = Graph.nearbyNode3(serverId);//这里以前是nearbyNode3
				}
				
				if(overall<nearByServer.size() && overall<6){			
					
					serverLocation.remove(times2);
					serverLocation.add(times2,nearByServer.get(overall));	//在哪个位置删除，在哪个位子插入
					overall++;
				}else{
					serverLocation.clear();
					serverLocation.addAll(loop4ServerCopy);//保存上一次的最好结果，为下一步的服务器替换做准备工作		
				
					nearByServer=null;
					times2--;//运行到减少服务器的个数的时候，才开始计算减少times
					overall = 0;
				}
																
				//执行相关清理工作，待下次循环使用
				Graph.isSuccess = false;			
				Graph.clear();	
				g4++;
			}
			
			
/*			long now5 = System.currentTimeMillis();
			System.out.println("第四层循环已经用的时间"+(now5-start));
			System.out.println("第四层循环的次数"+g4);
			System.out.println("第四层循环的最小费用："+contemporyMin);
			System.out.println("第四层循环的最优服务器个数："+serverProvideCopy.size());*/
			
							
		}
		
		System.out.println("最终的最小费用为："+Graph.minCost);			
		//计算最后的实际路径
		for(int m=0;m<serverProvideCopy.size();m++){
			Graph.addEdge(s, serverProvideCopy.get(m), Integer.MAX_VALUE, 0);	
		}		
		while(bellManford(s,t) && !Graph.isSuccess){		
			
		}								
		//使用DFS保存实际路径
		Map<List<Integer>,Integer> result = new HashMap<List<Integer>,Integer>();
		finalResult.clear();
		Graph.searchFinalRoute(result,serverProvideCopy);
		Graph.findTrueRoute(finalResult, result, consumeMap);																		
		
		return finalResult;	
				
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
	
	
	public static List<Integer> mapSort(Map<Integer,Integer> serverProvide){
		
		//对每个服务器的供应量进行排序
		List<Integer> sortedKey = new ArrayList<Integer>();
		List<Map.Entry<Integer, Integer>> sortedServerProvide = new ArrayList<Map.Entry<Integer, Integer>>(serverProvide.entrySet());
		Collections.sort(sortedServerProvide,new Comparator<Map.Entry<Integer, Integer>>(){

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue()-o1.getValue();
			}
			
		});
		
		for(int i=0;i<sortedServerProvide.size();i++){
			int key = sortedServerProvide.get(i).getKey();
			sortedKey.add(key);
		}
		
		return sortedKey;
		
		
				
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
			if(Graph.vertexNum>=500){
				
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
					
					//维护一个邻接表
					if(Graph.map.containsKey(id1)){
						int band = Graph.map.get(id1);
						int pri = Graph.map2.get(id1);
						Graph.map.put(id1, band+left_band);
						Graph.map2.put(id1, pri+price);
					}else{
						Graph.map.put(id1, left_band);
						Graph.map2.put(id1, price);
					}
					
					if(Graph.map.containsKey(id2)){
						int band = Graph.map.get(id2);
						int pri = Graph.map2.get(id2);
						Graph.map.put(id2, band+left_band);
						Graph.map2.put(id2, pri+price);
					}else{
						Graph.map.put(id2, left_band);
						Graph.map2.put(id2, price);
					}
					
					i++;
					
				}
				//初始化部分修改
				for(Map.Entry<Integer, Integer> entry:map.entrySet()){
					int key = entry.getKey();
					int band = entry.getKey();
					int pri = Graph.map2.get(key);
					int quality = band/pri;
					Graph.map3.put(key, quality);
				}
			}else{
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
					if(Graph.map3.containsKey(id1)){
						int band = Graph.map3.get(id1);
						Graph.map3.put(id1, band+everybandPrice);
					}else{
						Graph.map3.put(id1, everybandPrice);
					}
					
					if(Graph.map3.containsKey(id2)){
						int band = Graph.map3.get(id2);
						Graph.map3.put(id2, band+everybandPrice);
					}else{
						Graph.map3.put(id2, everybandPrice);
					}
					
					i++;
					
				}
				
				
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
			
		
			
			//初始化部分修改
/*			for(Map.Entry<Integer, Integer> entry:map.entrySet()){
				int key = entry.getKey();
				int band = entry.getKey();
				int pri = Graph.map2.get(key);
				int quality = band/pri;
				Graph.map3.put(key, quality);
			}*/
							
	}
	 	 
	 
	public static List<Integer> initializeServerLocation(Map<Integer,Integer> consumeMap){	
	 	List<Integer> serverLocation = new ArrayList<Integer>();	 			
		for(Map.Entry<Integer, Integer> temp:consumeMap.entrySet()) { 		
		    serverLocation.add(temp.getKey());	
		}	
			
		return serverLocation;
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