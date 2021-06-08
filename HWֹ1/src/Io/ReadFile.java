/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * Department responsible for loading instances of the simulation from a file.
 */
package Io;
import java.util.*;
import Population.Healthy;
import java.io.*;
import Country.*;
import Country.Settlement;
import Location.Location;
import Location.Point;
import Location.Size;
import Country.Map;
import Population.Person;



public class ReadFile {
	private String path;
	
	
	public ReadFile(String path)
	{
		this.path= path;
	}
	
  
	public void FillCountryFromFile(Map map) {
    try 
    {
      File myObj = new File(this.path);
      Scanner myReader = new Scanner(myObj);
    
     
      String data ;
      while (myReader.hasNext()) 
      {
    	data = myReader.nextLine(); 
    	InsertCityToMap(map,data);   
      }
      myReader.close();
    } 
    catch (FileNotFoundException e) 
    {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
  /*
   * A method that creates a list of people according to the details written in a text file and creates a place for them and puts them on the map
   */
	private void InsertCityToMap(Map map,String data)
	{
	  String name , tipe;
	  int xAge,yAge;
	  int height,width,num,indexEnd, x, y,age;
	  Random rand = new Random();

	  //Getting the type of area examples:city...
	  indexEnd = data.indexOf(";");
	  tipe = (data.substring(0,indexEnd)).trim();
	  data = data.substring(indexEnd+1, data.length());
	  
	  
	  //Getting the name of area
	  indexEnd = data.indexOf(";");
	  name = (data.substring(0, indexEnd)).trim();
	  data = data.substring(indexEnd+1, data.length());
	  
	  //Getting the X position
	  indexEnd = data.indexOf(";");
	  x= Integer.parseInt((data.substring(0, indexEnd)).trim());
	  data = data.substring(indexEnd+1, data.length());
	  
	  //Getting the y position
	  indexEnd = data.indexOf(";");
	  y = Integer.parseInt(data.substring(0, indexEnd).trim());
	  data = data.substring(indexEnd+1, data.length());

	  //Getting the height size
	  indexEnd = data.indexOf(";");
	  height = Integer.parseInt(data.substring(0, indexEnd).trim());
	  data = data.substring(indexEnd+1, data.length());
	  
	  //Getting the width size
	  indexEnd = data.indexOf(";");
	  width = Integer.parseInt(data.substring(0, indexEnd).trim());
	  data = data.substring(indexEnd+1, data.length());
	  
	  // getting num of people in city
	  indexEnd = data.length(); //data.indexOf(";");
	  num = Integer.parseInt(data.substring(0, indexEnd).trim());
	  
	  
	  //creating new element to add into array
	  Point point = new Point(x,y);
	  Size size = new Size(width, height);
	  Location location= new Location(point,size);

	  
	  List<Person> listhealthy = new ArrayList<Person>();
	  List<Person> listsick = new ArrayList<Person>();
	  Settlement st = null ;
	  
	  int vaccine_doses = 0;
	  Settlement [] neighbors;
	  int max = (int) ((int)num * 1.3);
	  st.setmax_people(max); 
	  
	  if (tipe.equals("City")) 
		    st = new City(name,location,RamzorColor.Green,null,null,max,vaccine_doses,null);   
	  if (tipe.equals("Moshav"))
		    st = new Moshav(name,location,RamzorColor.Green,null,null,max,vaccine_doses,null);
	  if (tipe.equals("Kibbutz"))
		    st = new Kibbutz(name,location,RamzorColor.Green,null,null,max,vaccine_doses,null);

	  Healthy h;
	  for(int i=0;i<num;i++)
	  {
		  
		 Random r = new Random();
		 xAge = (int) (r.nextGaussian()*6 + 9);
		 yAge = rand.nextInt(5); //getting number between 0-4	
		 age = Math.abs(5*xAge+yAge);
		 h = new Healthy(age,location,st);
		 h.Setlocation( h.Getsettlement().RandomLocation());
		 listhealthy.add(h);
	  }
	  //inserting into map
	  st.setListhealthy(listhealthy);
	}
     
}
