package com.novahome.data.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.TestClob;

@Component("testClobDao")
public class TestClobDao {

	@Autowired
	private SessionFactory sessionFactory;
	public String saveTestClob(TestClob testClob) {
		Session s = sessionFactory.getCurrentSession();
		return s.save(testClob).toString();
	}
	
	public void updateText(TestClob text) {
		Session s = sessionFactory.getCurrentSession();
		s.update(text);
	}

	public TestClob getTextById (String name) {
		return (TestClob)sessionFactory.getCurrentSession().get(TestClob.class,name);
	}
	
	public long deleteTextById (String name) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Text where name = :name");
		query.setParameter("name", name);
		//select * from testclob order by publish asc
		return query.executeUpdate();
	}
}


