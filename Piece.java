
// Represents a piece on the chessboard
public class Piece {
	
	protected boolean isWhite;
	private String name;
	private boolean hasMoved;
	protected int x;
	protected int y;
	
	public Piece(boolean isWhite, String name, int xPos, int yPos) {
		this.hasMoved = false;
		this.isWhite = isWhite;
		this.name = name;
		this.x = xPos;
		this.y = yPos;
	}
	
   	public void actuallyMoved() {
      	hasMoved = true;
   	}
   
	public void move(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	public boolean isValid(int destX, int destY, boolean isWhite, Piece[][] board) {
		return false;
	}
	
	public String toString() {
		if (isWhite) {
			return name + 1;
		} else {
			return name + 2;
		}
	}

}
