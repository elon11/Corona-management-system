/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * Interface in which to describe the behavior of a virus
 */
package Virus;
import Population.Person;
import Population.Sick;

public interface IVirus {
	/*
	 * @param p - A method that calculates the probability that the transferred person will be infected.
	 */
	public double ContagionProbability(Person p);
	/*
	 * @param p1 and p2 - A method that accepts 2 people and results in pasting the other with the help of a formula and distance from the first person
	 */
	public boolean tryToContagion(Person p1, Person p2);
	/*
	 * @param p - A method that calculates the probability that the person being transferred will die from the disease
	 */
	public boolean tryToKill(Sick p);
	/*
	 * @param p - An help method that accepts a patient and returns the probability of death from his virus
	 */
	// help method To calculate the probability of death
	public  double p_to_death(Sick p);
	public String toString();
	public boolean equals(Object o);
	
}
   