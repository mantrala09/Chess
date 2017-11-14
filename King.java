
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
//			System.out.println("first case");
			return false;
		} 
		if (deltaY > 1 || deltaX > 1 || (deltaY == 0 && deltaX == 0)) {
//			System.out.println("this.x = " + this.x);
//			System.out.println("this.y = " + this.y);
//			System.out.println(destX);
//			System.out.println(destY);
			return false;
		}
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board.length; j++) {
//				if (board[i][j] != null && 
//						board[i][j].isWhite != isWhite &&
//						board[i][j].isValid(j, i, destX, destY, board[i][j].isWhite, board)) {
//					//System.out.println(i + " " + j);
//					return false;
//				}
//			}
//		}
		//System.out.println("last one");
		return isValid;
	}
	

	

	
	
}
