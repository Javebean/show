package com.novahome.data.model;

import com.novahome.data.pojo.Audience;
import com.novahome.data.pojo.Event;


public class PeopleEvent {

	private Event event;
	private Audience audience;
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Audience getAudience() {
		return audience;
	}
	public void setAudience(Audience audience) {
		this.audience = audience;
	}
	
	
}
