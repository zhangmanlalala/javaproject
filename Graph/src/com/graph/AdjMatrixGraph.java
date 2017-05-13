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
		//������ͼ�����·�� ������㷨�ܺã�����Ҫ��ĸĶ�
		 /**
		  * 
		  * @param W1   ���뵥�۾���
		  * @param start  ������ʼ��
		  * @param end  �����յ�
		  * @return
		  */	 
		/* public static int[] dijkstra(int[][] W1, int start, int end) {  
			 	
		        boolean[] isLabel = new boolean[W1[0].length];// �Ƿ���  
		        int[] indexs = new int[W1[0].length];// ���б�ŵĵ���±꼯�ϣ��Ա�ŵ��Ⱥ�˳����д洢��ʵ������һ���������ʾ��ջ  
		        int i_count = -1;//ջ�Ķ���  
		        int[] distance = W1[start].clone();// v0���������̾���ĳ�ʼֵ  
		        int index = start;// �ӳ�ʼ�㿪ʼ  
		        int presentShortest = 0;//��ǰ��ʱ��̾���  
		 
		        indexs[++i_count] = index;// ���Ѿ���ŵ��±�����±꼯��  
		        isLabel[index] = true;  
		        
		          
		        while (i_count<W1[0].length) {  
		            // ��һ�������v0,��w[0][0]�ҵ�����v0����ĵ�  
		 
		            int min = Integer.MAX_VALUE;  
		            for (int i = 0; i < distance.length; i++) {  
		                if (!isLabel[i] && distance[i] != -1 && i != index) {  
		                    // �����������б�,����û�б����  
		                    if (distance[i] < min) {  
		                        min = distance[i];  
		                        index = i;// ���±��Ϊ��ǰ�±�  
		                    }  
		                }  
		            }  
		            if (index == end) {//�Ѿ��ҵ���ǰ���ˣ��ͽ�������  
		                break;  
		            }  
		            isLabel[index] = true;//�Ե���б��  
		            indexs[++i_count] = index;// ���Ѿ���ŵ��±�����±꼯��  
		            if (W1[indexs[i_count - 1]][index] == -1 
		                    || presentShortest + W1[indexs[i_count - 1]][index] > distance[index]) {  
		                // ���������û��ֱ�������������������·���������·��  
		                presentShortest = distance[index];  
		            } else {  
		                presentShortest += W1[indexs[i_count - 1]][index];  
		            }  
		 
		            // �ڶ�������distance�еľ������vi  
		            for (int i = 0; i < distance.length; i++) {  
		                // ���vi���Ǹ����бߣ���v0�������ľ����  
		                if (distance[i] == -1 && W1[index][i] != -1) {// �����ǰ���ɴ�����ڿɴ���  
		                    distance[i] = presentShortest + W1[index][i];  
		                } else if (W1[index][i] != -1 
		                        && presentShortest + W1[index][i] < distance[i]) {  
		                    // �����ǰ�ɴ�����ڵ�·������ǰ���̣�������ɸ��̵�·��  
		                    distance[i] = presentShortest + W1[index][i];  
		                }  
		 
		            }  
		        }  
		        //���ȫ���㶼�����꣬��distance�д洢���ǿ�ʼ�㵽����������·��  
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
		 
		 //��ʼ��ͼ
		
		 
		
		 public static void recursive(AdjMatrixGraph graph,List<Map.Entry<Integer, List<Integer>>> infoIds){
			 	
			    //����serverLocationλ��һֱ�ڱ仯������ÿ�θ�ֵǰ������һ�ε�ֵ���
			 	if(graph.serverLocation.size()>0){
			 		graph.serverLocation.clear();
			 	}
			 	
			 	
			 	//������С�������������Ҹ��·�����λ��
			 	int serverNumMin = minServerNum(graph,infoIds);
			 	
			 	//�����ҵ��ķ����������Ӧ��
				Map<Integer,Integer> maxProvide = findServerLocation(graph);
				
				System.out.println("the need of the Server number:   "+graph.serverLocation.size());
				System.out.println("the location of server:  "+graph.serverLocation.toString());
				System.out.println("=============================================");
				System.out.println("=============================================");
				System.out.println("=============================================");
				
		
				
				//ͳ��ÿ��������ʵ�ʵĹ�Ӧ��
				Map<Integer,Integer> serverRealProvide = new HashMap<Integer,Integer>();
				for(int i=0;i<consume.size();i++){		
					
					System.out.println("=======================================================");
		
					Map<List<Integer>,Integer> route = AdjMatrixGraph.findRoute(consume.get(i),graph,1,serverRealProvide);
					for(Map.Entry<List<Integer>, Integer> entry : route.entrySet()){
						System.out.println("ѡ���·����"+entry.getKey().toString() + "   ��·�����ĵĴ��� �� "+entry.getValue());
					}
					
				}
				
				for(int i=0;i<graph.serverLocation.size();i++){
					int serverId = graph.serverLocation.get(i);
					
					System.out.println("��������ţ�" + serverId + " �����ṩ��ʵ�ʴ��� ��  " +serverRealProvide.get(serverId) + "������ṩ�Ĵ���" + maxProvide.get(serverId) );
					double ratio = 1.0*serverRealProvide.get(serverId)/maxProvide.get(serverId); 
					System.out.println("������ ��" + ratio);
				}
				
			 
		 }
		 
		 //�ܷ��ط����������Ӧ�� 
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
				    System.out.println(location+ "�������ڵ�����Ӧ��== " +eachSum);
				    

				    
				}		
				return maxProvide;
						
		 }	 
		 
		 *//**
		  * ������С�������������Ҹ��·�����λ��
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
				System.out.println("--------------����ǰ--------------");  
				for (i = 0; i < infoIds.size(); i++) {  
				    String id = infoIds.get(i).toString();  
				    System.out.println(id);  
				}  
				// �Խڵ���ȸ�������
				Collections.sort(infoIds, new Comparator<Map.Entry<Integer, List<Integer>>>() {  
				    public int compare(Map.Entry<Integer, List<Integer>> o1,  
				            Map.Entry<Integer, List<Integer>> o2) {  
				        return ( o2.getValue().size()-o1.getValue().size());  
				    }  
				});  
				
				
				System.out.println("------total band-----" + totalBand);			
				System.out.println("--------------�����--------------"); 
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
			 
				//�����ѽڵ�consume_id��������������·��������ǰ          
				//System.out.println("�ҵ���·�����£�=========");
				List<List<Integer>> result = graph.searchRoute(graph.map,consumePoint.linkedVertexId,graph.serverLocation);
				for(List<Integer> tmp:result){
					System.out.println(tmp.toString());
				}
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
				System.out.println("�ҵ���·���������£�=========" + result.size());
				System.out.println("������·�����£�===========");
				for(List<Integer> tmp:result){
					System.out.println(tmp.toString());
				}
				
				
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
						route1_provide = (int)(ratio*route1_provide);//ÿ��ֻ�õ�һ���ı����������꣬�������������
					}
					
					present_band +=route1_provide;
					
					
					if(present_band>=consumePoint.need){//�������һ����Ҫ��
						int last_time_need = route1_provide-(present_band-consumePoint.need);
						//�����·��
						haveFindRoute.put(route1, last_time_need);
						
						if(serverRealProvide.containsKey(serverId)){
							int band_sum = last_time_need+serverRealProvide.get(serverId);
							serverRealProvide.put(serverId, band_sum);
						}else{
							serverRealProvide.put(serverId, last_time_need);
						}
						System.out.println("�Ѿ�����������-----------------------");
						System.out.println("����·���ϵ���С����" +route1_provide);
						System.out.println("ʵ���õ��Ĵ���  " +last_time_need);
						System.out.println("�Ѿ��õ���·����  " +route1.toString());
						
						//����֮�����
						for(int j=1;j<route1.size();j++){
							graph.adjmatrix[route1.get(j-1)][route1.get(j)] -= last_time_need ;				
						}
						break;
						
						
					}else{//����ȫ��
						
						haveFindRoute.put(route1, route1_provide);
						
						if(serverRealProvide.containsKey(serverId)){
							int band_sum = route1_provide+serverRealProvide.get(serverId);
							serverRealProvide.put(serverId, band_sum);
						}else{
							serverRealProvide.put(serverId, route1_provide);
						}
						//����֮�����
						for(int j=1;j<route1.size();j++){
							graph.adjmatrix[route1.get(j-1)][route1.get(j)] -= route1_provide ;				
						}
							
						//System.out.println("����·���ϵ���С����" +route1_provide);
						//System.out.println("�Ѿ��õ���·����  " +route1.toString());
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
			
			AdjMatrixGraph graph = AdjMatrixGraph.initializeGraph(data);
			
			//d��ÿ�����㰴�������������ȴ������ǰ��
			List<Map.Entry<Integer, List<Integer>>> infoIds = AdjMatrixGraph.sortVertexIndegree(graph);
			
			//�����ѽڵ㰴����������
			System.out.println("����ǰ");
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
			System.out.println("�����");
			for(ConsumePoint tp:consume){
				System.out.println(tp.id);
			}
			
			*/
			
			
			        

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
	
	
	//������2
	
	
	


}	

/*//�Ұ����ִ�A��B�ʹ�B��A�����������װ��һ���Ŷ�����
class Edge{
	//����������ͼ����û�з������ԣ������յ㻻��λ��Ҳ��һ��������ע����ô�Ľ�
	//���������ˣ�����ʵ����һ������ͼ������һ��˫��ͼ���൱�ڴ�A��B���ʹ�B��A���ǿ��Եģ�������������Ӱ��
	public int start;//�ߵ���ʼλ��
	
	public int dest;  //�ߵ��յ�λ��
	
	public int left_band;  //�ߵ�ʣ�����
	
	public int price;//�����ѵ���
	
	

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
