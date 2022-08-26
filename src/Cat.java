import java.awt.Color;

public class Cat {
	// data fields of the cat
	private int x;
	private int y;
	private Color color;
	private int foodCount;

	Cat(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x2) {
		x = x2;
	}

	public void setY(int y2) {
		y = y2;
	}

	public int getY() {
		return y;
	}

	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}

	public int getFoodCount() {
		return foodCount;
	}

	// draw the cat as a circle
	public void draw() {
		double xCenter = x + 0.5;
		double yCenter = y + 0.5;
		double radius = 0.4;
		StdDraw.setPenColor(color);
		StdDraw.setPenRadius(radius);
		StdDraw.filledCircle(xCenter, yCenter, radius);
	}

	
}
