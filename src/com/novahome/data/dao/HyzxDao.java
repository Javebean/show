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
import org.springframework.transaction.annotation.Transactional;


import com.novahome.data.model.ShortHyzx;
import com.novahome.data.pojo.Hyzx;


//@Transactional
@Component("hyzxDao")
public class HyzxDao {

	private static final Logger logger = Logger.getLogger(HyzxDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getHyzxTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Hyzx");
		return (Long) query.uniqueResult();
	}
	
	public String saveHyzx(Hyzx hy)
	{
		return sessionFactory.getCurrentSession().save(hy).toString();
	}
	
	public Hyzx getHyzxById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Hyzx a where a.id=:id");
				query.setString("id", id);
				return (Hyzx) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Hyzx>getHyzxByTitle(String title)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Hyzx a where a.title = :title");
				query.setParameter("title", title);
				return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Hyzx>getHyzxForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Hyzx order by publishTime desc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ShortHyzx>getShortHyzxForPage(int start, int number)
	{
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				"Select a.id, a.title, a.publishTime,a.abs from Hyzx a order by a.publishTime desc")
				 .addScalar("id").addScalar("title")
				  .addScalar("publishTime").addScalar("abs") ;
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数	
		Class cls = null;
		try {
			cls = Class.forName("com.novahome.data.model.ShortHyzx");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = query.setResultTransformer(Transformers.aliasToBean(cls)).list();
		return list;
	}
	
	public long deleteHyzxById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Hyzx where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateHyzx(Hyzx hyzx) {
		Session s = sessionFactory.getCurrentSession();
		s.update(hyzx);
		return true;
	}
}
