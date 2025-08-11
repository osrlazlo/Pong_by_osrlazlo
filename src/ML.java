package personal_PONG;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ML extends MouseAdapter implements MouseMotionListener{
	
	public boolean isPressed = false;
	public double x=0, y=0;
	
	public void mousePressed(MouseEvent e) {
		isPressed = true;
	}
	
	public void mouseReleased(MouseEvent e) {
		isPressed = false;
	}
	
	public void mouseMoved(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}
	
	public double getMouseX() {
		return this.x;
	}
	
	public double getMouseY() {
		return this.y;
	}
	
	public boolean isMousePressed() {
		return this.isPressed;
	}
	

}
