package personal_PONG;

import java.awt.event.KeyEvent;

public class PlayerController {
	Rect player;
	KL keyListener;
	
	public PlayerController(Rect player, KL keyListener) {
		this.player = player;
		this.keyListener = keyListener;
	}
	
	public PlayerController(Rect ai) {
		this.player = ai;
		this.keyListener = null;
	}

	public void update(double dt) {
		if (keyListener != null) {
			if (keyListener.isKeyPressed(KeyEvent.VK_UP)) { 
				System.out.println("UP");
				this.player.moveUP(dt);
			} else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) { 
				System.out.println("DOWN");
				this.player.moveDOWN(dt);
			}
		}
	}
}
