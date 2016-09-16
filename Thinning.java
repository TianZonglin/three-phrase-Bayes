package bayes;

import java.util.ArrayList;

/**
 * 该类主要就是实现Thinning算法的类，进一步精简图的边
 * @author tang
 * @Time 2014-06-06
 */

public class Thinning {
	private Cut_set cs;
	
	/**
	 * 该方法是构造函数，仅仅是处理割集初始化
	 * @author tang
	 * @Time 2014-06-06
	 */
	
	public Thinning(){
		cs=new Cut_set();
	}
	
	/**
	 * 该方法是Thinning类的主要的函数就是实现精简图的功能
	 * @param graph 图
	 * @param node_lst 节点链表
	 * @author tang
	 * @Time 2014-06-06
	 */
	
	public void thinning(Graph graph,ArrayList<Node> node_lst){
		float val=0;
		ArrayList<Node> cs_list=new ArrayList<Node>();
		Cut_set cs=new Cut_set();
		System.out.println("最后的割集结果是:");
		Node n1 = null,n2 = null;
		for(int i=0;i<graph.get_vertex_count();++i){
			for(int j=0;j<graph.get_vertex_count();++j){
				if(graph.get_matrix_value(i, j)==1){
					graph.delete_edge(i, j);//如果两个节点之间有边就删除这条边。
					for(int k=0;k<node_lst.size();++k){
						if(i==node_lst.get(k).get_node_num())
							n1=node_lst.get(k);
						if(j==node_lst.get(k).get_node_num())
							n2=node_lst.get(k);
					}
					//System.out.print("<"+n1.get_node_num()+","+n2.get_node_num()+">"+" ");
					cs_list=cs.D_separate(graph, node_lst, n1, n2);
                    if(cs_list.isEmpty()){
                    	//System.out.print("空集"+" ");
                    	val=0.2f;
                    	if(val>0.15f)
                    		System.out.print("成功"+" ");
                    }else{
                    	//如果最小割集不是空我们就计算条件概率。
                    	//调用mutual_node函数就可以了。
                    }
				}
			}
		}
		graph.print_digraph();
	}
	
	/**
	 * 
	 * @param n1
	 * @param n2
	 * @param cutset
	 * @return
	 */
	
	public float mutual_node(Node n1,Node n2,ArrayList<Node> cutset){
		float val = 0;
		float result=0;
		for(int p=0;p<cutset.size();++p){
		   for(int i=0;i<n1.get_feature_count();++i){
			  for(int j=0;j<n2.get_feature_count();++j){
				for(int k=0;k<cutset.get(p).get_feature_count();++k){
					val=0;
					val+=val;//这里互信息的计算结果是恒为0的。
				 }
			  }
		}
        result+=val; 
	 }
     return result;
   }
}
