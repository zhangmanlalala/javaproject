package com.graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * 
 * 
 * Execute类负责执行任务，和图本身分开
 * @author Administrator
 *
 */
public class Execute {
	
			
	/**
	 * 
	 * 使用DFS算法找路径
	 * @param map
	 * @param start
	 * @param end
	 * @param graph
	 * @return
	 */
	public static List<List<Integer>> searchRoute(Map<Integer,List<Integer>> map,int start,List<Integer> end,Vertex[] vertexlist){
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> lst = new ArrayList<Integer>();
		
		vertexlist[start].wasVisited = true;
		lst.add(start);
		dfs(map,result,start,end,lst,vertexlist);
		
		vertexlist[start].wasVisited = false;
		
		return result;
			
		
	}
	
	/**
	 * 
	 * 使用DFS算法找路径，这是迭代过程
	 * @param map
	 * @param result
	 * @param start
	 * @param end
	 * @param lst
	 * @param graph
	 */
	private static void  dfs(Map<Integer,List<Integer>> map,List<List<Integer>> result,int start,List<Integer> end,List<Integer> lst,Vertex[] vertexlist){
		
		if(lst.size()>0 && end.contains(lst.get(lst.size()-1)) ){
			List<Integer> re = new ArrayList<Integer>(lst);
			result.add(re);
			return ;
		}
		
		if(lst.size() >=10 || result.size()>=50){
			return ;
		}
/*		if(lst.size() >=7){
			return ;
		}
		*/
		
		List<Integer> temp = map.get(start);

		for(int i=0;i<temp.size();i++){
			
				if(vertexlist[temp.get(i)].wasVisited == false){
					lst.add(temp.get(i));	
					vertexlist[temp.get(i)].wasVisited= true;
				
					dfs(map,result,temp.get(i),end,lst,vertexlist);
					
					lst.remove(lst.size()-1);
					vertexlist[temp.get(i)].wasVisited= false;
				}
			
			
			
		}
		
	}
	
	
/*	private static void  bfs(Map<Integer,List<Integer>> map,List<List<Integer>> result,int start,List<Integer> end,List<Integer> lst,Vertex[] vertexlist){
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		vertexlist[start].wasVisited = true;
		
		while(!queue.isEmpty()){
			
			//从队列中删除下一个顶点
			int node = queue.remove();
			
			List<Integer> temp = map.get(start);
			
			for(int i=0;i<temp.size();i++){
				//对于每个未被标记的顶点
				if(vertexlist[temp.get(i)].wasVisited == false){
					
					vertexlist[temp.get(i)].wasVisited= true;
					lst.add(temp.get(i));
					if(end.contains(temp.get(i))){
						
					}
					
					queue.add(temp.get(i));
				}
			}

			
			
			
			
		}
		
		
		
		if(lst.size()>0 && end.contains(lst.get(lst.size()-1)) ){
			List<Integer> re = new ArrayList<Integer>(lst);
			result.add(re);
			return ;
		}
		
		if(lst.size() >=10 || result.size()>=50){
			return ;
		}
		if(lst.size() >=7){
			return ;
		}
		
		
		List<Integer> temp = map.get(start);

		for(int i=0;i<temp.size();i++){
			
				if(vertexlist[temp.get(i)].wasVisited == false){
					lst.add(temp.get(i));	
					vertexlist[temp.get(i)].wasVisited= true;
				
					dfs(map,result,temp.get(i),end,lst,vertexlist);
					
					lst.remove(lst.size()-1);
					vertexlist[temp.get(i)].wasVisited= false;
				}
			
			
			
		}
		
	}
	*/
	
