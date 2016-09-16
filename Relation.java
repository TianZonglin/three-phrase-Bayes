package bayes;

/**
 * 给类是定义关系类代码，类内定义关系的基本数据成员
 * 和主要的成员函数
 * @author tang
 * @Time 2014-06-07
 */

public class Relation {//关系类

	private int node_num1;//所属源节点编号
	private int node_num2;//所属目标节点
    private int start;//表示源下标。
    private int end;  //表示目标下标。
    
    /**
     * 该方法是构造函数对关系对象进行初始化
     * @param n 节点号
     * @param m 节点号
     * @param rand_num1 特征号
     * @param rand_num2 特征号
     * @author tang
     * @Time 2014-06-07
     */
    
    public Relation(int n,int m,int rand_num1,int rand_num2){//构造函数实现关系的创建
    	int subscript1,subscript2;
        subscript1=n*10+rand_num1;
        subscript2=m*10+rand_num2;
        this.start=subscript1;
        this.end=subscript2;
        this.node_num1=n;
        this.node_num2=m;
    }
    
    /**
     * 该方法就是打印关系表
     * @author tang
     * @Time 2014-06-07
     */
    
    public void print_rel(){//打印关系。
    	
    	System.out.print("<"+start+","+end+">"+" ");
    }
    
    /**
     * 该方法就是获取开始特征的下标函数
     * @return 开始下标
     * @author tang
     * @Time 2014-06-07
     */
    
    public int get_start(){//获取源特征下标
    	return start;
    }
    
    /**
     * 该方法就是获取结束下标的函数
     * @author tang
     * @Time 2014-06-07
     */
    
    public int get_end(){//获取目标特征下标
    	return end;
    }
    
    /**
     * 该方法就是获取开始节点的编号
     * @return 节点编号
     * @author tang
     * @Time 2014-06-07
     */
    
    public int get_node_num1(){//获取源节点编号
    	return node_num1;
    }
    
    /**
     * 该方法就是获取结束节点的编号
     * @return 节点编号
     * @author tang
     * @Time 2014-06-07
     */
    
    public int get_node_num2(){//获取目标节点编号
    	return node_num2;
    }

}
