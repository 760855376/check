package dcc2;

import java.util.ArrayList;

public class text {
    public static void main(String[] args) {
        //��ͼ����
        ArrayList<Vertex> vlist;
        int[][] edgeReflect;
 
        vlist= new ArrayList<Vertex>();
        Vertex v0=new Vertex(0,"У�ſ�","����У��");
        Vertex v1=new Vertex(1,"����","�Է�");
        Vertex v2=new Vertex(2,"�칫¥","�칫");
        Vertex v3=new Vertex(3,"�ٳ�","�˶�");
        Vertex v4=new Vertex(4,"��ѧ¥","��ѧ");
        Vertex v5=new Vertex(5,"������","��Ϣ");
        vlist.add(v0);
        vlist.add(v1);
        vlist.add(v2);
        vlist.add(v3);
        vlist.add(v4);
        vlist.add(v5);
        edgeReflect=new int[][]{
                {0,2,100},
                {0,1,130},
                {0,3,120},
                {1,2,150},
                {1,4,75},
                {2,3,150},
                {2,4,80},
                {3,5,80},
                {4,5,60},
        };
 
 
        Graph g=new Graph();
        g.creatGraph(vlist,edgeReflect);
        g.print();
 
        dijkstra dj=new dijkstra(g);
        dj.Dijkstra_cal(1,2);
 
 
    }
 
}
 