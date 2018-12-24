import java.awt.Graphics;


public class Player {
	int x = 0;
	int y = 0;
	
	Drawing drawing;
	
	public Player() {
		
	}
	
	public void paint (Graphics g) {
		
	}
	
	/** PLayer Movement methods
	 * 
	 * @param x is player x-coord
	 * @param y is player y-coord
	 */
	
	public void moveUP(int x, int y) {
		this.x = x;
		this.y = y;
		
		y -= 1;
		drawing.repaint();
	}
	
	public void moveDOWN(int x, int y) {
		this.x = x;
		this.y = y;
		
		y += 1;
	}
	public void moveLEFT(int x, int y) {
		this.x = x;
		this.y = y;
		
		x -= 1;
		drawing.repaint();
	}
	public void moveRIGHT(int x, int y) {
		this.x = x;
		this.y = y;
		
		x +=1;
		drawing.repaint();
	}
}
