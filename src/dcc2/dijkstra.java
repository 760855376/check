package dcc2;

import java.util.ArrayList;

import java.util.Collections;

	public class dijkstra {
	    //ͼ
	    public Graph gh;
	    //δ������·���ĵ㼯��
	    public ArrayList<Integer> unVisited=new ArrayList();//ʹ��Integer�ķ�������Ϊ����ֱ��ͨ��Removeȥ��Ԫ��
	    //��������·���ĵ㼯��
	    public ArrayList hasVisited=new ArrayList();
	    //��¼����㵽��������һ���·������
	    public Double distances[];
	    //��¼Path[i]��ʾ��S��i�����·���У����i֮ǰ�Ľ��ı��,����Ӧ���ǰһ���ڵ�
	    public int paths[];
	 
	    /**
	     * ��ʼ��������뼰·��
	     */
	    public void init(int x,int y){
	        distances=new Double[gh.vertexNum];
	        paths=new int[gh.vertexNum];
	        for(int i=0;i<distances.length;i++){
	            distances[i]=Double.MAX_VALUE;
	        }
	        distances[x]=0.0;
	        //����x���ڵĵ�ľ������,����׼��ʼ·��
	        for(Edge edge=gh.adjList[x].firstedge;edge!=null;edge=edge.next){
	            distances[edge.adjvertex]=edge.value;
	            paths[edge.adjvertex]=x;
	        }
	        //��ʼ��δ֪���·�㼯�Ϻ���֪���·����
	        unVisited.clear();
	        hasVisited.clear();
	        hasVisited.add(x);
	        //�����Ϊδ֪
	        for(int i=0;i<gh.adjList.length;i++){
	            if(i!=x){
	                unVisited.add(i);
//	                System.out.println(unVisited.add(i));
	            }
	        }
	    }
	    /**
	     * Dijkstra·�������㷨
	     * @param x ��ʼ�㣨number��
	     * @param y Ŀ�ĵ�
	     */
	    public void Dijkstra_cal(int x,int y){
	        init(x,y);
	        while(!unVisited.isEmpty()){
	            //�������x��̵ĵ�
	            int number=pickMinInUnvisited(x);
	            if(number==-1){
	                break;
//	            	continue;
	            }
	            //���õ���뵽hasvisited�����У�����δ���ʼ�����ȥ��
	            hasVisited.add(number);
	            unVisited.remove((Integer)number);
	            //�Ըõ㣨u��Ϊ���,���ڵĵ�Ϊ�յ���ٽӵ�����ɳڲ���������������ĵ�ǰ���·��
	            relax(number);
	        }
	        for(int i=0;i<distances.length;i++){
	            System.out.println(x+"-->"+i+"����Ϊ"+distances[i]);
	        }
	 
	        ArrayList path=getPath(x,y);
	        System.out.println("·����"+path.toString());
	 
	    }
	 
	    /**
	     * ��δ������·���ļ������ҵ���ԭ������ĵ�
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
	     * �Ըõ㣨u��Ϊ���,���ڵĵ�Ϊ�յ���ٽӵ�����ɳڲ���������������ĵ�ǰ���·��
	     * @param u
	     */
	    public void relax(int u){
	        for(Edge edge=gh.adjList[u].firstedge;edge!=null;edge=edge.next){
	            int v=edge.adjvertex;
	            //���Ƿ�����distances[v]>distances[u]+w[u][v]
	            Double value=edge.value;
	            if(distances[v]>distances[u]+value){
	                distances[v]=distances[u]+value;
	                //��¼v�����·���ǣ�ǰһ���ڵ�Ϊu
	                paths[v]=u;
	            }
	        }
	 
	    }
	 
	    /**
	     * ��ȡ�����·����Ϣ
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
	        //·������,��Ҫ���û���
	        Collections.reverse(path);
	        return path;
	    }
	 
	 
	 
	    public dijkstra(Graph gh) {
	        this.gh = gh;
	    }
	 
	    public dijkstra(){
	 
	    }
	 
	 
	}

