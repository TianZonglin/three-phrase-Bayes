package bayes;
import java.util.Comparator;

/**
 * 该类是重写接口的比较函数，有且仅有这一个函数
 * @version v0.1
 * @author Don King
 * @Time 2014-06-01
 */

public class Node_comparator implements Comparator<Node_pair> {
	
	public int compare(Node_pair o1, Node_pair o2) {
		// TODO Auto-generated method stub
		float mutual1=o1.get_mutual();
		float mutual2=o2.get_mutual();
		if(mutual1>mutual2)
			return 1;
		else
			return 0;
	}
}
