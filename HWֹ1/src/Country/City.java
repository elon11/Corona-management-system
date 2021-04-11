
package Country;

import java.util.List;

import Location.Location;
import Population.Person;

public class City extends Settlement {
	public City(String name,Location location,RamzorColor color, List<Person> listp) {
		super( name, location, color,  listp);
	}
	
	@Override
	public  RamzorColor CalculateRamzorGrade() {
		double num;
		num = 0.2*Math.pow(4, (1.25*ContagiousPercent()));
		if(num <=0.4)
			this.color = RamzorColor.Green;
		if(num >0.4 && num<=0.6)
			this.color = RamzorColor.Yellow; 
		if(num >0.6 && num <=0.8)
			this.color = RamzorColor.Orange; 
		if(num >0.8)
			this.color = RamzorColor.Red; 
		
		this.setColor(color);
		return this.color;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String toString(){
    	return  super.toString();
	}

}

