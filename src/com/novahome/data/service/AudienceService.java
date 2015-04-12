package com.novahome.data.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.commonservice.Constants;
import com.novahome.commonservice.MD5;
import com.novahome.commonservice.PeopleState;
import com.novahome.commonservice.RandCodeGenerator;
import com.novahome.data.dao.AudienceDao;
import com.novahome.data.pojo.Audience;

@Service("audienceService")
@Transactional(readOnly = false)
@Repository
public class AudienceService {
	
	private static final Logger logger = Logger.getLogger(AudienceService.class);
	@Resource(name = "audienceDao")
	private AudienceDao audienceDao;
	
	public String getAudienceTotalCount() 
	{
		long count = audienceDao.getAudienceTotalCount();
		logger.info("count:" + count);
		return "{'count':" + count +"}";
	}
	
	public String saveAudience(Audience audience)
	{
		audience.setApplyTime(new Date());
		String userName = RandCodeGenerator.generateAudiUser();
		String pwd = RandCodeGenerator.generatePwd();
		String encodePwd = MD5.compute(pwd);
		/**
		 * set username and password here
		 */
		audience.setUsername(userName);
		audience.setPassword(encodePwd);
		String id = audienceDao.saveAudience(audience);
		logger.info("save audience");
		audience.setId(id);
		
		
		JSONObject obj = new JSONObject();
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功注册,即将跳转！");
		obj.put("username", userName);
		obj.put("password", pwd);
		obj.put("id", id);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getAudienceByUserName(String userName)
	{
		Audience audience = audienceDao.getAudienceByUserName(userName);
		JSONObject obj = new JSONObject(audience);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getAudienceByName(String name)
	{
		List<Audience> ls = audienceDao.getAudienceByName(name);
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Audience audience : ls )
		{
			array.put(new JSONObject(audience));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getAudienceById(String id)
	{
		Audience audience = audienceDao.getAudienceById(id);
		JSONObject obj = new JSONObject(audience);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String login(String username,String password) {
		HttpSession session = WebContextFactory.get().getSession();
		
		JSONObject obj = new JSONObject();
		Audience audience = audienceDao.getAudienceByUserName(username);
		if (audience == null) {
			obj.put("result", false);
			obj.put("message", "该用户不存在！");
			return obj.toString();
		}
		if (MD5.compute(password).equals(audience.getPassword())) {
			session.setAttribute(Constants.SESSION_SHOW_ID, audience.getId());
			session.setAttribute(Constants.SESSION_SHOW_NAME, audience.getUsername());
			session.setAttribute(Constants.SESSION_SHOW_TYPE, PeopleState.AUDIENCE);
			obj.put("result", true);
			obj.put("message", "成功登录");
			obj.put("username", audience.getUsername());
			//obj.put("cookie", MD5.compute(audience.getId()+":"+audience.getPassword()));
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		} else {
			obj.put("result", false);
			obj.put("message", "密码错误！");
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		}
	}
	
	public String getAudienceForPage(int start, int number)
	{
		List<Audience>ls = audienceDao.getAudienceForPage(start, number);
		long size = audienceDao.getAudienceTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Audience audience : ls )
		{
			array.put(new JSONObject(audience));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.info("audience:" + ret);
		return ret;
		
	}
	
	public long deleteAudienceById(String id)
	{
		return audienceDao.deleteAudienceById(id);
	}
	
}
