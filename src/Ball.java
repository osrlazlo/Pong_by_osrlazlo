package personal_PONG;

import java.awt.Font;
import java.util.Random;

public class Ball {
	public Rect ball, player1, player2;
	public Window window;
	public Random random = new Random();
	
	private double vy = 0;
	private double vx = -Constants.BALL_SPEED;
	Font font = new Font("Times New Roman", Font.PLAIN, 60);
	
	public Ball(Rect ball, Rect player1, Rect player2, Window window) {
		this.ball = ball;
		this.player1 = player1;
		this.player2 = player2;
		this.window = window;
	}
	
	public double calculateNewAngle(Rect player) {
		double playerCenter = player.getCenterY();
		double ballCenter = ball.getCenterY();
		double intersectY = playerCenter-ballCenter;
		double normalIntersectY = intersectY/player.getHeight()/2;
		double theta = normalIntersectY*Constants.MAX_ANGLE;
		return Math.toRadians(theta);
	}
	
	public void update(double dt) {
		
		double sign = Math.signum(vx);
		
		if (vx < 0.0) {
			if (this.ball.getX() <= this.player1.getX() + this.player1.getWidth() && this.ball.getX() >= this.player1.getX() && 
					this.ball.getY()+this.ball.getHeight() >= this.player1.getY() && this.ball.getY() <= this.player1.getY()+this.player1.getHeight()) {
			
			double theta = calculateNewAngle(player1);
			System.out.println(theta);
			double newVX = Math.abs(Math.cos(theta)*Constants.BALL_SPEED);
			double newVY = (-Math.sin(theta))*Constants.BALL_SPEED;
	
			this.vx = newVX*(-1*sign);
			this.vy = newVY;
			
			}
			else if (this.ball.getX() + this.ball.getWidth() < this.player1.getX()) {
				System.out.println("Player1 Lost!");
				Window.setPlayer2Score(Window.getPlayer2Score()+1);
				if (Window.getPlayer2Score() >= Constants.FINAL_SCORE) {
					System.out.println("PLAYER 2 WINS!");
					Window.isGameOver = true;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				this.resetPositions(sign);
			}
		} else if (vx > 0.0) {
			if (this.ball.getX()+this.ball.getWidth() >= this.player2.getX() && this.ball.getX() <= this.player2.getX() + this.player2.getWidth() && 
					this.ball.getY()+this.ball.getHeight() >= this.player2.getY() && this.ball.getY() <= this.player2.getY()+this.player2.getHeight()) {
				double theta = calculateNewAngle(player2);
				System.out.println(theta);
				double newVX = Math.abs(Math.cos(theta)*Constants.BALL_SPEED);
				double newVY = (-Math.sin(theta))*Constants.BALL_SPEED;
				
				this.vx = newVX*(-1*sign);
				this.vy = newVY;
				
				int speedPct = random.nextInt(30);
				double ratio = (100.0-speedPct)/100.0; 
				player2.setSpeed(Constants.AI_SPEED*ratio);
				System.out.println("SPEED"+player2.getSpeed());
			}
			else if (this.ball.getX() + this.ball.getWidth() > this.player2.getX() + this.player2.getWidth()) {
				System.out.println("Player2 Lost!");
				Window.setPlayer1Score(Window.getPlayer1Score()+1);
				if (Window.getPlayer1Score() >= Constants.FINAL_SCORE) {
					System.out.println("PLAYER 1 WINS!");
					Window.isGameOver = true;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				this.resetPositions(sign);
			}
		}
		
		if (vy > 0) {
			if (this.ball.getY() + this.ball.getHeight() > Constants.SCREEN_HEIGHT-Constants.PADDING) {
				this.vy *= -1;
			} 
		} else if (vy < 0) {
			if (this.ball.getY() < Constants.PADDING) {
				this.vy *= -1;
			}
		}
		
		this.ball.setX(this.ball.getX()+(vx*dt));
		this.ball.setY(this.ball.getY()+(vy*dt));
	}
	
	public void resetPositions(double sign) {
		player1.setY(Constants.SCREEN_HEIGHT/2-player1.getHeight()/2);
		player2.setY(Constants.SCREEN_HEIGHT/2-player2.getHeight()/2);
		this.ball.setX(Constants.BALL_X);
		this.ball.setY(Constants.BALL_Y);
		this.vx = (-sign)*Constants.BALL_SPEED;
		this.vy = 0;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	
}
