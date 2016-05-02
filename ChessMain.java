import java.util.Scanner;

// across is the second variable in the array

public class ChessMain {
	public static void main(String[] args) {
		String pieceChosen = "";
		
		Scanner input = new Scanner(System.in);
		String playerOne;
		String playerTwo;
			
		boolean validMove;
		int whichPlayer = 1;
			
		char[][] board = new char[8][8];
		Board chessBoard = new Board();
		chessBoard.initBoard(board);
	
		System.out.println("Player one: Please enter your name >> ");
		playerOne = input.nextLine();
		System.out.println("Player two: Please enter your name >> ");
		playerTwo = input.nextLine();
		System.out.println();
			
		
		while(!pieceChosen.equals("q")) {	
			int turn = 0;
			displayBoard(board);
			if(whichPlayer == 1) {
				System.out.println("\n" + playerOne + ", your turn. Please make your move. Select a piece (e.g. d5) or \"q\" to quit >>> ");
			}
			else {
				System.out.println("\n" + playerTwo + ", your turn. Please make your move. Select a piece (e.g. d5) or \"q\" to quit >>> ");
			}
			
			pieceChosen = input.nextLine().toLowerCase();
			
			if(!pieceChosen.equals("q")) {
				int across = letterToNumber(pieceChosen); // "across" is equal to the letter
				while(across == 10) {
					System.out.println("Invalid selection");
					System.out.println("Please try again >>> ");
					pieceChosen = input.nextLine().toLowerCase();
					across = letterToNumber(pieceChosen);
				}
				
					char number = pieceChosen.charAt(1);
					
					if(Character.isDigit(number)) {
						int down = Character.getNumericValue(number) - 1;
						
						//Up until here I was validating whether or not the piece selected is a valid piece
						//Now I'm checking to make sure that the player actually selected a piece and not an empty square
						
						char pieceType = checkPlaceOnBoard(board, down, across, whichPlayer);
						
						while(pieceType == 'X') {
							System.out.println("That's an empty square.");
							displayBoard(board);
							System.out.println("Please pick a piece >>> ");
							pieceChosen = input.nextLine().toLowerCase();
							
							across = letterToNumber(pieceChosen);
							if(across == 10) {
								System.out.println("Invalid selection");
							}
							else {
								number = pieceChosen.charAt(1);
							}
							if(Character.isDigit(number)) {
								down = Character.getNumericValue(number) - 1;
								pieceType = checkPlaceOnBoard(board, down, across, whichPlayer);
							}
						}
						while(pieceType == 'Y') {
							System.out.println("That's the other player's piece.");
							System.out.println("Please pick a piece >>> ");
							pieceChosen = input.nextLine().toLowerCase();
							
							across = letterToNumber(pieceChosen);
							if(across == 10) {
								System.out.println("Invalid selection");
							}
							else {
								number = pieceChosen.charAt(1);
							}
							if(Character.isDigit(number)) {
								down = Character.getNumericValue(number) - 1;
								pieceType = checkPlaceOnBoard(board, down, across, whichPlayer);
							}
						}
						int tempDown = down;
						int tempAcross = across;
						char tempPieceType = pieceType;
						
						
						System.out.println("Where do you want to move that piece to >>> ");
						pieceChosen = input.nextLine().toLowerCase();
						
						across = letterToNumber(pieceChosen); // Returns 10 if it's an invalid piece
						
						while(across == 10) { 
							System.out.println("Invalid selection");
							System.out.println("Please try again >>> ");
							pieceChosen = input.nextLine().toLowerCase();
							across = letterToNumber(pieceChosen);
						}
						
						number = pieceChosen.charAt(1);
						
						if(Character.isDigit(number)) {
							down = Character.getNumericValue(number) - 1;
							
							pieceType = checkPlaceOnBoard(board, down, across, whichPlayer);
							
							validMove = checkMove(tempPieceType, tempDown, tempAcross, down, across, whichPlayer, board);
							
							
							while(!validMove) {
								System.out.println("Invalid Move");
								System.out.println("Where do you want to move that piece to >>> ");
								pieceChosen = input.nextLine().toLowerCase();
								
								across = letterToNumber(pieceChosen); // Returns 10 if it's an invalid piece
								
								while(across == 10) { 
									System.out.println("Invalid selection");
									System.out.println("Please try again >>> ");
									pieceChosen = input.nextLine().toLowerCase();
									across = letterToNumber(pieceChosen);
								}
								
								number = pieceChosen.charAt(1);
								
								if(Character.isDigit(number)) {
									down = Character.getNumericValue(number) - 1;
									
									pieceType = checkPlaceOnBoard(board, down, across, whichPlayer);
									
									validMove = checkMove(tempPieceType, tempDown, tempAcross, down, across, whichPlayer, board);
								}
							}
							
							System.out.println("Moved the piece successfully.");
						}
						
						board = movePiece(board, tempPieceType, tempDown, down, tempAcross, across);
					}
					else {
						System.out.println("That is an invalid piece.");
					}
					if(whichPlayer == 1) {
						whichPlayer = 2;
					}
					else {
						whichPlayer = 1;
					}
					turn++;
			}
		}
	}
	public static char[][] movePiece(char[][] chessBoard, char tempPieceType, int tempDown, int down, int tempAcross, int across) {
		//int[] opposite = {7,6,5,4,3,2,1,0};
		
		chessBoard[down][across] = tempPieceType;
		chessBoard[tempDown][tempAcross] = '`';
		return chessBoard;
	}
		
