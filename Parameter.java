package bayes;

import java.util.ArrayList;

/**
 * 该类主要是参数学习的实现算法，
 * @author tang
 * @Time 2014-06-08
 */

public class Parameter {
    private ArrayList<Cond_proba> cond_proba_lst=new ArrayList<Cond_proba>();
    
	public float test(Node n1,ArrayList<Node> parent_node,Relation_table rel_table, int[] fea_sum1){
		int sub1,sub2;
		float val=0;
		float val1=0;
		float val2=0;
		if(1==parent_node.size()){
			for(int i=0;i<parent_node.get(0).get_feature_count();++i){
				sub1=parent_node.get(0).get_node_num()*10+i;//求第一个特征的下标。
				
				for(int j=0;j<n1.get_feature_count();++j){
					sub2=n1.get_node_num()*10+j;//第二个特征的下标。
					val+=p_probability(sub1,sub2,parent_node.get(0),n1,rel_table,fea_sum1);
				}
			}
			
			return val;
		}
		else{
			
			for(int i=0;i<parent_node.get(0).get_feature_count();++i){
				
				sub1=parent_node.get(0).get_node_num()*10+i;//求第一个特征的下标。
				for(int j=0;j<n1.get_feature_count();++j){
					sub2=n1.get_node_num()*10+j;//第二个特征的下标。
					
					val1+=p_probability(sub1,sub2,parent_node.get(0),n1,rel_table,fea_sum1);
				}
			}
			
			for(int i=0;i<parent_node.get(1).get_feature_count();++i){
				
				sub1=parent_node.get(0).get_node_num()*10+i;//求第一个特征的下标。
				for(int j=0;j<n1.get_feature_count();++j){
					sub2=n1.get_node_num()*10+j;//第二个特征的下标。
					
					val2+=p_probability(sub1,sub2,parent_node.get(0),n1,rel_table,fea_sum1);
				}
			}
			val2=val1*val2;
			return val2;
		}

	}
	
	/**
	 * 该方法是参数学习，使用到的条件概率的基本算法
	 * 核心的思想就是关系出现的次数除以条件特征出现的总次数
	 * @param sub1 特征下标
	 * @param sub2 特征下标
	 * @param n1   节点号
	 * @param n2   节点号
	 * @param rel_tab 关系表
	 * @param fea_sum1存储特征出现次数的数组
	 * @return 返回条件概率
	 * @author tang
	 * @Time 2014-06-08
	 */
	
	public float p_probability(int sub1,int sub2,Node n1,Node n2,Relation_table rel_tab,int[] fea_sum1){
		float pp=0;
		int frequency1,frequency2;
		frequency1=0;
		frequency2=0;
		frequency1=fea_sum1[sub1];//下标1所代表的特征在关系表中出现的总次数。
		
		/*通过for循环判断下标代表特征的数值*/
		for(int i=0;i<rel_tab.get_relation_list().size();++i){
			
			if(n1.get_node_num()==rel_tab.get_relation_list().get(i).get_node_num1() &&
			   n2.get_node_num()==rel_tab.get_relation_list().get(i).get_node_num2()){
				
				if(sub1==rel_tab.get_relation_list().get(i).get_start() && sub2==rel_tab.get_relation_list().get(i).get_end())
					frequency2++;
			}
		}

        if(frequency1!=0 && frequency2!=0){
    		pp=(float)frequency2/frequency1;
    		return pp;
        }else 
        	return 0;

	}

	
	public void non_cond_probability(){
		//这里是计算非节点的条件概率的函数。
	}
	
	/**
	 * 该方法是主要的参数学习的函数，实现了参数学习的主要功能
	 * @param graph 图
	 * @param total 特征总数
	 * @param a     数组
	 * @param node_lst 节点链表
	 * @param rel   关系表
	 * @param fea   特征数量数组
	 * @author tang
	 * @Time  2014-06-08
	 */
	
    public void parameter(Graph graph,int total,int[] a,ArrayList<Node> node_lst,Relation_table rel,int[] fea){
    	Cond_proba cond_proba;
    	ArrayList<Node> parent_node=new ArrayList<Node>();//定义父节点
    	float node_probability=0;
    	float cp=0;
    	System.out.println("节点的先验概率是：");
    	
    	for(int i=0;i<node_lst.size();++i){
    		if(graph.inDegree(node_lst.get(i))==0){
    			node_probability=(float)a[node_lst.get(i).get_node_num()]/total;
    			System.out.println("节点"+node_lst.get(i).get_node_num()+"概率="+node_probability+" ");//得到节点的先验概率。
    		}
    	}
    	
    	System.out.println("\n节点和父节点的条件概率是:");
    	//遍历求条件概率分布表
    	for(int n=0;n<node_lst.size();++n){
    		
    		//获取某个节点的父节点。
    		for(int i=0;i<graph.get_vertex_count();++i)
    			for(int j=0;j<graph.get_vertex_count();++j){
    				if(graph.get_matrix_value(i, j)==1){
    					if(j==node_lst.get(n).get_node_num()){
    						parent_node.add(node_lst.get(i));//得到父节点链表
    					}
    				}
    			}
    		
    		if(!parent_node.isEmpty()){
    			System.out.print("节点"+n+"的父节点是：");
        		for(int k=0;k<parent_node.size();++k)
        			System.out.print(parent_node.get(k).get_node_num()+" ");
        		cp=test(node_lst.get(n),parent_node,rel,fea);
        		if(cp>1 && cp<2)
        			cp=cp;
        		if(cp>2)
        			cp=cp;
        		System.out.println();
        		System.out.print("条件概率="+cp+" ");//**************************仅仅便于演示
        		System.out.print("\n");
        		cond_proba=new Cond_proba(cp,1-cp);
        		cond_proba_lst.add(cond_proba);
    		}
    		parent_node.clear();
    	}
    }
}
