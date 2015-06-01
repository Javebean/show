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

import com.novahome.data.model.ShortZlzx;
import com.novahome.data.pojo.Event;
import com.novahome.data.pojo.Zlzx;
import com.novahome.data.pojo.Zytz;

@Component("zlzxDao")
public class ZlzxDao {

	private static final Logger logger = Logger.getLogger(ZlzxDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getZlzxTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Zlzx");
		return (Long) query.uniqueResult();
	}
	
	public String saveZlzx(Zlzx zy)
	{
		return sessionFactory.getCurrentSession().save(zy).toString();
	}
	
	public Zlzx getZlzxById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Zlzx a where a.id=:id");
				query.setString("id", id);
				return (Zlzx) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Zlzx>getZlzxByTitle(String title)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Zlzx a where a.title = :title");
				query.setParameter("title", title);
				return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Zlzx>getZlzxForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Zlzx order by publishTime desc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ShortZlzx>getShortZlzxForPage(int start, int number)
	{
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				"Select a.id, a.title, a.publishTime,a.abs from Zlzx a order by a.publishTime desc")
				 .addScalar("id").addScalar("title")
				  .addScalar("publishTime").addScalar("abs") ;
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数	
		Class cls = null;
		try {
			cls = Class.forName("com.novahome.data.model.ShortZlzx");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = query.setResultTransformer(Transformers.aliasToBean(cls)).list();
		return list;
	}
	
	
	public long deleteZlzxById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Zlzx where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateZlzx(Zlzx zlzx) {
		Session s = sessionFactory.getCurrentSession();
		s.update(zlzx);
		return true;
	}
}
