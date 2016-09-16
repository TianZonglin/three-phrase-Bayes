package bayes;

import java.util.ArrayList;

/**
 * 该类定义的是割集的全部内容，定义类入节点和出节点，
 * 主要的方法是交集函数，并集函数，D-分离函数
 * @author tang
 * @Time   2014-05-29
 */

public class Cut_set {
	/*定义出入节点*/
	private ArrayList<Node> inNode1=new ArrayList<Node>();
	private ArrayList<Node> inNode2=new ArrayList<Node>();
	private ArrayList<Node> outNode1=new ArrayList<Node>();
	private ArrayList<Node> outNode2=new ArrayList<Node>();
	
    /**
     * 该方法是交集函数，主要是实现的是两个ArrayList求交集
     * @param n_list1 集合1
     * @param n_list2 集合2
     * @return 两个集合的交集
     * @author tang
     * @Time 2014-05-29
     */
	
	public ArrayList<Node> retainAll(ArrayList<Node> n_list1,ArrayList<Node> n_list2){
		int flag;
		/*如果有一个为空则返回空集*/
		if(n_list1==null || n_list2==null)
			return null;
		else{
		/*通过两个for循环判断两个链表中相同的元素并合并到一起*/
		   for(int i=n_list1.size()-1;i>=0;i--){
			  flag=0;
			  for(int j=0;j<n_list2.size();++j){
				  if(n_list1.get(i).get_node_num()==n_list2.get(j).get_node_num())
					  flag=1;
			  }
			  if(flag==0){
				  n_list1.remove(i);
			  }
		  }
		  return n_list1;//返回交集。
		}
	}
    
	/**
	 * 该方法是并集函数，实现的主要功能是求两个ArrayList链表并集
	 * @param n_list1
	 * @param n_list2
	 * @return 两个集合的并集
	 * @author tang
	 * @Time 2014-05-29
	 */
	
	public ArrayList<Node> addAll(ArrayList<Node> n_list1,ArrayList<Node> n_list2){
		int flag;
		if(n_list1==null && n_list2!=null)
			return n_list2;
		else if(n_list1!=null && n_list2==null)
			return n_list1;
		else if(n_list1==null && n_list2==null)
			return null;
		else {
			for(int i=0;i<n_list1.size();++i){
				flag=1;
				for(int j=0;j<n_list2.size();++j){
					if(n_list1.get(i).get_node_num()==n_list2.get(j).get_node_num())
						flag=0;
				}
				if(flag==1)
					n_list2.add(n_list1.get(i));
			}
			return n_list2;
		}
		
	}
	
	/**
	 * 该方法是求D-分离的方法，主要是实现的功能就是
	 * 利用图和给定节点求出其交集
	 * @param graph
	 * @param node_lst
	 * @param n1
	 * @param n2
	 * @return 割集对象(节点链表)
	 * @author tang
	 * @Time 2014-05-29
	 */
	
    public ArrayList<Node> D_separate(Graph graph,ArrayList<Node> node_lst,Node n1,Node n2){
    	ArrayList<Node> cutset=new ArrayList<Node>();//初始化节点割集
    	for(int i=0;i<graph.get_vertex_count();++i){
    		for(int j=0;j<graph.get_vertex_count();++j){
    			if(graph.get_matrix_value(i,j)==1){
    				//System.out.print("<"+i+","+j+">"+" ");
    				if(i==n1.get_node_num()){
        				for(int k=0;k<node_lst.size();++k){
        					if(j==node_lst.get(k).get_node_num())
        						outNode1.add(node_lst.get(k));
        				}
    				}
    				if(j==n1.get_node_num()){
        				for(int k=0;k<node_lst.size();++k){
        					if(i==node_lst.get(k).get_node_num())
        						inNode1.add(node_lst.get(k));
        				}
    				}
    				if(i==n2.get_node_num()){
        				for(int k=0;k<node_lst.size();++k){
        					if(j==node_lst.get(k).get_node_num())
        						outNode2.add(node_lst.get(k));
        				}
    				}
    				if(j==n2.get_node_num()){
        				for(int k=0;k<node_lst.size();++k){
        					if(i==node_lst.get(k).get_node_num())
        						inNode2.add(node_lst.get(k));
        				}
    				}
    			}
    		}
    	}
        
    	/*
    	System.out.print("outNode1=");
    	for(int i=0;i<outNode1.size();++i){
    		System.out.print(outNode1.get(i).get_node_num()+" ");
    	}
    	System.out.print("inNode1=");
    	for(int i=0;i<inNode1.size();++i)
    		System.out.print(inNode1.get(i).get_node_num()+" ");
    	System.out.print("outNode2=");
    	for(int i=0;i<outNode2.size();++i)
    		System.out.print(outNode2.get(i).get_node_num()+" ");
    	System.out.print("inNode2=");
    	for(int i=0;i<inNode2.size();++i)
    		System.out.print(inNode2.get(i).get_node_num()+" ");
    	*/
    	
    	/*进行节点交并的算法*/
    	cutset=addAll(cutset,retainAll(outNode1,inNode2));
    	cutset=addAll(cutset,retainAll(outNode2,inNode1));
    	cutset=addAll(cutset,retainAll(inNode1,inNode2));
    	if(!retainAll(outNode1,outNode2).isEmpty()){
    		cutset=addAll(cutset,retainAll(outNode1,outNode2));
    	}
    	//System.out.print("割集结果是:"+cutset.get(0).get_node_num());
		return cutset;
    }
}
