package com.graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

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
	//param1表示路径的最大长度
	public static int param1 = 20;
	//param2表示找到路径的最大容量阈值，就停止
	public static int param2 = 80;
	
	private static void  dfs(Map<Integer,List<Integer>> map,List<List<Integer>> result,int start,List<Integer> end,List<Integer> lst,Vertex[] vertexlist){
		
		if(lst.size()>0 && end.contains(lst.get(lst.size()-1)) ){
			List<Integer> re = new ArrayList<Integer>(lst);
			result.add(re);
			return ;
		}
		
		if(lst.size() >=30 || result.size()>=120){
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
	 
	 //初始化图
	
	
	 public static int totalMoney = 0;
	 public static int minExpenditure = Integer.MAX_VALUE;
	 public static int serverNum = 0;
	 
	 /**
	  * 
	  * @param graph
	  * @param infoIds
	  * @param maxProvide
	  */
	 
	 public static void recursive(AdjMatrixGraph graph,List<Map.Entry<Integer, List<Integer>>> infoIds,Map<Integer,Integer> maxProvide){
		 	
		     //每次迭代前都需要拷贝一份邻接矩阵副本,这里的拷贝要实现深拷贝
			 int n = graph.adjmatrix.length;
			 int[][] adjmatrix = graph.adjmatrix;
			 int[][] adjmatrixCopy = new int[n][n];
		 	
			//存放服务器备选节点的索引
			int serverChangeIdx = 0;
		 	
			//serverLocation初始化
			List<Integer> serverLocation = new ArrayList<Integer>();
			for(Integer integer:maxProvide.keySet()){
				serverLocation.add(integer);
			}
		    Map<Integer,Integer> serverRealProvide = new HashMap<Integer,Integer>();
		    int p = serverLocation.size();
		    
		    while(p <= AdjMatrixGraph.consume.size()){
		    	   	
		    	
		    	    serverChangeIdx = p;
				 	//变更服务器的位置，这里层迭代10次，可以试情况而定
					for(int j=0;j<40;j++){
						
						System.out.println("=============================================");
						System.out.println("=============================================");
						System.out.println("=============================================");
						System.out.println("选用"+p+"个服务器--------"+"第 "+ j + "次变更服务器的位置");
						System.out.println("the need of the Server number:   "+p);
						System.out.println("the location of server:  "+serverLocation.toString());
		
						
						
						//实现剩余带宽矩阵的深拷贝
						for (int t = 0; t < n; t++) {                     //实现深拷贝的方法！！！！！！！！！！！！ 
					           System.arraycopy(adjmatrix[t], 0, adjmatrixCopy[t], 0, adjmatrix[t].length); 
					           //adjmatrixCopy[i] = adjmatrix[i].clone();
					    }
						
						//这里要查看拷贝的对不对，后面注释掉
						for(int r1=0;r1<n;r1++){
							for(int r2=0;r2<n;r2++){
								if(adjmatrix[r1][r2] != adjmatrixCopy[r1][r2]){
									System.out.println("数组深拷贝出问题了==================================");
								}
							}
						}
						
				
						serverRealProvide.clear();
						Execute.totalMoney = 0;
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
						}else if(actualProvide==AdjMatrixGraph.totalBand){
								Execute.totalMoney += graph.servicePrices*p;
								if(Execute.totalMoney < minExpenditure){
									minExpenditure = Execute.totalMoney;
									serverNum = p;
								}
								System.out.println("==========================================");
								System.out.println("==========================================");
								System.out.println("已经满足所有消费节点的需求了，共花费" + Execute.totalMoney +  "元，可以结束迭代了！！！！！！！");
								System.out.println("==========================================");
								System.out.println("==========================================");
						}
						
						
						//将实际供应量中最小的那个找出来，准备剔除掉
						double min_provide=2.0;
						int min_provide_id = 0;
						for(int i=0;i<serverLocation.size();i++){
							int serverId = serverLocation.get(i);
							
							double ratio = 0;
							if(serverRealProvide.get(serverId)!=null){
								System.out.println("服务器编号：" + serverId + " 向外提供的实际带宽 ：  " +serverRealProvide.get(serverId) + "   最大能提供的带宽  " + maxProvide.get(serverId) );
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
						
						//从serverLocation中将其剔除掉
						if(serverLocation.contains(min_provide_id)){
							serverLocation.remove((Integer)min_provide_id);			
						}
						
						
						//选下一个被选中的serverId很关键，真的很关键，后面多考虑
						//下一个被选中的serverId,加入之后维护好两个容器
						if(serverChangeIdx==infoIds.size()){
							System.err.println("在当前服务器节点个数不变的情况下，已经没有备用服务器节点位置了，跳出循环！！！！！！！！！！！！！");
							break;
						}
						int selectServerId = infoIds.get(serverChangeIdx++).getKey();
						serverLocation.add(selectServerId);//用两个容器存server，这里存在优化的可能
						maxProvide.remove(min_provide_id);
						int theIdMaxProvide = findServerMaxProvide(graph,selectServerId);
						maxProvide.put(selectServerId, theIdMaxProvide);
					}
					
					
					
					System.out.println("迭代过程终止=====================================");
					System.out.println("迭代过程终止=====================================");
					System.out.println("迭代过程终止=====================================");
					System.out.println("迭代过程终止=====================================");
					if(minExpenditure<Integer.MAX_VALUE){
						System.out.println("迭代成功，找到当前最优的解法，一共花费：" + minExpenditure);
						break;
					}else{
						System.out.println("迭代失败，请增大服务器的个数，继续迭代......................");
					}
					//更新服务器的个数,清空一些资源
					p = p+2;
					serverLocation.clear();
					maxProvide.clear();
					
					for (int k = 0; k < p; k++) {  
						Entry<Integer,List<Integer>> ent=infoIds.get(k); 
						int sortedVertex = ent.getKey();	    
						//System.out.println(sortedVertex+ "==" +ent.getValue().toString());
						serverLocation.add(sortedVertex);
						
						int serverMaxProvide = findServerMaxProvide(graph,sortedVertex);
						maxProvide.put(sortedVertex, serverMaxProvide);
						
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
	  * 初始化服务器的位置，返回服务器的最大供应量
	  * 
	  * @param graph
	  * @param infoIds
	  * @return
	  */
	 public static Map<Integer,Integer> initializeServerLocation(AdjMatrixGraph graph,List<Map.Entry<Integer, List<Integer>>> infoIds,int a){	
		 	Map<Integer,Integer> maxProvide = new HashMap<Integer,Integer>();
			int i = 0,sum = 0,eachSum=0;
			for (; i < infoIds.size(); i++) {  
			    Entry<Integer,List<Integer>> ent=infoIds.get(i); 
			    int sortedVertex = ent.getKey();
			    List<Integer> relation = ent.getValue();
			    
			    eachSum = 0;
			    for(Integer tp:relation){
			    	sum += graph.adjmatrix[sortedVertex][tp];
			    	eachSum += graph.adjmatrix[sortedVertex][tp];
			    	
			    }
			    System.out.println(sortedVertex+ "==" +ent.getValue().size()); 
			    System.out.println("================" + sum);
			    
			    maxProvide.put(sortedVertex, eachSum);
			    
			    if(sum>=a*AdjMatrixGraph.totalBand){
			    	break;
			    }
			    
			}
			
			return maxProvide;
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
	 
	 
	 public static Map<List<Integer>,Integer> findRoute(ConsumePoint consumePoint,AdjMatrixGraph graph,int[][] adjmatrixCopy,double ratio,Map<Integer,Integer> serverRealProvide,List<Integer> serverLocation){
		 	Map<List<Integer>,Integer> haveFindRoute = new HashMap<List<Integer>,Integer>();
		 
			//找消费节点consume_id到各个服务器的路径，排序前          
			//这里传入的graph.vertexlist被修改了，要单步运行看方法执行完后vertexlist有没有恢复
			List<List<Integer>> result = searchRoute(graph.map,consumePoint.linkedVertexId,serverLocation,graph.vertexlist);//重点看的地方
			for(Vertex tmp:graph.vertexlist){
				if(tmp.isWasVisited() == true){
					System.out.println("出问题了！！！！！！！！！！！！！！！！========================================================");
				}
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
					
					if(serverRealProvide.containsKey(serverId)){
						int band_sum = route1_provide+serverRealProvide.get(serverId);
						serverRealProvide.put(serverId, band_sum);
					}else{
						serverRealProvide.put(serverId, route1_provide);
					}
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

		String[] data = readData("C:\\Users\\Administrator\\Desktop\\Advanced\\3.txt");
		
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
		Map<Integer,Integer> maxProvide = Execute.initializeServerLocation(graph,infoIds,1);
	 	Execute.recursive(graph,infoIds,maxProvide);
		
		
	        

 }  
	 

}
