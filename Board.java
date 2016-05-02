import java.util.Arrays;

public class Board {
	public Board() {
		char[][] gameBoard = new char[8][8];
	}
	public char[][] initBoard(char[][] board) {
		//Filling the board with "x"s as empty locations
		for(int i = 0; i < board[0].length; i++) {
			Arrays.fill(board[i], '`');
		}
		//First Row***
		board[0][0] = 'r';
		board[0][1] = 'h';
		board[0][2] = 'b';
		board[0][3] = 'q';
		board[0][4] = 'k';
		board[0][5] = 'b';
		board[0][6] = 'h';
		board[0][7] = 'r';
		//First Row***
		
		//Second Row***
		Arrays.fill(board[1], 'p');
		//Second Row***

		//Seventh Row***
		Arrays.fill(board[6], 'P');
		//Seventh Row***
		
		//Eighth Row***
		board[7][0] = 'R';
		board[7][1] = 'H';
		board[7][2] = 'B';
		board[7][3] = 'Q';
		board[7][4] = 'K';
		board[7][5] = 'B';
		board[7][6] = 'H';
		board[7][7] = 'R';
		//Eighth Row***
		
		return board;
	}
}
