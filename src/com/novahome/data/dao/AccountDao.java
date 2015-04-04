package com.novahome.data.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Account;
import com.novahome.utils.Ut;

@Component("accountDao")
public class AccountDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public long getAccontTotalCount() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Account");
		return (Long) query.uniqueResult();
	}
	
	public boolean saveAccount(Account account) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(account);
		} catch (DataAccessException e) {
			return false;
		}
		Ut.pt("account saved!");
		return true;
	}
	
	public Account getAccountByName(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		"from Account a where a.username=:username");
		query.setString("username", username);
		return (Account) query.uniqueResult();
	}
}
