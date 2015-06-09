package com.novahome.data.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Account;
import com.novahome.data.pojo.Audience;
import com.novahome.data.pojo.Construction;
import com.novahome.dwr.TestClobDwr;
import com.novahome.utils.Ut;

@Component("audienceDao")
public class AudienceDao {

	private static final Logger logger = Logger.getLogger(AudienceDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getAudienceTotalCount(String name) {
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery("select count(*) from Audience");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery("select count(*) from Audience where name like '%" + name + "%'");
		}
		return (Long) query.uniqueResult();
	}
	
	/*public long getAudienceCountByRegion(String region)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(a.id) from Exhibitors a where a.state = :state and a.recommender = :recommender");
		query.setParameter("state", 1);
		query.setParameter("region", region);
		return (Long) query.uniqueResult();
	}
	
	public List<String>getDistinctRecommenders()
	{
		SQLQuery  query = sessionFactory.getCurrentSession().createSQLQuery(
				"select DISTINCT recommender from exhibitors");
		return query.list();
	}*/
	
	
	public String saveAudience(Audience audience) {
		return sessionFactory.getCurrentSession().save(audience).toString();
	}
	
	public Audience getAudienceByUserName(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Audience a where a.username=:username");
		query.setString("username", username);
		return (Audience) query.uniqueResult();
	} 

	public Audience getAudienceById(String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Audience a where a.id=:id");
		query.setString("id", id);
		return (Audience) query.uniqueResult();
	} 
	
	@SuppressWarnings("unchecked")
	public List<Audience>getAudienceForPage(int start, int number, String name)
	{
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery(
					"from Audience order by applyTime desc");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery(
				"from Audience where name like '%" + name + "%' order by applyTime desc" );
		}
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		
		return query.list();
	}

	
	public long deleteAudienceById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Audience where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateAudience(Audience audience) {
		Session s = sessionFactory.getCurrentSession();
		s.update(audience);
		return true;
	} 
}
