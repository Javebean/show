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
import com.novahome.data.pojo.DisplayItem;
import com.novahome.data.pojo.Zytz;

@Component("displayItemDao")
public class DisplayItemDao {

	private static final Logger logger = Logger.getLogger(DisplayItemDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getDisplayItemTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from DisplayItem");
		return (Long) query.uniqueResult();
	}
	
	public String saveDisplayItem(DisplayItem displayItem)
	{
		return sessionFactory.getCurrentSession().save(displayItem).toString();
	}
	
	public DisplayItem getDisplayItemById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from DisplayItem a where a.id=:id");
				query.setString("id", id);
				return (DisplayItem) query.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DisplayItem>getDisplayItemForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from DisplayItem order by id desc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DisplayItem>getDisplayItemByEid(String eid)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from DisplayItem a where a.eid = :eid");
		query.setString("eid", eid);
		return query.list();
	}
	
	public long deleteDisplayItemById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from DisplayItem where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateDisplayItem(DisplayItem displayItem) {
		Session s = sessionFactory.getCurrentSession();
		s.update(displayItem);
		return true;
	}
}
