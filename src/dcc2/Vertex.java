package dcc2;

public class Vertex {
	public int number;     //�ص�ID
    public String name;    //�ص�����
    public String info;    //�ص���Ϣ
 
    public Edge firstedge;     //ָ���һ����
 
    public Vertex(int number, String name, String info) {
        this.number = number;
        this.name = name;
        this.info = info;
}
}