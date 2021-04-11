package Country;
import Country.Settlement;

public class Map {
	private Settlement [] settlement;
	private static int size =1;
	private static int index =0;
	
	public  Map()
	{
		settlement = new Settlement[size];
		System.out.println("end constructor of map with this.size: "+ this.size); 
	}
	
	public Settlement[] getSettlement()
	{
		return settlement;
	}
	
	public void setSettlement(Settlement [] settlement)
	{
		this.settlement =  settlement;
	}
	
	
	
	
	public void AddSettlements(Settlement st)
	{
		
		settlement[index]=st;
		index++;
		//checking if the map is full mult the size of it and copy elements
		if(index == size) 
		{
			size = size *2;
			Settlement [] settlementTemp = new Settlement[size];
			for(int i=0;i<settlement.length;i++)
			{
				settlementTemp[i]= settlement[i];
			}
			settlement= settlementTemp;

		}
	}

}
