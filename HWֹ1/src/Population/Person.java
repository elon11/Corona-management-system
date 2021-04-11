
package Population;

import Country.Settlement;
import Location.*;
import Virus.*;

public abstract class Person {
	protected int age;
	protected Location location;
	protected Settlement settlement;
	
	public abstract double ContagionProbability(); 

	public  abstract Person Contagion(IVirus ivirus) ;

	
	public Person (int age , Location location , Settlement settlement) {
		this.age = age;
		this.location = location;
		this.settlement = settlement;
	}
	public void Setage(int age) {
	this.age = age;
	}
	public int getAge() {
	return age;
	}
	public void Setlocation(Location location) {
		this.location = location;
	}
	public Location Getlocation() {
	return location;
	}
	public void Setlocation(Settlement settlement) {
		this.settlement = settlement;
	}
	public Settlement Getsettlement() {
	return settlement;
	}
	@Override
	public String toString() {
		
	    return "age:"+this.age +", Location"+ this.location.toString() +", Settlement:"+ this.settlement.getName();
	}

}
