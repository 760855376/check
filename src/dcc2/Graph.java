package dcc2;

import java.util.ArrayList;


/**
 *  图
 *  带权值的邻接表
 */
public class Graph {
    final int MAX_VERTEX_NUM=120;   //最大节点数
    final int MAX_EDGE_NUM=120;   //最大边数
    final Double INFINITY=65565.0;  //表示无穷大
 
 
    public int vertexNum;  //顶点数
    public int edgeNum;    //边数
 
    public Vertex[] adjList;
    public boolean[] visited;          // 判断顶点是否被访问过
 
 
    public void creatGraph(ArrayList<Vertex> vexs,int[][] edges){
        //初始化顶点数和边数
        vertexNum=vexs.size();
        edgeNum=edges.length;
 
        //初始化顶点
        adjList=new Vertex[vertexNum];
        for(int i=0;i<adjList.length;i++){
            adjList[i]=vexs.get(i);
            adjList[i].firstedge=null;
//            System.out.print(adjList[i].number);
//            System.out.println(adjList[i].name);
        }
        //初始化边
        for(int i=0;i<edgeNum;i++){
            //获取边的起始顶点和结束顶点
            int p1=edges[i][0];
            int p2=edges[i][1];
            Double weight=(double)edges[i][2];
            System.out.println(p1+"-"+p2);
            //生成边节点
            Edge e1=new Edge();
            e1.adjvertex=p2;
            e1.value=weight;
            //把边节点添加到起始顶点的链表中
            if(adjList[p1].firstedge==null){
                adjList[p1].firstedge=e1;
            }else{
                linkLast(adjList[p1].firstedge,e1);
            }
            //同样把边节点添加到结束顶点的链表中
            Edge e2=new Edge();
            e2.adjvertex=p1;
            e2.value=weight;
            if(adjList[p2].firstedge!=null){
                linkLast(adjList[p2].firstedge,e2);
            }else{
                adjList[p2].firstedge=e2;
            }
        }
    }
    /**
     * 把边节点链接在最后
     * @param beg   顶点节点指向的第一个边节点
     * @param end   要加入的边节点
     */
    public void linkLast(Edge beg,Edge end){
        Edge p=beg;
        while(p.next!=null){
            p=p.next;
        }
        p.next=end;
    }
    public void print() {
        System.out.printf("List Graph:\n");
        for (int i = 0; i < adjList.length; i++) {
            System.out.printf("%d(%s): ", i, adjList[i].name);
            Edge node = adjList[i].firstedge;
            while (node != null) {
                System.out.printf("%d(%s)路程[%.1f] ", node.adjvertex, adjList[node.adjvertex].name,node.value);
                node = node.next;
            }
            System.out.printf("\n");
        }
    }


 
//    public Vertex() {
//    }
}