	 public static boolean calculate(AdjMatrixGraph graph,int[][] adjmatrixCopy,List<Integer> serverLocation,Map<Integer,Integer> unsatisfied,Map<Integer,Integer> unsatisfiedInfo,int p){
		 	//Execute.totalMoney = 0;
			//统计每个服务器实际的供应量和所有服务器的实际供应量
		 	int eachActualProvide=0,actualProvide=0;
			ConsumePoint con= null;
			Map<Integer,Integer> statistic = new HashMap<Integer,Integer>();
			int statNum = 0; 
			for(int i=0;i<AdjMatrixGraph.consume.size();i++){	
				
				System.out.println("=======================================================");
				eachActualProvide=0;
				con = AdjMatrixGraph.consume.get(i);
				//传入的邻接矩阵的拷贝adjmatrixCopy很关键，看拷贝的对不对
				Map<List<Integer>,Integer> route = findRoute(con,graph,adjmatrixCopy,1,serverRealProvide,serverLocation);
				
				
				for(Map.Entry<List<Integer>, Integer> entry : route.entrySet()){
					System.out.println("选择的路径："+entry.getKey().toString() + "   该路径消耗的带宽 ： "+entry.getValue());	
					eachActualProvide += entry.getValue();
				}
				
				actualProvide += eachActualProvide;
				System.out.println("消费节点"+con.getId()+ " 实际满足的带宽：  " + eachActualProvide + "  应该满足的带宽： "+con.need);
				if(eachActualProvide<con.need){
					
					if(unsatisfied.containsKey(con.getId())){
						int num0 = unsatisfied.get(con.getId())+1;
						unsatisfied.put(con.getId(), num0);
					}else{
						unsatisfied.put(con.getId(), 1);
					}										
					//统计未满足要求的消费者节点中出现次数最多的，很关键
					if(!unsatisfiedInfo.containsKey(con.getId())){
						for(Map.Entry<List<Integer>, Integer> entry : route.entrySet()){
							
							//System.out.println("选择的路径："+entry.getKey().toString() + "   该路径消耗的带宽 ： "+entry.getValue());	
							
							List<Integer> contemp = entry.getKey();//list
							//针对一个消费节点con，统计每条线路上出现次数最多的顶点,终点是服务器不用统计
							for(int h=0;h<contemp.size()-1;h++){
								if(statistic.containsKey(contemp.get(h))){
									statNum = statistic.get(contemp.get(h));
									statistic.put(contemp.get(h),statNum+1);
								}else{
									statistic.put(contemp.get(h),1);
								}
							}	
							
						}
						
						
						//对统计结果找出出现次数最多的节点
						int idx = 0,Maxnum = -1;
						for(Map.Entry<Integer, Integer> tp :statistic.entrySet()){
							if(tp.getValue() >Maxnum){
								Maxnum = tp.getValue();
								idx = tp.getKey();//存放出现次数最多的节点
							}
							
						}
						if(route.size() >0 && (Maxnum!=-1) ){
							unsatisfiedInfo.put(con.getId(), idx);
						}else{
							unsatisfiedInfo.put(con.getId(), con.getLinkedVertexId());
						}
					}
					
					
					
					System.out.println("消费节点  "+con.getId()+" 未能满足要求，进行下一次循环");
					return false;
						
					
				}
	
			}
			

			Execute.totalMoney += (graph.servicePrices*p);
			if(Execute.totalMoney < minExpenditure){
				minExpenditure = Execute.totalMoney;
				serverNum = p;
			}
			System.out.println("==========================================");
			System.out.println("==========================================");
			System.out.println("已经满足所有消费节点的需求了，共花费" + minExpenditure +  "继续迭代，寻找最优");
			System.out.println("==========================================");
			System.out.println("==========================================");
			return true;
			
			
			
					 
	 }
	 
	 
	 //重载方法，用于第二层迭代逻辑
	 public static int calculate(AdjMatrixGraph graph,int[][] adjmatrixCopy,Map<Integer,Integer> serverRealProvide,List<Integer> serverLocation,int p){
		 	//Execute.totalMoney = 0;
			//统计每个服务器实际的供应量和所有服务器的实际供应量
		 	int eachActualProvide=0,actualProvide=0;
			ConsumePoint con= null;
			for(int i=0;i<AdjMatrixGraph.consume.size();i++){	
				
				System.out.println("=======================================================");
				eachActualProvide=0;
				con = AdjMatrixGraph.consume.get(i);
				//传入的邻接矩阵的拷贝adjmatrixCopy很关键，看拷贝的对不对
				Map<List<Integer>,Integer> route = findRoute(con,graph,adjmatrixCopy,1,serverRealProvide,serverLocation);
				
				
				for(Map.Entry<List<Integer>, Integer> entry : route.entrySet()){
					System.out.println("选择的路径："+entry.getKey().toString() + "   该路径消耗的带宽 ： "+entry.getValue());	
					eachActualProvide += entry.getValue();
				}
				
				actualProvide += eachActualProvide;
				System.out.println("消费节点"+con.getId()+ " 实际满足的带宽：  " + eachActualProvide + "  应该满足的带宽： "+con.need);
	
			}
			
			if(actualProvide<AdjMatrixGraph.totalBand){
				System.out.println("==========================================");
				System.out.println("==========================================");
				System.out.println("该次迭代未能满足全部消费节点要求，开始准备下一次迭代-----");
				System.out.println("==========================================");
				System.out.println("==========================================");
			}else {
				Execute.totalMoney += (graph.servicePrices*p);
				if(Execute.totalMoney < minExpenditure){
					minExpenditure = Execute.totalMoney;
					serverNum = p;
				}
				System.out.println("==========================================");
				System.out.println("==========================================");
				System.out.println("已经满足所有消费节点的需求了，共花费" + minExpenditure +  "继续迭代，寻找最优");
				System.out.println("==========================================");
				System.out.println("==========================================");
			}
			
			return actualProvide;
					 
	 }
	 
