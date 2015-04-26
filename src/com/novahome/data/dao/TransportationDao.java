package com.novahome.data.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.SceneServ;
import com.novahome.data.pojo.Transportation;

@Component("transportationDao")
public class TransportationDao {

	private static final Logger logger = Logger.getLogger(TransportationDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getTransportationTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Transportation");
		return (Long) query.uniqueResult();
	}
	
	public long getTransportationCountByEid(String eid)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Transportation a where a.eid = :eid");
		query.setString("eid", eid);
		return (Long) query.uniqueResult();
	}
	
	public String saveTransportation(Transportation transportation)
	{
		return sessionFactory.getCurrentSession().save(transportation).toString();
	}
	
	public Transportation getTransportationById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Transportation a where a.id=:id");
				query.setString("id", id);
				return (Transportation) query.uniqueResult();
	}
	
	public List<Transportation> getTransportationByEid(String eid)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Transportation a where a.eid=:eid");
				query.setString("eid", eid);
				return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Transportation>getTransportationForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Transportation order by id desc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	
	
	public long deleteTransportationById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Transportation where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateTransportation(Transportation transportation) {
		Session s = sessionFactory.getCurrentSession();
		s.update(transportation);
		return true;
	}
	
	public List<Transportation>getAllTransportation()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Transportation");
		return query.list();
	}
	
}
