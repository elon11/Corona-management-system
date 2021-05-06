/*
 * elon ifrah 207229931
 * yossi iluz 208510248
 * The department describes the map of the area
 */
package Country;


public class Map {
	private Settlement [] settlement;
	private static int size =1;
	private static int index =0;
	
	public  Map ()
	{
		
		settlement = new Settlement[size];
		System.out.println("end constructor of map with this.size: "+ Map.size); 
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
		System.out.println("*****"+settlement.length);
	}
	
	
	

}
