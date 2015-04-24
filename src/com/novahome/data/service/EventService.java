package com.novahome.data.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.data.dao.EventDao;
import com.novahome.data.dao.ZytzDao;
import com.novahome.data.model.PeopleEvent;
import com.novahome.data.pojo.Event;
import com.novahome.data.pojo.Zytz;
import com.novahome.utils.Ut;

@Service("eventService")
@Transactional(readOnly = false)
@Repository
public class EventService {


	private static final Logger logger = Logger.getLogger(EventService.class);
	@Resource(name = "eventDao")
	private EventDao eventDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的活动申请\"}";

	
	public String getEventTotalCount() 
	{
		long count = eventDao.getEventTotalCount();
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String saveEvent(Event event)
	{
		event.setApplyTime(new Date());
		String id = eventDao.saveEvent(event);
		logger.info("save event");
		
		JSONObject obj = new JSONObject();
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功申请专题活动！");
		obj.put("name", event.getEventName());
		obj.put("id", id);
		obj.put("applytime", Ut.newsDf.format(event.getApplyTime()));
		
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getEventBetweenTime(Date time)
	{
		List<Event> ls = eventDao.getEventBetweenTime(time);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Event event : ls )
		{
			array.put(new JSONObject(event));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getPeopleEventById(String id)
	{
		PeopleEvent event = eventDao.getPeopleEventById(id);
		if(event == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(event);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getEventByPeople(String aid)
	{
		List<Event>ls = eventDao.getEventByPeople(aid);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Event event : ls )
		{
			array.put(new JSONObject(event));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getEventForPage(int start, int number)
	{
		List<Event>ls = eventDao.getEventForPage(start, number);
		if(ls == null  || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = eventDao.getEventTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Event event : ls )
		{
			array.put(new JSONObject(event));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("event:"+ ret);
		return ret;	
	}
	
	public String getPeopleEventForPage(int start, int number)
	{
		List<PeopleEvent>ls = eventDao.getPeopleEventForPage(start, number);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = eventDao.getEventTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(PeopleEvent event : ls )
		{
			array.put(new JSONObject(event));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("event:"+ ret);
		return ret;	
	}
	
	public long deleteEventById(String id)
	{
		return eventDao.deleteEventById(id);
	}
	
	
	
}
