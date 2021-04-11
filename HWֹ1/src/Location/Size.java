package Location;

public class Size {
	private int width;
	private int height;
	
	public Size() {
       width = height = 0;
	}
	public Size(int width, int height) {
	   this.width = width;
	   this.height = height;
	}
	public void Setwidth(int width) {
		this.width = width;
	}
	public void Setheight(int height) {
	    this.height = height;
	}
	public int Getwidth() {
	    return width;
	}
	public int Getheight() {
	    return height;
	}
	public String toString() {
		return ("(h:"+height+ ", w:"+width+")");
	}

}
