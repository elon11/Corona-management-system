/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * A class that describes a place on a map
 */
package Country;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;

import Country.Map;
import Io.StatisticsFile;
import Population.*;
import Simulation.Clock;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;
import Location.Location;
import Location.Point;




public abstract class Settlement implements Runnable{
	protected String name;
	private Map map;
	protected Location location;
	protected List<Person> listhealthy= new ArrayList<Person>();
	protected List<Person> listsick= new ArrayList<Person>();
	protected RamzorColor color;
    protected int max_people;
	protected int vaccine_doses;
	protected Settlement [] neighbors;
	protected int sizeN ;
	protected int currentN ;
	protected int num_of_dead = 0;
	
	
	public Settlement(String name,Location location,RamzorColor color, List<Person> healthy,List<Person> sick,int max_people,int vaccine_doses,Settlement [] neighbors) {
		this.name = name;
		this.location = location;
		this.color = color;
		this.listhealthy = healthy;
		this.listsick = sick;
		this.max_people = max_people;
		this.vaccine_doses = vaccine_doses;
		sizeN = 20;
		currentN = 0;
		this.neighbors = new Settlement[sizeN];
		this.map = null;
	}
	public void set_map(Map map) {
		this.map = map;
	}
	
	public Settlement(Settlement s) {
		this.name = s.getName();
		this.location = s.getlocation();
		this.color = s.getColor();
		this.listhealthy = s.getListhealthy();
		this.listsick = s.getListsick();
		this.max_people = s.getmax_people();
		this.vaccine_doses = s.getvaccine_doses();
		
		
		this.neighbors = s.getneighbors();
		
	}
	
	
	public void AddNeighbors(Settlement newS)
	{
		neighbors[currentN]= newS;
		currentN ++;
		if(currentN==sizeN)
		{
			sizeN = sizeN *2;
			Settlement [] neighborsNew = new Settlement[sizeN];
			for(int i=0;i<=currentN;i++)
			{
				neighborsNew[i] = neighbors[i];
			}
			neighbors = neighborsNew;
		}
	}
	
	public void setListhealthy(List<Person> healthy)
	{ 
		this.listhealthy=healthy;
	}
	
	public List<Person> getListhealthy()
	{ 
		return this.listhealthy;
	}
	
	public void setListsick(List<Person> sick)
	{ 
		this.listsick=sick;
	}
	
	public List<Person> getListsick()
	{ 
		return this.listsick;
	}
	
	public  int getmax_people()
	{ 
		return this.max_people;
	}
	public  void setmax_people( int max)
	{ 
		 this.max_people = max;
	}
	public  void setnum_of_dead()
	{ 
		this.num_of_dead++;
	}
	public  int getnum_of_dead()
	{ 
		return this.num_of_dead;
	}

	
	public int getvaccine_doses()
	{ 
		return this.vaccine_doses;
	}
	public synchronized void setvaccine_doses(int vaccine_doses )
	{ 
		this.vaccine_doses += vaccine_doses;
	}
	public int getPopulation()
	{ 
		return this.listhealthy.size()+this.listsick.size();
	}
	public Location getlocation()
	{ 
		return this.location;
	}
	public void setName(String name)
	{ 
		this.name = name;
	}
	public String getName()
	{ 
		return this.name;
	}
	 
	public Settlement[] getneighbors()
	{
		return neighbors;
	}
	
	public void setSettlement(Settlement [] neighbors)
	{
		this.neighbors =  neighbors;
	}
	public int getNumN()
	{
		return this.currentN;
	}
	
	public RamzorColor getColor() {
		return this.color;
	}
	public void setColor(RamzorColor color) {
		 this.color = color;
	}
	
	/*
	 * A method that calculates according to a formula the color of the traffic light of the place
	 */
	public abstract  RamzorColor CalculateRamzorGrade();
	
	
	
	
	/*
	 * A method that calculates the percentage of patients in place
	 */
	public double ContagiousPercent() {
		return listsick.size()/(listsick.size()+listhealthy.size());
	}
	
