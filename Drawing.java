import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class Drawing extends JFrame implements KeyListener {
	private final Color white = new Color (0xFFFFFF);// defining colors
	private final Color black = new Color (0,0,0);// in multiple ways
	private final Color yellow = new Color(255,191,0); // but not all are used; and are for reference.
	JPanel panel = new JPanel(new BorderLayout()); // set the border as a border
	private final Random rand = new Random(); // this is a random number thats constructed by imported math. This is used to make a random number in the getRandom1to11 method.
	public int player = 3; // set player value to 3
	public int moho = 2; // set mho value to 2
	public int wall = 1;// set wall to 1
	public int empty = 0; // empty to 0
	public Player playerObject = new Player(); // make a player object. This is not used.
	int[] mhox = new int[12]; // create array for the X value of the mhos
	int[] mhoy = new int[12];// create array for the Y value of the mhos
	private int[] fenceX = new int[64]; // the x for the fences
	private int[] fenceY = new int[64];// the y for the fences
	private int mhosKilled = 0; // ints that are projected to show how many enemies are left
	private int gamesWon = 0; //  int that is projected to show games won
	private int gamesLost = 0; // int that is projected to show games lost

	
	static int[][] FullArray = new int[12][12]; // the most important Array that holds the location and values of the grid on the board.
	
	
	/**
	 * getters and setters for the playerx and player y values
	 */
	private int playerX = this.getX();
	private int playerY = this.getY();

	public int getPlayerX() {
		return playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	public void setPlayerX(int x) {
		this.playerX = x;
	}
	public void setPlayerY(int y) {
		this.playerY = y;
	} 



	//This code makes a random selection of space between 1 to 11 so that it leaves the  OUTER boundaries out. This will randomly select a place between the boundaries 
	public int getRandom1to11() {
		return rand.nextInt(10) + 1;
	}
	//This code is just a test, but it allows you to find the Y and X values of any number point.
	//Right now this only works for the player because thats the only value with only one number inside the 2d array.
	public int getX() {
		int retval = 0; // set retval
		for(int y = 0; y < 12; y++){
			for(int x = 0; x < 12; x++){ // double for loop
				if(FullArray[x][y] == player){ // if the x and y of the for loop are the player
					retval = x; // return the x of that coord
				}else if(FullArray[y][x] == player){  // if the reverse of the coords has a player
					retval = y; // return the X coord of that, even though its the Y of the for loop. This is to increase efficiency. 
				}
			}
		}
		return retval; // return the retval coord X
	}
	public int getY() { 
		int retval = 0;// set retval
		for(int y = 0; y < 12; y++){
			for(int x = 0; x < 12; x++){ // double for loop
				if(FullArray[x][y] == player){ // if the x and y of the for loop are the player
					retval = y; // return the Y of that coord
				}else if(FullArray[y][x] == player){ // if the reverse of the coords has a player
					retval = x;// return the Y coord of that, even though its the X of the for loop. This is to increase efficiency. 
				}
			}
		}
		return retval; // return retval coord Y
	}



	/**
	 * this code below is the attempt to return a coordinate that is not already filled, but this code is done and does work.
	 * @TODO this is not how mr K wants it and if we have time afterward, we can fix this. 
	 * @param find
	 * @return array of 2 ints that make a coord that is empty.
	 */
	public int[] RandCoord(int find){ 
		int x = getRandom1to11(); // initializing the x and y ints
		int y = getRandom1to11();
		do{
			x = getRandom1to11(); //loops forever getting new values for x and y 
			y = getRandom1to11();

			if(FullArray[x][y] == find) { // checks to see if the x and y values make a coordnate of the int you want
				return new int[] {x,y}; // returns that value
			}

		}while(true); // will do this forever until something is found to return
	}
	/**
	 * This method returns the coord that the player could jump to. This is a modified version of the code above.
	 * 
	 * @return coord of a 'jumpable' location
	 */
	public int[] RandJump(){ // 
		int x = getRandom1to11(); // initializing the x and y ints
		int y = getRandom1to11();
		do{
			x = getRandom1to11(); //loops forever getting new values for x and y 
			y = getRandom1to11();

			if(FullArray[x][y] == empty  || FullArray[x][y] ==  moho) { // checks to see if the x and y values make a coordinate of the int you want
				return new int[] {x,y}; // returns that value
			}

		}while(true); // will do this forever until something is found to return
	}






	public Drawing () {
		init();
		addKeyListener(this);
		setFocusable(true);
		//make the full array
		//set OUTER boundaries
		for(int y = 0; y < 12; y++){
			//this code sets the first and last value of the array as boundaries (left & right bounds).
			FullArray[0][y] = 1;
			FullArray[11][y] = 1;
			//this code makes the  top y values boundaries and the bottom y's x values 1s(top and bottom bounds).
			for(int x = 0; x < 12; x++){
				FullArray[x][0] = 1;
				FullArray[x][11] = 1;
			}
		}

		//select 12 random places for barriers
		for(int z = 0; z < 20; z++){ // for loops for scope of a temp val that holds random coord locations that are not on the fence and that do not overlap for the required 
			int[] tempval = RandCoord(empty); // sets a temp val array to coords of a random empty coord
			FullArray[tempval[0]][tempval[1]] = wall; // set the x and y of that rand coord to wall(1)
		}
		//Moho start location generator
		for(int z = 0; z < 12; z++){// for loops for scope of a temp val that holds random coord locations that are not on the fence and that do not overlap for the required 
			int[] tempval = RandCoord(empty); // sets a temp val array to coords of a random empty coord
			FullArray[tempval[0]][tempval[1]] = moho;// set the x and y of that rand coord to mho(2)
			mhox[z] = tempval[0]; // set x and y of the mho locations into the created array.
			mhoy[z] = tempval[1]; //^
		}
		//player set to 3
		for( int z = 0; z< 1; z++){// for loops for scope of a temp val that holds random coord locations that are not on the fence and that do not overlap for the required 
			int[] tempval = RandCoord(empty);
			FullArray[tempval[0]][tempval[1]] = player;
			playerObject.x = getPlayerX(); // not sure if we need these, but sets the Player object's x and y to the coord as well.
			playerObject.y = getPlayerY();
			setPlayerX(tempval[0]); // setting the X variable locations to the correct coord, so that it draws correctly.
			setPlayerY(tempval[1]); // setting the Y variable locations to the correct coord, so that it draws correctly.

		}

		//print the 2D array into console
		for(int y = 0; y < 12; y++){
			System.out.println();
			for(int x = 0; x < 12; x++){
				System.out.print(FullArray[x][y] + " ");
			}
		}
		System.out.println();
		//This line proves that getX and getY work, be advised that that it starts at 0 not 1, so thats why it may not feel right when double checking.
		System.out.println("(" + this.getX() + " , "+  this.getY() + ")");



	}
	/**
	 * reDraw re-draws the board by first clearing it, then remaking and reseting the board.
	 */
	public void reDraw() {
		mhosKilled = 0;
		for(int y = 0; y < 12; y++){ // clears board for redraw
			for(int x = 0; x < 12; x++){
				FullArray[x][y] = 0;
			}
		}
		/**
		 * 
		 *  The rest of this code is very close(almost the same) as the regular Draw() method. However, this method draws the player first
		 *  instead of last because it was gliching when the player was put down first in the regular Draw, but in the reDraw it was glitching when it was put down last,
		 *  so thats why i dont call Draw() in reDraw().
		 *  
		 *  *** please read the comments in Draw() for a majority of the reDraw() method, as its very similar. ***
		 */
		for(int y = 0; y < 12; y++){
			//this code sets the first and last value of the array as boundaries (left & right bounds).
			FullArray[0][y] = 1;
			FullArray[11][y] = 1;
			//this code makes the  top y values boundaries and the bottom y's x values 1s(top and bottom bounds).
			for(int x = 0; x < 12; x++){
				FullArray[x][0] = 1;
				FullArray[x][11] = 1;
			}
		}
		//player set to 3
		for( int z = 0; z< 1; z++){
			int[] tempval = RandCoord(0);
			FullArray[tempval[0]][tempval[1]] = player;
			playerObject.x = getPlayerX(); // not sure if we need these, but sets the Player object's x and y to the coord as well.
			playerObject.y = getPlayerY();
			setPlayerX(tempval[0]); // setting the X variable locations to the correct coord, so that it draws correctly.
			setPlayerY(tempval[1]); // setting the Y variable locations to the correct coord, so that it draws correctly.
		}

		//select 12 random places for barriers
		for(int z = 0; z < 20; z++){
			int[] tempval = RandCoord(0);
			FullArray[tempval[0]][tempval[1]] = wall;
		}
		//Moho start location generator
		for(int z = 0; z < 12; z++){
			int[] tempval = RandCoord(0);
			FullArray[tempval[0]][tempval[1]] = moho;
			mhox[z] = tempval[0];
			mhoy[z] = tempval[1];

			FullArray[tempval[0]][tempval[1]] = moho;
		}

		//print the 2D array into console
		for(int y = 0; y < 12; y++){
			System.out.println();
			for(int x = 0; x < 12; x++){
				System.out.print(FullArray[x][y] + " ");
			}
		}
		System.out.println();
		//This line proves that getX and getY work, be advised that that it starts at 0 not 1, so that’s why it may not feel right when double checking.
		System.out.println("(" + this.getX() + " , "+  this.getY() + ")");



	}

	//TODO SOME GOD DAMN COMMENTS!!!
	public void init() {
		setSize(800,800);
		setBackground(black);

		//sets frame in the center of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		repaint();

	}

	//TODO SOME GOD DAMN COMMENTS!!!

	public void paint(Graphics g) {

		g.setColor(Color.GRAY);
		g.fillRect(0,0,800,800);

		g.setColor(Color.DARK_GRAY);
		//g.fillRect(90,90,620,620);

		g.setColor(Color.BLACK);
		g.fillRect(100,100,600,600);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("dirt_grid.png") , 100, 100, this);

		g.setColor(Color.BLACK);
		for(int y = 0; y < 12; y++){
			for(int x = 0; x < 12; x++){
				//g.drawRect(x*50 + 100, y*50 + 100, 50, 50);
			}
		}

		//Print statistics (games lost, enemies remaining, games won) as well as controls
		g.setColor(Color.DARK_GRAY);
		g.fillRect(100, 710, 600, 70);
		g.setColor(yellow);
		g.drawRect(100, 710, 600, 70);
		g.drawLine(270, 710, 270, 780);
		String mhosRemaining = "Enemies Remaining: " + Integer.toString(12-mhosKilled);
		g.drawString(mhosRemaining, 110, 730);

		g.drawString("Games Won: " + Integer.toString(gamesWon), 110, 750);
		g.drawString("Games Lost: " + Integer.toString(gamesLost), 110, 770);




		g.drawString("Q: Up and Left", 300, 730);
		g.drawString("W: Up", 400, 730);
		g.drawString("E: Up and right", 460, 730);
		g.drawString("J: Jump", 570, 730);

		g.drawString("A: Left", 300, 750);
		g.drawString("S: Stay", 400, 750);
		g.drawString("D: Right", 460, 750);

		g.drawString("Z: Down and left", 300, 770);
		g.drawString("X: Down", 400, 770);
		g.drawString("C: Down and right", 460, 770);


		drawComp(g);
	}

	public void drawComp(Graphics g) {
		int mhoIdentity = 0; //mhoIdentity is the mho identity
		int fenceIdentity = 0; //J is the fence identity

		//paints all the graphics;
		/**
		 * if 1 then wall
		 * if 2 then mho
		 * if 3 then player
		 */

		for(int y = 0; y < 12; y++){
			for(int x = 0; x < 12; x++){


				if(FullArray[x][y] == wall){
					fenceX[fenceIdentity] = x;
					fenceY[fenceIdentity] = y;
					drawFence(fenceX[fenceIdentity], fenceY[fenceIdentity], g);
					fenceIdentity++;

				}else if(FullArray[x][y] == 2){


					drawMho(mhox[mhoIdentity],mhoy[mhoIdentity], g);
					mhoIdentity++;
				}else if(FullArray[x][y] == 3){

					drawFace(getPlayerX(), getPlayerY(), g);
				}
			}

		}

	}

	public void drawFace(int x,int y, Graphics g) {
		g.drawImage(Toolkit.getDefaultToolkit().getImage("b1.png") , x*50 + 105, y*50 + 105, this); // TODO all these numbers have to be variables for readability!!!!
	}

	public void drawMho(int x, int y, Graphics g) {
		//		g.setColor(yellow);
		//		g.fillOval(x*50+105, y*50+105, 40, 40);
		//		g.setColor(Color.BLACK);
		//		g.fillOval(x*50+112, y*50+115, 10, 10);
		//		g.fillOval(x*50+127, y*50+115, 10, 10);
		//		g.drawArc(x*50+111, y*50+133, 25, 15, 34, 100);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("spider2.png") , x*50 + 105, y*50+105, this);// TODO all these numbers have to be variables for readability!!!!
	}

	public void drawFence(int x, int y, Graphics g) {
		g.drawImage(Toolkit.getDefaultToolkit().getImage("dirt_rock.png") , x*50 + 105, y*50+105, this);// TODO all these numbers have to be variables for readability!!!!
	}


	public void isOnMho() {// checks to see if the player is on a mho using a double nested for loop.
		for (int i = 0; i < 12; i++) {
			if (playerX == mhox[i] && playerY == mhoy[i]) {
				System.out.println("You Lose");
				playerX = 100;
				playerY = 100;
				init();
				gameLost();

			}
		}
	}


	public void mhoIsOnFence() { // checks to see if a mho is on a fence using a double nested for loop.
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 12; j++) {
				if (mhox[j] == fenceX[i] && mhoy[j] == fenceY[i]) { 
					mhox[j] = 1000;
					mhoy[j] = 1000;
					mhosKilled++;
					System.out.println("Mhos Killed: " + mhosKilled);
				}
			}
		}
	}


	//checks if player is on a fence. If so, player is teleported to the coordinates (100,100)
	public void isOnFence() {
		for (int i = 0; i < 64; i++) {
			if (playerX == fenceX[i] && playerY == fenceY[i]) {
				System.out.println("You Lose");
				playerX = 100;
				playerY = 100;
				init();
				gameLost();

			}


		}
	}
	/**
	 * method that is run when the game has been lost and gives you the option to play again or quit
	 */
	public void gameLost() {
		//Prints a Frame when you die - tells the player that the game is over and asks if the player wants to start a new game
		gamesLost++;
		JFrame deathScreen = new JFrame();
		int choice = JOptionPane.showConfirmDialog(deathScreen, "GAME OVER | YOU LOSE\nStart new game?", "Game Over", JOptionPane.YES_NO_OPTION, 
				JOptionPane.PLAIN_MESSAGE);
		//0 = Yes
		//1 = No
		if (choice == 0) {
			//redraw grid with new random locations
			reDraw();

		}
		if (choice == 1) {
			System.exit(0);
		}
	}
	/**
	 * method that is run when the game has been won and gives you the option to play again or quit
	 */
	public void gameWon() { 
		//Prints a Frame when all Mhos have been killed - tells player game is over and asks if the player wants to start a new game
		gamesWon++;
		JFrame winScreen = new JFrame();
		int choice = JOptionPane.showConfirmDialog(winScreen, "GAME OVER | YOU WIN\nStart new game?", "Congratulations", JOptionPane.YES_NO_OPTION, 
				JOptionPane.PLAIN_MESSAGE);
		//0 = Yes
		//1 = No
		if (choice == 0) {
			//redraw grid with new random locations
			reDraw();

		}
		if (choice == 1) {
			System.exit(0);
		}
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO SOME GOD DAMN COMMENTS

		//Moves player up
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.setPlayerY(getPlayerY() - 1);
			mhomovement();
			isOnMho();
			isOnFence();
		}
		//Moves player down
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.setPlayerY(getPlayerY() + 1);
			mhomovement();
			isOnMho();
			isOnFence();
		}
		//Moves player left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.setPlayerX(getPlayerX()-1);
			mhomovement();
			isOnMho();
			isOnFence();
		}
		//Moves player right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.setPlayerX(getPlayerX() +1);
			mhomovement();
			isOnMho();
			isOnFence();
		}
		//Moves player up and left
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			this.setPlayerY(getPlayerY() - 1);
			this.setPlayerX(getPlayerX()-1);
			mhomovement();
			isOnMho();
			isOnFence();

			if (mhosKilled == 12)
				gameWon();
		}
		//Moves player up
		if (e.getKeyCode() == KeyEvent.VK_W) {
			this.setPlayerY(getPlayerY() - 1);
			mhomovement();
			isOnMho();
			isOnFence();
			if (mhosKilled == 12)
				gameWon();
		}
		//Moves player up and right
		if (e.getKeyCode() == KeyEvent.VK_E) {
			this.setPlayerY(getPlayerY() - 1);
			this.setPlayerX(getPlayerX() +1);
			mhomovement();
			isOnMho();
			isOnFence();			
			if (mhosKilled == 12)
				gameWon();
		}
		//Moves player left
		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.setPlayerX(getPlayerX()-1);
			mhomovement();
			isOnMho();
			isOnFence();			
			if (mhosKilled == 12)
				gameWon();
		}
		//Moves player no where
		if (e.getKeyCode() == KeyEvent.VK_S) {
			this.setPlayerX(getPlayerX());
			this.setPlayerY(getPlayerY());
			mhomovement();
			isOnMho();
			isOnFence();			
			if (mhosKilled == 12)
				gameWon();
		}
		//Moves player right
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.setPlayerX(getPlayerX() +1);
			mhomovement();
			isOnMho();
			isOnFence();			
			if (mhosKilled == 12)
				gameWon();
		}
		//Moves player down and left
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			this.setPlayerY(getPlayerY() + 1);
			this.setPlayerX(getPlayerX()-1);
			mhomovement();
			isOnMho();
			isOnFence();			
			if (mhosKilled == 12)
				gameWon();
		}
		//Moves player down
		if (e.getKeyCode() == KeyEvent.VK_X) {
			this.setPlayerY(getPlayerY() + 1);
			mhomovement();
			isOnMho();
			isOnFence();			
			if (mhosKilled == 12)
				gameWon();
		}
		//Moves player down and right
		if (e.getKeyCode() == KeyEvent.VK_C) {
			this.setPlayerY(getPlayerY() + 1);
			this.setPlayerX(getPlayerX() +1);
			mhomovement();
			isOnMho();
			isOnFence();			
			if (mhosKilled == 12)
				gameWon();
		}
		//Moves player to random location on board -- player has possibility of landing on fence and dying
		if (e.getKeyCode() == KeyEvent.VK_J) {
			for(int i = 0; i < 1; i++){
				int[] tempval = RandJump();
				this.setPlayerX(tempval[0]);
				this.setPlayerY(tempval[1]);
			}
			isOnMho();
			isOnFence();
		}


		System.out.println("move");
		System.out.println("(" + getPlayerX() + ", " + getPlayerY() + ")");
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO SOME GOD DAMN COMMENTS
		char key = e.getKeyChar();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO SOME GOD DAMN COMMENTS
		char key = e.getKeyChar();

	}

