package Virus;
import Population.*;
import Simulation.Clock;
import java.lang.Math;


public class ChineseVariant implements IVirus {
	
	public double ContagionProbability(Person p) {
		double percent;
		if(p.getAge()<=18)
			percent =  0.2;
		else if(p.getAge()>55)
			percent = 0.7;
		else
			percent = 0.5;
		return percent*p.ContagionProbability();
	}
	
	public double p_to_death(Sick p) {
		if(p.getAge()<18)
			return 0.001;
		if(p.getAge()>55)
			return 0.1;
		return 0.05;
		
	}
	
	public boolean tryToContagion(Person p1, Person p2) {
		double distance, min1;
		if(p2 instanceof Healthy || p2 instanceof Vaccinated || p2 instanceof Convalescent)
		{
			distance = p1.Getlocation().GetPoint().getDistance(p2.Getlocation().GetPoint());
			min1 = Math.min(1, 0.14*Math.pow(2.718281828,(2-0.25*distance)));
			min1 = min1*ContagionProbability(p2);
			double r =  Math.random();
		    if(min1<=r)
		    	return true;
		    return false;
			
		}
		else {
			System.out.println("A sick person cannot be infected");
		}
		return false;
	}
	
	public boolean tryToKill(Sick p) {
		double x = p.getVirus().p_to_death(p);
		double max1 = Math.max(0, x - 0.01*x* Math.pow(Clock.now() - p.getContagiousTime()-15, 2));
	    double r =  Math.random();
	    if(max1<=r)
	    	return true;
	    return false;
	
	}
	public String toString() {
		return "ChineseVariant";
	}
}