	/*
	 * Returns a random location 
	 */
	public Location RandomLocation() {
		
		 Random rand = new Random();
		 Location random_location;
		 Point random_point;
		 int xnew = rand.nextInt(this.location.GetSize().Getwidth()) + this.location.GetPoint().GetX();
         int ynew = rand.nextInt(this.location.GetSize().Getheight()) + (this.location.GetPoint().GetY() - this.location.GetSize().Getheight());
         random_point = new Point(xnew,ynew);
         random_location = new Location(random_point, this.location.GetSize());
         return random_location;
	}
	
	
	public boolean AddPerson (Person p) {
		if(p  instanceof Sick) 
			return this.listsick.add(p);
		return this.listhealthy.add(p);
	}
	
	
	public boolean TransferPerson(Person p, Settlement s) {
		if(listsick.size()+listhealthy.size() < max_people)
		{
			double p1=this.getColor().getnumber();
			double p2=s.getColor().getnumber();
			if(transferPropabillity(p1,p2)) {
				if(p  instanceof Sick) 
				{
					s.listsick.add(p);
					this.listsick.remove(p);
					return true;
				}
				s.listhealthy.add(p);
				this.listhealthy.remove(p);
				return true;
		    }
		}
		return false;
	}
	private boolean transferPropabillity(double p1, double p2)
	{
		
		 double chance =p1*p2;
		 return chance >=Math.random();	 
	}
	
