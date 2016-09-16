package bayes;
import java.util.ArrayList;
import java.util.List;
	
    /**
	 * 图  邻接矩阵  开放路径  弗洛伊德算法
	 * 该类主要实现的是获取路径的功能，主要用到的算法是弗洛伊德算法和一些其他的小函数
	 * @author tang
	 * @Time 2014-05-26
	 */

public class Find_path {
	private static int INF=Integer.MAX_VALUE;
         //dist[i][j]=INF<==>no edges between i and j
	private int[][] dist;
         //the distance between i and j.At first,dist[i][j] is the weight of edge [i,j]  
	private int[][] path;  
	private List<Integer> result=new ArrayList<Integer>();
	
	/**
	 * 构造函数，定义路径矩阵
	 * @param size 矩阵长度
	 * @author tang
	 * @Time 2014-05-26
	 */
	
	public Find_path(int size){
		this.path=new int[size][size];
		this.dist=new int[size][size];
	}

	/**
	 * 该方法是找到最短路径，使用了弗洛伊德函数
	 * @param begin 开始节点
	 * @param end   结束节点
	 * @param matrix 矩阵
	 * @author tang
	 * @Time 2014-05-27
	 */
	
	public  void findCheapestPath(int begin,int end,int[][] matrix){
		floyd(matrix);
		result.add(begin);
		findPath(begin,end);
		result.add(end);
	}
	
	/**
	 * 该方法的主要功能就是获取开放路径的函数
	 * @param i 节点号
	 * @param j 节点号
	 * @author tang
	 * @Time 2014-05-27
	 */
	
	public void findPath(int i,int j){
		int k=path[i][j];
		if(k==-1)return;
		findPath(i,k);
		result.add(k);
		findPath(k,j);
	}
	
	/**
	 * 该方法是弗洛伊德算法，核心代码就是三个for循环
	 * @param matrix 图矩阵
	 * @author tang
	 * @Time 2014-05-27
	 */
	
	public  void floyd(int[][] matrix){
		int size=matrix.length;
		//initialize dist and path
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				path[i][j]=-1;
				dist[i][j]=matrix[i][j];
			}
		}
		for(int k=0;k<size;k++){
			for(int i=0;i<size;i++){
				for(int j=0;j<size;j++){
					if(dist[i][k]!=INF&& dist[k][j]!=INF&&dist[i][k]+dist[k][j]<dist[i][j]){//dist[i][k]+dist[k][j]>dist[i][j]-->longestPath
						dist[i][j]=dist[i][k]+dist[k][j];
						path[i][j]=k;
					}
				}
			}
		}
		
	}

	/**
	 * 该方法获取一个矩阵的。。。。。。。。。。。。
	 * @param begin 开始节点
	 * @param end   结束节点
	 * @param matrix1 邻接矩阵
	 * @return 一个整数
	 * @author tang
	 * @Time 2014-05-27
	 */
	
	public int get_dist(int begin, int end,int[][] matrix1) {
		// TODO Auto-generated method stub
		System.out.println("@get_dist@-> "+begin+"  "+end);
		findCheapestPath(begin,end,matrix1);
		return this.dist[begin][end];
	}
}
