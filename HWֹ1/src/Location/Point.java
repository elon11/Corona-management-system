package Location;
import java.lang.Math;

public class Point {
	private int x;
	private int y;
	
	public Point() {
	x = y = 0;
	}
	public Point(int x, int y) {
	this.x = x;
	this.y = y;
	}
	public void SetX(int x) {
		this.x = x;
	}
	public void SetY(int y) {
	this.y = y;
	}
	public int GetX() {
	return x;
	}
	public int GetY() {
	return y;
	}
	public String toString() {
		return "("+x+","+y+")";
	}
	public double getDistance(Point point) {
		double distance;
		distance = Math.sqrt(Math.pow((this.GetX()- point.GetX()), 2)+Math.pow((this.GetY()- point.GetY()), 2));
		return distance;
	}

}
