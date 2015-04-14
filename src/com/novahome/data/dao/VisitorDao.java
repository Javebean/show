package com.novahome.data.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Transportation;
import com.novahome.data.pojo.Visitor;


@Component("visitorDao")
public class VisitorDao {

	private static final Logger logger = Logger.getLogger(VisitorDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getVisitorTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Visitor");
		return (Long) query.uniqueResult();
	}
	
	public long getVisitorCountByType(int type)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Visitor a where a.type = :type");
		query.setParameter("type", type);
		return (Long) query.uniqueResult();
	}
	
	public String saveVisitor(Visitor visitor)
	{
		return sessionFactory.getCurrentSession().save(visitor).toString();
	}
	
	public Visitor getVisitorById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor a where a.id=:id");
				query.setString("id", id);
				return (Visitor) query.uniqueResult();
	}
	
	public List<Visitor> getVisitorByEid(String eid)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor a where a.eid=:eid");
				query.setString("eid", eid);
				return query.list();
	}
	
	public List<Visitor> getVisitorByOrg(String org)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor a where a.org=:org");
				query.setString("org", org);
				return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visitor>getVisitorForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor order by id desc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	
	public long deleteVisitorById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Visitor where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateVisitor(Visitor visitor) {
		Session s = sessionFactory.getCurrentSession();
		s.update(visitor);
		return true;
	}
	
}
