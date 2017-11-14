
public class Knight extends Piece {

	public Knight(boolean isWhite, int x, int y) {
		super(isWhite, "N", x, y);
	}
	
	public boolean isValid(int destX, int destY,
			boolean isWhite, Piece[][] board) {
		int deltaX = Math.abs(this.x - destX);
		int deltaY = Math.abs(this.y - destY);
		if ((board[destY][destX] != null && board[destY][destX].isWhite == isWhite)
				|| destX > 7 || destY > 7 || this.isWhite != isWhite) {
			return false;
		}
		if (deltaX == 2 && deltaY == 1) {
			return true;
		} else if (deltaY == 2 && deltaX == 1) {
			return true;
		} else {
			return false;
		}
	}
}
