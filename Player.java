
public class Player extends Sprite {
	private String name;
	private int x;
	private int y;
	
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
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void moveRight() {
		this.x += 1;
	}
	public void moveLeft() {
		this.x -= 1;
	}
	public void moveUp() {
		this.y -= 1;
	}
	public void moveDown() {
		this.y += 1;
	}
	public String getName() {
		return this.name;
	}
}
