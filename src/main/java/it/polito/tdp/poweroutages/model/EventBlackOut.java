package it.polito.tdp.poweroutages.model;
import java.time.*;
public class EventBlackOut implements Comparable<EventBlackOut>{
	Integer event_type_id;
	LocalDateTime date_event_began;
	LocalDateTime date_event_finished;
	Integer customers_affected;
	float oreDisservizio;
	int anno;

	
	
	public EventBlackOut(Integer event_type_id, LocalDateTime date_event_began, LocalDateTime date_event_finished,
			Integer customers_affected, float oreDisservizio, int anno) {
		super();
		this.event_type_id = event_type_id;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.customers_affected = customers_affected;
		this.oreDisservizio = oreDisservizio;
		this.anno = anno;
	}
	
	
	public Integer getEvent_type_id() {
		return event_type_id;
	}


	public void setEvent_type_id(Integer event_type_id) {
		this.event_type_id = event_type_id;
	}


	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}


	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}


	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}


	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}


	public Integer getCustomers_affected() {
		return customers_affected;
	}


	public void setCustomers_affected(Integer customers_affected) {
		this.customers_affected = customers_affected;
	}


	public float getOreDisservizio() {
		return oreDisservizio;
	}


	public void setOreDisservizio(float oreDisservizio) {
		this.oreDisservizio = oreDisservizio;
	}


	public int getAnno() {
		return anno;
	}


	public void setAnno(int anno) {
		this.anno = anno;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event_type_id == null) ? 0 : event_type_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventBlackOut other = (EventBlackOut) obj;
		if (event_type_id == null) {
			if (other.event_type_id != null)
				return false;
		} else if (!event_type_id.equals(other.event_type_id))
			return false;
		return true;
	}


	

	@Override
	public int compareTo(EventBlackOut o) {
		// TODO Auto-generated method stub
		return this.getDate_event_began().compareTo(o.getDate_event_began());
		
	}
	
	
	
	
	
	
}
