
public class node<T1 extends Comparable<T1>> {
	
	protected int rowPosition;
	protected int colPosition;
	protected int[][] currentBoardArray;
	protected board newBoard;
	protected int score;
	protected node<T1> sibling;
	protected linkedList<T1> child;

	
	public node(){
		sibling = null;
		child = null;
	}
	public node(int row, int col, int[][] myBoard, int blackMinusWhite){
		rowPosition = row;
		colPosition = col;
		currentBoardArray = myBoard;
		newBoard = new board(currentBoardArray);
		score = blackMinusWhite;
		sibling = null;
		child = null;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void updateScore(int value)
	{
		score = value;
	}
	
	public node<T1> getSibling(){
		return sibling;
	}
	public void connectNodes(node<T1> n){
		sibling = n;
	}
	public void insertAfter(int row, int col, int[][] theBoard, int blackMinusWhite){
		node<T1> temp = new node(row, col, theBoard, blackMinusWhite);
		temp.sibling = sibling;
		sibling = temp;
	}

}