package bayes;

import java.util.ArrayList;
import java.util.Collections;

  /**
   * 该类就是定义S_class和R_class对象的基本结构
   * 和主要的方法
   * @author tang
   * @Time 2014-06-05
   */

public class SR_class {
	private ArrayList<Node_pair> node_pair_lst;
	private Node_pair node_pair;
	
	/**
	 * 该方法是构造函数，功能只有一个就是初始化节点对链表
	 * @author tang
	 * @Time 2014-06-07
	 */
	
	public SR_class(){
		node_pair_lst=new ArrayList<Node_pair>();
	}
	
	/**
	 * 该方法是想节点对链表中添加节点
	 * @param node1 节点1
	 * @param node2 节点2
	 * @param node_mutual 节点互信息
	 * @author tang
	 * @Time 2014-06-07
	 */
	
	public void add_node_pair(Node node1,Node node2,float node_mutual){
		node_pair=new Node_pair(node1,node2,node_mutual);
		node_pair_lst.add(node_pair);
	}
	
	/**
	 * 该方法是想节点对链表中添加节点
	 * @param node1 节点1
	 * @param node2 节点2
	 * @author tang
	 * @Time 2014-06-07
	 */
	
	public void add_node_pair(Node_pair node_p){
		//node_pair=new Node_pair(node1,node2);
		node_pair_lst.add(node_p);
	}
	
	/**
	 * 该方法就是打印S_class类的对象的函数
	 * @author tang
	 * @Time 2014-06-07
	 */
	
	public void print_info(){
		int num1,num2;
		for(int i=0;i<node_pair_lst.size();++i){
			if(i!=0 && i%2==0)
				System.out.println();
			num1=node_pair_lst.get(i).get_node1().get_node_num();
			num2=node_pair_lst.get(i).get_node2().get_node_num();
			System.out.print("<"+num1+","+num2+">"+node_pair_lst.get(i).get_mutual()+" ");
		}
	}
	
	/**
	 * 该方法就是打印R_class类的对象的函数
	 * @author tang
	 * @Time 2014-06-07
	 */
	
	public void print_R_info(){
		int num1,num2;
		for(int i=0;i<node_pair_lst.size();++i){
			if(i!=0 && i%2==0)
				System.out.println();
			num1=node_pair_lst.get(i).get_node1().get_node_num();
			num2=node_pair_lst.get(i).get_node2().get_node_num();
			System.out.print("<"+num1+","+num2+">"+" ");
		}
	}

     /**
      * 该方法实现的功能就是对S_class和R_class进行排序
      * @author tang
	  * @Time 2014-06-07
      */
	
	public void sort_lst(){
		Node_comparator node_cmp=new Node_comparator();
		Collections.sort(node_pair_lst,node_cmp);
	}
	
	/**
	 * 该方法就是获取节点对链表
	 * @return 节点对链表
	 * @author tang
	 * @Time 2014-06-07
	 */
	
	public ArrayList<Node_pair> get_nodepair_list(){
		return node_pair_lst;
	}
}
