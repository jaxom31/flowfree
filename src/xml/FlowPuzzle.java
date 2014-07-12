package xml;

import java.util.ArrayList;

public class FlowPuzzle {
	
	private int width = 0 ;
	private int height = 0 ;

	ArrayList<Line> lines = new ArrayList<Line>() ;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}
	
}
