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

import com.novahome.data.pojo.Exhibitors;
import com.novahome.data.pojo.Transportation;
import com.novahome.data.pojo.Visitor;
import com.novahome.utils.VisitorQueryCreator;


@Component("visitorDao")
public class VisitorDao {

	private static final Logger logger = Logger.getLogger(VisitorDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getVisitorTotalCount(String name)
	{
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Visitor");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Visitor where name like '%" + name + "%'");
		}
		return (Long) query.uniqueResult();
	}
	
	public long getVisitorTotalCountMutipleCon(String name, int type)
	{
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			if(type == -1)
			{
				query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Visitor");
			}
			else 
			{
				query = sessionFactory.getCurrentSession().createQuery(
						"select count(*) from Visitor a where a.type = :type");
				query.setParameter("type", type);
			}
		}
		else
		{
			if(type == -1 )
			{
				query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Visitor where name like '%" + name + "%'");
			}
			else
			{
				query = sessionFactory.getCurrentSession().createQuery(
						"select count(*) from Visitor a where a.type = :type and a.name like '%" + name + "%'");
				query.setParameter("type", type);
			}
		}
		return (Long) query.uniqueResult();
	}
	
	
	public long getVisitorCountByState(int state, String name)
	{
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery("select count(*) from Visitor a where a.state = :state");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery("select count(*) from Visitor a where a.state = :state and a.name like '%" + name + "%'");
		}
		query.setParameter("state", state);
		return (Long) query.uniqueResult();
	}
	
	public long getVisitorCountByStateMutipleCon(int state, String name, int type)
	{
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			if(type == -1)
			{
				query = sessionFactory.getCurrentSession().createQuery("select count(*) from Visitor a where a.state = :state");
			}
			else
			{
				query = sessionFactory.getCurrentSession().createQuery("select count(*) from Visitor a where a.type = :type and a.state = :state");
				query.setParameter("type", type);
			}
		}
		else
		{
			if(type == -1)
			{
				query = sessionFactory.getCurrentSession().createQuery("select count(*) from Visitor a where a.state = :state and a.name like '%" + name + "%'");
			}
			else
			{
				query = sessionFactory.getCurrentSession().createQuery("select count(*) from Visitor a where a.type = :type and a.state = :state and a.name like '%" + name + "%'");
				query.setParameter("type", type);
			}
		}
		query.setParameter("state", state);
		return (Long) query.uniqueResult();
	}
	
	
	public long getVisitorCountByType(int type)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Visitor a where a.type = :type");
		query.setParameter("type", type);
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 9-14,根据穆东成9月9日意见新增
	 * @param type
	 * @param con
	 * @return
	 */
	public long getVisitorCountMutipleConFirstAuditNoState(int type, String... con)
	{
		StringBuffer basicQueryStr  =  new StringBuffer("select count(*) from Visitor");
		VisitorQueryCreator creator = new VisitorQueryCreator(con);
		String creatorStr = creator.createStatement();
		Query query = createVisitorQuery(type, basicQueryStr, creatorStr,0);		
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 9-14,根据穆东成9月9日意见新增
	 * @param firstState
	 * @param type
	 * @param con
	 * @return
	 */
	public long getVisitorCountMutipleConFirstAudit(int firstState, int type, String... con)
	{
		StringBuffer basicQueryStr  =  new StringBuffer("select count(*) from Visitor where firstState = :firstState");
		VisitorQueryCreator creator = new VisitorQueryCreator(con);
		String creatorStr = creator.createStatement();
		Query query = createVisitorQuery(type, basicQueryStr, creatorStr,0);		
		query.setParameter("firstState", firstState);
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 9-14,根据穆东成9月9日意见新增
	 * @param type
	 * @param con
	 * @return
	 */
	public long getVisitorCountMutipleConFinalNoState(int type, String... con)
	{
		StringBuffer basicQueryStr  =  new StringBuffer("select count(*) from Visitor where (firstState = " + 1 + " or state = " + 2 + ")");
		VisitorQueryCreator creator = new VisitorQueryCreator(con);
		String creatorStr = creator.createStatement();
		Query query = createVisitorQuery(type, basicQueryStr, creatorStr,0);				
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 9-14,根据穆东成9月9日意见新增
	 * @param state
	 * @param type
	 * @param con
	 * @return
	 */
	public long  getVisitorCountMutipleConFinal(int state, int type, String... con)
	{
		StringBuffer basicQueryStr;
		if(state == 2)
			basicQueryStr  =  new StringBuffer("select count(*) from Visitor where state = :state");
		else
			basicQueryStr  =  new StringBuffer("select count(*) from Visitor where (firstState = 1 and state = :state)");
		VisitorQueryCreator creator = new VisitorQueryCreator(con);
		String creatorStr = creator.createStatement();
		Query query = createVisitorQuery(type, basicQueryStr, creatorStr,0);
		query.setParameter("state", state);
		return (Long) query.uniqueResult();
	}
	
	public String saveVisitor(Visitor visitor)
	{
		return sessionFactory.getCurrentSession().save(visitor).toString();
	}
	
	public Visitor getVisitorById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor a where a.id=:id");
				query.setString("id", id);
				return (Visitor) query.uniqueResult();
	}
	
	public List<Visitor> getVisitorByEid(String eid)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor a where a.eid=:eid");
				query.setString("eid", eid);
				return query.list();
	}
	
	public List<Visitor> getVisitorByOrg(String org)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor a where a.org=:org");
				query.setString("org", org);
				return query.list();
	}
	
	public List<Visitor>getVisitorByName(String name)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor a where a.name=:name");
				query.setString("name", name);
				return query.list();
	}
	
	public Visitor getVisitorWithIdNoRegistered(String idNo, int idType)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor a where a.idNo=:idNo and a.idType = :idType");
				query.setParameter("idNo", idNo);
				query.setParameter("idType", idType);
		return (Visitor) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visitor>getVisitorForPage(int start, int number, String name)
	{
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery("from Visitor order by applyTime desc");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery("from Visitor where name like '%" + name  + "%' order by applyTime desc");
		}
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visitor>getVisitorForPageMutipleCon(int start, int number, String name, int type)
	{
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			if(type == -1)
			{
				query = sessionFactory.getCurrentSession().createQuery("from Visitor order by applyTime desc");
			}
			else
			{
				query = sessionFactory.getCurrentSession().createQuery("from Visitor a where a.type = :type order by applyTime desc");
				query.setParameter("type", type);
			}
		}
		else
		{
			if(type == -1)
			{
				query = sessionFactory.getCurrentSession().createQuery("from Visitor where name like '%" + name  + "%' order by applyTime desc");
			}
			else
			{
				query = sessionFactory.getCurrentSession().createQuery("from Visitor a where a.type = :type and a.name like '%" + name  + "%' order by applyTime desc");
				query.setParameter("type", type);
			}
		}
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	/**
	 * 根据穆东成9月9日意见新增，为展会后准备 9-14
	 * 融合多种添加查询
	 * @param start
	 * @param number
	 * @param type
	 * @param strings
	 * @return
	 */
	public List<Visitor>getVisitorForPageMutipleConFirstAuditNoState(int start, int number, int type, String...con)
	{
		StringBuffer basicQueryStr  =  new StringBuffer("from Visitor");
		VisitorQueryCreator creator = new VisitorQueryCreator(con);
		String creatorStr = creator.createStatement();
		Query query = createVisitorQuery(type, basicQueryStr, creatorStr,1);									
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数	
		return query.list();
	}
	
	public List<Visitor>getVisitorForPageMutipleConFirstAudit(int start, int number, int firstState, int type, String...con)
	{
		StringBuffer basicQueryStr  =  new StringBuffer("from Visitor where firstState = :firstState");
		VisitorQueryCreator creator = new VisitorQueryCreator(con);
		String creatorStr = creator.createStatement();
		Query query = createVisitorQuery(type, basicQueryStr, creatorStr,1);									
		query.setParameter("firstState", firstState);
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数	
		return query.list();
	}

	public List<Visitor>getVisitorForPageMutipleConFinalNoState(int start, int number,int type, String...con)
	{
		StringBuffer basicQueryStr  =  new StringBuffer("from Visitor where (firstState = 1 or state = 2 )");
		VisitorQueryCreator creator = new VisitorQueryCreator(con);
		String creatorStr = creator.createStatement();
		Query query = createVisitorQuery(type, basicQueryStr, creatorStr,1);									
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数	
		return query.list();
	}
	
	public List<Visitor> getVisitorForPageMutipleConFinal(int start, int number, int state, int type, String...con)
	{
		StringBuffer basicQueryStr  ;
		if(state != 2)
			basicQueryStr =  new StringBuffer("from Visitor where firstState = 1 and state = :state");
		else
			basicQueryStr =  new StringBuffer("from Visitor where state = :state");

		VisitorQueryCreator creator = new VisitorQueryCreator(con);
		String creatorStr = creator.createStatement();
		Query query = createVisitorQuery(type, basicQueryStr, creatorStr,1);									
		query.setParameter("state", state);
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数	
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visitor>getVisitorForPageByState(int start, int number,int state, String name)
	{
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery(
				"from Visitor a where a.state = :state order by a.applyTime desc");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery(
					"from Visitor a where a.state = :state and a.name like '%" + name + "%' order by a.applyTime desc");
		}
		query.setParameter("state", state);
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visitor>getVisitorForPageByStateMutipleCon(int start, int number,int state, String name, int type)
	{
		Query query;
		if(name == null || name.isEmpty() || name.trim().isEmpty())
		{
			if(type == -1)
			{
				query = sessionFactory.getCurrentSession().createQuery(
						"from Visitor a where a.state = :state order by a.applyTime desc");
			}
			else
			{
				query = sessionFactory.getCurrentSession().createQuery(
						"from Visitor a where a.state = :state and a.type = :type order by a.applyTime desc");
				query.setParameter("type", type);
			}
		}
		else
		{
			if(type == -1)
			{
				query = sessionFactory.getCurrentSession().createQuery(
					"from Visitor a where a.state = :state and a.name like '%" + name + "%' order by a.applyTime desc");
			}
			else
			{
				query = sessionFactory.getCurrentSession().createQuery(
						"from Visitor a where a.state = :state and a.type = :type and a.name like '%" + name + "%' order by a.applyTime desc");
				query.setParameter("type", type);
			}
		}
		query.setParameter("state", state);
		query.setFirstResult(start);//设置起始行
		query.setMaxResults(number);//每页条数		
		return query.list();
	}
	
	public long deleteVisitorById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Visitor where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public long deleteVisitorByEid (String eid) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Visitor where eid = :eid");
		query.setParameter("eid", eid);
		return query.executeUpdate();
	}
	
	public boolean updateVisitor(Visitor visitor) {
		Session s = sessionFactory.getCurrentSession();
		s.update(visitor);
		return true;
	}
	
	private Query createVisitorQuery(int type, StringBuffer basicQueryStr, String creatorStr, int orderByTime)
	{
		Query query;
		String prefix = " and ";
		if(basicQueryStr.indexOf("where") == -1)
			prefix = " where ";
		
		if(type == -1)
		{
			if(creatorStr != null && !creatorStr.isEmpty())
			{
				basicQueryStr.append(prefix);
				basicQueryStr.append(creatorStr);
			}
			if(orderByTime > 0 )
				basicQueryStr.append(" order by applyTime desc");
			logger.debug(basicQueryStr.toString());
			query = sessionFactory.getCurrentSession().createQuery(basicQueryStr.toString());
		}
		else
		{
			basicQueryStr.append(prefix);
			basicQueryStr.append("type = :type");
			if(creatorStr != null && !creatorStr.isEmpty())
			{
				basicQueryStr.append(" and ");
				basicQueryStr.append(creatorStr);
			}
			if(orderByTime > 0 )
				basicQueryStr.append(" order by applyTime desc");
			logger.debug(basicQueryStr.toString());
			query = sessionFactory.getCurrentSession().createQuery(basicQueryStr.toString());
			query.setParameter("type", type);
		}
		
		return query;
	}
}
