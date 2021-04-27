/*
 * elon ifrah 207229931
 * yosi iluz 208510248
 * The class describes the current time in the simulation
 */
package Simulation;

public class Clock {
	private static long time;
	private static long ticks_per_day;
	
	public Clock()
	{
		Clock.time = 0;
		Clock.ticks_per_day = 1;
	}
	public Clock(long time)
	{
		Clock.time = time;
	}
	
	/*
	 * { @time #STATIC_FIELD }
	 */
	public static long now()
	{
		return time;
	}
	
	/*
	 * Promote the value of the clock by 1
	 */
	public static void nextTick()
	{
		time++;
	}
	
	public static long past_day(int start)
	{
		long day = Math.round( time/ticks_per_day + start +0.5);
		return day;
	}
}
