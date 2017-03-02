import java.util.Arrays;

public class board {
	
	private int[][] tempBoard = new int[8][8]; 
	private int[][] parentBoard = new int[8][8];
	private String printedRow = "";
	private int rowNum = 0, colNum = 0, blackMinusWhiteTokens=0, blackTokens = 0, whiteTokens = 0;
	private boolean validMove = false;
	private linkedList childrenList = new linkedList();
	
	board()
	{
		tempBoard[3][3] = 1;
		tempBoard[4][4] = 1;
		tempBoard[3][4] = -1;
		tempBoard[4][3] = -1;
		parentBoard[3][3] = 1;
		parentBoard[4][4] = 1;
		parentBoard[3][4] = -1;
		parentBoard[4][3] = -1;
	}
	 
	board(int[][] originalBoard)
	{
		tempBoard = copy2dArray(originalBoard);
		parentBoard = copy2dArray(originalBoard);
	}
	
	public int[][] copy2dArray(int[][] copiedArray)
	{
		int[][] newCopy = new int[8][8];
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
				newCopy[i][j] = copiedArray[i][j];
		}
		return newCopy;
	}
	
	public void printBoard()
	{
		String s = "   0  1  2  3  4  5  6  7" + '\n';
		System.out.print(s);
		printRows(0,0, "");
	}
	
	public int[][] getBoard()
	{
		return parentBoard;
	}
	
	public linkedList getLinkedList()
	{
		return childrenList;
	}
	
	
	public void printRows(int row, int i, String printedRow)
	{
		if(i == 7 && row == 7)
		{
			System.out.println(printedRow + "  " + convertToColor(tempBoard[row][i]));
		}
		else if(i == 7)
		{
			System.out.println(printedRow + "  " + convertToColor(tempBoard[row][i]));
			i = 0;
			row++;
			printRows(row, i, "");
		}
		else
		{
			if(i == 1)
				printedRow = row + printedRow;
			printedRow = printedRow + "  " + convertToColor(tempBoard[row][i]);
			printRows(row, i+1, printedRow);
		}
		return;
	}
	
	public String convertToColor(int color)
	{
		String s = "";
		if(color == 1)
			s = "b";
		else if(color == -1)
			s = "w";
		else if(color == 0)
			s = "-";
		return s;
	}
	
	//I think the problem is here
	public void possibleMoves(int n, int m, int color)
	{
		if(parentBoard[n][m] == 0)
		{
			searchMoves(n, m, color);
		}
		if(m == 7 && n == 7)
		{
			return;
		}
		if(m == 7)
		{
			possibleMoves(n+1, 0, color);
		}
		else
			possibleMoves(n, m+1, color);
		
	}
	
	public void searchMoves(int row, int col, int color)
	{
		if(EightDirectionCheck(0, -1,row, col, color, false))
			EightDirectionCheck(0, -1, row, col, color, true);
		if(EightDirectionCheck(0, 1, row, col, color, false))
			EightDirectionCheck(0, 1, row, col, color, true);
		if(EightDirectionCheck(-1, 0, row, col, color, false))
			EightDirectionCheck(-1, 0, row, col, color, true);
		if(EightDirectionCheck(1, 0, row, col, color, false))
			EightDirectionCheck(1, 0, row, col, color, true);
		if(EightDirectionCheck(-1, 1, row, col, color, false))
			EightDirectionCheck(-1, 1, row, col, color, true);
		if(EightDirectionCheck(-1, -1, row, col, color, false))
			EightDirectionCheck(-1, -1, row, col, color, true);
		if(EightDirectionCheck(1, -1, row, col, color, false))
			EightDirectionCheck(1, -1, row, col, color, true);	
		if(EightDirectionCheck(1, 1, row, col, color, false))
			EightDirectionCheck(1, 1, row, col, color, true);
		
		if(!Arrays.deepEquals(tempBoard, parentBoard))
		{
			tempBoard[row][col] = color;
			if(col == 7)
				Arrays.deepToString(tempBoard);
			blackMinusWhiteTokens = blackMinusWhite();
			childrenList.insertAfter(row, col, tempBoard, blackMinusWhiteTokens);
			blackMinusWhiteTokens = 0;
			blackTokens = 0; 
			whiteTokens = 0;
		}
		tempBoard = copy2dArray(parentBoard);
		
	}
	
	//color is the color of the player themselves
	public boolean EightDirectionCheck(int rowDirection, int colDirection, int rowPos, int colPos, int color, boolean timeToFlip)
	{
		int count = 0;
		colPos = colPos + colDirection;	
		rowPos = rowPos + rowDirection;
		while(colPos >= 0 && colPos <= 7 && rowPos >= 0 && rowPos <= 7)
		{
			if(tempBoard[rowPos][colPos] == 0)
			{
				return false;
			}
			else if(tempBoard[rowPos][colPos] != color)
			{
				count++;
				if(timeToFlip)
				{
					tempBoard[rowPos][colPos] = color;
				}
			}
			else if(tempBoard[rowPos][colPos] == color)
			{
				if(count == 0)
					return false;
				else
				{
					return true;
				}
			}
			
			if(rowPos == 7 && rowDirection == 1)
				return false;
			else if(colPos == 7 && colDirection == 1)
				return false;
			else if(rowPos == 0 && rowDirection == -1)
				return false;
			else if(colPos == 0 && colDirection == -1)
				return false;
			
			colPos = colPos + colDirection;	
			rowPos = rowPos + rowDirection;
		}
		return false;
	}
	
	
	public int blackMinusWhite()
	{
		blackTokens=0;
		whiteTokens=0;
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if(tempBoard[r][c] == 1)
					blackTokens++;
				else if(tempBoard[r][c] == -1)
					whiteTokens++;
			}
		}
		blackMinusWhiteTokens = blackTokens - whiteTokens;
		return blackMinusWhiteTokens;
	}
	
	public int getBlack()
	{
		return blackTokens;
	}
	public int getWhite()
	{
		return whiteTokens;
	}
			
}
