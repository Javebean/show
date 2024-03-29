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
	
	/**
	 * 修改后，查看经过初次审批的count
	 * @param orgName
	 * @return
	 */
	public long getExhibitorsTotalCountFinalAudit(String orgName)
	{
		Query query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery("select count(*) from Exhibitors a where a.firstState = " + 1);
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Exhibitors a where a.orgName like '%" + orgName +"%' and a.firstState = " + 1 );
		}
		return (Long) query.uniqueResult();
	}
	
	public long getExhibitorsCountByStateFinalAudit(int state, String orgName)
	{
		Query query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Exhibitors a where a.state = :state and a.firstState = 1");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Exhibitors a where a.state = :state and a.orgName like '%" + orgName +"%' and a.firstState = 1");
		}
		query.setParameter("state", state);
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 修改后，查看经过初次审批的count
	 * 增加recommender字段过滤
	 * @param orgName
	 * @return
	 */
	public long getExhibitorsTotalCountFinalAuditMutipleCon(String orgName, String recommender)
	{
		Query query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			if(recommender== null  ||   recommender.isEmpty() || recommender.trim().isEmpty() ||recommender.equals("-1"))
				query = sessionFactory.getCurrentSession().createQuery("select count(*) from Exhibitors a where (a.firstState = " + 1 + " or a.state = " + 2 + ")");
			else
				query = sessionFactory.getCurrentSession().createQuery("select count(*) from Exhibitors a where (a.firstState = " + 1 + " or a.state = " + 2 + ") and a.recommender like '%" + recommender + "%'");

				
		}
		else
		{
			if(recommender== null  ||   recommender.isEmpty() || recommender.trim().isEmpty() ||recommender.equals("-1"))
				query = sessionFactory.getCurrentSession().createQuery(
						"select count(*) from Exhibitors a where a.orgName like '%" + orgName +"%' and (a.firstState = " + 1 + " or a.state = " + 2 + ")");
			else
				query = sessionFactory.getCurrentSession().createQuery("select count(*) from Exhibitors a where a.orgName like '%" + orgName + "%' and (a.firstState = " + 1 + " or a.state = " + 2 + ") and a.recommender like '%" + recommender + "%'");

		}
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 需要判断state==2的情况
	 * @param state
	 * @param orgName
	 * @param recommender
	 * @return
	 */
	public long getExhibitorsCountByStateFinalAuditMutipleCon(int state, String orgName, String recommender)
	{
		Query query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			if(recommender== null  ||   recommender.isEmpty() || recommender.trim().isEmpty() ||recommender.equals("-1"))
			{
				if(state != 2)
				{
					query = sessionFactory.getCurrentSession().createQuery(
						"select count(*) from Exhibitors a where a.state = :state and a.firstState = 1");
				}
				else
				{
					//终审时，state=2 就能看到被驳回单位，无需查看firststate
					query = sessionFactory.getCurrentSession().createQuery(
							"select count(*) from Exhibitors a where a.state = :state");
				}
			}
			else
			{
				if(state != 2)
				{
					query = sessionFactory.getCurrentSession().createQuery(
						"select count(*) from Exhibitors a where a.state = :state and a.firstState = 1 and a.recommender like '%" + recommender + "%'");
				}
				else
				{
					query = sessionFactory.getCurrentSession().createQuery(
							"select count(*) from Exhibitors a where a.state = :state and a.recommender like '%" + recommender + "%'");
				}
			}
		}
		else
		{
			if(recommender== null  ||   recommender.isEmpty() || recommender.trim().isEmpty() ||recommender.equals("-1"))
			{
				if(state != 2)
				{
					query = sessionFactory.getCurrentSession().createQuery(
							"select count(*) from Exhibitors a where a.state = :state and a.orgName like '%" + orgName +"%' and a.firstState = 1");
				}
				else
				{
					query = sessionFactory.getCurrentSession().createQuery(
							"select count(*) from Exhibitors a where a.state = :state and a.orgName like '%" + orgName +"%'");
				}
			}
			else
			{
				if(state != 2)
				{
					query = sessionFactory.getCurrentSession().createQuery(
						"select count(*) from Exhibitors a where a.state = :state and a.orgName like '%" + orgName +"%' and a.firstState = 1 and a.recommender like '%" + recommender + "%'");
				}
				else
				{
					query = sessionFactory.getCurrentSession().createQuery(
							"select count(*) from Exhibitors a where a.state = :state and a.orgName like '%" + orgName +"%' and a.recommender like '%" + recommender + "%'");
				}
			}
		}
		query.setParameter("state", state);
		return (Long) query.uniqueResult();
	}
	
	
	
	
	public long getExhibitorsTotalCountFirst(String orgName, String showName)
	{
		Query query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createQuery("select count(*) from Exhibitors a where a.recommender like '%" + showName + "%'");
		}
		else
		{
			query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Exhibitors a where a.orgName like '%" + orgName +"%' and a.recommender like '%" + showName + "%'" );
		}
		return (Long) query.uniqueResult();
	}
	
	public long getExhibitorsCountByStateFirst(int state, String orgName, String showName)
	{
		Query query = null;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			if(state < 3)
			{
				query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Exhibitors a where a.firstState = :state and a.recommender like '%" + showName + "%'");
				query.setParameter("state", state);
			}
			else if(state == 3)
			{
				query = sessionFactory.getCurrentSession().createQuery(
						"select count(*) from Exhibitors a where a.firstState = 1 and a.state = 1 and a.recommender like '%" + showName + "%'");
			}
		}
		else
		{
			if(state < 3)
			{
				query = sessionFactory.getCurrentSession().createQuery(
					"select count(*) from Exhibitors a where a.firstState = :state and a.orgName like '%" + orgName +"%' and a.recommender like '%" + showName + "%'");
				query.setParameter("state", state);
			}
			else if(state == 3)
			{
				query = sessionFactory.getCurrentSession().createQuery(
						"select count(*) from Exhibitors a where a.firstState = 1 and a.state = 1 and a.orgName like '%" + orgName +"%' and a.recommender like '%" + showName + "%'");
			}
		}
		
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
	
	public long getExhibitorsCountByRegion(String region)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(a.id) from Exhibitors a where a.state = :state and a.region = :region");
		query.setParameter("state", 1);
		query.setParameter("region", region);
		return (Long) query.uniqueResult();
	}
	
	public List<String>getDistinctRegion()
	{
		SQLQuery  query = sessionFactory.getCurrentSession().createSQLQuery(
				"select DISTINCT region from exhibitors a where a.state = :state");
		query.setParameter("state", 1);
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
				"from Exhibitors a where a.orgName=:orgName and (a.state = 1 or a.state = 0) and (a.firstState = 0 or a.firstState = 1)");
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
	public List<ShortExhibitor>getShortExhibitorForPageFinalAudit(int start, int number, String orgName)
	{
		SQLQuery query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
				"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1 order by applyTime desc")
				 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
				  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState") ;
		}
		else
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1 and a.orgName like '%" + orgName + "%' order by applyTime desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
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
	public List<ShortExhibitor>getShortExhibitorForPageFinalAuditMutipleCon(int start, int number, String orgName, String recommender)
	{
		SQLQuery query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			if(recommender== null  ||   recommender.isEmpty() || recommender.trim().isEmpty() ||recommender.equals("-1"))
			{
				query = sessionFactory.getCurrentSession().createSQLQuery(
				"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where (a.firstState = 1 or a.state = 2 ) order by applyTime desc")
				 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
				  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState") ;
			}
			else
			{
				query = sessionFactory.getCurrentSession().createSQLQuery(
						"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where (a.firstState = 1 or a.state = 2) and a.recommender like '%" + recommender + "%' order by applyTime desc")
						 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
						  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState") ;
			}
		}
		else
		{
			if(recommender== null  ||   recommender.isEmpty() || recommender.trim().isEmpty() ||recommender.equals("-1"))
			{
				query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where (a.firstState = 1 or a.state = 2) and a.orgName like '%" + orgName + "%' order by applyTime desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
			}
			else
			{
				query = sessionFactory.getCurrentSession().createSQLQuery(
						"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where (a.firstState = 1 or a.state =2 ) and a.orgName like '%" + orgName + "%' and a.recommender like '%" + recommender + "%' order by applyTime desc")
						 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
						  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
			}
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
	
	
	public List<ShortExhibitor> getShortExhibitorForPageFirst(int start, int number, String orgName, String showName)
	{
		SQLQuery query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
				"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.recommender like '%" + showName + "%' order by applyTime desc")
				 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
				  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
		}
		else
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.recommender like '%" + showName + "%' and a.orgName like '%" + orgName + "%' order by applyTime desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
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
	public List<ShortExhibitor>getShortExhibitorForPageByStateFinalAudit(int start, int number, int state, String orgName)
	{
		//sql  ="(select * from exhibitors where logo is not null order by applyTime desc  limit 999999) union (select * from exhibitors where logo is null order by applyTime desc  limit 999999)";
		
		SQLQuery query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1 and a.state = :state order by applyTime desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
			
		}
		else
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1 and a.state = :state and a.orgName like '%" + orgName + "%' order by applyTime desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
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
	
	/**
	 * 增加recommender
	 * 需要判断state == 2的情况
	 * @param start
	 * @param number
	 * @param state
	 * @param orgName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ShortExhibitor>getShortExhibitorForPageByStateFinalAuditMutipleCon(int start, int number, int state, String orgName, String recommender)
	{
		//sql  ="(select * from exhibitors where logo is not null order by applyTime desc  limit 999999) union (select * from exhibitors where logo is null order by applyTime desc  limit 999999)";
		
		SQLQuery query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			if(recommender== null  ||   recommender.isEmpty() || recommender.trim().isEmpty() ||recommender.equals("-1"))
			{
				if(state != 2)
				{
					query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1 and a.state = :state order by applyTime desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				}
				else
				{
					query = sessionFactory.getCurrentSession().createSQLQuery(
							"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.state = :state order by applyTime desc")
							 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
							  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				}
			}
			else
			{
				if(state != 2)
				{
					query = sessionFactory.getCurrentSession().createSQLQuery(
						"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1 and a.state = :state and a.recommender like '%" + recommender + "%' order by applyTime desc")
						 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
						  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				}
				else
				{
					query = sessionFactory.getCurrentSession().createSQLQuery(
							"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.state = :state and a.recommender like '%" + recommender + "%' order by applyTime desc")
							 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
							  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				}
			}
			
		}
		else
		{
			if(recommender== null  ||   recommender.isEmpty() || recommender.trim().isEmpty() ||recommender.equals("-1"))
			{
				if(state != 2)
				{
					query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1 and a.state = :state and a.orgName like '%" + orgName + "%' order by applyTime desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				}
				else
				{
					query = sessionFactory.getCurrentSession().createSQLQuery(
							"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.state = :state and a.orgName like '%" + orgName + "%' order by applyTime desc")
							 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
							  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				}
			}
			else
			{
				if(state != 2)
				{
					query = sessionFactory.getCurrentSession().createSQLQuery(
						"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1 and a.state = :state and a.orgName like '%" + orgName + "%' and a.recommender like '%" + recommender + "%' order by applyTime desc")
						 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
						  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				}
				else
				{
					query = sessionFactory.getCurrentSession().createSQLQuery(
							"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.state = :state and a.orgName like '%" + orgName + "%' and a.recommender like '%" + recommender + "%' order by applyTime desc")
							 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
							  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				}
			}
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
	
	
	public List<ShortExhibitor>getShortExhibitorForPageByStateFirst(int start, int number, int state, String orgName, String showName)
	{
		SQLQuery query = null;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			if(state < 3)
			{
				query = sessionFactory.getCurrentSession().createSQLQuery(
						"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.recommender like '%" + showName + "%' and a.firstState = :state order by applyTime desc")
						 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
						  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				query.setParameter("state", state);
			}
			else if(state == 3 )
			{
				query = sessionFactory.getCurrentSession().createSQLQuery(
						"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.recommender like '%" + showName + "%' and a.firstState = 1 and a.state = 1  order by applyTime desc")
						 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
						  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
			}
		}
		else
		{
			if(state < 3)
			{
				query = sessionFactory.getCurrentSession().createSQLQuery(
						"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.recommender like '%" + showName + "%' and a.firstState = :state and a.orgName like '%" + orgName + "%' order by applyTime desc")
						 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
						  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
				query.setParameter("state", state);
			}
			else if(state == 3 )
			{
				query = sessionFactory.getCurrentSession().createSQLQuery(
						"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.recommender like '%" + showName + "%' and a.firstState = 1 and a.state = 1 and a.orgName like '%" + orgName + "%' order by applyTime desc")
						 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
						  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
			}
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
	public List<ShortExhibitor>getShortExhibitorForPageByStateLogoOrder(int start, int number, int state, String orgName)
	{
		//sql  ="(select * from exhibitors where logo is not null order by applyTime desc  limit 999999) union (select * from exhibitors where logo is null order by applyTime desc  limit 999999)";
		SQLQuery query;
		if(orgName == null || orgName.isEmpty() || orgName.trim().isEmpty())
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1  and a.state = :state order by logo desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
			
		}
		else
		{
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select a.id, a.orgName, a.region,a.phone,a.logo,a.username,a.industryType,a.applyTime,a.state,a.booth,a.firstState from Exhibitors a where a.firstState = 1 and a.state = :state and a.orgName like '%" + orgName + "%' order by logo desc")
					 .addScalar("id").addScalar("orgName").addScalar("region").addScalar("phone").addScalar("logo")
					  .addScalar("username").addScalar("industryType").addScalar("applyTime").addScalar("state").addScalar("booth").addScalar("firstState")  ;
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
	
	public String getIdByUsername(String username)
	{
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				"select id from Exhibitors where username = '" + username  + "'");
		return query.uniqueResult().toString();	
	}
	
	public int getStateByUsername(String username)
	{
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				"select state from Exhibitors where username = '" + username  + "'");
		return (Integer) query.uniqueResult();	
	}
}
