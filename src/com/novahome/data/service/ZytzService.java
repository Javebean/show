package com.novahome.data.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.directwebremoting.WebContextFactory;
import com.novahome.commonservice.Constants;
import com.novahome.data.dao.ZytzDao;
import com.novahome.data.model.ShortZytz;
import com.novahome.data.pojo.Zlzx;
import com.novahome.data.pojo.Zytz;
import com.novahome.utils.HtmlParser;
import com.novahome.utils.MailUtil;
import com.novahome.utils.Ut;

@Service("zytzService")
@Transactional(readOnly = false)
@Repository
public class ZytzService {

	private static final Logger logger = Logger.getLogger(ZytzService.class);
	@Resource(name = "zytzDao")
	private ZytzDao zytzDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的重要通知新闻\"}";

	
	public String getZytzTotalCount() 
	{
		long count = zytzDao.getZytzTotalCount();
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String saveZytz(Zytz zy)
	{
		HttpSession session=  WebContextFactory.get().getSession();
		String userName = (String) session.getAttribute(Constants.SESSION_NAME);
		if(userName != null && !userName.isEmpty())
		{
			zy.setPublishTime(new Date());
			String id = zytzDao.saveZytz(zy);
			logger.debug("save zytz");
			//zy.setId(id);
			
			JSONObject obj = new JSONObject();
			//return obj.toString();
			obj.put("result", true);
			obj.put("message", "您已成功发布新闻！");
			obj.put("title", zy.getTitle());
			obj.put("id", id);
			obj.put("publishTime", Ut.newsDf.format(zy.getPublishTime()));
			
			String content = MailUtil.replaceVariable(Constants.MONITOR_ZYTZ_CONTENT, zy.getTitle());
			String email = MailUtil.getMonitorAddr();
			if(email != null && !email.isEmpty())
			{
				MailUtil.sendMail(email, Constants.MONITOR_ZYTZ_TITLE, content);
			}
			
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		}
		return null;
	}
	
	public String getZytzByTitle(String title)
	{
		List<Zytz> ls = zytzDao.getZytzByTitle(title);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Zytz zytz : ls )
		{
			array.put(new JSONObject(zytz));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getZytzById(String id)
	{
		Zytz zytz = zytzDao.getZytzById(id);
		logger.debug("zytz id:" + id);
		if(zytz == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(zytz);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	
	public String getZytzForPage(int start, int number)
	{
		List<Zytz>ls = zytzDao.getZytzForPage(start, number);
		return procssListRet(ls);
	}
	
	private String procssListRet(List ls)
	{
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = zytzDao.getZytzTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Object zytz : ls )
		{
			array.put(new JSONObject(zytz));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("Zytz:" + ret);
		return ret;
	}
	
	public String getShortZytzForPage(int start, int number)
	{
		List<ShortZytz>ls = zytzDao.getShortZytzForPage(start, number);
		return procssListRet(ls);
	}
	
	public String getShortZytzForPhonePage(int start, int number)
	{
		List<Zytz>ls = zytzDao.getZytzForPage(start, number);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = zytzDao.getZytzTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Zytz zytz : ls)
		{
			String content = zytz.getContent();
			String pictureUrl = HtmlParser.extractPicFromHtml(content);
			JSONObject j = new JSONObject(zytz);
			j.put("content", pictureUrl);
			array.put(j);
		}
			
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("Zytz:" + ret);
		return ret;
	}
	
	public long deleteZytzById(String id)
	{
		HttpSession session=  WebContextFactory.get().getSession();
		String userName = (String) session.getAttribute(Constants.SESSION_NAME);
		if(userName != null && !userName.isEmpty())
		{
			return zytzDao.deleteZytzById(id);
		}
		return 0;
	}
	
	public boolean updateZytz(Zytz zy)
	{
		HttpSession session=  WebContextFactory.get().getSession();
		String userName = (String) session.getAttribute(Constants.SESSION_NAME);
		if(userName != null && !userName.isEmpty())
		{
			zy.setPublishTime(new Date());
			logger.info("更新重要通知新闻...");
			String content = MailUtil.replaceVariable(Constants.MONITOR_ZYTZ_CONTENT, zy.getTitle());
			logger.debug("content:" + content);
			String email = MailUtil.getMonitorAddr();
			if(email != null && !email.isEmpty())
			{
				MailUtil.sendMail(email, Constants.MONITOR_ZYTZ_TITLE, content);
			}
			return zytzDao.updateZytz(zy);
		}
		return false;
	}
}
