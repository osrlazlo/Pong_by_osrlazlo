package personal_PONG;

import java.util.Random;

public class AIController {
	public PlayerController controller;
	public Rect ball;
	Random random = new Random();
	
	public AIController(PlayerController controller, Rect ball) {
		this.controller = controller;
		this.ball = ball;
	}
	
	public void update(double dt) {
		
		controller.update(dt);
		
		if (ball.getY() < controller.player.getY()) {
			controller.player.moveUP(dt);
		} else if (ball.getY()+ball.getHeight() > controller.player.getY()+controller.player.getHeight()) {
			controller.player.moveDOWN(dt);
		}
	}

}
