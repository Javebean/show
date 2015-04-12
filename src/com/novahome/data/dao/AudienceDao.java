package com.novahome.data.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Account;
import com.novahome.data.pojo.Audience;
import com.novahome.dwr.TestClobDwr;
import com.novahome.utils.Ut;

@Component("audienceDao")
public class AudienceDao {

	private static final Logger logger = Logger.getLogger(AudienceDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getAudienceTotalCount() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Audience");
		return (Long) query.uniqueResult();
	}
	
	public String saveAudience(Audience audience) {
		return sessionFactory.getCurrentSession().save(audience).toString();
	}
	
	public Audience getAudienceByUserName(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Audience a where a.username=:username");
		query.setString("username", username);
		return (Audience) query.uniqueResult();
	} 
	
	@SuppressWarnings("unchecked")
	public List<Audience> getAudienceByName(String name) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Audience a where a.name=:name");
		query.setParameter("name", name);
		return query.list();
	}  
	
	public Audience getAudienceById(String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Audience a where a.id=:id");
		query.setString("id", id);
		return (Audience) query.uniqueResult();
	} 
	
	@SuppressWarnings("unchecked")
	public List<Audience>getAudienceForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Audience order by id asc");
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
}
