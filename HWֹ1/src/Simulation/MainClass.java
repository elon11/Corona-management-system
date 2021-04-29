/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 */

package Simulation;
import Io.*;
import Population.*;
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
		
		
		
		
	
	 // A main function whose function is to load the file in places and populations define patients and perform simulations to try to infect people
	 
	     
	      
	      
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
				num = (int)(0.2*num);
			    
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
		Sick p2 = null;
		boolean flag = false;
		for(int j = 0; j< Map.getCurrentSize(); j++) {// Goes through all the places on the map
			for(int a = 0; a<map.getSettlement()[j].getListsick().size();a++) {
				for(int k = 0; k<3; k++) {//try to contagion 3 people healthy
					descVirus = map.getSettlement()[j].getListsick().get(a).toString();
					if(descVirus.contains("ChineseVariant"))
						virus = new ChineseVariant();
					if(descVirus.contains("BritishVariant"))
						virus = new BritishVariant();
					if(descVirus.contains("SouthAfricanVariant"))
						virus = new SouthAfricanVariant();
					if(virus.tryToContagion(map.getSettlement()[j].getListsick().get(a),map.getSettlement()[j].getListhealthy().get(k))) {
						//לבדוק באיזה וירוס מדביקקים אותו לפי ההסבר בסעיף 2.6
						p2 = new Sick(map.getSettlement()[j].getListhealthy().get(k).getAge(),map.getSettlement()[j].getListhealthy().get(k).Getlocation(),map.getSettlement()[j].getListhealthy().get(k).Getsettlement(),Clock.now(),virus0);
						 map.getSettlement()[j].getListhealthy().remove(k);
						 map.getSettlement()[j].getListsick().add(p2);
						 //לבדוק אם זה נכון הלולאה שרצה 3 פעמים ומנסה להדביק 3 אנשים כי תמיד הוא בודק רק את ה 3 אנשים ראשונים שברשימה
					}
					
				}
			}
			Person b = null;
			for(int i = 0; i< Map.getCurrentSize(); i++) {// make sick to Convalescent
				for(int a = 0; a<map.getSettlement()[i].getListsick().size();a++) {
					if(Clock.now() - ((Sick) map.getSettlement()[i].getListsick().get(a)).getContagiousTime()>25) {
						 b = new Convalescent(((Sick) map.getSettlement()[i].getListsick().get(a)).getAge(),((Sick) map.getSettlement()[i].getListsick().get(a)).Getlocation(),((Sick) map.getSettlement()[i].getListsick().get(a)).Getsettlement(),((Sick) map.getSettlement()[i].getListsick().get(a)).getVirus());
						 map.getSettlement()[i].getListsick().remove(a);
						 map.getSettlement()[i].getListhealthy().add(b);
					}
				}
					
			}
	
				
		Clock.nextTick();
		
		try {
				
		      FileWriter myWriter = new FileWriter(fd.getDirectory() +"/"+ fd.getFile());//new FileWriter("C:\\Users\\elony\\Desktop\\MapOutput.txt");
		      
		      
		      for(int n = 0; n< Map.getCurrentSize(); n++) {
					map.getSettlement()[n].CalculateRamzorGrade();
					myWriter.write(map.getSettlement()[n].toString());
					System.out.println(map.getSettlement()[n].toString());
				}	
		      
		      myWriter.close();
		    }
		catch (IOException e) {
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

