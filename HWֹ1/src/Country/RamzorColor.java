package Country;

public enum RamzorColor {

	Green (0.4), Yellow (0.6), Orange (0.8), Red (1) ;

	RamzorColor(double d) {
		// TODO Auto-generated constructor stub
		//מה צריך לעשות פה?
	}
	
	public double getValue(RamzorColor color) {
		return color.getValue(color);
			
	}

	
}
