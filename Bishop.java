
public class Bishop extends Piece {

	public Bishop(boolean isWhite, int x, int y) {
		super(isWhite, "B", x, y);
	}
	
	public boolean isValid(int destX, int destY, boolean isWhite, Piece[][] board) {
		boolean isValid = true;
		isValid &= this.isWhite == isWhite;
		if ((this.y == destY || this.x == destX) || (board[destY][destX] != null && board[destY][destX].isWhite == isWhite)
				|| destX > 7 || destY > 7 || Math.abs(destX - this.x) != Math.abs(destY - this.y)) {
			return false;
		}
		int deltaX = 0;
		int deltaY = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				deltaX = Math.abs(j - this.x);
				deltaY = Math.abs(i - this.y);
				if (((i > this.y && i < destY) || (i < this.y && i > destY)) &&
						(((j > this.x && j < destX) || (j < this.x && j > destX)))
						&& board[i][j] != null && deltaX == deltaY) {
					return false;					
				}
			}
		}
		return isValid;
	}
	
	

}
