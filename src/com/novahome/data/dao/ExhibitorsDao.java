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

import com.novahome.data.model.ShortExhibitor;

import com.novahome.data.pojo.Exhibitors;


@Component("exhibitorsDao")
public class ExhibitorsDao {

	private static final Logger logger = Logger.getLogger(ExhibitorsDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getExhibitorsTotalCount(String orgName)
	{
		Query query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery("select count(*) from Exhibitors");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Exhibitors a where a.orgName like '%" + orgName +"%'");
		}
		return (Long) query.uniqueResult();
	}
	
	public long getExhibitorsCountByState(int state, String orgName)
	{
		Query query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Exhibitors a where a.state = :state");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Exhibitors a where a.state = :state and a.orgName like '%" + orgName +"%'");
		}
		query.setParameter("state", state);
		return (Long) query.uniqueResult();
	}
	
	public long getExhibitorsCountByRecommender(String recommender)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(a.id) from Exhibitors a where a.state = :state and a.recommender = :recommender");
		query.setParameter("state", 1);
		query.setParameter("recommender", recommender);
		return (Long) query.uniqueResult();
	}
	
	public List<String>getDistinctRecommenders()
	{
		SQLQuery  query = sessionFactory.getCurrentSession().createSQLQuery(
				"select DISTINCT recommender from exhibitors");
		return query.list();
	}
	
	public String saveExhibitor(Exhibitors exhibitor)
	{
		return sessionFactory.getCurrentSession().save(exhibitor).toString();
	}
	
	public List<Exhibitors> getExhibitorByOrgName(String orgName)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Exhibitors a where a.orgName=:orgName");
				query.setString("orgName", orgName);
		return (List<Exhibitors>) query.list();
	}
	
	public Exhibitors getExhibitorByOrgNameWithRegistered(String orgName)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Exhibitors a where a.orgName=:orgName and (a.state = 1 or a.state = 0)");
				query.setParameter("orgName", orgName);
		return (Exhibitors) query.uniqueResult();
	}
	
	public Exhibitors getExhibitorById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Exhibitors a where a.id=:id");
				query.setString("id", id);
				return (Exhibitors) query.uniqueResult();
	}
	
	public Exhibitors getExhibitorsByUserName(String username)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Exhibitors a where a.username=:username");
				query.setString("username", username);
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
	public List<ShortExhibitor>getShortExhibitorForPage(int start, int number, String orgName)
	{
		SQLQuery query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
				"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth from Exhibitors a order by logo desc")
				 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
				  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth") ;
		}
		else
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth from Exhibitors a where a.orgName like '%" + orgName + "%' order by logo desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth") ;
		}
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
	
	@SuppressWarnings("unchecked")
	public List<ShortExhibitor>getShortExhibitorForPageByState(int start, int number, int state, String orgName)
	{
		//sql  ="(select * from exhibitors where logo is not null order by applyTime desc  limit 999999) union (select * from exhibitors where logo is null order by applyTime desc  limit 999999)";
		
		SQLQuery query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth from Exhibitors a where a.state = :state order by logo desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth") ;
			
		}
		else
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth from Exhibitors a where a.state = :state and a.orgName like '%" + orgName + "%' order by logo desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth") ;
		}
		query.setParameter("state", state);
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
