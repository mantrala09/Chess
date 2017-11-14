import java.util.HashSet;
import java.util.Set;

public class Board {

	private Piece[][] board = new Piece[8][8];
	
	private int whiteXPos;
	private int whiteYPos;
	private int blackXPos;
	private int blackYPos;
	private Set<Piece> blackPieces;
	private Set<Piece> whitePieces;

	public Board() {
		this.blackPieces = new HashSet<Piece>();
		this.whitePieces = new HashSet<Piece>();
		this.board = new Piece[8][8];
		whiteXPos = 4;
		whiteYPos = 7;
		blackXPos = 4;
		blackYPos = 0;
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(false, i, 1);
		}
		for (int i = 0; i < 8; i++) {
			board[6][i] = new Pawn(true, i, 6);
		}
		board[0][0] = new Rook(false, 0, 0);
		board[0][7] = new Rook(false, 7, 0);
		board[0][1] = new Knight(false, 1, 0);
		board[0][6] = new Knight(false, 6, 0);
		board[0][2] = new Bishop(false, 2, 0);
		board[0][5] = new Bishop(false, 5, 0);
		board[0][3] = new Queen(false, 3, 0);
		board[0][4] = new King(false, 4, 0);
		board[7][4] = new King(true, 4, 7);
		board[7][0] = new Rook(true, 0, 7);
		board[7][7] = new Rook(true, 7, 7);
		board[7][1] = new Knight(true, 1, 7);
		board[7][7] = new Rook(true, 7, 7);
		board[7][6] = new Knight(true, 6, 7);
		board[7][2] = new Bishop(true, 2, 7);
		board[7][5] = new Bishop(true, 5, 7);
		board[7][3] = new Queen(true, 3, 7);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != null) {
					if (board[i][j].isWhite) {
						whitePieces.add(board[i][j]);
					} else {
						blackPieces.add(board[i][j]);
					}
				}
			}
		}
	}

	public void printBoard() {
		 System.out.print("  ");
		  for (int x = 0; x < 41; x++) {
			  System.out.print("-");
		  }
		  System.out.println();
		  
		  for (int i = 0; i < board.length; i++) {
			  System.out.print(board.length-(i)+" ");
			  System.out.print("| ");
			  for (int j = 0; j < board.length; j++) {
				  if (board[i][j] == null) {
					  System.out.print("   | ");
				  }
				  else {
					  System.out.print(board[i][j].toString() + " | ");   
				  } 
		       }
			  System.out.println();
			  System.out.print("  ");
			  for(int z = 0; z < 41; z++) { 
				  System.out.print("-");
			  }
			  System.out.println();
			  
		  }
		  System.out.println("     a    b    c    d    e    f    g    h");
	}
	
	public boolean castle(String side, boolean isWhite) {
		if (side.equals("K") && isWhite) {
			return castleKingside(7);
		} else if (side.equals("K")) {
			return castleKingside(0);
		} else if (side.equals("Q") && isWhite) {
			return castleQueenside(7);
		} else {
			return castleQueenside(0);
		}
	}
	
	public boolean castleKingside(int row) {	
		if (board[row][6] != null || board[row][5] != null
				|| board[row][4].hasMoved() || board[row][7].hasMoved()) {
			return false;
		}
		Set<Piece> opposite = row == 7 ? blackPieces : whitePieces;
		for (Piece p : opposite) {
			if (p.isValid(4, row, p.isWhite, board) || p.isValid(5, row, p.isWhite, board)
					|| p.isValid(6, row, p.isWhite, board)) {
				System.out.println(p.toString());
				return false;
			}
		}				
		board[row][6] = board[row][4];
		board[row][4] = null;
		board[row][5] = board[row][7];
		board[row][7] = null;
		board[row][5].move(5, row);
		board[row][6].move(6, row);
		whiteXPos = row == 7 ? 6 : whiteXPos;
		blackXPos = row == 7 ? blackXPos : 6;
		return true;
	
	}
	
	public boolean castleQueenside(int row) {
		if (board[row][1] != null || board[row][2] != null || board[row][3] != null
				|| board[row][0].hasMoved() || board[row][4].hasMoved()) {
			return false;
		} else {
			Set<Piece> opposite = row == 7 ? blackPieces : whitePieces;
			for (Piece p : opposite) {
				if (p.isValid(4, row, p.isWhite, board) || p.isValid(3, row, p.isWhite, board)
						|| p.isValid(2, row, p.isWhite, board)) {
					return false;
				}
			}	
			board[row][2] = board[row][4];
			board[row][4] = null;
			board[row][3] = board[row][0];
			board[row][0] = null;
			board[row][2].move(2, row);
			board[row][3].move(3, row);
			whiteXPos = row == 7 ? 2 : whiteXPos;
			blackXPos = row == 7 ? blackXPos : 2;
			return true;
		}
	}

	public boolean move(String start, String dest, boolean isWhite) {
		int startX = getX(start.charAt(0));
		int startY = board.length - (Integer.parseInt("" + start.charAt(1)));
		int destX = getX(dest.charAt(0));
		int destY = board.length - (Integer.parseInt("" + dest.charAt(1)));
		if (startX >= 0 && destY >= 0 && 
				destX < 8 && destY < 8 && 
				board[startY][startX] != null &&
				board[startY][startX].isValid(destX, destY, isWhite, board)) {
			
			return notCausingCheck(startX, startY, destX, destY, isWhite, true);
		} else {
			return false;
		}

	}
	
	private boolean notCausingCheck(int startX, int startY, int destX, int destY, boolean isWhite, boolean makeMove) {
		int kingX = isWhite ? whiteXPos : blackXPos;
		int kingY = isWhite ? whiteYPos : blackYPos;
		if (board[startY][startX] != null && board[startY][startX].toString().startsWith("K")) {
			kingX = destX;
			kingY = destY;
		}
		Piece taken = board[destY][destX];
		board[destY][destX] = board[startY][startX];
		board[startY][startX] = null;		
		if (board[destY][destX] != null && board[destY][destX].toString().startsWith("K")) {
			kingX = destX;
			kingY = destY;
		}
		if (board[destY][destX] != null) {
			board[destY][destX].move(destX, destY);
		}
		blackPieces.remove(taken);
		whitePieces.remove(taken);		
		Set<Piece> opposite = isWhite ? blackPieces : whitePieces;
		for (Piece p : opposite) {
			if (p.isValid(kingX, kingY, p.isWhite, board)) {
				reverseMove(taken, startX, startY, destX, destY);
				return false;
			}
		}
		if (board[destY][destX] != null && board[destY][destX].toString().startsWith("K") && makeMove) {
			King k = (King) board[startY][startX];
			if (isWhite) {
				whiteXPos = destX;
				whiteYPos = destY;
			} else {
				blackXPos = destX;
				blackYPos = destY;
			}
		}		
		if (!makeMove) {
			reverseMove(taken, startX, startY, destX, destY);
		}
		return true;
	}
	
	private void reverseMove(Piece taken, int startX, int startY, int destX, int destY) {
		if (taken != null && taken.isWhite) {
			whitePieces.add(taken);
		} else if (taken != null && !taken.isWhite) {
			blackPieces.add(taken);
		}
		board[startY][startX] = board[destY][destX];
		board[destY][destX] = taken;
		board[startY][startX].move(startX, startY); 
	}
	
	// check whether the given color is checkmated or not
	public boolean checkForCheckMate(boolean isWhite) {
		for (Piece p : isWhite ? whitePieces : blackPieces) {
			// For every piece of the given color, determine if there
			// exists at least one legal move.
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (p.isValid(j, i, p.isWhite, board) && notCausingCheck(p.getX(), p.getY(), j, i, p.isWhite, false)) {
						return false;
					}
				}
			}
		}
		return true;
	}


	private int getX(char ch) {
		String s = "abcdefgh";
		return s.indexOf(ch);
	}

}
