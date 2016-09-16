package bayes;

/**
 * 该类是特征定义类，主要是定义的特征的一些基本数据成员和函数成员
 * 方法有：获取数据的函数
 * @author tang
 * @Time 2014-05-31
 */

public class Feature {//本类是实现最基本的数据的类，用于以后的计算。

	private int data;//实验用的数据。
	
	/**
	 * 该方法是构造函数，主要就是定义特征对象
	 * @param n 一个整数
	 * @author tang
	 * @Time 2014-05-31
	 */
	
	public Feature(int n){
		this.data=n;
	}
	
	/**
	 * 该方法是获取数据的函数
	 * @return 特征对象的具体数值
	 * @author tang
	 * @Time 2014-05-31
	 */
	public int get_data(){
		return data;
	}
}
