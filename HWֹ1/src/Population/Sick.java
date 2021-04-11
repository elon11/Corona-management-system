package Population;
import Location.Location;
import Simulation.Clock;
import Country.Settlement;
import Virus.IVirus;

public class Sick extends Person {
	private long contagiousTime;
	private IVirus virus;
	
	
	
	public Sick( int age,Location location,Settlement settlement, long contagiousTime, IVirus virus) {
		super(age,location,settlement);
		this.contagiousTime = contagiousTime;
		this.virus = virus;
		this.settlement.CalculateRamzorGrade();
		
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
	
	
	public double ContagionProbability(){
		return 0;
		
	}
	public  Person Contagion(IVirus ivirus) {
		System.out.println("A sick person cannot get sick again");
		return null;
	}
	
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

}
