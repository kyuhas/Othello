import java.util.Arrays;

public class GameTree {
	private linkedList rootList = new linkedList();
	private node root;
	private int[][] bestMove;
	private boolean entered = false;
	public int score;
	
	//if comptuer's first move lit is empty, go back to human
	
	GameTree(int[][] cBoard, int blackMinusWhite)
	{
		rootList.insertAfter(-1, -1, cBoard, blackMinusWhite);
		root = rootList.front.getSibling();
		makeTree(rootList, root, 1);
	}
	
	public int[][] getMove()
	{
		return bestMove;
	}
	
	public void makeTree(linkedList myList, node n, int count)
	{
		//System.out.println("the count is " + count + " sibling = " + n.sibling);
		int color = 1;
		if(count%2 == 0)
			color = -1; 

		if(count == 1 && entered)
		{
			PrintMoves(root.child.front.getSibling());
			color = 1;
			int finalScore = minimax(root.child.front.getSibling() , color);
			root.updateScore(finalScore);
			decideMove(root.child, root.child.front.getSibling(), finalScore);
			System.out.println("best is " + finalScore);
			return;
		}
		else if(count == 4|| n.sibling == null)
		{
			//System.out.println(count);
			if(myList.length == 0)
			{
				myList.insertAfter(-1, -1, myList.nodeParent.currentBoardArray, myList.nodeParent.score);
				myList.nodeParent.newBoard.possibleMoves(0, 0, color);
				linkedList temp = myList.front.getSibling().newBoard.getLinkedList();
				myList.front.getSibling().child = temp;
				temp.nodeParent = myList.front.getSibling();
				temp.llParent = myList;
				if(count != 4)
					makeTree(temp, temp.front.getSibling(), count + 1);
			}
			else
			{
				if(count %2 == 0)
				{
					color = 1;
				}
				else
					color = -1;
				
				//count--;
				int Value = minimax( myList.front.getSibling(), color);
				//System.out.println(Value);
				myList.nodeParent.updateScore(Value);
				//System.out.println(myList.nodeParent.sibling);
				makeTree(myList.llParent, myList.nodeParent.sibling, count-1);
			}
		}
		else
		{
			entered = true;
			n.newBoard.possibleMoves(0, 0, color);
			//System.out.println(n.newBoard.getLinkedList().length);
			if (n.newBoard.getLinkedList().length != 0)
			{
				//linkedList temp = new LinkedList();
				//linkedList temp = n.newBoard.getLinkedList();
				n.child = n.newBoard.getLinkedList();
				n.newBoard.getLinkedList().nodeParent = n;
				n.newBoard.getLinkedList().llParent = myList;
				//System.out.println(n.child.nodeParent);
				makeTree(n.newBoard.getLinkedList(), n.newBoard.getLinkedList().front.getSibling(), count + 1);
			}
			else
				makeTree(myList.llParent, myList.nodeParent.sibling, count-1);
				
		}
		return;
	}
	
	public int minimax(node myNode, int myColor)
	{
		int returnValue = 0;
		if(myColor == 1)
		{
			returnValue = getMax(myNode, Integer.MIN_VALUE);
		}
		else if(myColor == -1)
		{
			returnValue = getMin(myNode, Integer.MAX_VALUE);
		}
		//System.out.println("length " + theList.length);
		return returnValue;
	}
	
	public int getMax(node TheNode, int maxValue) 
	{
	    if (TheNode.sibling == null)
	        return maxValue;
	    else 
	    {
	    	//System.out.println("node score "+ TheNode.getScore());
	        return getMax(TheNode.getSibling(), Math.max(TheNode.getScore(), maxValue));
	    }
	}
	
	public int getMin(node TheNode, int minValue) 
	{
	    if (TheNode.sibling == null)
	        return minValue;
	    else 
	    {
	        return getMin(TheNode.getSibling(), Math.min(TheNode.getScore(), minValue));
	    }
	}
	
	public void decideMove(linkedList theList, node Node, int best)
	{
		if(Node.sibling == null)
			return;
		else
		{
			//System.out.println("row " + Node.rowPosition + " Col " + Node.colPosition + " BW " + Node.getScore());
			if(Node.getScore() == best)
			{
				System.out.println("the best is supposedly " + best);
				System.out.println(Node.rowPosition);
				System.out.println(Node.colPosition);
				bestMove = Node.currentBoardArray;
				return;
			}
			decideMove(theList, Node.sibling, best);
		}
		return;
	}
	
	
	public void PrintMoves(node n)
	{
		if(n.sibling == null)
			return;
		else
		{
			System.out.println("row is " + n.rowPosition + " col is " + n.colPosition + " BW is " + n.getScore());
			PrintMoves( n.getSibling());
		}
	}
	
}
