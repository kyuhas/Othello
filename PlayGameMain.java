import java.util.Arrays;

public class PlayGameMain {

	public static void main(String[] args) 
	{
		int gameCount = 0;
		boolean b;

		board currentBoard = new board();
		int[][] currentState = currentBoard.getBoard();
		currentBoard.printBoard();
		
		Player human = new Player(-1);
		Player computer = new Player(1);
		
		
		while(gameCount < 2)
		{
			gameCount = 0;
			System.out.println("It is the human's turn.");
			b = human.humanTurn(currentState);
			if(!b)
				gameCount++;
			currentState = human.getBoard();
			currentBoard = new board(currentState);
			currentBoard.printBoard();
			System.out.println("It is the computer's turn.");
			b = computer.computerTurn(currentState);
			//System.out.println(1+Arrays.deepToString(currentState));
			if(!b)
				gameCount++;
			currentState = computer.getBoard();
			//System.out.println(2+Arrays.deepToString(currentState));
			currentBoard = new board(currentState);
			currentBoard.printBoard();
			System.out.println("executed");
		}
		
		//decide who wins
		int fScore=currentBoard.blackMinusWhite();
		if(fScore < 0)
			System.out.println("YOU WIN! Final Score: Black " + currentBoard.getBlack() + " - " + currentBoard.getWhite() + " White");
		else if(fScore > 0)
			System.out.println("YOU LOSE! Final Score: Black " + currentBoard.getBlack() + " - " + currentBoard.getWhite() + " White");
		else if(fScore == 0)
			System.out.println("IT'S A TIE!");
	}

}

