package Country;

import java.util.*;
import Population.*;
import Location.Location;
import Location.Point;




public class Settlement {
	protected String name;
	protected Location location;
	List<Person> listp;
	RamzorColor color;
	public Settlement() {

	
	}
	public Settlement(String name,Location location,RamzorColor color, List<Person> listp) {
		this.name = name;
		this.location = location;
		this.color = color;
		this.listp = listp;
	
	}
	public void setListP(List<Person> listp)
	{ 
		this.listp=listp;
	}
	public List<Person> getListP()
	{ 
		return this.listp;
	}
	public String getName()
	{ 
		return this.name;
	}
	public void setName(String name)
	{ 
		this.name = name;
	}
	//לבודוק אם צריך לעשות מתודה של השוואה בין אנשים וכך למצוא מיקום של מישו ברשימה 
	
	
	public RamzorColor getColor() {
		return this.color;
	}
	public void setColor(RamzorColor color) {
		 this.color = color;
	}
	
	//למצוא דרך לדרוס את זה
	
	public  RamzorColor CalculateRamzorGrade() {
		return this.color;
	}
	
	
	
	
	public double ContagiousPercent() {
		double count = 0.0;
		for(int i = 0; i<listp.size(); i++)
		{
			if(listp.get(i) instanceof Sick)
				count++;
		}
		return count/listp.size();
		
	}
	
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
		return this.listp.add(p);
		
	}
	public boolean TransferPerson(Person p, Settlement s) {
		s.listp.add(p);
		this.listp.remove(p);
		return true;
	
	}
	
	
	public String toString() {
		String result;
		result = "name: "+name+", location:"+location.toString()+", RamzorColor:" + color.toString() + "\nList of People:\n";

		if(listp!=null)
		{
			for (int i=0;i<listp.size();i++) 
			{
				Person p = listp.get(i);
				result =result + p.toString() + "\n";
			}
		}
		else
			result = result + "Empty List";
		
		return result;
	}

}
