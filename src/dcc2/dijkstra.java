package dcc2;

import java.util.ArrayList;

import java.util.Collections;

	public class dijkstra {
	    //图
	    public Graph gh;
	    //未求出最短路径的点集合
	    public ArrayList<Integer> unVisited=new ArrayList();//使用Integer的泛型是因为可以直接通过Remove去掉元素
	    //已求出最短路径的点集合
	    public ArrayList hasVisited=new ArrayList();
	    //记录从起点到其他任意一点的路径长度
	    public Double distances[];
	    //记录Path[i]表示从S到i的最短路径中，结点i之前的结点的编号,即对应点的前一个节点
	    public int paths[];
	 
	    /**
	     * 初始化各点距离及路径
	     */
	    public void init(int x,int y){
	        distances=new Double[gh.vertexNum];
	        paths=new int[gh.vertexNum];
	        for(int i=0;i<distances.length;i++){
	            distances[i]=Double.MAX_VALUE;
	        }
	        distances[x]=0.0;
	        //把与x相邻的点的距离求出,并标准初始路径
	        for(Edge edge=gh.adjList[x].firstedge;edge!=null;edge=edge.next){
	            distances[edge.adjvertex]=edge.value;
	            paths[edge.adjvertex]=x;
	        }
	        //初始化未知最短路点集合和已知最短路集合
	        unVisited.clear();
	        hasVisited.clear();
	        hasVisited.add(x);
	        //其余点为未知
	        for(int i=0;i<gh.adjList.length;i++){
	            if(i!=x){
	                unVisited.add(i);
//	                System.out.println(unVisited.add(i));
	            }
	        }
	    }
	    /**
	     * Dijkstra路径计算算法
	     * @param x 初始点（number）
	     * @param y 目的点
	     */
	    public void Dijkstra_cal(int x,int y){
	        init(x,y);
	        while(!unVisited.isEmpty()){
	            //求出距离x最短的点
	            int number=pickMinInUnvisited(x);
	            if(number==-1){
	                break;
//	            	continue;
	            }
	            //将该点加入到hasvisited集合中，并从未访问集合中去掉
	            hasVisited.add(number);
	            unVisited.remove((Integer)number);
	            //对该点（u）为起点,相邻的点为终点的临接点进行松弛操作，更新其他点的当前最短路径
	            relax(number);
	        }
	        for(int i=0;i<distances.length;i++){
	            System.out.println(x+"-->"+i+"距离为"+distances[i]);
	        }
	 
	        ArrayList path=getPath(x,y);
	        System.out.println("路程是"+path.toString());
	 
	    }
	 
	    /**
	     * 从未求出最短路径的集合中找到与原点最近的点
	     * @param x
	     * @return
	     */
	    public int pickMinInUnvisited(int x){
	        int minIndex=-1;
	        Double min=Double.MAX_VALUE;
	        for(int i=0;i<distances.length;i++){
	            if(unVisited.contains(i)){
	                if(distances[i]<min){
	                    minIndex=i;
	                    min=distances[i];
	                }
	            }
	        }
	        return minIndex;
	    }
	    /**
	     * 对该点（u）为起点,相邻的点为终点的临接点进行松弛操作，更新其他点的当前最短路径
	     * @param u
	     */
	    public void relax(int u){
	        for(Edge edge=gh.adjList[u].firstedge;edge!=null;edge=edge.next){
	            int v=edge.adjvertex;
	            //看是否满足distances[v]>distances[u]+w[u][v]
	            Double value=edge.value;
	            if(distances[v]>distances[u]+value){
	                distances[v]=distances[u]+value;
	                //记录v的最短路径是，前一个节点为u
	                paths[v]=u;
	            }
	        }
	 
	    }
	 
	    /**
	     * 获取正向的路径信息
	     * @param x
	     * @param y
	     * @return
	     */
	    public ArrayList getPath(int x,int y){
	        ArrayList path=new ArrayList();
	        while(y!=x){
	            path.add(y);
	            y=paths[y];
	        }
	       
	        path.add(x);
	        //路径倒置,需要反置回来
	        Collections.reverse(path);
	        return path;
	    }
	 
	 
	 
	    public dijkstra(Graph gh) {
	        this.gh = gh;
	    }
	 
	    public dijkstra(){
	 
	    }
	 
	 
	}

