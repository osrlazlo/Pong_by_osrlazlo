package personal_PONG;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL implements KeyListener {

	private boolean[] keyPressed = new boolean[128];
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keyPressed[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keyPressed[e.getKeyCode()] = false;
	}
	
	public boolean isKeyPressed(int keyCode) {
		return keyPressed[keyCode];
	}

}
