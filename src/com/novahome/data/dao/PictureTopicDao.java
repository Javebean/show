package com.novahome.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.PictureTopic;
import com.novahome.data.pojo.Text;
import com.novahome.utils.Ut;

@Component("pictureTopicDao")
public class PictureTopicDao {
	@Autowired
	private SessionFactory sessionFactory;

	public String savePT(PictureTopic pt) {
		Session s = sessionFactory.getCurrentSession();
		return s.save(pt).toString();
	}
	
	public void updatePT(PictureTopic pt) {
		Session s = sessionFactory.getCurrentSession();
		s.update(pt);
	}

	public PictureTopic getPTById (String id) {
		return (PictureTopic)sessionFactory.getCurrentSession().get(PictureTopic.class,id);
	}
	
	public long deletePTById (String id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"delete from PictureTopic where id = :id");
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
	
	public List<PictureTopic> getPTsByType(String type) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from PictureTopic where type = :type order by updatetime");
		query.setParameter("type", type);
		
		return query.list();
	}
}
