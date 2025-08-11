package personal_PONG;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rect {
	private double x,y,width,height, speed;
	private Color color;
	public Rect(double x, double y, double width, double height, Color color, double speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fill(new Rectangle2D.Double(x,y,width,height));
	}
	
	public void moveUP(double dt) {
		if (this.y > Constants.PADDING)
		this.y -= speed*dt;
	}
	
	public void moveDOWN(double dt) {
		if (this.y+this.height < Constants.SCREEN_HEIGHT - Constants.PADDING)
		this.y += speed*dt;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	public double getCenterY() {
		return this.y+this.height/2;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	
	
	
}
