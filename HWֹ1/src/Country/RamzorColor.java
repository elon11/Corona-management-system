/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 */
package Country;

public enum RamzorColor {

	Green (0.4), Yellow (0.6), Orange (0.8), Red (1) ;
	

	RamzorColor(double d) {
		this.d = d;
	}
	
    
	
	
	
	private final double d;

	public double getValue() {
		return d;
	}

	
}
