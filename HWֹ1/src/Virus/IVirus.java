package Virus;
import Population.Person;
import Population.Sick;

public interface IVirus {
	public double ContagionProbability(Person p);
	public boolean tryToContagion(Person p1, Person p2);
	public boolean tryToKill(Sick p);
	// help method To calculate the probability of death
	public  double p_to_death(Sick p);
	public String toString();
	
}
