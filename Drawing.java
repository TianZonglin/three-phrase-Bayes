package bayes;
import java.util.Scanner; //键盘扫描类
import java.util.ArrayList;

/**
 * 该类是Drafting方法类和主函数所在类，该类内有很多的方法，主要有
 * 互信息函数，先验概率函数，条件概率函数......
 * @author tang
 * @Time 2014-05-27
 */

public class Drawing {
	
	private static int sum_node[];//每个节点的特征数量的数组。
	private static int fea_total;//特征总数
	private static int fea_sum[];//存放特征关系总数的数组。
	private int fea_sum_count=0;//设定存放特征的数组，用于计算先验概率和条件概率。
	private static int node_count=0;//(int)(Math.random()*7);//节点数量
	private static SR_class r_class=new SR_class();
	private static Relation_table rel_table;
	private static ArrayList<Node> node_list;
	private SR_class s_class=new SR_class();
	private float threshold=0.15f;//阈值用于进行比较
	static int[] fea_count = new int[50];//{5,3,6,7,7,3,5,5,3,6,7,7,3,5};//不同节点中是特征数量。
	private static int[] fea_rel_count = new int[50];//int fea_rel_count[]={6,5,5,5,2,2,8,9,7,2};//各个节点之间的关系数量。
    
	private static int rand1 = 0;

