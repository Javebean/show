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

import com.novahome.data.model.ShortZytz;
import com.novahome.data.pojo.Event;
import com.novahome.data.pojo.Zlzx;
import com.novahome.data.pojo.Zytz;

@Component("zytzDao")
public class ZytzDao {

	private static final Logger logger = Logger.getLogger(ZytzDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getZytzTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Zytz");
		return (Long) query.uniqueResult();
	}
	
	public String saveZytz(Zytz zy)
	{
		return sessionFactory.getCurrentSession().save(zy).toString();
	}
	
	public Zytz getZytzById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Zytz a where a.id=:id");
				query.setString("id", id);
				return (Zytz) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Zytz>getZytzByTitle(String title)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Zytz a where a.title = :title");
				query.setParameter("title", title);
				return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Zytz>getZytzForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Zytz order by publishTime desc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ShortZytz>getShortZytzForPage(int start, int number)
	{
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				"Select a.id, a.title, a.publishTime,a.abs from Zytz a order by id desc")
				 .addScalar("id").addScalar("title")
				  .addScalar("publishTime").addScalar("abs") ;
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数	
		Class cls = null;
		try {
			cls = Class.forName("com.novahome.data.model.ShortZytz");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = query.setResultTransformer(Transformers.aliasToBean(cls)).list();
		return list;
	}
	
	public long deleteZytzById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Zytz where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateZytz(Zytz zytz) {
		Session s = sessionFactory.getCurrentSession();
		s.update(zytz);
		return true;
	}
}
