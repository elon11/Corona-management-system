package Io;

public class Originator {
	private String state; 
	
	public static  void setState(String state) { 
		state = state;
	} 
	
	public String getState() {
		return state;
	} 
	
	public Memento createMemento() { 
		return new Memento(state); 
	} 
	public void setMemento(Memento memento) { 
		state = memento.getState(); 
	} 
	

}
