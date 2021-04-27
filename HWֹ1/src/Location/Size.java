/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * The class describes the width and height of a place
 */
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
		return ("(w:"+width+ ", h:"+height+")");
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Size))
		   return false;
		Size n = (Size)o;
		if(this.height == n.height && this.width == n.width )
			return true;
		return false;
	}

}
