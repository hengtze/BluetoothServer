import processing.core.*;

public class ProcessingPlot extends PApplet {
	public void setup() {
		size(1200,800);
		background(0);	
//		noStroke();
//		smooth();
	}
	public void draw() {
		stroke(255);

		if (WaitThread.isConnected) {
			background(0);
			for (int i=0; i<99; i++) {				
				line( i*10, (int)(ProcessConnectionThread.dataArray[i]*100)+300, 
						(i+1)*10, (int)(ProcessConnectionThread.dataArray[i+1]*100)+300
						);
				//println(ProcessConnectionThread.dataArray[i]);
			}
		}
		//		if (mousePressed) {
		//			line(mouseX, mouseY, pmouseX, pmouseY);
		//		}
	}
}
