package com.novahome.data.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.Staff;

@Component("staffDao")
public class StaffDao {

	private static final Logger logger = Logger.getLogger(StaffDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getStaffTotalCount()
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Staff");
		return (Long) query.uniqueResult();
	}
	
	public String saveStaff(Staff staff)
	{
		return sessionFactory.getCurrentSession().save(staff).toString();
	}
	
	public Staff getStaffById(String id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Staff a where a.id=:id");
				query.setString("id", id);
				return (Staff) query.uniqueResult();
	}
	
	public Staff getStaffByUserName(String userName)
	{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Staff a where a.userName=:userName");
				query.setString("userName", userName);
				return (Staff) query.uniqueResult();
	}
	
	public long deleteStaffById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Staff where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public boolean updateStaff(Staff staff) {
		Session s = sessionFactory.getCurrentSession();
		s.update(staff);
		return true;
	}
}
