package bayes;

import java.util.ArrayList;
 /**
  * 该类主要功能就是定义节点的基本数据结构和
  * 主要的成员函数
  * @author tang
  * @Time 2014-06-08
  */

public class Node {

	private int node_num;//节点编号。
	private int feature_count;//节点内特征的数量。
	private Feature fea;      //特征。
	private ArrayList<Feature> feature_list=new ArrayList<Feature>();
	
	/**
	 * 该方法是构造函数，主要是实现对节点的初始化
	 * @param count 特征数量
	 * @param num 节点编号
	 * @author tang
	 * @Time 2014-06-08
	 */
	
	public Node(int count,int num){//构造函数:参数count是特征数量，num是节点号。
		this.feature_count=count;
		this.node_num=num;
		for(int i=0;i<count;++i){
			fea=new Feature(i);//创建一个新的特征数据。
			feature_list.add(fea);//将特征数据加入到特征链表中。
		}
	}
	
	/**
	 * 该方法就是打印特征的函数
	 * @author tang
	 * @Time 2014-06-08
	 */
	
	public void print_feature(){//打印特征的函数。
		for(int i=0;i<feature_list.size();++i){
			System.out.print(feature_list.get(i).get_data()+" ");
		}
	}
	
	/**
	 * 该方法就是获取特征数量的函数
	 * @return 特征数量
	 * @author tang
	 * @Time 2014-06-08
	 */
	
	public int get_feature_count(){//获取特征数量。
		return feature_count;
	}
	
	/**
	 * 该方法是获取节点号的函数
	 * @return 节点号
	 * @author tang
	 * @Time 2014-06-08
	 */
    public int get_node_num(){//获取节点编号的函数。
    	return node_num;
    }
}
