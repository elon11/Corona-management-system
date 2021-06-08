package Io;


import java.util.*;

import Population.Person;

public class Caretaker {

	private List<Memento> statesList = new ArrayList<Memento>(); 
	public void addMemento(Memento m) { 
		statesList.add(m); 
	} 
	public Memento getMemento(int index) { 
		return statesList.get(index); 
	} 

}
