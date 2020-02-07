
public class Player extends Sprite {
	private String name;
	public final int MOVESPEED = 3;
	
	public Player() {
		this("", 100,100);
	}
	public Player(String name) {
		this(name, 100, 100);
	}
	public Player(int x, int y) {
		this("", x, y);
	}
	public Player(String name, int x, int y) {
		this.name = name;
		this.setX(x);
		this.setY(y);
	}
	public void moveRight() {
		this.setX(this.getX() + MOVESPEED);
	}
	public void moveLeft() {
		this.setX(this.getX() - MOVESPEED);
	}
	public void moveUp() {
		this.setY(this.getY() - MOVESPEED);
	}
	public void moveDown() {
		this.setY(this.getY() + MOVESPEED);
	}
	public String getName() {
		return this.name;
	}
}
