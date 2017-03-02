import java.util.Arrays;
import java.util.Scanner;

public class Player {
	
	private int color;
	private int[][] BoardMove;
	
	Player(int c)
	{
		color = c;
	}
	
	public int[][] getBoard()
	{
		return BoardMove;
	}
	
	public boolean humanTurn(int[][] cBoard)
	{
		boolean b = false;
		int row, col;
		board updated = new board(cBoard);
		updated.possibleMoves(0, 0, color);
		linkedList humanMoves = updated.getLinkedList();
		PrintMoves(humanMoves.front.getSibling());
		if(humanMoves.length == 0)
		{
			System.out.println("You cannot make any moves this turn.");
			BoardMove = cBoard;
			return false;
		}
		else
		{
			while(!b)
			{
				Scanner sc = new Scanner(System.in);
				System.out.println("Where do you want to place a white token? <row> <col>");
				row = sc.nextInt();
				col = sc.nextInt();
				b = checkIfValid(row, col, humanMoves, humanMoves.front.getSibling());
			}
			return true;
		}
	}
	
	public void PrintMoves(node n)
	{
		if(n.sibling == null)
			return;
		else
		{
			System.out.println("row is " + n.rowPosition + " col is " + n.colPosition + " BW is " + n.getScore());
			PrintMoves(n.getSibling());
		}
	}
	
	
	public boolean checkIfValid(int Row, int Col, linkedList HumanMoves, node n)
	{
		if(n.sibling == null)
		{
			return false;
		}
		else
		{
			if(Row == n.rowPosition && Col == n.colPosition)
			{
				BoardMove = n.currentBoardArray;
				return true;
			}
		}
		return checkIfValid(Row, Col, HumanMoves, n.getSibling());
	}
	
	public boolean computerTurn(int[][] cBoard)
	{
		board updated = new board(cBoard);
		updated.possibleMoves(0, 0, color);
		linkedList compMoves = updated.getLinkedList();
		if(compMoves.length == 0)
		{
			System.out.println("Computer cannot make any moves this turn.");
			BoardMove = cBoard;
			return false;
		}
		else
		{
			GameTree theGameTree = new GameTree(cBoard, updated.blackMinusWhite());
			BoardMove = theGameTree.getMove();
		}
		return true;
	}

}

