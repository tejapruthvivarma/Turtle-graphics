package turtle;

import java.awt.geom.Point2D;

public class Turtle {
	private int degrees;
	private Point2D point;
	private boolean isPenDown;

	public Turtle() {
		this.degrees = 0;
		this.point = new Point2D.Double(0.0, 0.0);
		this.isPenDown = false;
	}

	public void move(int distance) {
		double radians = Math.PI * ((double) degrees / 180);
		double deltaX = Math.cos(radians) * distance;
		double deltaY = Math.sin(radians) * distance;
		double x = point.getX() + deltaX;
		double y = point.getY() + deltaY;
		point.setLocation(x, y);
	}

	public void turn(int degrees) {
		this.degrees += degrees;
	}

	public void penUp() {
		this.isPenDown = false;
	}

	public void penDown() {
		this.isPenDown = true;
	}

	public boolean isPenUp() {
		return !isPenDown;
	}

	public boolean isPenDown() {
		return this.isPenDown;
	}

	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}

	public int direction() {
		return 0;
	}

	public Point2D location() {
		return point;

	}

}
