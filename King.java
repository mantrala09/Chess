
public class King extends Piece {
	

	
	public King(boolean isWhite, int x, int y) {
		super(isWhite, "K", x, y);
	}
	
	public boolean isValid(int destX, int destY, boolean isWhite, Piece[][] board) {
		boolean isValid = true;
		isValid &= this.isWhite == isWhite;
		int deltaX = Math.abs(destX - this.x);
		int deltaY = Math.abs(destY - this.y);
		if ((board[destY][destX] != null && board[destY][destX].isWhite == isWhite)
				|| destX > 7 || destY > 7) {
			return false;
		} 
		if (deltaY > 1 || deltaX > 1 || (deltaY == 0 && deltaX == 0)) {
			return false;
		}
		return isValid;
	}
	

	

	
	
}
