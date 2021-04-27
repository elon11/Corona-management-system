/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * The department describes a specific point in the field
 */
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
	
	public boolean equals(Object o) {
		if (!(o instanceof Point))
		   return false;
		Point n = (Point)o;
		if(this.x == n.x && this.y == n.y )
			return true;
		return false;
	}

}
