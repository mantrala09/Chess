
public class Queen extends Piece {

	public Queen(boolean isWhite, int x, int y) {
		super(isWhite, "Q", x, y);
	}
	
	public boolean isValid(int destX, int destY, boolean isWhite, Piece[][] board) {
		if (this.isWhite != isWhite) {
			return false;
		} else if (this.x == destX || this.y == destY) {
			Rook r = new Rook(isWhite, this.x, this.y);
			return r.isValid(destX, destY, isWhite, board);
		} else {
			Bishop b = new Bishop(isWhite, this.x, this.y);
			return b.isValid(destX, destY, isWhite, board);
		}
	}
	
}
