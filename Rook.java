
public class Rook extends Piece {
	public Rook(boolean isWhite, int x, int y) {
		super(isWhite, "R", x, y);
	}
	
	public boolean isValid(int destX, int destY, boolean isWhite, Piece[][] board) {
		boolean isValid = true;
		isValid &= this.isWhite == isWhite;
		if ((this.y != destY && this.x != destX) || (board[destY][destX] != null && board[destY][destX].isWhite == isWhite)
				|| destX > 7 || destY > 7) {
			return false;
		}
		for (int i = Math.min(this.x, destX) + 1; i < Math.max(this.x, destX); i++) {
			if (board[this.y][i] != null ) {
				return false;
			}
		}
		for (int i = Math.min(this.y, destY) + 1; i < Math.max(destY, this.y); i++) {
			if (board[i][this.x] != null ) {
				return false;
			}
		}

		return isValid;
	}
}
