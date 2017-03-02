import java.io.FileWriter;
import java.io.IOException;

public class linkedList <T1 extends Comparable<T1>>{

	protected int length;
	protected node<T1> front;
	protected node<T1> rear;
	protected node<T1> nodeParent;
	protected linkedList llParent;
	
	public linkedList(){
		front = new node<T1>();
		rear = new node<T1>();
		//link to dummy nodes
		front.connectNodes(rear);
		length = 0;
	}

	public void insertAfter(int row, int col,int[][] theBoard, int blackMinusWhite){
		front.insertAfter(row, col, theBoard, blackMinusWhite);
		length++;
	}
	public int getLength(){
		return length;
	}
	
}