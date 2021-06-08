package Io;



import Country.City;
import Country.Kibbutz;
import Country.Moshav;
import Country.RamzorColor;
import Country.Settlement;
import Location.Location;

public class FactorySettlement 
{
	public Settlement makeInsttance(String type,String name, Location location, int population, int capacity)
	{
		if (type.equals("City")) 
		  {
			  City st = new City(name,location,RamzorColor.Green,null,null,capacity,0,null);
			  st.setmax_people(capacity);
			  return st;
		  }
		else if (type.equals("Moshav"))
		  {
			  Moshav st = new Moshav(name,location,RamzorColor.Green,null,null,capacity,0,null);
			  st.setmax_people(capacity);
			  return st;
		  }    
		else
		  {
			  Kibbutz st = new Kibbutz(name,location,RamzorColor.Green,null,null,capacity,0,null);  
			  st.setmax_people(capacity);
			  return st;
		  }
	}

}
