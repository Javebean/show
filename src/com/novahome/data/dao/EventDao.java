package com.novahome.data.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novahome.data.model.PeopleEvent;
import com.novahome.data.pojo.Audience;
import com.novahome.data.pojo.Event;

@Component("eventDao")
public class EventDao {

	private static final Logger logger = Logger.getLogger(EventDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getEventTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Event");
		return (Long) query.uniqueResult();
	}
	
	public String saveEvent(Event event)
	{
		return sessionFactory.getCurrentSession().save(event).toString();
	}
	
	public PeopleEvent getPeopleEventById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Audience a, Event e where e.aid = a.id and e.id = :id");
		//???可以考虑从audience的id换成audience的username来查询，引文username也是系统唯一的
				query.setString("id", id);
				Object[] obs = (Object[])query.uniqueResult();
				logger.info("obs0:" + obs[0]  );
				logger.info("*****************");
				logger.info("obs1:" + obs[1]);
				if(obs.length >= 2)
				{	
					PeopleEvent peopleEvent  = new PeopleEvent();
					peopleEvent.setAudience((Audience) obs[0]);
					peopleEvent.setEvent((Event) obs[1]);
					return peopleEvent;
				}
				return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Event>getEventBetweenTime(Date time)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Event a where a.starttime <= :time and a.endtime >=:time");
				query.setParameter("time", time);
				return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getEventByPeople(String aid) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Event a where a.aid=:aid");
		query.setParameter("aid", aid);
		return query.list();
	}  
	
	@SuppressWarnings("unchecked")
	public Event getEventById(String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Event a where a.id=:id");
		query.setParameter("id", id);
		return (Event) query.uniqueResult();
	}  
		
	@SuppressWarnings("unchecked")
	public List<Event>getEventForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Event order by id asc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PeopleEvent>getPeopleEventForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Audience a, Event e where e.aid = a.id order by e.id asc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数				
		List ls = query.list();
		List<PeopleEvent>result = new ArrayList<PeopleEvent>();
		for(int i=0; i<ls.size(); i++)
		{
			Object[]obs = (Object[])ls.get(i);
			if(obs.length >=2)
			{
				PeopleEvent peopleEvent  = new PeopleEvent();
				peopleEvent.setAudience((Audience) obs[0]);
				peopleEvent.setEvent((Event) obs[1]);
				result.add(peopleEvent);
			}
		}
		return result;	
	}
	
	public long deleteEventById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Event where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
}
