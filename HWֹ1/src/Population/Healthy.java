/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * The department came to describe a Healthy person.
 */
package Population;
import Simulation.Clock;
import Location.Location;

import Country.Settlement;
import Virus.IVirus;

public class Healthy extends Person {
	
	
	public Healthy(int age,Location location,Settlement settlement) {
		super(age,location,settlement);
		
	}
	
	/*
	 * A method that returns a copy of data of the current person while making him a vaccinated person.
	 */
    public Person vaccinate() {
    	Vaccinated new_person = new Vaccinated(this.getAge(),this.Getlocation(),this.Getsettlement(),Clock.now());
    	return new_person;
    	
		
	}
    
    /*
	 * @inheritDoc
	 */
    @Override
    public  Person Contagion(IVirus ivirus) {
    	
    	Sick p = new Sick( this.getAge(),this.Getlocation(),this.Getsettlement(), Clock.now(), ivirus);
		return p;
		
    }
    /*
	 * @inheritDoc
	 */
    @Override
    public double ContagionProbability(){
		return 1.0;
	}
    @Override
    public String toString(){
    	return super.toString();
	}
    
    public boolean equals(Object o) {
		if (!(o instanceof Healthy))
		   return false;
		return  super.equals(o);
	}

}
