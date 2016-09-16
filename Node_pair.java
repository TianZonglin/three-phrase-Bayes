package bayes;

 /**
  * 该类主要是用于实现存放节点对的代码
  * @author tang
  * @Time 2014-06-08
  */

public class Node_pair {
	private int node_num1;//节点1的编号
	private int node_num2;//节点2的编号
	private Node node1;
	private Node node2;
	private float node_mutual;//存放节点对的阈值。
    
    /**
     * 该方法是构造函数，主要实现的是存放节点对
     * @param n1 节点1
     * @param n2 节点2
     * @param mutual 互信息
     * @author tang
     * @Time 2014-06-08
     */
	
    public Node_pair(Node n1,Node n2,float mutual){
    	this.node_num1=n1.get_node_num();
    	this.node_num2=n2.get_node_num();
    	
    	this.node1=n1;//直接赋值
    	this.node2=n2;//直接赋值
    	this.node_mutual=mutual;
    }
    
    /**
     * 重载构造函数，用于存放节点对
     * @param n1 节点1
     * @param n2 节点2
     * @author tang
     * @Time 2014-06-08
     */
    
    public Node_pair(Node n1,Node n2){
    	this.node_num1=n1.get_node_num();
    	this.node_num2=n2.get_node_num();
    }
    
    /**
     * 获取一个节点
     * @return 节点号
     * @author tang
     * @Time 2014-06-08
     */
    
    public int get_node_num1(){
    	return node_num1;
    }
    
    /**
     * 获取一个节点
     * @return 节点号
     * @author tang
     * @Time 2014-06-08
     */
    
    public int get_node_num2(){
    	return node_num2;
    }
    
    /**
     * 获取节点对的互信息
     * @return 互信息
     * @author tang
     * @Time 2014-06-08
     */
    
    public float get_mutual(){
    	return node_mutual;
    }
    
    /**
     * 获取节点
     * @return 节点
     * @author tang
     * @Time 2014-06-08
     */
    
    public Node get_node1(){
    	return node1;
    }
    
    /**
     * 获取节点
     * @return 节点
     * @author tang
     * @Time 2014-06-08
     */
    
    public Node get_node2(){
    	return node2;
    }
}
