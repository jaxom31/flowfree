package xml;

public class Line {
	
	private String color = "none" ;
	private int xSrc, ySrc,xDest, yDest ;
	/**
	 * @return the yDest
	 */
	public int getyDest() {
		return yDest;
	}
	/**
	 * @param yDest the yDest to set
	 */
	public void setyDest(int yDest) {
		this.yDest = yDest;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getxSrc() {
		return xSrc;
	}
	public void setxSrc(int xSrc) {
		this.xSrc = xSrc;
	}
	public int getySrc() {
		return ySrc;
	}
	public void setySrc(int ySrc) {
		this.ySrc = ySrc;
	}
	/**
	 * @return the xDest
	 */
	public int getxDest() {
		return xDest;
	}
	/**
	 * @param xDest the xDest to set
	 */
	public void setxDest(int xDest) {
		this.xDest = xDest;
	}
	

}
