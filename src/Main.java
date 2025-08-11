package personal_PONG;

public class Main {
	
	public static int state = 0;
	public static Thread mainThread;
	public static Window window;
	
	public static void main(String[] args) {
		
		//Window window = new Window();
		
		//create thread to run the window
		window = new Window();
		//window = new Window();
		mainThread = new Thread(window);
		mainThread.start();
	}

}
