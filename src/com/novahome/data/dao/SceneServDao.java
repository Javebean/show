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
import com.novahome.data.pojo.SceneServ;
import com.novahome.data.pojo.Zytz;

@Component("sceneServDao")
public class SceneServDao {

	private static final Logger logger = Logger.getLogger(SceneServDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getSceneServTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from SceneServ");
		return (Long) query.uniqueResult();
	}
	
	public long getSceneServCountByEid(String eid)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from SceneServ a where a.eid = :eid");
		query.setString("eid", eid);
		return (Long) query.uniqueResult();
	}
	
	public String saveSceneServ(SceneServ sceneServ)
	{
		return sessionFactory.getCurrentSession().save(sceneServ).toString();
	}
	
	public SceneServ getSceneServById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from SceneServ a where a.id=:id");
				query.setString("id", id);
				return (SceneServ) query.uniqueResult();
	}
	
	public List<SceneServ> getSceneServByEid(String eid)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from SceneServ a where a.eid=:eid");
				query.setString("eid", eid);
				return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<SceneServ>getSceneServByUsername(String username)
	{
		return  sessionFactory.getCurrentSession().createSQLQuery(
				"select * from SceneServ s where s.eid in (select id from exhibitors e where e.username = '" + username + "')")
				.addEntity( "SceneServ" , SceneServ. class ).list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<SceneServ>getSceneServForPage(int start, int number)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from SceneServ order by id desc");
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	
	
	public long deleteSceneServById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from SceneServ where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateSceneServ(SceneServ sceneServ) {
		Session s = sessionFactory.getCurrentSession();
		s.update(sceneServ);
		return true;
	}
	
	public List<SceneServ>getAllSceneServ()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from SceneServ");
		return query.list();
	}
}
