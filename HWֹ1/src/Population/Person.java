/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * A class that describes a person
 */
package Population;

import Country.Settlement;
import Location.*;
import Virus.*;

public abstract class Person {
	protected int age;
	protected Location location;
	protected Settlement settlement;
	
	/*
	 * An abstract method that returns a coefficient of probability for virus-independent infection
	 */
	public abstract double ContagionProbability(); 

	/*
	 * A method that returns a copy of data of the current person while turning it into a person with the transmitted virus.
	 */
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
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Person))
		   return false;
		Person n = (Person)o;
		if(this.age==n.age && this.location.equals(n.location)&& this.settlement.equals(n.settlement))
			return true;
		return false; 
	}

}