	/**
	 * 该方法是构造函数，主要用于初始化一些基本的变量
	 * @author tang
	 * @Time 2014-05-27
	 */	
	public Drawing(){
	
		System.out.print("node_count:"+node_count+"\n");
		fea_sum_count = 20*node_count;
		sum_node=new int[node_count];
		fea_total=0;
		node_list=new ArrayList<Node>();
		
		/*System.out.print("*******************随机******************\n\nfea_count[]= ");
		for(int i=0;i<node_count;i++){ //////random-->fea_count[]=1~15
			fea_count[i] = 5+(int)(Math.random()*15);
			System.out.print(fea_count[i]+" ");
		}
		System.out.println();
		//rand1=10;//1+(int)(Math.random()*node_count);/////random-->fea_real_count[]=1~10
		System.out.println("fea_rel_count.length= "+rand1);
		
		System.out.print("fea_rel_count[]= ");
		for(int i=0;i<rand1;i++){/////random-->fea_rel_count[]=1~15
			fea_rel_count[i] = 1 + (int)(Math.random()*15);
			System.out.print(fea_rel_count[i]+" ");
		}
		System.out.println();*/
	}
	
	
	/**
	 * 该方法是初始化节点的函数，主要用于将节点链表全部初始化
	 * @author tang
	 * @Time 2014-05-27
	 */	
	public void init_node(){
				
		Node node;
		for(int i=0;i<node_count;++i){
			
			node=new Node(fea_count[i],i);
			node_list.add(node);
			
		}
		
	}

	
	/**
	 * 该方法是打印节点的函数
	 * 主要是遍历打印所有的节点标识符
	 * @author tang
	 * @Time 2014-05-27
	 */	
	public void print_node(){
		System.out.println("系统的全部节点是:");
		for(int j=0;j<node_count;++j)
			System.out.print("节点"+j+" ");
		System.out.println("\n\n");
		System.out.println("不同节点内的特征数量:");
		for(int i=0;i<node_count;++i){
			System.out.print("第"+i+"号节点内的特征是:");
			node_list.get(i).print_feature();
			System.out.println();
		}
		System.out.print("\n\n");
	}
	
	
	/**
	 * 该方法是初始化关系表的函数，主要功能就是利用定义
	 * 好的两个数组进行关系表的初始化
	 * @author tang
	 * @Time 2014-05-27
	 */
	public void init_rel_table(){
		int n[]=new int[rand1];//{0,0,0,1,2,2,2,3,4,6};
		int m[]=new int[rand1];//{1,2,4,2,3,4,5,4,6,5};

/*		for(int i=0;i<rand1;i++){
			n[i]=(int)(Math.random()*(node_count-1));
		    m[i]=(int)(Math.random()*(node_count-1));
		    System.out.print(n[i]+" ");
		}*/
		
		Scanner input=new Scanner(System.in);//创建一个键盘扫描类对象
		System.out.print("请输入n[i],输入"+rand1+"个:");
		for(int i=0;i<rand1;i++) n[i]=input.nextInt();
		System.out.print("请输入m[i],输入"+rand1+"个:");
		for(int i=0;i<rand1;i++) m[i]=input.nextInt();
		
		System.out.println("-------------------------");
		System.out.print("n[]= ");
		for(int i=0;i<rand1;i++){System.out.print(n[i]+" ");}
		System.out.println();
		System.out.print("m[]= ");
		for(int i=0;i<rand1;i++){System.out.print(m[i]+" ");}
		rel_table=new Relation_table(n,m,rand1,fea_count,fea_rel_count,rand1);//////////////////////////////////////////////
	}
	
	
	/**
	 * 该方法是打印关系表的函数，就是遍历关系表并打印
	 * @author tang
	 * @Time 2014-05-27
	 */	
	public void print_rel_table(){
		rel_table.print_relation();
		System.out.print("\n\n\n");
	}

	
	/**
	 * 该方法是数据准备函数，主要的功能就是初始化一些常用数据
	 * 例如fea_total、fea_sum、sum_node
	 * @author tang
	 * @Time 2014-05-27
	 */	
	public void data_preparation(){
		fea_sum=new int[fea_sum_count];
		System.out.print("各个节点的特征数量是:");
		
		for(int i=0;i<node_count;i++){
			fea_total+=fea_count[i];
			sum_node[i]=fea_count[i];
			System.out.print(sum_node[i]+" ");
		}
		
		System.out.println();
		System.out.println("\n特征总数是:"+fea_total);
		System.out.println("\n关系总数是:"+rel_table.get_relation_list().size());
		
		/*计算每个特征在关系表中出现的次数*/
		for(int i=0;i<rel_table.get_relation_list().size();++i){
		    fea_sum[rel_table.get_relation_list().get(i).get_start()]++;
		    fea_sum[rel_table.get_relation_list().get(i).get_end()]++;
		}
		
		System.out.println("不同特征出现的次数是:");
		
		for(int j=0;j<fea_sum_count;++j){
			if(j!=0 && j%4==0)
				System.out.print("\n");
			System.out.print(fea_sum[j]+"次"+" ");
		}
		
		System.out.print("\n\n\n");
	}

	
	/**
	 * 该方法是求先验概率的函数
	 * @param sub 特征下标
	 * @return  先验概率 cp
	 * @author tang
	 * @Time 2014-05-27
	 */	
	public float c_probability(int sub){
		float cp = 0;
		cp=(float)fea_sum[sub]/63;
		return cp;
	}

	
	/**
	 * 该方法是计算条件概率的函数，就是计算两个特征之间的条件概率
	 * @param sub1 特征下标1
	 * @param sub2 特征下标2
	 * @param n1    节点1
	 * @param n2    节点2
	 * @param rel_tab 关系表
	 * @return 条件概率
	 * @author tang
	 * @Time 2014-05-27
	 */	
	public float p_probability(int sub1,int sub2,Node n1,Node n2,Relation_table rel_tab){
		float pp=0;
		int frequency1,frequency2;
		frequency1=0;
		frequency2=0;
		
		/*通过for循环判断下标代表特征的数值*/
		for(int i=0;i<rel_tab.get_relation_list().size();++i){
			
			if(n1.get_node_num()==rel_tab.get_relation_list().get(i).get_node_num1() &&
			   n2.get_node_num()==rel_tab.get_relation_list().get(i).get_node_num2()){
				
				if(sub1==rel_tab.get_relation_list().get(i).get_start())
					frequency1++;
				
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
	
	
	/**
	 * 该方法是利用条件概率和先验概率求互信息的函数
	 * @param n1 节点1
	 * @param n2 节点2
	 * @return 浮点数表示的互信息
	 * @author tang
	 * @Time  2014-05-27
	 */	
	public float mutual_info(Node n1, Node n2){
		int subs1,subs2;
		float p1,p2,p3;
		float val=0;
		for(int i=0;i<n1.get_feature_count();++i){
			subs1=n1.get_node_num()*10+i;
			p1=c_probability(subs1);
			
			for(int j=0;j<n2.get_feature_count();++j){
				subs2=n2.get_node_num()*10+j;
				p2=c_probability(subs2);
				p3=p_probability(subs1,subs2,n1,n2,rel_table);
				
				if(p1!=0 && p2!=0 && p3!=0){
				    val+=(float) ((p1*p3)*Math.log10((p1*p3)/(p1*p2)));
				}
				
			}
		}
		return val;
	}

	
	/**
	 * 该方法是Drating函数就是利用互信息构建有向图
	 * @param graph 图像
	 * @author tang
	 * @Time 2014-05-27
	 */	
	public void drafting(Graph graph){//drafting函数
		float temp_mutual;
		for(int i=0;i<node_count;++i){
			for(int j=0;j<node_count;++j){//两层for循环遍历每个节点
				
				if(i!=j){//节点不能自旋。
					temp_mutual=mutual_info(node_list.get(i),node_list.get(j));
					if(temp_mutual>threshold){
						s_class.add_node_pair(node_list.get(i), node_list.get(j), temp_mutual);
					}
				}	
				
			}
		}
		
        
		s_class.sort_lst();
		System.out.println("大于阈值的各个节点之间的互信息是:");
		s_class.print_info();
		
		int nm1,nm2;
		System.out.println("\n\ns_class.get_nodepair_list().size() = "+s_class.get_nodepair_list().size());
		for(int i=0;i<s_class.get_nodepair_list().size();++i){
			nm1=s_class.get_nodepair_list().get(i).get_node_num1();
			nm2=s_class.get_nodepair_list().get(i).get_node_num2();

			if(graph.is_path(nm1, nm2)){
				r_class.add_node_pair(s_class.get_nodepair_list().get(i));
			}else{
				graph.add_edge(nm1,nm2);
				graph.add_edge1(nm1,nm2);
			}

		}
		
		System.out.println();
		System.out.print("\n有向图是：");
		graph.print_digraph();
		System.out.print("\nwwww向图是：");
		graph.print_graph();
		System.out.print("\n\n\n");
		
	}

	
	/**
	 * 本函数是主函数仅仅是用于测试，没有其他用途
	 * @param args 空参
	 * @author tang
	 * @Time 2014-05-27
	 */	
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);//创建一个键盘扫描类对象
		System.out.print("请输入节点个数[node_count]:");
			node_count=input.nextInt();   
		if(node_count<=0||node_count>100 ){
			System.out.print("Input error in node_count!");
			System.exit(0);
		}
		System.out.print("请输入存在边的条数[rand1]:");
			rand1=input.nextInt();    
		System.out.print("请输入节点每个含有的特征数量fea_count[]，大小与node_count的值相同，输入"+node_count+"个:");
			for(int i=0;i<node_count;i++)fea_count[i]=input.nextInt();    
		System.out.print("请输入节点间关系数量fea_rel_count[],大小与rand1的值相同，输入"+rand1+"个:");
			for(int i=0;i<rand1;i++)fea_rel_count[i]=input.nextInt();    

		Graph graph=new Graph(node_count);
		Thickening thick=new Thickening();//初始化thick函数。
		Thinning thin=new Thinning();
		Parameter para=new Parameter();
		Drawing drawing=new Drawing();
		System.out.println("\n\n******************最基本是数据*******************");
		drawing.init_node();
		drawing.print_node();
		drawing.init_rel_table();
		drawing.print_rel_table();
		System.out.println("******************由基本数据得到的参数*******************");
		drawing.data_preparation();
		System.out.println("****************经过算法之后得到的结果和数据****************");
		drawing.drafting(graph);
		System.out.println("****************经过算法之后得到的结果和数据****************");
		graph=thick.thicken(graph,node_list,r_class);
		System.out.print("\n结果有向图是：");
		graph.print_digraph();
		System.out.print("\n\n\n");
		System.out.println("****************经过算法之后得到的结果和数据****************");
		para.parameter(graph, fea_total, sum_node, node_list, rel_table, fea_sum);
		System.out.print(fea_total+" "+sum_node[0]+" "+fea_sum[0]+" "+node_list.get(0).get_node_num()+" "+node_list.size()+" "+rel_table.get_rel_count());//+rel_table+
		System.out.println("获取的所有的节点的路径");
		Ser_recommend ser=new Ser_recommend();
		ser.ser_recommend(graph, node_list, rel_table);
	}

}
