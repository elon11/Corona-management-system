/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * The department came to describe a vaccinated person. A vaccinated person can get sick after the vaccine!
 */
package Population;
import Location.Location;
import Country.Settlement;
import Virus.IVirus;
import java.lang.Math;
import Simulation.Clock;

public class Vaccinated extends Person  {
	
	private long vaccinationTime;
	
	public Vaccinated( int age,Location location,Settlement settlement, long contagiousTime) {
		super(age,location,settlement);
		this.vaccinationTime = Clock.now();
	}
	
	
	public  long getVaccinationTime(){
		return this.vaccinationTime;
	}
	public  void setVaccinationTime (long vaccinationTime){
		this.vaccinationTime = vaccinationTime;
	}
	
	/*
	 * @inheritDoc
	 */
	@Override
	public  double ContagionProbability(){
		double x = 0.0;
		if(Clock.now() - this.getVaccinationTime() < 21)
			x = Math.min(1.0, 0.56+0.15*Math.sqrt(21 - this.getVaccinationTime()));
		else
			x = Math.max(0.05, 1.05/(this.getVaccinationTime() - 14));
		return x;
	}
	
	/*
	 * @inheritDoc
	 */
	@Override
	public  Person Contagion(IVirus ivirus) {
		Sick p = new Sick( this.getAge(),this.Getlocation(),this.Getsettlement(), Clock.now(), ivirus);
		return p;
		
	}
	
    public String toString(){
    	return "vaccinationTime:"+this.vaccinationTime+ super.toString();
	}
    
    public boolean equals(Object o) {
		if (!(o instanceof Vaccinated))
		   return false;
		Vaccinated n = (Vaccinated)o;
		if( this.vaccinationTime == n.vaccinationTime  && super.equals(o))
			return true;
		return false;
	}

}
