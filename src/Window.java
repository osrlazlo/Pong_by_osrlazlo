/*
 *extends JFrame and implements Runnable -> runs the window on a separate thread
 */
package personal_PONG;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {
	
	Graphics2D g2;
	public KL keyListener = new KL();
	public Rect player1, player2, ballRect;
	PlayerController playerController, aiController;
	AIController ai;
	Ball ball;
	public Text player1ScoreTxt, player2ScoreTxt;
	private static int player1Score = 0, player2Score = 0;
	public static boolean isGameRunning = false, isGameOver = false;
	
	
	
	Text title, startGame, endGame;
	ML mouseListener = new ML();
	Font font = new Font("Times New Roman", Font.PLAIN, 40);
	Font titleFont = new Font("Times New Roman", Font.BOLD, 100);
	
	public Window() {
		
		this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		this.setTitle(Constants.SCREEN_TITLE);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(keyListener);
		g2 = (Graphics2D) this.getGraphics();
		
		//MainMenu elements
		this.addKeyListener(keyListener);
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		
		title = new Text("PONG", titleFont, Constants.SCREEN_WIDTH/2 - 150, Constants.SCREEN_HEIGHT/2-80);
		startGame = new Text("START GAME", font, Constants.SCREEN_WIDTH/2 - 134, Constants.SCREEN_HEIGHT/2);
		endGame = new Text("EXIT", font, Constants.SCREEN_WIDTH/2 - 50, Constants.SCREEN_HEIGHT/2+60);
		
		
		
		player1 = new Rect(Constants.PADDING, Constants.SCREEN_HEIGHT/2 - Constants.PLAYER_HEIGHT/2, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT, Constants.PLAYER_COLOR, Constants.PLAYER_SPEED);
		player2 = new Rect(Constants.SCREEN_WIDTH-Constants.PADDING, Constants.SCREEN_HEIGHT/2 - Constants.PLAYER_HEIGHT/2, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT, Constants.PLAYER_COLOR, Constants.AI_SPEED);
		ballRect = new Rect(Constants.BALL_X, Constants.BALL_Y, Constants.BALL_WIDTH, Constants.BALL_WIDTH, Constants.BALL_COLOR, Constants.BALL_SPEED);
		ball = new Ball(ballRect, player1, player2, this);
		
		playerController = new PlayerController(player1, keyListener);
		aiController = new PlayerController(player2);
		ai = new AIController(aiController, ballRect);
		Font scoreFont = new Font("Times New Roman", Font.PLAIN, Constants.FONT_SIZE);
		
		player1ScoreTxt = new Text(player1Score, scoreFont, Constants.SCREEN_WIDTH/2-Constants.FONT_SIZE, Constants.PADDING);
		player2ScoreTxt = new Text(player2Score, scoreFont, Constants.SCREEN_WIDTH/2, Constants.PADDING);
	}
	
	public void updateGame(double dt) {
		
		//System.out.println(dt+" seconds passed");
		//System.out.println(1/dt+" fps");
		
		Image dbImage = createImage(getWidth(), getHeight());
		Graphics dbg = dbImage.getGraphics();
		this.drawGame(dbg);
		g2.drawImage(dbImage, 0,0, this);
		
		playerController.update(dt);
		ai.update(dt);
		ball.update(dt);
		player1ScoreTxt.update(player1Score);
		player2ScoreTxt.update(player2Score);
		
		if (isGameOver) {
			player1ScoreTxt.update(player1Score);
			player2ScoreTxt.update(player2Score);
			
			this.drawGame(dbg);
			g2.drawImage(dbImage, 0,0, this);
			
			try { 
				Thread.sleep(3000);
			} catch (Exception e) {}
			
			this.stopGame();
		}
	}
	
	public void drawGame(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Constants.SCREEN_COLOR);
		g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		player1.draw(g2);
		player2.draw(g2);
		ballRect.draw(g2);
		player1ScoreTxt.draw(g2);
		player2ScoreTxt.draw(g2);
		
		if (isGameOver) {
			String winner = (player1Score > player2Score) ? "PLAYER 1 WINS!":"PLAYER 2 WINS!";
			Text winnerTxt = new Text (winner, font, Constants.SCREEN_WIDTH/2 - 140, Constants.SCREEN_HEIGHT/2-40);
			winnerTxt.draw(g2);
		}
		
	}
	
	public void startGame() {
		isGameRunning = true;
		isGameOver = false;
		ball.setVy(0);
		ball.setVx(-Constants.BALL_SPEED);
	}
	
	public void stopGame() {
		isGameRunning = false;
		isGameOver = false;
		player1Score = 0;
		player2Score = 0;
		//resetPositions();
	}
	
	public void resetPositions() {
		player1.setY(Constants.SCREEN_HEIGHT/2-player1.getHeight()/2);
		player2.setY(Constants.SCREEN_HEIGHT/2-player2.getHeight()/2);
		ballRect.setX(Constants.SCREEN_WIDTH/2-ballRect.getWidth()/2);
		ballRect.setY(Constants.SCREEN_HEIGHT/2-ballRect.getHeight()/2);
	}
	
public void updateMainMenu(double dt) {
		
		//System.out.println(dt+" seconds passed");
		//System.out.println(1/dt+" fps");
		
		Image dbImage = createImage(getWidth(), getHeight());
		Graphics dbg = dbImage.getGraphics();
		this.drawMainMenu(dbg);
		g2.drawImage(dbImage, 0,0, this);
		
		//System.out.println("X:"+startGame.x);
		//System.out.println("Y:"+mouseListener.getMouseY());
		
		if (mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x+startGame.width &&
				mouseListener.getMouseY() > startGame.y-startGame.height && mouseListener.getMouseY() < startGame.y+startGame.height/2) {
			startGame.hover();
			if (mouseListener.isPressed) {
				this.startGame();
			}
		} else startGame.resetColor();
		

		if (mouseListener.getMouseX() > endGame.x && mouseListener.getMouseX() < endGame.x+endGame.width &&
				mouseListener.getMouseY() > endGame.y-endGame.height/2 && mouseListener.getMouseY() < endGame.y+endGame.height/2) {
			endGame.hover();
			if (mouseListener.isPressed) {
				System.exit(0);
			}
		} else endGame.resetColor();
		
	}
	
	public void drawMainMenu(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Constants.SCREEN_COLOR);
		g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		title.draw(g2);
		startGame.draw(g2);
		endGame.draw(g2);
		
	}
	
	@Override
	public void run() {
		double lastFrameTime = 0.0;
		
		//game loop
		while (true) {
			double time = Time.getTime();
			double dt = time - lastFrameTime;
			lastFrameTime = time;
			
			if (isGameRunning) updateGame(dt);
			else updateMainMenu(dt);
			
			try {Thread.sleep(30);} catch (Exception e) {}
		}
	}

	public static int getPlayer1Score() {
		return player1Score;
	}

	public static void setPlayer1Score(int newScore) {
		player1Score = newScore;
	}

	public static int getPlayer2Score() {
		return player2Score;
	}

	public static void setPlayer2Score(int newScore) {
		player2Score = newScore;
	}
	
	public void mainMenu() {
		
		
	
	}
	
	
}
