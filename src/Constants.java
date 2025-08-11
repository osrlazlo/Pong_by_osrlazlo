/*
 * Contains all constant values for easy maintenance
 */
package personal_PONG;

import java.awt.Color;

public class Constants {
	//Screen constants
	public final static int SCREEN_WIDTH = 800;
	public final static int SCREEN_HEIGHT = 600;
	public final static String SCREEN_TITLE = "PONG";
	public final static Color SCREEN_COLOR = Color.BLACK;
	public final static double PADDING = 50;
	public final static Color TEXT_COLOR = Color.white;
	public final static int FONT_SIZE = 28;
	public final static int FINAL_SCORE = 5;
	
	//Player constants
	public final static Color PLAYER_COLOR = Color.WHITE;
	public final static double PLAYER_WIDTH = 15;
	public final static double PLAYER_HEIGHT = 70;
	public final static double PLAYER_SPEED = 200;
	public final static double AI_SPEED = 180;
	
	//Ball constants
	public final static Color BALL_COLOR = Color.WHITE;
	public final static double BALL_WIDTH = 15;
	public static double BALL_X = SCREEN_WIDTH/2 - BALL_WIDTH/2;
	public static double BALL_Y = SCREEN_HEIGHT/2 - BALL_WIDTH/2;
	public static double BALL_SPEED = 300;
	public final static double MAX_ANGLE = 130;

}
