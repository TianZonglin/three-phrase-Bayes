package bayes;
/**
 * 主要就是定义了Wsnode类的基本功能和一些其他方法
 * @author tang
 * @Time 2014-06-20
 */
public class Wsnode{
  private Node node;
  private double match;
  
  /**
   * 构造函数，主要就是实现对wsnode的初始化
   * @param ne 节点
   * @param d 匹配度
   */
  public Wsnode(Node ne,double d){
	  this.node=ne;
	  this.match=d;
  }
}
