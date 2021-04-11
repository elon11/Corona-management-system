package Population;
import Location.Location;
import Simulation.Clock;
import Country.Settlement;
import Virus.IVirus;

public class Convalescent extends Person {
	
private IVirus  virus;
	
	public Convalescent( int age,Location location,Settlement settlement, IVirus virus) {
		super(age,location,settlement);
		this.virus = virus;
		
	}

	
	public  IVirus getVirus(){
		return this.virus;
	}
	public  void setVirus (IVirus  virus){
		this.virus = virus;
	}
	
	public  double ContagionProbability(){
		return 0.2;
	}
	public  Person Contagion(IVirus ivirus) {
		Sick p = new Sick( this.getAge(),this.Getlocation(),this.Getsettlement(), Clock.now(), ivirus);
		return p;
	}
	
    public String toString(){
    	return "virus:"+this.virus+ super.toString();
	}
}
