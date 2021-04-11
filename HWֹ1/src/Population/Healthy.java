package Population;
import Simulation.Clock;
import Location.Location;

import Country.Settlement;
import Virus.IVirus;

public class Healthy extends Person {
	
	
	public Healthy(int age,Location location,Settlement settlement) {
		super(age,location,settlement);
		
	}
	
	
    public Person vaccinate() {
    	Vaccinated new_person = new Vaccinated(this.getAge(),this.Getlocation(),this.Getsettlement(),Clock.now());
    	return new_person;
		
	}
    public  Person Contagion(IVirus ivirus) {
    	
    	Sick p = new Sick( this.getAge(),this.Getlocation(),this.Getsettlement(), Clock.now(), ivirus);
		return p;
    }
    public double ContagionProbability(){
		return 1.0;
	}
    @Override
    public String toString(){
    	return super.toString();
	}

}
