package bayes;

import java.util.ArrayList;

/*服务推荐算法*/
public class Ser_recommend {
  private ArrayList<Node> rnodes=new ArrayList<Node>();
  private ArrayList<Wsnode> wsnodes=new ArrayList<Wsnode>();
  public void similar_match(int RE,Node n){
	  System.out.print("这里进行相似度匹配");
  }
  
  public void ser_recommend(Graph graph,ArrayList<Node> node_lst,Relation_table rel_table){
	 int re=0;
	 Wsnode ws;
	 for(int j=0;j<node_lst.size();++j){
		 rnodes.add(node_lst.get(j));
		 ws=new Wsnode(node_lst.get(j),1.0);
		 wsnodes.add(ws);
	 }
	 //System.out.print("rnodes的所有的节点\n");
	 for(int i=0;i<rnodes.size();++i){
		 System.out.print("节点"+rnodes.get(i).get_node_num()+" ");
		 graph.get_node_path(rnodes.get(i));//传入某个节点，作为参数
		 System.out.println();
	 }
  }
}
