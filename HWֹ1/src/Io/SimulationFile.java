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



public class SimulationFile {
	private File file;
	private Map map;


	public SimulationFile(File file)
	{
		this.file= file;
		
	}
	
  
	public Map FillCountryFromFile() {
	  map = new Map();
	  String firstTav;
	  
	    try 
	    {
	    	
	    	Scanner myReader = new Scanner(this.file);
	    
	     
	      String data ;
	      while (myReader.hasNext()) 
	      {
	        
	    	data = myReader.nextLine();
	    	firstTav = data.substring(0,1);
	    	if(!firstTav.equals("#")) 
	    		InsertCityToMap(map,data);
	    	else
	    		InsertN_B(map,data);
	      }
	      
	      myReader.close();
	      return map;
	    } 
	    catch (FileNotFoundException e) 
	    {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
        return null;
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
	   
	  
	  //inserting into map
	  
	  FactorySettlement fs=new FactorySettlement();
	  st=fs.makeInsttance(tipe, name, location, num, max);
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
		  st.setListhealthy(listhealthy);
		  st.setListsick(listsick);
		  assert st != null;
		  map.AddSettlements(st);
	  
	  
	}
	
	
	
	
	/*
	   * A method that inserting 2 neighborous into settelment n array 
	   */
		private void InsertN_B(Map map,String data)
		{
		  String name_first,name_second,nameS;
		  Settlement s1=null,s2=null;
		  int indexEnd;

		  
		  //Put-out the "#"char
		  data = data.substring(2, data.length());
		  
		  
		  
		  //Put-out the first NB
		  indexEnd = data.indexOf(";");
		  name_first = (data.substring(0, indexEnd)).trim();
		  data = data.substring(indexEnd+1, data.length());
		  
		  //Put-out the second NB
		  indexEnd = data.length();
		  name_second = (data.substring(0, indexEnd)).trim();
		
		  
		 int indexS1=-1,indexS2=-1,j=0;
		 for (Settlement st : map) {
			 if (name_first.equals(st.getName())) {
				 indexS1= j;
			 }
			 if (name_second.equals(st.getName())) {
				 indexS2= j;
			 }
			 j++;
		 }
				
	      if(indexS1 != -1 &&indexS2!=-1)
	      {
	    	  map.at(indexS1).AddNeighbors(map.at(indexS2));
	    	  map.at(indexS2).AddNeighbors(map.at(indexS1));
	      }
	      
		}    
}
