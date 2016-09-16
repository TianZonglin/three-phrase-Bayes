package bayes;

import java.util.ArrayList;

 /**
  * 该类主要就是图的算法，里面定义了图的一些节本数据成员
  * 同时定义实现了所有用到的图的算法，例如加边，减边，求路径。。。。
  * @author tang
  * @Time 2014-06-01
  */

public class Graph {
	
	/*说明：这里为了方便直观演示从而用数字矩阵但是最好用节点矩阵存储图这样方便后面的运算*/
	private int g_matrix[][];//邻接矩阵用于存储图。
	private int digraph_matrix[][];//存储有向图的邻接矩阵。
	private boolean[] hasFlag;
	private int ver_cnt;//保存节点的数量。
	private static int INF=Integer.MAX_VALUE;//最大值表示节点之间不可达。
	private ArrayList<String> res=new ArrayList<String>();//最后的所有的路径的结果。每一条路径的格式是如：0->2->1->3:7
	
    /**
     * 该方法是构造函数，主要实现的就是
     * 对图的初始化。
     * @param vertex_count 节点的个数
     * @author tang
     * @Time 2014-06-01
     */
    
	public Graph(int vertex_count){
		this.ver_cnt=vertex_count;
		g_matrix=new int[vertex_count][vertex_count];
		digraph_matrix=new int[vertex_count][vertex_count];
		hasFlag=new boolean[ver_cnt];
		for(int i=0;i<vertex_count;++i){//初始化邻接矩阵
			for(int j=0;j<vertex_count;++j){
				g_matrix[i][j]=INF;//表示节点之间不可达。
				digraph_matrix[i][j]=0;
			}
		}
		
	}
	
	/**
	 * 该方法的功能就是获取节点数
	 * @return 图中节点的个数
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public int get_vertex_count(){
		return ver_cnt;
	}
	
	/**
	 * 该方法就是向图中加边
	 * @param m 开始节点号
	 * @param n 结束节点号
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public void add_edge(int m,int n){
		g_matrix[m][n]=1;
		g_matrix[n][m]=1;
	}
	
	/**
	 * 该方法就是向有向图加边
	 * @param m 开始节点号
	 * @param n 结束节点号
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public void add_edge1(int m,int n){
		digraph_matrix[m][n]=1;
	}
	
	/**
	 * 该方法就是删除一条边
	 * @param m 开始节点号
	 * @param n 结束节点号
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public void delete_edge(int m,int n){
		digraph_matrix[m][n]=0;
	}
	
	/**
	 * 该方法就是打印图的函数
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public void print_graph(){
		for(int i=0;i<ver_cnt;i++){
			System.out.println();
			for(int j=0;j<ver_cnt;j++){
				if(g_matrix[i][j]==INF)
				    System.out.print("∞"+" ");
				else
					System.out.print(g_matrix[i][j]+" ");
			}
		}
	}
	
	/**
	 * 该方法就是打印有向图
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public void print_digraph(){
		for(int i=0;i<ver_cnt;i++){
			System.out.println();
			for(int j=0;j<ver_cnt;j++){
				System.out.print(digraph_matrix[i][j]+" ");
			}
		}
	}
	
	/**
	 * 该方法实现的就是判断连个节点之间是否存在开放路径
	 * 要用到Find_path文件中函数
	 * @param begin 开始节点号
	 * @param end 结束节点号
	 * @return 是/否
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public boolean is_path(int begin,int end){

		Find_path find_path=new Find_path(ver_cnt);
		if(find_path.get_dist(begin, end, g_matrix)!=INF){
			return true;
		}else
			return false;
	}
	
	/**
	 * 该方法就是获取某个节点入度函数
	 * @param n 节点号
	 * @return 入度数
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public int inDegree(Node n){  
        int indegree=0;
        int k;
        k=n.get_node_num();
        for(int i=0;i<ver_cnt;++i)
        	indegree+=digraph_matrix[i][k];
        return indegree;
	}  
	
	/**
	 * 重载入度函数，节点入度
	 * @param k 节点号
	 * @return 入度
	 * @author tang
	 * @Time 2014-06-20
	 */
	
