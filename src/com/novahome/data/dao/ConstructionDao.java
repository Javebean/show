package com.novahome.data.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("constructionDao")
public class ConstructionDao {

	private static final Logger logger = Logger.getLogger(VisitorDao.class);
	@Autowired
	private SessionFactory sessionFactory;
}
