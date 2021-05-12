/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 */

package Simulation;
import Io.*;
import Population.*;
import UI.MainWindows;
import Virus.*;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import Country.*;


 
 

public class MainClass {
	
	private static boolean ifFirstSim = false;
	private static boolean play = false;
	private static boolean pause = false;
	private static boolean stop = false;
	private static FileWriter outputFile;
	public static Map map;
	
	public static void main(String [] args) throws InterruptedException, IOException 
	{
		MainWindows window =new MainWindows();
	    map=window.getmap();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true)
		{
			
			if(map!=null) {
			//map=window.getmap();
			
			if(stop) {
				map = null;
				ifFirstSim = false;
			}
			if(map==null)
			{
				map=window.getmap();
			}
			try {
				
				if(play && !ifFirstSim)
				{
					
					StartSimulation1(map);
					ifFirstSim=true;
				}

				if(!pause && !stop && play && ifFirstSim)
				{	
					StartSimulation2(map);
					window.updateAll();
					Clock.nextTick();
					Thread.sleep(window.getsleeptime());
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(window.getsleeptime());	
			}
		}
	}
	
	
	
	  public static File loadFileFunc() {
			 // Instead of "(Frame) null" use a real frame, when GUI is learned
			 FileDialog fd = new FileDialog((Frame) null, "Please choose a file for loading:", FileDialog.LOAD);
			 fd.setVisible(true);
			
			 if (fd.getFile() == null)
			     return null;
			 File f = new File(fd.getDirectory(), fd.getFile());
			 
			 System.out.println(f.getPath());
			 return f;
		}
	  
	  
	     public static void setPause(boolean b)
	 	{
	 		/**
	 		 *pause setter
	 		 *@param b the current state of pause putton 
	 		 */
	 		pause=b;
	 	}
	 	public static void setStop(boolean b)
	 	{
	 		/**
	 		 *stop setter
	 		 *@param b the current state of stop putton 
	 		 */
	 		stop=b;
	 	}
	 	public static void setPlay(boolean b)
	 	{
	 		/**
	 		 *play setter
	 		 *@param b the current state of play putton 
	 		 */
	 		play=b;
	 	}
		
		
		
		
	
	 
	
	
	public static void StartSimulation1(Map map) {
		//Step Boot 
		Settlement settlement ;
		List<Person> listp = new ArrayList<Person>();
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
		Person p2 = null;
		for(int i = 0; i< 5; i++) {//Performs 5 simulations
			boolean flag = false;
			for(int j = 0; j< Map.getCurrentSize(); j++) {// îòáøGoes through all the places on the map
				int index = 0;
				int y = 0;
				for(int s = 0; s< map.getSettlement_by_insex(j).getListsick().size(); s++) {
					for(int k = 0; k<6; k++) {// Trying to infect 6 people randomly
						Random rand = new Random();
						int x = rand.nextInt( map.getSettlement()[j].getListhealthy().size());
						descVirus = map.getSettlement()[j].getListsick().get(index).toString();
						if(descVirus.contains("ChineseVariant"))
							virus = new ChineseVariant();
						if(descVirus.contains("BritishVariant"))
							virus = new BritishVariant();
						if(descVirus.contains("SouthAfricanVariant"))
							virus = new SouthAfricanVariant();
						if(virus.tryToContagion(map.getSettlement()[j].getListsick().get(s),map.getSettlement()[j].getListhealthy().get(x))) {
							 p2 = map.getSettlement()[j].getListhealthy().get(x).Contagion(virus);
							 map.getSettlement()[j].getListhealthy().remove(x);
							 map.getSettlement()[j].getListsick().add(p2);
						}
					}
				}
			}
		}
		Clock.nextTick();
	}
			
	 
	
		
	
	public static void StartSimulation2(Map map) {
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
						 p2 = new Sick(map.getSettlement()[j].getListhealthy().get(k).getAge(),map.getSettlement()[j].getListhealthy().get(k).Getlocation(),map.getSettlement()[j].getListhealthy().get(k).Getsettlement(),Clock.now(),virus);
						 map.getSettlement()[j].getListhealthy().remove(k);
						 map.getSettlement()[j].getListsick().add(p2);
					}
					
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
		List<Person> templist = null;
		for(int i = 0; i< Map.getCurrentSize(); i++) {// make sick to Convalescent
			for(int a = 0;a< map.getSettlement()[i].getListhealthy().size();a++) {
				templist.add( map.getSettlement()[i].getListhealthy().get(a));
			}
			for(int a = map.getSettlement()[i].getListhealthy().size(); a<map.getSettlement()[i].getListsick().size()+map.getSettlement()[i].getListhealthy().size();a++) {
				templist.add( map.getSettlement()[i].getListsick().get(a));
			}
			for(int a = 0;a<templist.size()*0.03;a++) {
				Random r = new Random();
		    	int s = r.nextInt(templist.size()); 
		    	int m= r.nextInt(templist.get(s).Getsettlement().getneighbors().length); 
		    	templist.get(s).Getsettlement().TransferPerson(templist.get(s), templist.get(s).Getsettlement().getneighbors()[m]);
			}
		 }
		Person b2 = null;
		for(int i = 0; i< Map.getCurrentSize(); i++) {
			if(map.getSettlement()[i].getvaccine_doses()>0) {
				for(int y = 0; y< map.getSettlement()[i].getListhealthy().size(); y++) {
					if(map.getSettlement()[i].getvaccine_doses()>0 && map.getSettlement()[i].getListhealthy().get(y) instanceof Healthy ) {
						 b2 = new Vaccinated(((Healthy) map.getSettlement()[i].getListhealthy().get(y)).getAge(),((Healthy) map.getSettlement()[i].getListhealthy().get(y)).Getlocation(),((Sick) map.getSettlement()[i].getListhealthy().get(y)).Getsettlement(),Clock.now());
					     map.getSettlement()[i].setvaccine_doses(map.getSettlement()[i].getvaccine_doses() - 1);
				    }
				}
			}
		}
		for(int i = 0; i< Map.getCurrentSize(); i++) {
			for(int j = 0;j<map.getSettlement()[i].getListsick().size();j++) {
				if(((Sick)map.getSettlement()[i].getListsick().get(j)).getVirus().tryToKill(((Sick)map.getSettlement()[i].getListsick().get(j)))){
					map.getSettlement()[i].setnum_of_dead();
					map.getSettlement()[i].getListsick().remove(j);
				}
			}
			
		}
	
				
		
	}
		
 }
		

