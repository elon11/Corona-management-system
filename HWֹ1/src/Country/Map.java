/*
 * elon ifrah 207229931
 * yossi iluz 208510248
 * The department describes the map of the area
 */
package Country;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CyclicBarrier;

public class Map implements Iterable<Settlement>{
	private Settlement [] settlement;
	public CyclicBarrier cyclic;
	private boolean notStop;
	private boolean isPaused;
	private static int size =1;
	private static int index =0;
	
	public  Map ()
	{
		
		settlement = new Settlement[size];
		notStop = false;
		isPaused = false;
	}
	
	public Settlement[] getSettlement()
	{
		return settlement;
	}
	
	public Settlement getSettlement_by_insex(int x)
	{
		return settlement[x];
	}
	
	public void setSettlement(Settlement [] settlement)
	{
		this.settlement =  settlement;
	}
	
	public static int getsize()
	{
		return size;
	}
	public static int getCurrentSize()
	{
		return index;
	}	
	
	public void setsize(int size)
	{
		Map.size =  size;
	}
	
	
	
	
	public void setnotStop(boolean x)
	{
		notStop = x;
	}
	public boolean getnotStop()
	{
		return notStop;
	}
	public void setisPaused(boolean x)
	{
		notStop = x;
	}
	public boolean getisPaused()
	{
		return isPaused;
	}
	
	
	
	/*
	 * The method activates the threads
	 */
	public void spawn_all() {
		Iterator<Settlement> iterator=this.iterator();
		while(iterator.hasNext())
			new Thread(iterator.next()).start();
		
	}
	
	
	@Override
	public Iterator<Settlement> iterator() {
		// TODO Auto-generated method stub
		return Arrays.stream(this.getSettlement()).iterator();
	}
	
	
	/*
	 * The method gets a place and adds it to the list of places on the map
	 */
	
	public void AddSettlements(Settlement st)
	{
		
		settlement[index]=st;
		index++;
		//checking if the map is full mult the size of it and copy elements
		if(index == size) 
		{
			size = size + 1;
			Settlement [] settlementTemp = new Settlement[size];
			for(int i=0;i<settlement.length;i++)
			{
				settlementTemp[i]= settlement[i];
			}
			
			settlement= settlementTemp;
			
			
		}
	}
	
}
