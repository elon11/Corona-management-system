/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * A class that describes a place on a map
 */
package Country;

import java.util.*;
import Population.*;
import Location.Location;
import Location.Point;




public abstract class Settlement {
	protected String name;
	protected Location location;
	protected List<Person> listhealthy;
	protected List<Person> listsick;
	protected RamzorColor color;
	static public int max_people;
	protected int vaccine_doses;
	protected Settlement [] neighbors;
	public Settlement() {

	
	}
	public Settlement(String name,Location location,RamzorColor color, List<Person> healthy,List<Person> sick,int max_people,int vaccine_doses,Settlement [] neighbors) {
		this.name = name;
		this.location = location;
		this.color = color;
		this.listhealthy = healthy;
		this.listsick = sick;
		this.max_people = max_people;
		this.vaccine_doses = vaccine_doses;
		this.neighbors = neighbors;
	}
	
	public void setListhealthy(List<Person> healthy)
	{ 
		this.listhealthy=healthy;
	}
	
	public List<Person> getListhealthy()
	{ 
		return this.listhealthy;
	}
	
	public void setListsick(List<Person> sick)
	{ 
		this.listsick=sick;
	}
	
	public List<Person> getListsick()
	{ 
		return this.listsick;
	}
	
	public static int getmax_people()
	{ 
		return max_people;
	}
	public static void setmax_people( int max)
	{ 
		 max_people = max;
	}

	
	public int getvaccine_doses()
	{ 
		return this.vaccine_doses;
	}
	public void setvaccine_doses(int vaccine_doses )
	{ 
		this.vaccine_doses = vaccine_doses;
	}
	
	public String getName()
	{ 
		return this.name;
	}
	public void setName(String name)
	{ 
		this.name = name;
	}
	 
	public Settlement[] getneighbors()
	{
		return neighbors;
	}
	
	public void setSettlement(Settlement [] neighbors)
	{
		this.neighbors =  neighbors;
	}
	
	public RamzorColor getColor() {
		return this.color;
	}
	public void setColor(RamzorColor color) {
		 this.color = color;
	}
	
	/*
	 * A method that calculates according to a formula the color of the traffic light of the place
	 */
	public abstract  RamzorColor CalculateRamzorGrade();
	
	
	
	
	/*
	 * A method that calculates the percentage of patients in place
	 */
	public double ContagiousPercent() {
		return listsick.size()/(listsick.size()+listhealthy.size());
	}
	
	/*
	 * Returns a random location 
	 */
	public Location RandomLocation() {
		
		 Random rand = new Random();
		 Location random_location;
		 Point random_point;
		 int xnew = rand.nextInt(this.location.GetSize().Getwidth()) + this.location.GetPoint().GetX();
         int ynew = rand.nextInt(this.location.GetSize().Getheight()) + (this.location.GetPoint().GetY() - this.location.GetSize().Getheight());
         random_point = new Point(xnew,ynew);
         random_location = new Location(random_point, this.location.GetSize());
         return random_location;
	}
	
	
	public boolean AddPerson (Person p) {
		if(p  instanceof Sick) 
			return this.listsick.add(p);
		return this.listhealthy.add(p);
	}
	
	
	public boolean TransferPerson(Person p, Settlement s) {
		if(listsick.size()+listhealthy.size() < max_people)
		{
			if(p  instanceof Sick) 
			{
				s.listsick.add(p);
				this.listsick.remove(p);
				return true;
			}
			s.listhealthy.add(p);
			this.listhealthy.remove(p);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String result;
		result = "name: "+name+", location:"+location.toString()+", RamzorColor:" + color.toString() +", max people:" +max_people+", vaccine doses:" +vaccine_doses+ ". \nList of healthy People:\nList of sick People:\n";

		if(listhealthy!=null)
		{
			for (int i=0;i<listhealthy.size();i++) 
			{
				Person p = listhealthy.get(i);
				result =result + p.toString() + "\n";
			}
		}
		else
			result = result + "Empty List";
		if(listsick!=null)
		{
			for (int i=0;i<listsick.size();i++) 
			{
				Person p = listsick.get(i);
				result =result + p.toString() + "\n";
			}
		}
		else
			result = result + "Empty List";
		return result;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Settlement))
		   return false;
		Settlement n = (Settlement)o;
		return this.name.equals(n.name) && this.location.equals(n.location)&& this.color.equals(n.color)&& this.listhealthy.equals(n.listhealthy)&& this.listsick.equals(n.listsick)&& max_people == n.max_people && this.vaccine_doses == n.vaccine_doses;
	}
	

}