// TODO some comments please
	public void mhomovement() {
		/**
		 * 
		 */
		for (int i = 0; i < 12; i++) {
			//vertical movement
			if (playerX == mhox[i]) {
				if (playerY > mhoy[i]) {
					mhoy[i] = mhoy[i] + 1;
					mhoIsOnFence();
				}
				if (playerY < mhoy[i]) {
					mhoy[i] = mhoy[i] - 1;
					mhoIsOnFence();
				}
			}
			//horizontal movement
			if (playerY == mhoy[i]) {
				if (playerX > mhox[i]) {
					mhox[i]++;
					mhoIsOnFence();
				}
				if (playerX < mhox[i]) {
					mhox[i]--;
					mhoIsOnFence();
				}
			}
			//diagonal movement
			else if (playerX < mhox[i] && playerY > mhoy[i]) {
				mhoy[i]++;
				mhox[i]--;
				mhoIsOnFence();
			}
			else if (playerX > mhox[i] && playerY < mhoy[i]) {
				mhoy[i]--;
				mhox[i]++;
				mhoIsOnFence();
			}
			else if (playerX < mhox[i] && playerY < mhoy[i]) {
				mhoy[i]--;
				mhox[i]--;
				mhoIsOnFence();
			}
			else if (playerX > mhox[i] && playerY > mhoy[i]) {
				mhoy[i]++;
				mhox[i]++;
				mhoIsOnFence();
			}

		}



	}
}



