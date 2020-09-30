package dcc2;

public class Vertex {
	public int number;     //地点ID
    public String name;    //地点名称
    public String info;    //地点信息
 
    public Edge firstedge;     //指向第一条边
 
    public Vertex(int number, String name, String info) {
        this.number = number;
        this.name = name;
        this.info = info;
}
}