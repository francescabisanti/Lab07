package it.polito.tdp.poweroutages.model;

import java.util.Comparator;

public class OrdinaAnno implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		EventBlackOut e1= (EventBlackOut) o1;
		EventBlackOut e2= (EventBlackOut) o2;
		return e1.getDate_event_began().compareTo(e2.getDate_event_began());
	}
	

}
