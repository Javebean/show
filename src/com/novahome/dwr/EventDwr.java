package com.novahome.dwr;

import java.util.Date;
import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.novahome.data.pojo.Event;
import com.novahome.data.service.EventService;


@Scope("prototype")
@Component("event_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "event_dwr"), name = "event_dwr")
public class EventDwr {

	@Resource
	private EventService eventService;
	@RemoteMethod
	public String getEventTotalCount() 
	{
		return eventService.getEventTotalCount();
	}
	
	@RemoteMethod
	public String saveEvent(Event event)
	{
		return eventService.saveEvent(event);
	}
	
	@RemoteMethod
	public String getEventBetweenTime(Date time)
	{
		return eventService.getEventBetweenTime(time);
	}
	
	@RemoteMethod
	public String getEventByPeople(String aid)
	{
		return eventService.getEventByPeople(aid);
	}
	
	@RemoteMethod
	public String getPeopleEventById(String id)
	{
		return eventService.getPeopleEventById(id);
	}
	
	@RemoteMethod
	public String getEventForPage(int start, int number)
	{
		return eventService.getEventForPage(start, number);
	}
	
	@RemoteMethod
	public String getPeopleEventForPage(int start, int number)
	{
		return eventService.getPeopleEventForPage(start, number);
	}
	
	@RemoteMethod
	public long deleteEventById(String id)
	{
		return eventService.deleteEventById(id);
	}
	
}
