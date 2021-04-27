/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * The ward came to describe a sick man. A sick person cannot get sick again
 */
package Population;
import Location.Location;
import Country.Settlement;
import Virus.IVirus;

public class Sick extends Person {
	private long contagiousTime;
	private IVirus virus;
	
	
	
	public Sick( int age,Location location,Settlement settlement, long contagiousTime, IVirus virus) {
		super(age,location,settlement);
		this.contagiousTime = contagiousTime;
		this.virus = virus;	
	}
	
	
	public  long getContagiousTime(){
		return this.contagiousTime;
	}
	public  void setContagiousTime (long contagiousTime){
		this.contagiousTime = contagiousTime;
	}
	
	public  IVirus getVirus(){
		return this.virus;
	}
	public  void setVirus (IVirus virus){
		this.virus = virus;
	}
	
	/*
	 * @inheritDoc
	 */
	@Override
	public double ContagionProbability(){
		return 1;
		
	}
	
	/*
	 * @inheritDoc
	 */
	@Override
	public  Person Contagion(IVirus ivirus) {
		System.out.println("A sick person cannot get sick again");
		return null;
	}
	
	/*
	 * A method that returns a copy of data of the current person while making him a healthy person.
	 */
	public Person Recover() {
		Convalescent p = new Convalescent(this.getAge(),this.Getlocation(),this.Getsettlement(), this.getVirus());
		return p;
		
	}
	
	
	public boolean tryToDie() {
		return this.virus.tryToKill(this);
	}

    public String toString(){
    	
    	return  "contagiousTime:"+this.contagiousTime +", virus:"+this.virus+", "+ super.toString();
	}
    public boolean equals(Object o) {
		if (!(o instanceof Sick))
		   return false;
		Sick n = (Sick)o;
		if( this.contagiousTime == n.contagiousTime &&  this.virus.equals(n.virus)&& super.equals(o))
			return true;
		return false;
	}

}
