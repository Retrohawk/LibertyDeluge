
public class Sprite {
	private boolean isAlive;
	
	public Sprite() {
		isAlive = true;
	}
	
	public void kill() {
		isAlive = false;
	}
	public boolean isAlive() {
		return this.isAlive;
	}
}
