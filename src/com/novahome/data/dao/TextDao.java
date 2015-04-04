package com.novahome.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Text;
import com.novahome.utils.Ut;

@Component("textDao")
public class TextDao {
	@Autowired
	private SessionFactory sessionFactory;

	public String saveText(Text text) {
		Session s = sessionFactory.getCurrentSession();
		return s.save(text).toString();
	}
	
	public void updateText(Text text) {
		Session s = sessionFactory.getCurrentSession();
		s.update(text);
	}

	public Text getTextById (String id) {
		return (Text)sessionFactory.getCurrentSession().get(Text.class,id);
	}
	
	public long deleteTextById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from Text where id = :id");
		query.setParameter("id", id);
		
		return query.executeUpdate();
	}
	
	/*public long updateTextContent (String id,String content) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"update Text set content = :content where id = :id");
		query.setParameter("content",content);
		query.setParameter("id",id);
		
		return query.executeUpdate();
	}*/
	
	public List<Text> getTextsByAccountId(String accountId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Text where accountId = :accountId order by createtime");
		query.setParameter("accountId", accountId);
		
		return query.list();
	}
	
	public Text getTextOneditByAccountId (String accountId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Text where accountId = :accountId and updatetime = (select max(updatetime) from Text where accountId = :accountId)");
		query.setParameter("accountId", accountId);
		
		return (Text) query.uniqueResult();
	}
}
