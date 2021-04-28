/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 */

package Country;

import java.awt.Color;

public enum RamzorColor {

	Green (0.4,Color.green,1.0), 
	Yellow (0.6, Color.yellow,0.8), 
	Orange (0.8, Color.orange,0.6),
	Red (1.0,Color.red,0.4) ;
	
	

	RamzorColor(double d,Color color,double number) {
		this.d = d;
		this.color= color;
		this.number=number;
	}
	
	
	RamzorColor(double d) {
		this.d = d;
		
	}


	private double d;
	private Color color;
	private double number;

	public double getValue() {
		return d;
	}

	public Color getcolor() {
		return color;
	}
	public double getnumber() {
		return number;
	}
}
