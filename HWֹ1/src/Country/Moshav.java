/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * The department describes a locality on a map
 */
package Country;

import java.util.List;

import Location.Location;
import Population.Person;

public class Moshav extends Settlement {
	public Moshav(String name,Location location,RamzorColor color, List<Person> healthy,List<Person> sick,int max_people,int vaccine_doses,Settlement [] neighbors) {
		super( name, location, color,  healthy, sick, max_people, vaccine_doses, neighbors);
	}
	
	

	/*
	 * { @inheritDoc } 
	 */
	@Override
	public  RamzorColor CalculateRamzorGrade() {
		double num;
		
		num = 0.3 + 3*Math.pow(Math.pow(1.2, this.color.getValue())*(ContagiousPercent()-0.35),5);
		if(num <=0.4)
			this.color = RamzorColor.Green;
		if(num >0.4 && num<=0.6)
			this.color = RamzorColor.Yellow; 
		if(num >0.6 && num <=0.8)
			this.color = RamzorColor.Orange; 
		if(num >0.8)
			this.color = RamzorColor.Red;
		
		
		return this.color;
	}
	
	
	public String toString(){
    	return  super.toString();
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Moshav))
		   return false;
		return true;
	}
	
	

}

