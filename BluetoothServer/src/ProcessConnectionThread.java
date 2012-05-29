import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable{

	private StreamConnection mConnection;

	// Constant that indicate command from devices
	private static final int EXIT_CMD = -1;
	private static final int KEY_RIGHT = 1;
	private static final int KEY_LEFT = 2;
	
	public static double [] dataArray;
	private static int idx;
	BufferedWriter out = null;
	
	public ProcessConnectionThread(StreamConnection connection)
	{
		dataArray = new double [100];
		idx = 0;
		try {
			out = new BufferedWriter(new FileWriter("/Users/hengtze/Dropbox/ProjectChar/Experiment/sensor_data.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mConnection = connection;
	}

	@Override
	public void run() {
		try {
			// prepare to receive data
			DataInputStream inputStream = mConnection.openDataInputStream();

			System.out.println("waiting for input");

			while (true) {
				double inputData = inputStream.readDouble();

				if (inputData == EXIT_CMD)
				{
					System.out.println("finish process");
					break;
				}
				processCommand(inputData);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Process the command from client
	 * @param command the command code
	 */
	private void processCommand(double inputData) {
		System.out.println(inputData);
		dataArray[idx] = inputData;
		if (idx+1 >= 100) {
			idx = 0;
		}
		else {
			idx++;
		}
		try {
			out = new BufferedWriter(new FileWriter("/Users/hengtze/Dropbox/ProjectChar/Experiment/sensor_data.txt"));			
			out.write(String.valueOf(inputData)+"\n");
			out.flush();
			out.close();
		} catch (IOException e) {
		}
		
//		try {
//			Robot robot = new Robot();
//			switch (command) {
//	    		case KEY_RIGHT:
//	    			robot.keyPress(KeyEvent.VK_RIGHT);
//	    			System.out.println("Right");
//	    			break;
//	    		case KEY_LEFT:
//	    			robot.keyPress(KeyEvent.VK_LEFT);
//	    			System.out.println("Left");
//	    			break;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
