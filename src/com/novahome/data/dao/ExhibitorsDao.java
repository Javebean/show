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
	
	public Exhibitors getExhibitorsByUserName(String username)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Exhibitors a where a.username=:username");
				query.setString("username", username);
				return (Exhibitors) query.uniqueResult();
	}
	
	/*public TotalExhibitInfo getTotalExhibitInfo(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Exhibitors e, Visitor v, DisplayItem d, Construction c, Transportation t, SceneServ s " +
				"where e.id =v.eid and e.id = c.eid and e.id = d.eid and e.id = t.eid and e.id = s.eid " +
				"and e.id = :id");
				query.setString("id", id);
				List ls = query.list();
				TotalExhibitInfo info = new TotalExhibitInfo();
				List<Construction>construction = new ArrayList<Construction>();
				List<Transportation>transportation = new ArrayList<Transportation>();
				List<SceneServ>sceneServ = new ArrayList<SceneServ>();
				List<DisplayItem>displayItem = new ArrayList<DisplayItem>();
				List<Visitor>visitor = new ArrayList<Visitor>();
				for(int i=0; i<ls.size(); i++)
				{
					System.out.println("index: " + i);
					Object[]objs = (Object[]) ls.get(i);
					for(int j =0; j< objs.length;j++ )
					{
						switch (j)
						{
							case 0:
								info.setExhibitors((Exhibitors) objs[j]);
								break;
							case 1:
								visitor.add((Visitor) objs[j]);
								break;
							case 2:
								displayItem.add((DisplayItem) objs[j]);
								break;
							case 3:
								construction.add((Construction) objs[j]);
								break;
							case 4:
								transportation.add((Transportation) objs[j]);
								break;
							case 5:
								sceneServ.add((SceneServ) objs[j]);
								break;
						}
					}
					info.setConstruction(construction);
					info.setDisplayItem(displayItem);
					info.setSceneServ(sceneServ);
					info.setVisitor(visitor);
					info.setTransportation(transportation);
					
					JSONObject o = new JSONObject(info);
					System.out.println(o.toString());
				}
				return null;
		
	}*/
	
	
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
	public List<ShortExhibitor>getShortExhibitorForPage(int start, int number)
	{
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime from Exhibitors a order by id desc")
				 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
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
