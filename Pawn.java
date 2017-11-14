
public class Pawn extends Piece {	
	public Pawn(boolean isWhite, int x, int y) {
		super(isWhite, "P", x, y);
	}
	public boolean isValid(int destX, int destY, boolean isWhite, Piece[][] board) {
		int deltaX = Math.abs(this.x - destX);
		int deltaY = Math.abs(this.y - destY);
		if ((board[destY][destX] != null && board[destY][destX].isWhite == isWhite)
				|| destX > 7 || destY > 7 || this.isWhite != isWhite || deltaY > 2) {
			return false;
		} 
		if (isWhite && destY >= this.y || !isWhite && destY <= this.y) {
			return false;
		}
		if ((deltaY == 2 && board[destY][destX] == null && deltaX == 0)
				&& (isWhite && this.y == 6 || !isWhite && this.y == 1)) {
			return true;
		}
		if (deltaY == 1 && deltaX == 0 && board[destY][destX] == null) {
			return true;
		} 
		if (board[destY][destX] != null && deltaX == 1 && deltaY == 1) {
			return true;
		}
		return false;		
	}
}
