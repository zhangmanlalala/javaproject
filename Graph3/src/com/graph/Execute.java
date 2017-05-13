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
 * Execute�ฺ��ִ�����񣬺�ͼ����ֿ�
 * @author Administrator
 *
 */
public class Execute {
	
			
	/**
	 * 
	 * ʹ��DFS�㷨��·��
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
	 * ʹ��DFS�㷨��·�������ǵ�������
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
			
			//�Ӷ�����ɾ����һ������
			int node = queue.remove();
			
			List<Integer> temp = map.get(start);
			
			for(int i=0;i<temp.size();i++){
				//����ÿ��δ����ǵĶ���
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
			//ͳ��ÿ��������ʵ�ʵĹ�Ӧ�������з�������ʵ�ʹ�Ӧ��
		 	int eachActualProvide=0,actualProvide=0;
			ConsumePoint con= null;
			Map<Integer,Integer> statistic = new HashMap<Integer,Integer>();
			int statNum = 0; 
			for(int i=0;i<AdjMatrixGraph.consume.size();i++){	
				
				System.out.println("=======================================================");
				eachActualProvide=0;
				con = AdjMatrixGraph.consume.get(i);
				//������ڽӾ���Ŀ���adjmatrixCopy�ܹؼ����������ĶԲ���
				Map<List<Integer>,Integer> route = findRoute(con,graph,adjmatrixCopy,1,serverRealProvide,serverLocation);
				
				
				for(Map.Entry<List<Integer>, Integer> entry : route.entrySet()){
					System.out.println("ѡ���·����"+entry.getKey().toString() + "   ��·�����ĵĴ��� �� "+entry.getValue());	
					eachActualProvide += entry.getValue();
				}
				
				actualProvide += eachActualProvide;
				System.out.println("���ѽڵ�"+con.getId()+ " ʵ������Ĵ���  " + eachActualProvide + "  Ӧ������Ĵ��� "+con.need);
				if(eachActualProvide<con.need){
					
					if(unsatisfied.containsKey(con.getId())){
						int num0 = unsatisfied.get(con.getId())+1;
						unsatisfied.put(con.getId(), num0);
					}else{
						unsatisfied.put(con.getId(), 1);
					}										
					//ͳ��δ����Ҫ��������߽ڵ��г��ִ������ģ��ܹؼ�
					if(!unsatisfiedInfo.containsKey(con.getId())){
						for(Map.Entry<List<Integer>, Integer> entry : route.entrySet()){
							
							//System.out.println("ѡ���·����"+entry.getKey().toString() + "   ��·�����ĵĴ��� �� "+entry.getValue());	
							
							List<Integer> contemp = entry.getKey();//list
							//���һ�����ѽڵ�con��ͳ��ÿ����·�ϳ��ִ������Ķ���,�յ��Ƿ���������ͳ��
							for(int h=0;h<contemp.size()-1;h++){
								if(statistic.containsKey(contemp.get(h))){
									statNum = statistic.get(contemp.get(h));
									statistic.put(contemp.get(h),statNum+1);
								}else{
									statistic.put(contemp.get(h),1);
								}
							}	
							
						}
						
						
						//��ͳ�ƽ���ҳ����ִ������Ľڵ�
						int idx = 0,Maxnum = -1;
						for(Map.Entry<Integer, Integer> tp :statistic.entrySet()){
							if(tp.getValue() >Maxnum){
								Maxnum = tp.getValue();
								idx = tp.getKey();//��ų��ִ������Ľڵ�
							}
							
						}
						if(route.size() >0 && (Maxnum!=-1) ){
							unsatisfiedInfo.put(con.getId(), idx);
						}else{
							unsatisfiedInfo.put(con.getId(), con.getLinkedVertexId());
						}
					}
					
					
					
					System.out.println("���ѽڵ�  "+con.getId()+" δ������Ҫ�󣬽�����һ��ѭ��");
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
			System.out.println("�Ѿ������������ѽڵ�������ˣ�������" + minExpenditure +  "����������Ѱ������");
			System.out.println("==========================================");
			System.out.println("==========================================");
			return true;
			
			
			
					 
	 }
	 
	 
	 //���ط��������ڵڶ�������߼�
	 public static int calculate(AdjMatrixGraph graph,int[][] adjmatrixCopy,Map<Integer,Integer> serverRealProvide,List<Integer> serverLocation,int p){
		 	//Execute.totalMoney = 0;
			//ͳ��ÿ��������ʵ�ʵĹ�Ӧ�������з�������ʵ�ʹ�Ӧ��
		 	int eachActualProvide=0,actualProvide=0;
			ConsumePoint con= null;
			for(int i=0;i<AdjMatrixGraph.consume.size();i++){	
				
				System.out.println("=======================================================");
				eachActualProvide=0;
				con = AdjMatrixGraph.consume.get(i);
				//������ڽӾ���Ŀ���adjmatrixCopy�ܹؼ����������ĶԲ���
				Map<List<Integer>,Integer> route = findRoute(con,graph,adjmatrixCopy,1,serverRealProvide,serverLocation);
				
				
				for(Map.Entry<List<Integer>, Integer> entry : route.entrySet()){
					System.out.println("ѡ���·����"+entry.getKey().toString() + "   ��·�����ĵĴ��� �� "+entry.getValue());	
					eachActualProvide += entry.getValue();
				}
				
				actualProvide += eachActualProvide;
				System.out.println("���ѽڵ�"+con.getId()+ " ʵ������Ĵ���  " + eachActualProvide + "  Ӧ������Ĵ��� "+con.need);
	
			}
			
			if(actualProvide<AdjMatrixGraph.totalBand){
				System.out.println("==========================================");
				System.out.println("==========================================");
				System.out.println("�ôε���δ������ȫ�����ѽڵ�Ҫ�󣬿�ʼ׼����һ�ε���-----");
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
				System.out.println("�Ѿ������������ѽڵ�������ˣ�������" + minExpenditure +  "����������Ѱ������");
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
	  * ѡ����һ��׼���޳����ķ�����
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
					System.out.println("��������ţ�" + serverId + " �����ṩ��ʵ�ʴ��� ��  " +serverRealProvide.get(serverId) );
					ratio = 1.0*serverRealProvide.get(serverId)/actualProvide; 
				}else{
					System.out.println("��������ţ�" + serverId + " û�������ṩ���� ������  ");
					ratio = 0;
				}
				System.out.println(" ��������ţ� " +serverId+ " �Ĵ���������ʵ�ʹ�Ӧ�еı���"+ ratio);
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
		 	
			System.out.println("-----------wΪ����ķ���������������---����ǰ--------------");  
			for (int i = 0; i < sortedUnsatisfied.size(); i++) {  
			    System.out.println(sortedUnsatisfied.get(i));  
			}  
			//����
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
		 	
		     //ÿ�ε���ǰ����Ҫ����һ���ڽӾ��󸱱�,����Ŀ���Ҫʵ�����
			 int n = graph.adjmatrix.length;
			 int[][] adjmatrix = graph.adjmatrix;
			 int[][] adjmatrixCopy = new int[n][n];
		 	
			//��ŷ�������ѡ�ڵ������
			int serverChangeIdx = 0;
		    int p = serverLocation.size();	    
		    
		    Map<Integer,Integer> unsatisfied = new HashMap<Integer,Integer>();
		    Map<Integer,Integer> unsatisfiedInfo = new HashMap<Integer,Integer>();
		    
		    
		    
		    
		    while(p <= AdjMatrixGraph.consume.size()){
		    	   	   
		    		//��ʼ���ռ��ôε�����Ϣ������
		    	    serverChangeIdx = p;
		    	    unsatisfied.clear();
		    	    unsatisfiedInfo.clear();
		    	    
				 	//��һ������߼��������������λ�ã���������20�Σ��������������
					for(int j=0;j<10;j++){
						
						System.out.println("=============================================");
						System.out.println("=============================================");
						System.out.println("=============================================");
						System.out.println("��һ������߼�:  "+" ѡ��"+p+"��������--------"+"�� "+ j + "�α����������λ��");
						System.out.println("the need of the Server number:   "+p);
						System.out.println("the location of server:  "+serverLocation.toString());
						
														
						//ʵ��ʣ������������
						for (int t = 0; t < n; t++) {       //ʵ������ķ��������������������������� 
					           System.arraycopy(adjmatrix[t], 0, adjmatrixCopy[t], 0, adjmatrix[t].length); 
					           //adjmatrixCopy[i] = adjmatrix[i].clone();
					    }
				
						Execute.totalMoney = 0;//���Ĺ��ܵĺ���������Ѱ��·���ͼ�����ã�ע�⣺����ĺ��������Execute.totalMoney
						boolean isComplement = calculate(graph,adjmatrixCopy,serverLocation,unsatisfied,unsatisfiedInfo,p);				
						
						List<Map.Entry<Integer, Integer>> sortedUnsatisfied = Execute.sortedMap(unsatisfied);
						
						
						if(isComplement){
							break;
						}else{//ά��һ�����ʳ�
							
							for(int i=0;i<sortedUnsatisfied.size();i++){
								int sever = sortedUnsatisfied.get(i).getValue();
								
							}
							
						}
						
						
					

					}
					
					//Ϊ�ڶ������׼������
				 	
				
					
					
					if(minExpenditure<Integer.MAX_VALUE){
						System.out.println("�����ɹ����ҵ���ǰ���ŵĽⷨ��һ�����ѣ�" + minExpenditure);
						break;
					}else{
						System.out.println("����ʧ�ܣ�������������ĸ�������������......................");
					}
					
					
					System.out.println("����������ֹ=====================================");
					System.out.println("����������ֹ=====================================");
					System.out.println("����������ֹ=====================================");
					System.out.println("����������ֹ=====================================");
					
					
					//���·������ĸ���,���һЩ��Դ
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
		    	System.out.println("����õ���ȫ����С����Ϊ��" + Execute.minExpenditure);
		    	System.out.println("���ѡ�õķ���������Ϊ��"+Execute.serverNum);
		    }else{
		    	System.out.println("���еļ�����̽�����û���ҵ����ʽ�");
		    }
		    
			
					
		 
	 }
	 
	 //�ܷ��ط����������Ӧ�� 
	 public static int findServerMaxProvide(AdjMatrixGraph graph,Integer serverId){		
				
			int eachSum = 0;  
			
			List<Integer> relation =  graph.map.get(serverId);

		    for(Integer tp:relation){
		    	eachSum  += graph.adjmatrix[serverId][tp];
		    	
		    }

			//System.out.println(serverId+ "�������ڵ�����Ӧ��== " +eachSum);
			    	
			return eachSum;
					
	 }
	 
	 
	 /**
	  * ��ʼ����������λ�ã����ط�����λ��
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
	  * ����ÿ����������
	  * @param graph
	  * @return
	  */
	 public static List<Map.Entry<Integer, List<Integer>>> sortVertexIndegree(AdjMatrixGraph graph){
		 	int i=0 ;			
			List<Map.Entry<Integer, List<Integer>>> infoIds = new ArrayList<Map.Entry<Integer, List<Integer>>>(  
					graph.map.entrySet());  
			System.out.println("--------------����ǰ--------------");  
			for (i = 0; i < infoIds.size(); i++) {  
			    String id = infoIds.get(i).toString();  
			    System.out.println(id);  
			}  
			// �Խڵ���ȸ����ʹ����������
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
			System.out.println("--------------�����--------------"); 
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
	  * Ѱ�����ѽڵ㵽���з������ڵ��·����Ҫ��֤ÿ�δ�����ڽӾ���adjmatrixCopy�����µ�
	  * @param consumePoint
	  * @param graph
	  * @param ratio
	  * @param serverRealProvide
	  * @return
	  */
	 
	 
	 public static Map<List<Integer>,Integer> findRoute(ConsumePoint consumePoint,AdjMatrixGraph graph,int[][] adjmatrixCopy,double ratio,List<Integer> serverLocation){
		 	Map<List<Integer>,Integer> haveFindRoute = new HashMap<List<Integer>,Integer>();
		 
			//�����ѽڵ�consume_id��������������·��������ǰ          
			//���ﴫ���graph.vertexlist���޸��ˣ�Ҫ�������п�����ִ�����vertexlist��û�лָ�
			List<List<Integer>> result = searchRoute(graph.map,consumePoint.linkedVertexId,serverLocation,graph.vertexlist);//�ص㿴�ĵط�
/*			for(Vertex tmp:graph.vertexlist){
				if(tmp.isWasVisited() == true){
					System.out.println("�������ˣ�������������������������������========================================================");
				}
			}*/
			//���ڽ�������
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
			
			//�����
/*				System.out.println("�ҵ���·���������£�=========" + result.size());
			System.out.println("������·�����£�===========");
			for(List<Integer> tmp:result){
				System.out.println(tmp.toString());
			}*/
			
			
			System.out.println("��ǰ���ѽڵ㣺" + consumePoint.id + " �������" +consumePoint.need+"============");
			
			//�õ���һ��·��
			int present_band = 0;
			for(int m=0;m<result.size();m++){
				List<Integer> route1 = result.get(m);
				
				//��ȡ������id
				int serverId = route1.get(route1.size()-1);
				
				//�ҵ���·��������ṩ�Ĵ���
				
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
				
				//����������ڵ�������ѽڵ��ٽ�ڵ�������
				if(route1.size() == 1){
					route1_provide = consumePoint.need;
		     	}else{
		     		if(route1_provide>10){//����������Ժ�������
		     			route1_provide = (int)(ratio*route1_provide);//ÿ��ֻ�õ�һ���ı����������꣬�������������
		     		}
		     	}
				
				
				//���route1_provide==0��ֱ�ӽ�����һ��ѭ��
				if(route1_provide == 0){  
					//haveFindRoute.put(route1, 0);  			
	     			continue;     			
	     		}
	     		
				
				
				
				present_band +=route1_provide;
				
				
				if(present_band>=consumePoint.need){//�������һ����Ҫ��
					int last_time_need = route1_provide-(present_band-consumePoint.need);
					//�����·��
					haveFindRoute.put(route1, last_time_need);
					
					/*System.out.println("�Ѿ�����������-----------------------");
					System.out.println("����·���ϵ���С����" +route1_provide);
					System.out.println("ʵ���õ��Ĵ���  " +last_time_need);
					System.out.println("�Ѿ��õ���·����  " +route1.toString());
					*/
					//����֮�����
					for(int j=1;j<route1.size();j++){
						adjmatrixCopy[route1.get(j-1)][route1.get(j)] -= last_time_need ;				
						Execute.totalMoney  += graph.prices[route1.get(j-1)][route1.get(j)]*last_time_need;
					}
					
					break;
					
					
				}else{//����ȫ��
					
					haveFindRoute.put(route1, route1_provide);
					
					//����֮�����
					for(int j=1;j<route1.size();j++){
						adjmatrixCopy[route1.get(j-1)][route1.get(j)] -= route1_provide ;
						Execute.totalMoney  += graph.prices[route1.get(j-1)][route1.get(j)]*route1_provide;
					}
						
					//System.out.println("����·���ϵ���С����" +route1_provide);
					//System.out.println("�Ѿ��õ���·����  " +route1.toString());
				}
				
				
			}
			
			
			
			return haveFindRoute;
			
			
	 }
	 
	 /**
	  * 
	  * ��txt�ļ��ж������ݣ������ַ�������
	  * @param path
	  * @return
	  * @throws FileNotFoundException
	  */	 
	 public static String[] readData(String path) throws FileNotFoundException{
			FileReader fileReader = new FileReader(path);
			
			Scanner sc = new Scanner(fileReader);
					
			String line1 = sc.nextLine();
			String[] str = line1.split(" ");		
			int totalLine = Integer.parseInt(str[1]);//���ûʲô��
			int consumeNum = Integer.parseInt(str[2]);
			String[] data = new String[totalLine+consumeNum+5];
			data[0] = line1;
			
			//��ʼ���뵽������
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
		
		
		//d��ÿ�����㰴�������������ȴ������ǰ��
		List<Map.Entry<Integer, List<Integer>>> infoIds = Execute.sortVertexIndegree(graph);
		
		//�����ѽڵ㰴����������
		System.out.println("����ǰ");
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
		System.out.println("�����");
		for(ConsumePoint tp:AdjMatrixGraph.consume){
			System.out.println(tp.id);
		}
		
	 	
	 	//������С�������������Ҹ��·�����λ��
		List<Integer> serverLocation = Execute.initializeServerLocation(graph,infoIds,1);
		Map<Integer,Double> locationInfo = new HashMap<Integer,Double>();
		for(int i=0;i<serverLocation.size();i++){
			locationInfo.put(serverLocation.get(i), 1.0/serverLocation.size());
		}
		
	 	Execute.recursive(graph,infoIds,serverLocation,locationInfo);
		
		
	        

 }  
	 

}
