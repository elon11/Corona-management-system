/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 */ 
package Simulation;
import Io.SimulationFile;
import Population.Person;
import Population.Sick;
import Virus.*;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Country.*;

/*
 
 

public class MainClass {
	
	private static boolean ifFirstSim = true;
	private static FileWriter outputFile;
	
	     private static SimulationFile loadFileFunc() {
			 // Instead of "(Frame) null" use a real frame, when GUI is learned
			 FileDialog fd = new FileDialog((Frame) null, "Please choose a file for loading:", FileDialog.LOAD);
			 fd.setVisible(true);
			
			 if (fd.getFile() == null)
			 return null;
			 File f = new File(fd.getDirectory(), fd.getFile());
			 SimulationFile fileObj = new SimulationFile(f.getPath());
			 System.out.println(f.getPath());
			 return fileObj;
		}
		
	*/	
		
		
	/*
	 * A main function whose function is to load the file in places and populations define patients and perform simulations to try to infect people
	 */
	     /*
	      
	      
	public static void main(String[] args) {
		//Step Charging 
		System.out.println("start main");    
		String path = "C:\\Users\\elony\\Desktop\\Map.Read.txt";
		
		
		
		SimulationFile fileObj = loadFileFunc();//new SimulationFile(path);
		
		 FileDialog fd = new FileDialog((Frame) null, "Please choose a file for saving:", FileDialog.SAVE);
		 fd.setFile("mapOutput");
		 fd.setVisible(true);
		
		 
		Map map = new Map();
		fileObj.FillCountryFromFile(map);
		
		//Step Boot 
		Settlement settlement ;
		Sick p = null;
		IVirus virus0 ,virus1 ,virus2;
		int num , chek;
		for (int i = 0; i<Map.getCurrentSize();i++) {
			
			settlement = map.getSettlement()[i];
				num = map.getSettlement()[i].getListhealthy().size();
				num = (int)(0.01*num);
			    
				virus0 = new ChineseVariant();
		    	virus1 = new BritishVariant();
		    	virus2 = new SouthAfricanVariant();
			    for (int j = 0; j< num; j++) {
			    	Random r = new Random();
			    	chek = r.nextInt(3); 
			    	if(chek ==0)
			    	    p = new Sick(map.getSettlement()[i].getListhealthy().get(j).getAge(),map.getSettlement()[i].getListhealthy().get(j).Getlocation(),map.getSettlement()[i].getListhealthy().get(j).Getsettlement(),Clock.now(),virus0);
			    	if(chek ==1)
			    		p = new Sick(map.getSettlement()[i].getListhealthy().get(j).getAge(),map.getSettlement()[i].getListhealthy().get(j).Getlocation(),map.getSettlement()[i].getListhealthy().get(j).Getsettlement(),Clock.now(),virus1);
			    	if(chek ==2)
			    		p = new Sick(map.getSettlement()[i].getListhealthy().get(j).getAge(),map.getSettlement()[i].getListhealthy().get(j).Getlocation(),map.getSettlement()[i].getListhealthy().get(j).Getsettlement(),Clock.now(),virus2);
			    	map.getSettlement()[i].getListsick().add(p);
			    	map.getSettlement()[i].getListhealthy().remove(j);
			    	System.out.println(p.toString());	
			    }  
		}
		
		//Step Simulation 
		IVirus virus = null;
		String descVirus;
		for(int i = 0; i< 5; i++) {//Performs 5 simulations
			boolean flag = false;
			for(int j = 0; j< Map.getCurrentSize(); j++) {// מעברGoes through all the places on the map
				int index = 0;
				int y = 0;
				while(!flag && y< map.getSettlement()[j].getListP().size()) {
					if(map.getSettlement()[j].getListP().get(y) instanceof Sick)
					{ 
						flag = true;
						index = y;
					}	
					y++;
				}
				Person p2 = null;
				for(int k = 0; k<6; k++) {// Trying to infect 6 people randomly
					Random rand = new Random();
					List<Person> temp = map.getSettlement()[j].getListP();
					int x = rand.nextInt(temp.size());
					if(!(map.getSettlement()[j].getListP().get(x) instanceof Sick))
					{
						descVirus = map.getSettlement()[j].getListP().get(index).toString();
						if(descVirus.contains("ChineseVariant"))
							virus = new ChineseVariant();
						if(descVirus.contains("BritishVariant"))
							virus = new BritishVariant();
						if(descVirus.contains("SouthAfricanVariant"))
							virus = new SouthAfricanVariant();
						if(virus.tryToContagion(map.getSettlement()[j].getListP().get(index),map.getSettlement()[j].getListP().get(x)))
						{
							 p2 = map.getSettlement()[j].getListP().get(x).Contagion(virus);
							 map.getSettlement()[j].getListP().remove(x);
							 map.getSettlement()[j].getListP().add(p2);
						}
					}
				}
			}
			Clock.nextTick();
			
			try {
					
			      FileWriter myWriter = new FileWriter(fd.getDirectory() +"/"+ fd.getFile());//new FileWriter("C:\\Users\\elony\\Desktop\\MapOutput.txt");
			      
			      
			      for(int j = 0; j< Map.getCurrentSize(); j++) {
						map.getSettlement()[j].CalculateRamzorGrade();
						myWriter.write(map.getSettlement()[j].toString());
						System.out.println(map.getSettlement()[j].toString());
					}	
			      
			      myWriter.close();
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			
		}
			
		
	}
	
	 private static FileWriter saveFileFunc(String fileName) {
			try {
				if(ifFirstSim == true)
				{
					 FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.SAVE);
					 fd.setFile(fileName);
					 fd.setVisible(true);
					
					 if (fd.getFile() == null)
						 outputFile=null;
					 else
						 outputFile = new FileWriter(fd.getDirectory() +"/"+ fd.getFile());
					ifFirstSim = false;
				}
		
		 //System.out.println(f.getPath());
			}
			catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			      outputFile=null;
			    }
			return outputFile;
			}
	 
	 }
		
*/