	 public static int totalMoney = 0;
	 public static int minExpenditure = Integer.MAX_VALUE;
	 public static int serverNum = 0;
	 
	 /**
	  * 
	  * 选出下一步准备剔除掉的服务器
	  * @param serverRealProvide
	  * @param serverLocation
	  * @param actualProvide
	  * @return
	  */
	 public static int removeServerIdx(Map<Integer,Integer> serverRealProvide,List<Integer> serverLocation,int actualProvide){
		 	
		 	double min_provide=2.0;
			int min_provide_id = 0;
			for(int i=0;i<serverLocation.size();i++){
				int serverId = serverLocation.get(i);
				
				double ratio = 0;
				if(serverRealProvide.get(serverId)!=null){
					System.out.println("服务器编号：" + serverId + " 向外提供的实际带宽 ：  " +serverRealProvide.get(serverId) );
					ratio = 1.0*serverRealProvide.get(serverId)/actualProvide; 
				}else{
					System.out.println("服务器编号：" + serverId + " 没有向外提供能量 ，后面  ");
					ratio = 0;
				}
				System.out.println(" 服务器编号： " +serverId+ " 的带宽在总体实际供应中的比例"+ ratio);
				if(ratio<min_provide){
					min_provide = ratio;
					min_provide_id = serverId;
				}
			}
			
			return min_provide_id;
			
	 }
	 
	 public static List<Map.Entry<Integer, Integer>> sortedMap(Map<Integer,Integer> unsatisfied){
		 	List<Map.Entry<Integer, Integer>> sortedUnsatisfied = new ArrayList<Map.Entry<Integer, Integer>>(  
		 			unsatisfied.entrySet()); 
		 	
			System.out.println("-----------w为满足的服务器按次数排序---排序前--------------");  
			for (int i = 0; i < sortedUnsatisfied.size(); i++) {  
			    System.out.println(sortedUnsatisfied.get(i));  
			}  
			//排序
			Collections.sort(sortedUnsatisfied,new Comparator<Map.Entry<Integer, Integer>>(){

				@Override
				public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
					
					
					if(o1.getValue()>o2.getValue()){
						return -1;
					}else if(o1.getValue()<o2.getValue()){
						return 1;
					}else{
						return 0;
					}
				}
				
			}) ;
			