	@Override
	public String toString() {
		String result;
		result = "name: "+name+", location:"+location.toString()+", RamzorColor:" + color.toString() +", max people:" +max_people+", vaccine doses:" +vaccine_doses+ ". \nList of healthy People:\nList of sick People:\n";

		if(listhealthy!=null)
		{
			for (int i=0;i<listhealthy.size();i++) 
			{
				Person p = listhealthy.get(i);
				result =result + p.toString() + "\n";
			}
		}
		else
			result = result + "Empty List";
		if(listsick!=null)
		{
			for (int i=0;i<listsick.size();i++) 
			{
				Person p = listsick.get(i);
				result =result + p.toString() + "\n";
			}
		}
		else
			result = result + "Empty List";
		return result;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Settlement))
		   return false;
		Settlement n = (Settlement)o;
		return this.name.equals(n.name) && this.location.equals(n.location)&& this.color.equals(n.color)&& this.listhealthy.equals(n.listhealthy)&& this.listsick.equals(n.listsick)&& max_people == n.max_people && this.vaccine_doses == n.vaccine_doses;
	}
	
	@Override
	public void run() {
		/**
		 * run the simmulation for one settlment
		 */
		try {
			this.simulation1();
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
		while (!map.getnotStop()) {
			synchronized (map) {
				while (map.getisPaused()) {
					try {
						map.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
			try {
				this.Simulation2(map);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				map.cyclic.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * The method performs the first simulation of infecting people
	 */
	public void simulation1() {
		
		List<Person> listp = new ArrayList<Person>();
		Sick p = null;
		IVirus virus0 ,virus1 ,virus2;
		int num , chek;
        for (int i = 0; i<Map.getCurrentSize();i++) {
        	
		    
			num = this.getListhealthy().size();
			num = (int)(0.01*num);
		    
			virus0 = new ChineseVariant();
	    	virus1 = new BritishVariant();
	    	virus2 = new SouthAfricanVariant();
		    for (int j = 0; j< num; j++) {
		    	Random r = new Random();
		    	chek = r.nextInt(3); 
		    	if(chek ==0)
		    	    p = new Sick(this.getListhealthy().get(j).getAge(),this.getListhealthy().get(j).Getlocation(),this.getListhealthy().get(j).Getsettlement(),Clock.now(),virus0);
		    	if(chek ==1)
		    		p = new Sick(this.getListhealthy().get(j).getAge(),this.getListhealthy().get(j).Getlocation(),this.getListhealthy().get(j).Getsettlement(),Clock.now(),virus1);
		    	if(chek ==2)
		    		p = new Sick(this.getListhealthy().get(j).getAge(),this.getListhealthy().get(j).Getlocation(),this.getListhealthy().get(j).Getsettlement(),Clock.now(),virus2);
		    	this.getListsick().add(p);
		    	this.getListhealthy().remove(j);
		    	
		    }  
		}
	}
	
	public void Simulation2(Map map) throws IOException {
		//to contagion
		this.contagion();
		
		//to recover
		this.recover();
		
		//to ytansfer
	    this.tryTotransfer();
	    this.setColor(this.CalculateRamzorGrade());
		
	    //to vaccine
		this.inoculate();
			
	    //to kill
		this.Kill();
			
		     
	}
	
	
	/*
	 * The method infects people in the simulation
	 */
	private synchronized void contagion() {
		Sick p = null;
		IVirus virus0 ,virus1 ,virus2;
		int num , chek;
		num = this.getListhealthy().size();
		num = (int)(0.2*num);
	    
		virus0 = new ChineseVariant();
    	virus1 = new BritishVariant();
    	virus2 = new SouthAfricanVariant();
	    for (int j = 0; j< num; j++) {
	    	Random r = new Random();
	    	chek = r.nextInt(3); 
	    	if(chek ==0)
	    	    p = new Sick(this.getListhealthy().get(j).getAge(),this.getListhealthy().get(j).Getlocation(),this.getListhealthy().get(j).Getsettlement(),Clock.now(),virus0);
	    	if(chek ==1)
	    		p = new Sick(this.getListhealthy().get(j).getAge(),this.getListhealthy().get(j).Getlocation(),this.getListhealthy().get(j).Getsettlement(),Clock.now(),virus1);
	    	if(chek ==2)
	    		p = new Sick(this.getListhealthy().get(j).getAge(),this.getListhealthy().get(j).Getlocation(),this.getListhealthy().get(j).Getsettlement(),Clock.now(),virus2);
	    	this.getListsick().add(p);
	    	this.getListhealthy().remove(j);
	    	System.out.println(p.toString());	
	    }  
	}
	
	/*
	 * The method stores people in the simulation
	 */
	private synchronized void inoculate() {
		Person b2 = null;
	    if(this.getvaccine_doses()>0) {
			for(int y = 0; y< this.getListhealthy().size(); y++) {
				if(this.getvaccine_doses()>0 &&this.getListhealthy().get(y) instanceof Healthy ) {
					 b2 = new Vaccinated(((Healthy) this.getListhealthy().get(y)).getAge(),((Healthy)this.getListhealthy().get(y)).Getlocation(),((Sick)this.getListhealthy().get(y)).Getsettlement(),Clock.now());
					 this.setvaccine_doses(this.getvaccine_doses() - 1);
			    }
			}
		}
	}
	
	/*
	 * The method kills people in the simulation
	 */
	private synchronized void Kill() throws IOException {
		if( this.getListsick().size() != 0)
		{
			for (int k = 0; k < this.getListsick().size(); k++) {
				Sick s = (Sick)this.getListsick().get(k);
				if(s != null)
				{
					if (s.tryToDie()) {
						this.getListsick().remove(s);
						this.setnum_of_dead();
					}
				}
				
			}	
		}
		if (this.getnum_of_dead() >= this.getPopulation() * 0.01 && StatisticsFile.path != null) {
			StatisticsFile.writeLog(this);
		}
	}
	
	
	/*
	 * The method makes sick people healthy in the simulation
	 */
	private synchronized void recover() {
		Person b = null;
		// make sick to Convalescent
		for(int a = 0; a<this.getListsick().size();a++) {
			if(Clock.now() - ((Sick) this.getListsick().get(a)).getContagiousTime()>25) {
				 b = new Convalescent(((Sick)this.getListsick().get(a)).getAge(),((Sick) this.getListsick().get(a)).Getlocation(),((Sick)this.getListsick().get(a)).Getsettlement(),((Sick) this.getListsick().get(a)).getVirus());
				 this.getListsick().remove(a);
				this.getListhealthy().add(b);
			}
		}		
		
	}

	
	
	
	
	/*
	 * The method returns a random person
	 */
	private synchronized Person GetPerson() {
		/**
		 * @return a random person from the settlment 
		 */
		Random rand = new Random();
		if(this.getneighbors().length==0)
			return null;
		List<Person> population = new ArrayList<Person>(this.getPopulation());
		population.addAll(this.getListhealthy());
		population.addAll(this.getListsick());
		int people = rand.nextInt(population.size()-1);
		return population.get(people); 
	}
	
	/*
	 * The method tries to move a random person from one place to another
	 */
	private void tryTotransfer() 
	{
		
		Random rand = new Random();
		Person p=null;
		Object o1,o2; 
		int hash1=System.identityHashCode(this);
		
		for (int transfer = 0; transfer < this.getPopulation() * 0.03; transfer++) {
			p=this.GetPerson();
			if(p!=null)
			{
				int settl = rand.nextInt(this.getneighbors().length);
				int hash2=System.identityHashCode(this.getneighbors()[settl]);
				if(Math.max(hash1,hash2)==hash1)
				{
					o1=this;
					o2=this.getneighbors()[settl];
				}
				else
				{
					o2=this;
					o1=this.getneighbors()[settl];
				}
				synchronized(o1) {
					synchronized(o2) {
						this.TransferPerson(p, this.getneighbors()[settl]);
					}
				}
			}
		}
		
	}
	

	
}
