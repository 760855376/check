package dcc2;

import java.util.ArrayList;


/**
 *  ͼ
 *  ��Ȩֵ���ڽӱ�
 */
public class Graph {
    final int MAX_VERTEX_NUM=120;   //���ڵ���
    final int MAX_EDGE_NUM=120;   //������
    final Double INFINITY=65565.0;  //��ʾ�����
 
 
    public int vertexNum;  //������
    public int edgeNum;    //����
 
    public Vertex[] adjList;
    public boolean[] visited;          // �ж϶����Ƿ񱻷��ʹ�
 
 
    public void creatGraph(ArrayList<Vertex> vexs,int[][] edges){
        //��ʼ���������ͱ���
        vertexNum=vexs.size();
        edgeNum=edges.length;
 
        //��ʼ������
        adjList=new Vertex[vertexNum];
        for(int i=0;i<adjList.length;i++){
            adjList[i]=vexs.get(i);
            adjList[i].firstedge=null;
//            System.out.print(adjList[i].number);
//            System.out.println(adjList[i].name);
        }
        //��ʼ����
        for(int i=0;i<edgeNum;i++){
            //��ȡ�ߵ���ʼ����ͽ�������
            int p1=edges[i][0];
            int p2=edges[i][1];
            Double weight=(double)edges[i][2];
            System.out.println(p1+"-"+p2);
            //���ɱ߽ڵ�
            Edge e1=new Edge();
            e1.adjvertex=p2;
            e1.value=weight;
            //�ѱ߽ڵ���ӵ���ʼ�����������
            if(adjList[p1].firstedge==null){
                adjList[p1].firstedge=e1;
            }else{
                linkLast(adjList[p1].firstedge,e1);
            }
            //ͬ���ѱ߽ڵ���ӵ����������������
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
     * �ѱ߽ڵ����������
     * @param beg   ����ڵ�ָ��ĵ�һ���߽ڵ�
     * @param end   Ҫ����ı߽ڵ�
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
                System.out.printf("%d(%s)·��[%.1f] ", node.adjvertex, adjList[node.adjvertex].name,node.value);
                node = node.next;
            }
            System.out.printf("\n");
        }
    }


 
//    public Vertex() {
//    }
}


