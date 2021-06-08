package Io;

import java.awt.List;
import java.util.ArrayList;

import Population.Person;

public class Caretaker {

	private ArrayList statesList = new ArrayList(); 
	public void addMemento(Memento m) { 
		statesList.add(m); 
	} 
	public Object getMemento(int index) { 
		return statesList.get(index); 
	} 

}
