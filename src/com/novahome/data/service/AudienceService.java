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
import com.novahome.utils.MailUtil;



@Service("audienceService")
@Transactional(readOnly = false)
@Repository
public class AudienceService {
	
	private static final Logger logger = Logger.getLogger(AudienceService.class);
	@Resource(name = "audienceDao")
	private AudienceDao audienceDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的观众\"}";
	
	public String getAudienceTotalCount() 
	{
		long count = audienceDao.getAudienceTotalCount(null);
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
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
		
		String email = audience.getEmail();
		if(email != null && email.matches(Constants.EMAIL_REGEX))  
		{
			logger.info("发送观众注册邮件...");
			String content = MailUtil.replaceVariable(Constants.AUDIENCE_REGISTER, userName,pwd);
			logger.debug("content:" + content);
			MailUtil.sendMail(email, Constants.AUDIENCE_SUBJECT_REGISTER, content);
		}
		
		logger.debug(ret);
		return ret;
	}
	
	public String getAudienceByUserName(String userName)
	{
		Audience audience = audienceDao.getAudienceByUserName(userName);
		if(audience == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}	
		JSONObject obj = new JSONObject(audience);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	
	public String getAudienceById(String id)
	{
		Audience audience = audienceDao.getAudienceById(id);
		if(audience == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(audience);
		String ret = obj.toString();
		logger.debug(ret);
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
			obj.put("type", 2);
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
	
	public String loginByServlet(String username,String password) {	
		JSONObject obj = new JSONObject();
		Audience audience = audienceDao.getAudienceByUserName(username);
		if (audience == null) {
			obj.put("result", false);
			obj.put("message", "该用户不存在！");
			return obj.toString();
		}
		if (MD5.compute(password).equals(audience.getPassword())) {
			obj.put("result", true);
			obj.put("message", "成功登录");
			obj.put("username", audience.getUsername());
			//obj.put("type", 2);
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
	
	public String getAudienceForPage(int start, int number, String name)
	{
		List<Audience>ls = audienceDao.getAudienceForPage(start, number, name);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = audienceDao.getAudienceTotalCount(name);
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Audience audience : ls )
		{
			array.put(new JSONObject(audience));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("audience:" + ret);
		return ret;
	}
	
	/**
	 * 获取观众地区统计
	 * @return
	 */
	public String getRegionStat()
	{
		List<String>ls = audienceDao.getDistinctRegion();
		String regNameTol ="";
		String numTol = "";
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		for(String rec : ls)
		{
			if(rec== null || rec.isEmpty())
				continue;
			long num = audienceDao.getAudienceCountByRegion(rec);
			regNameTol += rec + ",";
			numTol += num + ",";
		}
		regNameTol = regNameTol.substring(0, regNameTol.length()-1);
		numTol = numTol.substring(0, numTol.length()-1);
		JSONObject obj = new JSONObject();
		obj.put("name", regNameTol);
		obj.put("num", numTol);
		String ret = obj.toString();
		logger.debug("region:" + ret);
		return ret;
	}
	
	public long deleteAudienceById(String id)
	{
		return audienceDao.deleteAudienceById(id);
	}
	
	public boolean logout() {
		HttpSession session = WebContextFactory.get().getSession();
		session.removeAttribute(Constants.SESSION_SHOW_ID);
		session.removeAttribute(Constants.SESSION_SHOW_NAME);
		session.removeAttribute(Constants.SESSION_SHOW_TYPE);
		return true;
	}
	
	public boolean updateAudience(Audience audience)
	{
		return audienceDao.updateAudience(audience);
	}
	
	public String resetPsw(String id)
	{
		Audience ex = audienceDao.getAudienceById(id);
		String pwd = RandCodeGenerator.generatePwd();
		String encodePwd = MD5.compute(pwd);
		ex.setPassword(encodePwd);
		JSONObject obj = new JSONObject();
		obj.put("password", pwd);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
}
