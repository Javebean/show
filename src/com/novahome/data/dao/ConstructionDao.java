package com.novahome.data.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Construction;

@Component("constructionDao")
public class ConstructionDao {

	private static final Logger logger = Logger.getLogger(ConstructionDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getConstructionTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Construction");
		return (Long) query.uniqueResult();
	}
	
	public long getConstructionCountByEid(String eid)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Construction a where a.eid = :eid");
		query.setString("eid", eid);
		return (Long) query.uniqueResult();
	}
	
	public String saveConstruction(Construction construction)
	{
		return sessionFactory.getCurrentSession().save(construction).toString();
	}
	
	public Construction getConstructionById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Construction a where a.id=:id");
				query.setString("id", id);
				return (Construction) query.uniqueResult();
	}
	
	public List<Construction> getConstructionByEid(String eid)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Construction a where a.eid=:eid");
				query.setString("eid", eid);
				return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Construction>getConstructionForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Construction order by id desc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	
	
	public long deleteConstructionById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Construction where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateConstruction(Construction construction) {
		Session s = sessionFactory.getCurrentSession();
		s.update(construction);
		return true;
	}
}
