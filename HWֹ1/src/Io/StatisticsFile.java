/*
 * elon ifrah 207229931
 * yossi iluz 208510248
 *A class  that makes it possible to maintain the cutting of the disease status in all localities,
 */
package Io;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Country.*;

public class StatisticsFile   {
	public static String path=null;
	public static FileHandler fh=null;
	public static void writeCsv(Map map, File f) {

	    try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
	      StringBuilder sb = new StringBuilder();
	      sb.append("Settelement Name,");
	      sb.append(',');
	      sb.append("Settelement Type");
	      sb.append(',');
	      sb.append("Population");
	      sb.append(',');
	      sb.append("Ramzor color");
	      sb.append(',');
	      sb.append("sick Percent ");
	      sb.append(',');
	      sb.append("Number of vaccine doses ");
	      sb.append(',');
	      sb.append('\n');
	      for(int i=0; i<map.getSettlement().length-1;i++) {
	         sb.append(map.getSettlement()[i].getName());
	         sb.append(',');
	       	 if(map.getSettlement()[i] instanceof City ) {
	    	   sb.append("City ");}
	       	 else if(map.getSettlement()[i] instanceof Kibbutz) {
	       		   sb.append("Kibbutz ");}
	       	 else 
	       		 sb.append("Moshav ");
	       	 sb.append(',');
	       	 sb.append(map.getSettlement()[i].getPopulation());
	       	 sb.append(',');
	         sb.append(map.getSettlement()[i].getColor());
	    	 sb.append(',');
	      	 sb.append(map.getSettlement()[i].getListsick().size()/map.getSettlement()[i].getPopulation()*100+"%");
	       	 sb.append(',');
	       	 sb.append(map.getSettlement()[i].getvaccine_doses());
	         sb.append('\n');
	      }
	      writer.write(sb.toString());
	      writer.close();
	    } catch (FileNotFoundException e) {
	      System.out.println(e.getMessage());
	    }

	  }
	public static void writeLog(Settlement s) throws IOException
	{
		Logger logger = Logger.getLogger("");  
	     

	    try {  
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler(path); 
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	        // the following statement is used to log any messages  
	        logger.info(s.getName()+" Number of sick: "+s.getListsick().size()+" Number of dead: "+s.getnum_of_dead()+"\n");
	        fh.close();

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    }
	}
	public static void loadFileFunc() 
	{
		/**
		 * load new file
		 * @return file
		 */
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null)
            return ;
        path=fd.getFile();
        Originator.setState(path);
        memento = Originator.createMemento();
        caretaker.addMemento(memento);
        index++;
        try {
        	fh = new FileHandler(path);
        }catch (SecurityException | IOException e) {
        	e.printStackTrace();
        }
	}
}
