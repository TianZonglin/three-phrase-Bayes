package bayes;

import java.util.ArrayList;

/**
 * 该类就是Thickening算法的主要实现文件
 * 该类中有互信息计算函数，还有其他计算函数
 * @author tang
 * @Time 2014-06-07
 */

public class Thickening {
	private Cut_set cs;
	
	/**
	 * 该方法是构造函数，就是实现了割集的初始化
	 * @author tang
	 * @Time 2014-06-07
	 */
	
	public Thickening(){
		cs=new Cut_set();//初始化割集对象。
	}
	
	/**
	 * 该方法是计算互信息的函数，主要是实现的就是计算节点之间的互信息
	 * @param n1 节点1
	 * @param n2 节点2 
	 * @param cutset 割集
	 * @return 浮点数的互信息
	 * @author tang
	 * @Time 2014-06-07
	 */
	
	public float mutual_node(Node n1,Node n2,ArrayList<Node> cutset){
		float val = 0;
		for(int i=0;i<n1.get_feature_count();++i){
			for(int j=0;j<n2.get_feature_count();++j){
				for(int k=0;k<cutset.get(0).get_feature_count();++k){
					val=0;
					val+=val;//这里互信息的计算结果是恒为0的。@@@@@@@@@@@@@@@@@@@@@
				}
			}
		}
		return val;
	}
	
	public void joint_probability(int sub1,int sub2,Node n1,Node n2,Relation_table rel_tab){
		System.out.println("这是计算联合概率的函数");
	}
	
	/**
	 * 该方法就是Thickening算法的主要的函数，
	 * 实现了基本的功能
	 * @param graph1 图
	 * @param node_lst 几点链表
	 * @param r S_class类对象
	 * @return 处理过的的图
	 * @author tang
	 * @Time 2014-06-07
	 */
	
	public Graph thicken(Graph graph1,ArrayList<Node> node_lst,SR_class r){
		float threshold=0;

		ArrayList<Node> cs_list=new ArrayList<Node>();
		System.out.println("求割集组数："+r.get_nodepair_list().size()+"\n得到的割集结果是:");
		//System.out.println("r.get_nodepair_list().get(i).get_node1().get_node_num()----->"+r.get_nodepair_list().get(0).get_node1().get_node_num());
		for(int i=0;i<r.get_nodepair_list().size();i++){
			int m,n;
			m=r.get_nodepair_list().get(i).get_node1().get_node_num();
			n=r.get_nodepair_list().get(i).get_node2().get_node_num();
			
			System.out.println("<"+r.get_nodepair_list().get(i).get_node1().get_node_num()+","+r.get_nodepair_list().get(i).get_node2().get_node_num()+">");

			cs_list=cs.D_separate(graph1,node_lst,r.get_nodepair_list().get(i).get_node1(),r.get_nodepair_list().get(i).get_node2());
		   
			System.out.println("result——>"+cs_list.size());
			
			if(cs_list.size()!=0){
				System.out.print("综上<"+r.get_nodepair_list().get(i).get_node1().get_node_num()+","+
			    		r.get_nodepair_list().get(i).get_node2().get_node_num()+">"+"的割集是："+cs_list.get(0).get_node_num()+" ");
			

			//for(int ii=0;ii<cs_list.size();ii++){System.out.print(cs_list.get(ii).get_node_num()+" ");}

				System.out.println();
				threshold=mutual_node(r.get_nodepair_list().get(i).get_node1(),r.get_nodepair_list().get(i).get_node2(),cs_list);
			    if(threshold>0.15f){//0.15f是经过计算估算的出的阈值大小。
			        graph1.add_edge1(m, n);
			    }
			}
		}
		return graph1;
	}
	
}
