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
import com.novahome.data.dao.HyzxDao;
import com.novahome.data.model.ShortHyzx;
import com.novahome.data.pojo.Hyzx;
import com.novahome.utils.HtmlParser;
import com.novahome.utils.MailUtil;
import com.novahome.utils.Ut;

@Service("hyzxService")
@Transactional(readOnly = false)
@Repository
public class HyzxService {

	private static final Logger logger = Logger.getLogger(HyzxService.class);
	@Resource(name = "hyzxDao")
	private HyzxDao hyzxDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的展览咨询新闻\"}";

	public String getHyzxTotalCount() 
	{
		long count = hyzxDao.getHyzxTotalCount();
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String saveHyzx(Hyzx hyzx)
	{
		HttpSession session=  WebContextFactory.get().getSession();
		String userName = (String) session.getAttribute(Constants.SESSION_NAME);
		if(userName != null && !userName.isEmpty())
		{
			hyzx.setPublishTime(new Date());
			String id = hyzxDao.saveHyzx(hyzx);
			logger.debug("save Hyzx");
	
			JSONObject obj = new JSONObject();
			//return obj.toString();
			obj.put("result", true);
			obj.put("message", "您已成功发布新闻！");
			obj.put("title", hyzx.getTitle());
			obj.put("id", id);
			obj.put("publishTime", Ut.newsDf.format(hyzx.getPublishTime()));
			
			String content = MailUtil.replaceVariable(Constants.MONITOR_HYZX_CONTENT, hyzx.getTitle());
			String email = MailUtil.getMonitorAddr();
			if(email != null && !email.isEmpty())
			{
				MailUtil.sendMail(email, Constants.MONITOR_HYZX_TITLE, content);
			}
			
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		}
		return null;
	}
	
	public String getHyzxByTitle(String title)
	{
		List<Hyzx> ls = hyzxDao.getHyzxByTitle(title);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Hyzx hyzx : ls )
		{
			array.put(new JSONObject(hyzx));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getHyzxById(String id)
	{
		Hyzx hyzx = hyzxDao.getHyzxById(id);
		logger.debug("Hyzx id:" + id);
		if(hyzx == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(hyzx);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	
	public String getHyzxForPage(int start, int number)
	{
		List<Hyzx>ls = hyzxDao.getHyzxForPage(start, number);
		return procssListRet(ls);
	}
	
	private String procssListRet(List ls)
	{
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = hyzxDao.getHyzxTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Object hyzx : ls )
		{
			array.put(new JSONObject(hyzx));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("Hyzx:" + ret);
		return ret;
	}
	
	public String getShortHyzxForPage(int start, int number)
	{
		List<ShortHyzx>ls = hyzxDao.getShortHyzxForPage(start, number);
		return procssListRet(ls);
	}
	
	public String getShortHyzxForPhonePage(int start, int number)
	{
		List<Hyzx>ls = hyzxDao.getHyzxForPage(start, number);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = hyzxDao.getHyzxTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Hyzx hyzx : ls)
		{
			String content = hyzx.getContent();
			String pictureUrl = HtmlParser.extractPicFromHtml(content);
			JSONObject j = new JSONObject(hyzx);
			j.put("content", pictureUrl);
			array.put(j);
		}
			
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("Hyzx:" + ret);
		return ret;
	}
	
	public long deleteHyzxById(String id)
	{
		HttpSession session=  WebContextFactory.get().getSession();
		String userName = (String) session.getAttribute(Constants.SESSION_NAME);
		if(userName != null && !userName.isEmpty())
		{
			return hyzxDao.deleteHyzxById(id);
		}
		return 0;
	}
	
	public boolean updateHyzx(Hyzx hyzx)
	{
		HttpSession session=  WebContextFactory.get().getSession();
		String userName = (String) session.getAttribute(Constants.SESSION_NAME);
		if(userName != null && !userName.isEmpty())
		{
			hyzx.setPublishTime(new Date());
			logger.info("更新行业资讯新闻...");
			String content = MailUtil.replaceVariable(Constants.MONITOR_HYZX_CONTENT, hyzx.getTitle());
			String email = MailUtil.getMonitorAddr();
			if(email != null && !email.isEmpty())
			{
				logger.debug("content:" + content);
				MailUtil.sendMail(email, Constants.MONITOR_HYZX_TITLE, content);
			}
			return hyzxDao.updateHyzx(hyzx);
		}
		return false;
	}
}
