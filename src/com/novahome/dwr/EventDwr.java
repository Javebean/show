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
	/**
	 * 获取申办活动数量
	 * @return
	 */
	@RemoteMethod
	public String getEventTotalCount() 
	{
		return eventService.getEventTotalCount();
	}
	
	/**
	 * 存储一个申办活动
	 * @param event
	 * @return
	 */
	@RemoteMethod
	public String saveEvent(Event event)
	{
		return eventService.saveEvent(event);
	}
	
	/**
	 * 基于时间查询申办活动
	 * @param time
	 * @return
	 */
	@RemoteMethod
	public String getEventBetweenTime(Date time)
	{
		return eventService.getEventBetweenTime(time);
	}
	
	/**
	 * 通过发起人查找申办活动
	 * @param aid 发起观众id
	 * @return
	 */
	@RemoteMethod
	public String getEventByPeople(String aid)
	{
		return eventService.getEventByPeople(aid);
	}
	
	/**
	 * 通过活动id查找活动和发起人信息（包含了发起观众的所有信息）
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getPeopleEventById(String id)
	{
		return eventService.getPeopleEventById(id);
	}
	
	/**
	 * 分页查询申办活动
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getEventForPage(int start, int number)
	{
		return eventService.getEventForPage(start, number);
	}
	
	/**
	 * 分页查询活动和申办人信息（包含了发起观众的所有信息）
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getPeopleEventForPage(int start, int number)
	{
		return eventService.getPeopleEventForPage(start, number);
	}
	
	/**
	 * 删除一个申办活动
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteEventById(String id)
	{
		return eventService.deleteEventById(id);
	}
	
}
