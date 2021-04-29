/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * The department came to describe a recovering person.
 */
package Population;
import Location.Location;
import Simulation.Clock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Country.Settlement;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

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
	
	/*
	 * @inheritDoc
	 */
	@Override 
	public  double ContagionProbability(){
		return 0.2;
	}
	/*
	 * @inheritDoc
	 */
	@Override
	public  Person Contagion(IVirus ivirus) {
		Sick s;
		Random rand = new Random();
		if (ivirus instanceof BritishVariant)
		{
			List<IVirus> list = new ArrayList<IVirus>(BritishVariant.getMutation());
			int x1=rand.nextInt(list.size());
			ivirus = list.get(x1);
			s=new Sick(this.getAge(),this.Getlocation(),this.Getsettlement(),Clock.now(),ivirus);
			return s;
		}
		if (ivirus instanceof ChineseVariant)
		{
			List<IVirus> list = new ArrayList<IVirus>(BritishVariant.getMutation());
			int x1=rand.nextInt(list.size());
			ivirus = list.get(x1);
			s=new Sick(this.getAge(),this.Getlocation(),this.Getsettlement(),Clock.now(),ivirus);
			return s;
		}
		if (ivirus instanceof SouthAfricanVariant)
		{
			List<IVirus> list = new ArrayList<IVirus>(BritishVariant.getMutation());
			int x1=rand.nextInt(list.size());
			ivirus = list.get(x1);
			s=new Sick(this.getAge(),this.Getlocation(),this.Getsettlement(),Clock.now(),ivirus);
			return s;
		}
		return s=new Sick(this.getAge(),this.Getlocation(),this.Getsettlement(),Clock.now(),ivirus);
		
		
	}
	
    public String toString(){
    	return "virus:"+this.virus+ super.toString();
	}
    
    public boolean equals(Object o) {
		if (!(o instanceof Convalescent))
		   return false;
		Convalescent n = (Convalescent)o;
		return this.virus.equals(n.virus)&& super.equals(o);
	}
}
