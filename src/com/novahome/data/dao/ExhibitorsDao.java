package com.novahome.data.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novahome.data.model.ShortZlzx;
import com.novahome.data.pojo.Exhibitors;
import com.novahome.data.pojo.Zlzx;

@Component("exhibitsDao")
public class ExhibitorsDao {

	private static final Logger logger = Logger.getLogger(ExhibitorsDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getExhibitorsTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Exhibitors");
		return (Long) query.uniqueResult();
	}
	
	public String saveExhibitor(Exhibitors exhibitor)
	{
		return sessionFactory.getCurrentSession().save(exhibitor).toString();
	}
	
	public Exhibitors getExhibitorById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Exhibitors a where a.id=:id");
				query.setString("id", id);
				return (Exhibitors) query.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Exhibitors>getExhibitorForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Exhibitors order by id desc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ShortZlzx>getShortExhibitorForPage(int start, int number)
	{
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				"Select a.id, a.orgName, a.region,a.phone,a.username,a.applyTime from Exhibitors a order by id desc")
				 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone")
				  .addScalar("username").addScalar("applyTime") ;
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数	
		Class cls = null;
		try {
			cls = Class.forName("com.novahome.data.model.ShortExhibitor");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = query.setResultTransformer(Transformers.aliasToBean(cls)).list();
		return list;
	}
	
	
	public long deleteExhibitorById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Exhibitors where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateExhibitor(Exhibitors exhibitor) {
		Session s = sessionFactory.getCurrentSession();
		s.update(exhibitor);
		return true;
	}
}
