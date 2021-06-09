/*
 * elon ifrah 207229931
 * yossi iluz 208510248
 * The department describes the map of the area
 */
package Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Map implements Iterable<Settlement>{
	private static List<Settlement> settlement;
	public CyclicBarrier cyclic;
	private boolean notStop;
	private boolean isPaused;
	
	public  Map ()
	{
		settlement = new ArrayList<>();
		notStop = false;
		isPaused = false;
	}
	
	public static int getsize()
	{
		return settlement.size();
	}
	
	public Settlement at(int index) {
		return settlement.get(index);
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
		return settlement.iterator();
	}
	
	
	/*
	 * The method gets a place and adds it to the list of places on the map
	 */
	
	public void AddSettlements(Settlement st)
	{
		settlement.add(st);
	}

	public Settlement[] getSettlement() {
		// TODO Auto-generated method stub
		return settlement.toArray(new Settlement[0]);
	}
	
}
