package com.novahome.data.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.commonservice.Constants;
import com.novahome.commonservice.MD5;
import com.novahome.commonservice.PeopleState;
import com.novahome.data.dao.AccountDao;
import com.novahome.data.pojo.Account;
import com.novahome.utils.Ut;

@Service("accountService")
@Transactional(readOnly = false)
@Repository
public class AccountService {
	
	private static final Logger logger = Logger.getLogger(AccountService.class);

	@Resource(name = "accountDao")
	private AccountDao accountDao;
	
	TextService textservice;

	public String getAccontTotalCount() {
		long userCount = accountDao.getAccontTotalCount();
		return "User count: " + userCount;
	}
	
	public String login(String username,String password) {
		HttpSession session = WebContextFactory.get().getSession();
		
		JSONObject j = new JSONObject();
		Account account = accountDao.getAccountByName(username);
		if (account == null) {
			j.put("result", false);
			j.put("message", "该用户不存在！");
			return j.toString();
		}
		if (MD5.compute(password).equals(account.getPassword())) {
			session.setAttribute(Constants.SESSION_ID, account.getId());
			session.setAttribute(Constants.SESSION_NAME, account.getUsername());
			session.setAttribute(Constants.SESSION_OFFICIAL_NAME, account.getShowname());
			session.setAttribute(Constants.SESSION_SHOW_TYPE, PeopleState.STAFF);
			session.setAttribute(Constants.MENU_ACCESS, account.getPrivilege());
			j.put("result", true);
			j.put("message", "成功登录");
			j.put("cookie", MD5.compute(account.getId()+":"+account.getPassword()));
			HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
			String ip = request.getRemoteAddr();
			logger.debug("后台管理登录成功:" + username + "; ip:" + ip);
			return j.toString();
		} else {
			j.put("result", false);
			j.put("message", "密码错误！");
			return j.toString();
		}
	}
	
	public String cookielogin(String username,String value){
		HttpSession session = WebContextFactory.get().getSession();
		
		Account account = accountDao.getAccountByName(username);
		String md5value = MD5.compute(account.getId()+":"+account.getPassword());
		Ut.pt("cookie login!!");
		Ut.pt(value);
		Ut.pt(md5value);
		if(value.equals(md5value)) {
			session.setAttribute(Constants.SESSION_ID, account.getId());
			session.setAttribute(Constants.SESSION_NAME, account.getUsername());
			session.setAttribute(Constants.USER_CLASS, account);
			return "success";
		} else {
			return "fail";
		}
	}
	
	public String saveAccount(Account account) {
		JSONObject j = new JSONObject();
		String name = account.getUsername();
		
		if (accountDao.getAccountByName(name) != null) {
			j.put("result", false);
			j.put("message", "已经存在姓名为" + name + "的用户！");
			return j.toString();
		}
		account.setPassword(MD5.compute(account.getPassword()));

		if (accountDao.saveAccount(account)) {
			j.put("result", true);
			j.put("message", "您已成功注册,即将跳转！");
			return j.toString();
		} else {
			j.put("result", false);
			j.put("message", "对不起，程序错误，请稍候再试！");
			return j.toString();
		}
	}
	
	public boolean logout() {
		HttpSession session = WebContextFactory.get().getSession();
		session.removeAttribute(Constants.SESSION_ID);
		session.removeAttribute(Constants.SESSION_NAME);
		session.removeAttribute(Constants.SESSION_SHOW_TYPE);
		session.removeAttribute(Constants.SESSION_OFFICIAL_NAME);
		session.removeAttribute(Constants.MENU_ACCESS);
		logger.debug("后台管理退出成功:");
		return true;
	}

	public static void main(String[] args){
		Ut.pt(MD5.compute("123456"));
	}
}