	public int inDegree(int k){
		int indegree=0;
		for(int i=0;i<digraph_matrix.length;++i)
			indegree+=digraph_matrix[i][k];
		return indegree;
	}
	
	/**
	 * 求出某个节点的出度
	 * @param k节点的编号
	 * @return 节点的出度的值
	 * @author tang
	 * @Time 2014-06-20
	 */
	public int outDegree(int k){
		int outdegree=0;
		for(int i=0;i<digraph_matrix.length;++i){
			outdegree+=digraph_matrix[k][i];
		}
		return outdegree;
	}
	
	/**
	 * 该方法就是获取两个节点之间的边的数值
	 * 如果是0是没有变，如果是1就有边
	 * @param i 节点号
	 * @param j 节点号
	 * @return 节点之间的数值
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public int get_matrix_value(int i,int j) {
		return digraph_matrix[i][j];
	}
	
	/**
	 * 该方法就是获取任意两个节点之间的路径	
	 * @param s源节点
	 * @param d目的节点
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public void getPaths(int s,int d,String path)
	{
		 hasFlag[s]=true;//源点已访问过. 
	     for(int i=0;i<ver_cnt;i++){
		   if (digraph_matrix[s][i]==0 || hasFlag[i]){//若无路可通或已访问过，则找下一个结点。
			continue;
		 }

		 if(i==d){//若已找到一条路径 
			res.add(path+"->"+d);//加入结果。
			//System.out.print("path="+path+" d="+d+"\n");
		    continue;
		  }
		 getPaths(i, d, path+"-"+i);//继续找
		 hasFlag[i]=false;		
	   }
	}
	
	/**
	 * 获取与一个点相连的所有路径的函数
	 * @param n节点号
	 */
	public void get_node_path(Node n){
		//char cha=(char)(n.get_node_num()+'0');
		String ch = String.valueOf(n.get_node_num());
		ArrayList<String> tmp_lst=new ArrayList<String>();
	    ArrayList<String> res_lst=new ArrayList<String>();
		String str;
		  for(int i=0;i<digraph_matrix.length;++i){
			  if(inDegree(i)==0){
				  for(int j=0;j<digraph_matrix.length;++j){
					if(j!=i && outDegree(j)==0){
						str=String.valueOf(i);
						getPaths(i,j,str);
						//System.out.print(" "+res);
						if(!res.isEmpty()){
							
							for(String e:res){
								res_lst.add(e);
							}
						}
						res.clear();//将res清空
					}
				  }
			  }
		    }
		  int flag=0;
		  for(int i=0;i<res_lst.size();++i){
			  //System.out.print(res_lst.get(i));
              String term = res_lst.get(i);
              term = term.replace("->", ",");
              term = term.replace("-", ",");

              //term=term.replace(' ', ',');
             // System.out.println("   "+term);
  
              String[] data = term.split(",");
              //System.out.println(">>>>>>>>>> "+data.length);
              if(data.length!=0){
            	  for(int ii=0;ii<data.length;ii++){
            		  //System.out.println(data[ii]+"----"+ch);
            		  if(data[ii].equals(ch)){flag=1;}//System.out.println(">>>>>>>>>> ");}
            	  }
              }
			  if(flag==1){
				  tmp_lst.add(res_lst.get(i));
			  }
			  flag = 0;
		  }
		  for(int i=0;i<tmp_lst.size();++i){
			  System.out.print(tmp_lst.get(i)+" ");
		  }
	}
	


	/**
	 * 该方法是打印路径
	 * @author tang
	 * @Time 2014-06-01
	 */
	
	public void print_path(){
		System.out.print("路径的节点是:");
		for(int i=0;i<res.size();++i)
			System.out.print(res.get(i)+" ");
	}
}