			return sortedUnsatisfied;
	 }
	 
	
	 
	 /**
	  * 
	  * @param graph
	  * @param infoIds
	  * @param maxProvide
	  */	 
	 public static void recursive(AdjMatrixGraph graph,List<Map.Entry<Integer, List<Integer>>> infoIds,List<Integer> serverLocation,Map<Integer,Double> LocationInfo){
		 	
		     //每次迭代前都需要拷贝一份邻接矩阵副本,这里的拷贝要实现深拷贝
			 int n = graph.adjmatrix.length;
			 int[][] adjmatrix = graph.adjmatrix;
			 int[][] adjmatrixCopy = new int[n][n];
		 	
			//存放服务器备选节点的索引
			int serverChangeIdx = 0;
		    int p = serverLocation.size();	    
		    
		    Map<Integer,Integer> unsatisfied = new HashMap<Integer,Integer>();
		    Map<Integer,Integer> unsatisfiedInfo = new HashMap<Integer,Integer>();
		    
		    
		    
		    
		    while(p <= AdjMatrixGraph.consume.size()){
		    	   	   
		    		//初始化收集该次迭代信息的容器
		    	    serverChangeIdx = p;
		    	    unsatisfied.clear();
		    	    unsatisfiedInfo.clear();
		    	    
				 	//第一层迭代逻辑：变更服务器的位置，这里层迭代20次，可以试情况而定
					for(int j=0;j<10;j++){
						
						System.out.println("=============================================");
						System.out.println("=============================================");
						System.out.println("=============================================");
						System.out.println("第一层迭代逻辑:  "+" 选用"+p+"个服务器--------"+"第 "+ j + "次变更服务器的位置");
						System.out.println("the need of the Server number:   "+p);
						System.out.println("the location of server:  "+serverLocation.toString());
						
														
						//实现剩余带宽矩阵的深拷贝
						for (int t = 0; t < n; t++) {       //实现深拷贝的方法！！！！！！！！！！！！ 
					           System.arraycopy(adjmatrix[t], 0, adjmatrixCopy[t], 0, adjmatrix[t].length); 
					           //adjmatrixCopy[i] = adjmatrix[i].clone();
					    }
				
						Execute.totalMoney = 0;//核心功能的函数，用于寻找路径和计算费用，注意：下面的函数会更新Execute.totalMoney
						boolean isComplement = calculate(graph,adjmatrixCopy,serverLocation,unsatisfied,unsatisfiedInfo,p);				
						
						List<Map.Entry<Integer, Integer>> sortedUnsatisfied = Execute.sortedMap(unsatisfied);
						
						
						if(isComplement){
							break;
						}else{//维持一个概率池
							
							for(int i=0;i<sortedUnsatisfied.size();i++){
								int sever = sortedUnsatisfied.get(i).getValue();
								
							}
							
						}
						
						
					

					}
					
					//为第二层迭代准备数据
				 	
				
					
					
					if(minExpenditure<Integer.MAX_VALUE){
						System.out.println("迭代成功，找到当前最优的解法，一共花费：" + minExpenditure);
						break;
					}else{
						System.out.println("迭代失败，请增大服务器的个数，继续迭代......................");
					}
					
					
					System.out.println("迭代过程终止=====================================");
					System.out.println("迭代过程终止=====================================");
					System.out.println("迭代过程终止=====================================");
					System.out.println("迭代过程终止=====================================");
					
					
					//更新服务器的个数,清空一些资源
					p++;
					serverLocation.clear();
					
					for (int k = 0; k < p; k++) {  
						Entry<Integer,List<Integer>> ent=infoIds.get(k); 
						int sortedVertex = ent.getKey();	    
						//System.out.println(sortedVertex+ "==" +ent.getValue().toString());
						serverLocation.add(sortedVertex);								
					}
					

					
					
					
					
		    }
		    
		    
		    
		    if(minExpenditure<Integer.MAX_VALUE){
		    	System.out.println("计算得到的全局最小费用为：" + Execute.minExpenditure);
		    	System.out.println("最后选用的服务器个数为："+Execute.serverNum);
		    }else{
		    	System.out.println("所有的计算过程结束，没有找到合适解");
		    }
		    
			
					
		 
	 }
	 
	 //能返回服务器的最大供应量 
	 public static int findServerMaxProvide(AdjMatrixGraph graph,Integer serverId){		
				
			int eachSum = 0;  
			
			List<Integer> relation =  graph.map.get(serverId);

		    for(Integer tp:relation){
		    	eachSum  += graph.adjmatrix[serverId][tp];
		    	
		    }

			//System.out.println(serverId+ "服务器节点的最大供应量== " +eachSum);
			    	
			return eachSum;
					
	 }
	 
	 
	 /**
	  * 初始化服务器的位置，返回服务器位置
	  * 
	  * @param graph
	  * @param infoIds
	  * @return
	  */
	 public static List<Integer> initializeServerLocation(AdjMatrixGraph graph,List<Map.Entry<Integer, List<Integer>>> infoIds,int a){	
		 	List<Integer> serverLocation = new ArrayList<Integer>();
			int i = 0,sum = 0;
			for (; i < infoIds.size(); i++) {  
			    Entry<Integer,List<Integer>> ent=infoIds.get(i); 
			    int sortedVertex = ent.getKey();
			    List<Integer> relation = ent.getValue();
			    
			    for(Integer tp:relation){
			    	sum += graph.adjmatrix[sortedVertex][tp];
			    	
			    }
			    System.out.println(sortedVertex+ "==" +ent.getValue().size()); 
			    System.out.println("================" + sum);
			    serverLocation.add(sortedVertex);
			    
			    if(sum>=a*AdjMatrixGraph.totalBand){
			    	break;
			    }
			    
			}
			
			return serverLocation;
	 }
	 /**
	  * 计算每个顶点的入度
	  * @param graph
	  * @return
	  */
	 public static List<Map.Entry<Integer, List<Integer>>> sortVertexIndegree(AdjMatrixGraph graph){
		 	int i=0 ;			
			List<Map.Entry<Integer, List<Integer>>> infoIds = new ArrayList<Map.Entry<Integer, List<Integer>>>(  
					graph.map.entrySet());  
			System.out.println("--------------排序前--------------");  
			for (i = 0; i < infoIds.size(); i++) {  
			    String id = infoIds.get(i).toString();  
			    System.out.println(id);  
			}  
			// 对节点入度个数和带宽比例排序
			Collections.sort(infoIds, new Comparator<Map.Entry<Integer, List<Integer>>>() {  
			    public int compare(Map.Entry<Integer, List<Integer>> o1,  
			            Map.Entry<Integer, List<Integer>> o2) {  
			       // return ( o2.getValue().size()-o1.getValue().size()); 
			    	int o1Degree = o1.getValue().size();
			    	List<Integer> o1Nabor = o1.getValue();
			    	int o1band = 0;
			    	for(int i=0;i<o1Nabor.size();i++){
			    		o1band += graph.adjmatrix[o1.getKey()][o1Nabor.get(i)];
			    	}
			    	
			    	int o2Degree = o2.getValue().size();
			    	List<Integer> o2Nabor = o2.getValue();
			    	int o2band = 0;
			    	
			    	for(int i=0;i<o2Nabor.size();i++){
			    		o2band += graph.adjmatrix[o2.getKey()][o2Nabor.get(i)];
			    	}
			    	
			    	
			    	double p1 = 0.7*o1band/AdjMatrixGraph.totalProvideBand+0.3*o1Degree/AdjMatrixGraph.totalEdgeNum;
			    	double p2 = 0.7*o2band/AdjMatrixGraph.totalProvideBand+0.3*o2Degree/AdjMatrixGraph.totalEdgeNum;
			    	
			    	if(p1>p2){
			    		return -1;
			    	}else if(p2>p1){
			    		return 1;
			    	}else{
			    		return 0;
			    	}
			    	
			    	
			    }  
			});  
			
			
			System.out.println("------total band-----" + graph.totalBand);			
			System.out.println("--------------排序后--------------"); 
			i = 0;
			for (; i < infoIds.size(); i++) {  
			    Entry<Integer,List<Integer>> ent=infoIds.get(i); 
			    int sortedVertex = ent.getKey();	    
			    System.out.println(sortedVertex+ "==" +ent.getValue().toString());
			}
			
			return infoIds;
	 }
	 
	 /**
	  * 
	  * 寻找消费节点到所有服务器节点的路径，要保证每次传入的邻接矩阵adjmatrixCopy都是新的
	  * @param consumePoint
	  * @param graph
	  * @param ratio
	  * @param serverRealProvide
	  * @return
	  */
	 
	 
	 public static Map<List<Integer>,Integer> findRoute(ConsumePoint consumePoint,AdjMatrixGraph graph,int[][] adjmatrixCopy,double ratio,List<Integer> serverLocation){
		 	Map<List<Integer>,Integer> haveFindRoute = new HashMap<List<Integer>,Integer>();
		 
			//找消费节点consume_id到各个服务器的路径，排序前          
			//这里传入的graph.vertexlist被修改了，要单步运行看方法执行完后vertexlist有没有恢复
			List<List<Integer>> result = searchRoute(graph.map,consumePoint.linkedVertexId,serverLocation,graph.vertexlist);//重点看的地方
/*			for(Vertex tmp:graph.vertexlist){
				if(tmp.isWasVisited() == true){
					System.out.println("出问题了！！！！！！！！！！！！！！！！========================================================");
				}
			}*/
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
/*				System.out.println("找到的路径个数如下：=========" + result.size());
			System.out.println("排序后的路径如下：===========");
			for(List<Integer> tmp:result){
				System.out.println(tmp.toString());
			}*/
			
			
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
					int s1 = adjmatrixCopy[route1.get(j-1)][route1.get(j)];
					if( s1 <route1_provide){
						route1_provide = s1;
					}
					if(route1_provide == 0){
						break;
					}
				}
				
				//处理服务器节点就在消费节点临界节点的情况：
				if(route1.size() == 1){
					route1_provide = consumePoint.need;
		     	}else{
		     		if(route1_provide>10){//这个参数可以后面设置
		     			route1_provide = (int)(ratio*route1_provide);//每次只用掉一定的比例，不用完，给后面留点余地
		     		}
		     	}
				
				
				//如果route1_provide==0，直接进行下一次循环
				if(route1_provide == 0){  
					//haveFindRoute.put(route1, 0);  			
	     			continue;     			
	     		}
	     		
				
				
				
				present_band +=route1_provide;
				
				
				if(present_band>=consumePoint.need){//减掉最后一次需要的
					int last_time_need = route1_provide-(present_band-consumePoint.need);
					//加入该路径
					haveFindRoute.put(route1, last_time_need);
					
					/*System.out.println("已经能满足需求：-----------------------");
					System.out.println("单条路径上的最小带宽：" +route1_provide);
					System.out.println("实际用掉的带宽：  " +last_time_need);
					System.out.println("已经用掉的路径：  " +route1.toString());
					*/
					//用完之后减掉
					for(int j=1;j<route1.size();j++){
						adjmatrixCopy[route1.get(j-1)][route1.get(j)] -= last_time_need ;				
						Execute.totalMoney  += graph.prices[route1.get(j-1)][route1.get(j)]*last_time_need;
					}
					
					break;
					
					
				}else{//减掉全部
					
					haveFindRoute.put(route1, route1_provide);
					
					//用完之后减掉
					for(int j=1;j<route1.size();j++){
						adjmatrixCopy[route1.get(j-1)][route1.get(j)] -= route1_provide ;
						Execute.totalMoney  += graph.prices[route1.get(j-1)][route1.get(j)]*route1_provide;
					}
						
					//System.out.println("单条路径上的最小带宽：" +route1_provide);
					//System.out.println("已经用掉的路径：  " +route1.toString());
				}
				
				
			}
			
			
			
			return haveFindRoute;
			
			
	 }
	 
	 /**
	  * 
	  * 从txt文件中读入数据，返回字符串数组
	  * @param path
	  * @return
	  * @throws FileNotFoundException
	  */	 
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
			
			return data;
			
	 }
	 
	
	 public static void main(String[] args) throws FileNotFoundException {  

		String[] data = readData("C:\\Users\\Administrator\\Desktop\\case1.txt");
		
		AdjMatrixGraph graph = AdjMatrixGraph.initializeGraph(data);
		
		
		//d对每个顶点按入度数量排序，入度大的排在前面
		List<Map.Entry<Integer, List<Integer>>> infoIds = Execute.sortVertexIndegree(graph);
		
		//对消费节点按需求量排序
		System.out.println("排序前");
		for(ConsumePoint tp:AdjMatrixGraph.consume){
			System.out.println(tp.id);
		}
		AdjMatrixGraph.consume.sort(new Comparator<ConsumePoint>(){

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
		for(ConsumePoint tp:AdjMatrixGraph.consume){
			System.out.println(tp.id);
		}
		
	 	
	 	//返回最小服务器数，并且更新服务器位置
		List<Integer> serverLocation = Execute.initializeServerLocation(graph,infoIds,1);
		Map<Integer,Double> locationInfo = new HashMap<Integer,Double>();
		for(int i=0;i<serverLocation.size();i++){
			locationInfo.put(serverLocation.get(i), 1.0/serverLocation.size());
		}
		
	 	Execute.recursive(graph,infoIds,serverLocation,locationInfo);
		
		
	        

 }  
	 

}
