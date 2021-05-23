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
		

 }
		

