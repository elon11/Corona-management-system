  
package Location;


public class Location {
	
	private Point point;
	private Size size;
	
	public Location() 
	{
		this.point = new Point(0,0);
		this.size = new Size(0,0);
	}
	
	public Location(Point point, Size size)
	{
		this.point = new Point(point.GetX(),point.GetY());
		this.size = new Size(size.Getheight(),size.Getwidth());
	}
	

	public void SetPoint(Point point) {
		this.point = point;
	}
	public Point GetPoint() {
		return this.point;
	}
	public void SetSize(Size size) {
		this.size = size;
	}
	public Size GetSize() {
		return this.size;
	}

	
	public String toString() {
		return "Point:"+point.toString()+" ,Size:"+size.toString();
	}

}
