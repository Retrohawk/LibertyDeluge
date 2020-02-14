import java.security.SecureRandom;
public class Zombie extends Sprite {
	private int speed;
	private Player currentTarget;
	
	public Zombie() {
		SecureRandom RNG = new SecureRandom();
		this.setX(5);
		this.setY(5);
		this.speed = RNG.nextInt(4) + 1;
	}
	public Zombie(int speed) {
		this.setX(5);
		this.setY(5);
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	public Player getTarget() {
		return currentTarget;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void stalk() {
		
		if (currentTarget.getX() < this.getX()) {
			this.setX(this.getX() - speed);
		}
		else if (currentTarget.getX() > this.getX()) {
			this.setX(this.getX() + speed);
		}
		
		if (currentTarget.getY() < this.getY()) {
			this.setY(this.getY() - speed);
		}
		else if (currentTarget.getY() > this.getY()) {
			this.setY(this.getY() + speed);
		}
	}
	public void setTarget(Player p) {
		currentTarget = p;
	}
	
}