	public static void displayBoard(char[][] board) {
		System.out.println("----------------");
		for(int i = 0; i < board.length; i++) {
			for(int x = 0; x < board[i].length; x++) {
				System.out.print(board[i][x] + " ");
			}
			System.out.println("|" + (i + 1));
		}
		System.out.println("----------------");
		System.out.println("A B C D E F G H");
	}
	
	public static int letterToNumber(String pieceChosen) {
		char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
		int[] letterCorrespond = {0, 1 , 2 , 3 , 4 , 5 , 6 , 7};
		
		char letterOfPiece = pieceChosen.charAt(0);
		int numberOfPiece = Character.getNumericValue(pieceChosen.charAt(1));
		
		if(numberOfPiece > 8 || numberOfPiece < 0) {
			return 10;
		}
		
		for(int i = 0; i < letters.length; i++) {
			if(letters[i] == letterOfPiece) {
				return letterCorrespond[i];
			}
		}
		return 10;
	} 
	public static char checkPlaceOnBoard(char[][] board, int down, int across, int whichPlayer) {
		
		if(down > 8 || across > 8 || down < 0 || across < 0) {
			return 'X';
		}
		char originalChar = board[down][across];
		char tempChar = Character.toUpperCase(originalChar);
		if(board[down][across] == '`') {
			return 'X';
		} 
		else {
			if(whichPlayer == 1){
				if(originalChar == tempChar) {
					return board[down][across];
				}
				else {
					return 'Y';
				}
			}
			else {
				if(originalChar == tempChar) {
					return 'Y';
				}
				else {
					return board[down][across];
				}
			}
		}
	}
	
	// tempDown is the place moved to. down is the original position
	public static boolean checkMove(char tempPieceType, int tempDown, int tempAcross, int down, int across, int whichPlayer, char[][] board) {
		boolean isValidMove = false;
			switch(tempPieceType) {
				case 'P':
					if(tempDown + 1 == 7) {
						if(tempAcross == across) {
							if(tempDown - down == 1 || tempDown - down == 2) {
								isValidMove = true;
							}
							else {
								isValidMove = false;
							}
						}
						else {
							isValidMove = false;
						}
					}
					else {
						if(tempDown - down != 1) {
							isValidMove = false;
						}
						else {
							isValidMove = true;
						}
					}
					break;
				case 'p':
					if(tempDown + 1 == 2) {
						if(tempDown - down == -1 || tempDown - down == -2) {
							isValidMove = true;
						}
						else {
							isValidMove = false;
						}
					}
					else if(tempDown - down != 1) {
						isValidMove = false;
					}
					else {
						isValidMove = true;
					}
					break;
				case 'R': case 'r':
					if(across != tempAcross && down != tempDown) {
						isValidMove = false;
					}
					else {
						isValidMove = true;
					}
					break;
				case 'H': case 'h':
					if(tempDown - down == 2 && tempAcross - across == 1 || tempAcross - across == - 1) {
						isValidMove = true;
					} 
					else if(tempDown - down == 1 && tempAcross - across == 2 || tempAcross - across == -2) {
						isValidMove = true; 	
					}
					else if(tempDown - down == -2 && tempAcross - across == 1 || tempAcross - across == -1) {
						isValidMove = true;
					}
					else if(tempDown - down == -1 && tempAcross - across == 2 || tempAcross - across == -2) {
						isValidMove = true;
					}
					else {
						isValidMove = false;
					}
					break;
				case 'B': case 'b':
					if(tempDown - down != tempAcross - across) {
						isValidMove = false;
					}
					else {
						isValidMove = true;
					}
					break;
				case 'Q': case 'q':
					if(tempDown - down != 0 && tempAcross - across == 0) {
						isValidMove = true;
					}
					else if(tempDown - down == 0 && tempAcross - across != 0) {
						isValidMove = true;
					}
					else if(tempDown - down == tempAcross - across) {
						isValidMove = true;
					}
					else {
						isValidMove = false;
					}
					break;
				case 'K': case 'k':
					if(tempDown - down == 1 || tempDown - down == -1) {
						isValidMove = true;
					}
					else if(tempAcross - across == 1 || tempAcross - across == -1){
						isValidMove = true;
					}
					else {
						isValidMove = false;
					}
					break;	
			}
			
		if(across == tempAcross && down == tempDown) {
			System.out.println("You need to move the piece somewhere else.");
			return false;
		}
		if(isValidMove) {
			isValidMove = checkPiecePath(tempPieceType, across, tempAcross, down, tempDown, board, whichPlayer);
		}
		return isValidMove;
	}
	public static boolean checkPiecePath(char tempPieceType, int across, int tempAcross, int down, int tempDown, char[][] board, int whichPlayer) {
		boolean isPathClear = true;
		int i = 0;
		int x = 0;
		int y;
		int z;
		switch(tempPieceType) {
			case 'P': case 'p': 
				i = tempDown - down;
				break;
			case 'B': case 'b': case 'q': case 'Q': case 'R': case 'r':
				i = tempDown - down;
				x = tempAcross - across;
		}
		if(x == 0) {
			for(y = 0; y < i; y++) {
				if(board[y][across] != '`') {
					isPathClear =  false;
				}
			}
			return true;
		}
		else if(i == 0){
			for(y = 0; y < x; y++) {
				if(board[down][y] != '`') {
					isPathClear = false;
				}
			}
			return true;
		}
		else {
			for(z = 0, y = 0; z < x && y < i; z++, y++) {
				if(board[z][y] != '`') {
					isPathClear = false;
				}
			}
			return true;
		}
	}
}