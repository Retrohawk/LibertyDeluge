
public class Zombie extends Sprite {
	private int speed;
	private int x;
	private int y;
	private Player currentTarget;
	
	public Zombie(int speed) {
		this.x = 5;
		this.y = 5;
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void stalk() {
		if (currentTarget.getX() < this.x) {
			this.x -= speed;
		}
		else if (currentTarget.getX() > this.x) {
			this.x += speed;
		}
		
		if (currentTarget.getY() < this.y) {
			this.y -= speed;
		}
		else if (currentTarget.getY() > this.y) {
			this.y += speed;
		}
	}
	public void setTarget(Player p) {
		currentTarget = p;
	}
	
}
