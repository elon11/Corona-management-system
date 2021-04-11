package Simulation;
import Country.Map;
import Country.Settlement;
import Io.ReadFile;
import Population.Person;
import Population.Sick;
import Virus.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;


public class MainClass {
	
	public static void main(String[] args) {
		// שלב הטעינה
		System.out.println("start main");    
		String path = "C:\\Users\\elony\\Desktop\\City.txt";
		ReadFile fileObj = new ReadFile(path);
		//JOptionPane.showMessageDialog(null, "start");
		HashMap<String,Settlement> map = new HashMap<String,Settlement>();
		fileObj.FillCountryFromFile(map);

		//שלב האיתחול
		Settlement settlement = new Settlement(); 
		List<Person> listp = new ArrayList<Person>();
		Person p = null;
		IVirus virus0 ,virus1 ,virus2;
		int num , chek;
		for (String key : map.keySet()) {
			settlement = map.get(key);
			listp = settlement.getListP();
			num = listp.size();
			num = (int)(0.5*num);// to change to 0.01
		    
			virus0 = new ChineseVariant();
	    	virus1 = new BritishVariant();
	    	virus2 = new SouthAfricanVariant();
		    for (int i = 0; i< num; i++) {
		    	Random r = new Random();
		    	chek = r.nextInt(3); 
		    	if(chek ==0)
		    	    p = listp.get(i).Contagion(virus0);
		    	if(chek ==1)
		    	    p = listp.get(i).Contagion(virus1);
		    	if(chek ==2)
		    	    p = listp.get(i).Contagion(virus2);
		    	listp.remove(i);
		    	listp.add(p);
		    	System.out.println(p.toString());
		    }
		}	
		
		
	}
		
	

}
