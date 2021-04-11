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
		System.out.println("end constructor of ReadFile with this.path: "+this.path);
	}
	
  public void FillCountryFromFile(HashMap<String,Settlement> map) {
	  System.out.println("start FillCountryFromFile");  
    try 
    {
      File myObj = new File(this.path);
      Scanner myReader = new Scanner(myObj);
      String data;
      while (myReader.hasNextLine()) 
      {
        data = myReader.nextLine();
        System.out.println("data:"+ data);  
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
  
  private void InsertCityToMap(HashMap<String,Settlement> map,String data)
	{
	  String name,area;
	  int xAge,yAge;
	  int height,width,num,indexEnd, x, y,age;
	  Random rand = new Random();

	  //Getting the type of area examples:city...
	  indexEnd = data.indexOf(";");
	  area = (data.substring(0,indexEnd)).trim();
	  data = data.substring(indexEnd+1, data.length());
	  System.out.println(data);
	  
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
	  //data = data.substring(indexEnd+1, data.length() );
	  
	  //creating new element to add into array
	  Point point = new Point(x,y);
	  Size size = new Size(width, height);
	  Location location= new Location(point,size);

	  System.out.println("location:"+location.toString());
	  
	  List<Person> listp = new ArrayList<Person>();
	  Settlement st = new Settlement(name,location,RamzorColor.Green,null);

	  Healthy h;
	  for(int i=0;i<num;i++)
	  {
		  
		 Random r = new Random();
		 xAge = (int) (r.nextGaussian()*6 + 9);
		 yAge = rand.nextInt(5); //getting number between 0-4	
		 age = Math.abs(5*xAge+yAge);
		 h = new Healthy(age,location,st);
		 h.Setlocation( h.Getsettlement().RandomLocation());
		 listp.add(h);
	  }
	  //inserting into map
	  st.setListP(listp);
	  System.out.println(st.toString());
	  map.put(st.getName(),st);
		
	}
  

  
}
