package bayes;

/**
 * 该类实现的是条件分布表的定义,两个浮点数存储
 * 是和非两种情况下的条件概率,同时还有获取这些
 * 条件概率的函数
 * @author tang
 * @Time   2014-05-28
 **/

public class Cond_proba{
	private float node_cp;
	private float non_node_cp;
	
	/**
	 * 本类是构造方法，用于存储两个条件概率。
	 * @param cp1 条件概率1
	 * @param cp2 条件概率2
	 * @author tang
	 * @Time  2014-05-28
	 */
	
	public Cond_proba(float cp1,float cp2){
		this.node_cp=cp1;
		this.non_node_cp=cp2;
	}
	
	/**
	 * 该方法是获取是条件下的条件概率
	 * @return 是条件下的条件概率
	 * @author tang
	 * @Time   2014-05-28
	 */
    public float get_cp(){ 
    	return node_cp;
    }
    
    /**
     * 该方法是获取非条件下的条件概率
     * @return 非条件下的条件概率
     * @author tang
     * @Time 2014-05-28
     */
    
    public float get_non_cp(){
    	return non_node_cp;
    }
}
