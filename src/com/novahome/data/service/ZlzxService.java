package com.novahome.data.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.novahome.data.dao.ZlzxDao;
import com.novahome.data.model.ShortZlzx;
import com.novahome.data.pojo.Zlzx;
import com.novahome.utils.HtmlParser;
import com.novahome.utils.Ut;

@Service("zlzxService")
@Transactional(readOnly = false)
@Repository
public class ZlzxService {

	private static final Logger logger = Logger.getLogger(ZlzxService.class);
	@Resource(name = "zlzxDao")
	private ZlzxDao zlzxDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的展览咨询新闻\"}";

	public String getZlzxTotalCount() 
	{
		long count = zlzxDao.getZlzxTotalCount();
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String saveZlzx(Zlzx zlzx)
	{
		zlzx.setPublishTime(new Date());
		String id = zlzxDao.saveZlzx(zlzx);
		logger.debug("save zlzx");

		JSONObject obj = new JSONObject();
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功发布新闻！");
		obj.put("title", zlzx.getTitle());
		obj.put("id", id);
		obj.put("publishTime", Ut.newsDf.format(zlzx.getPublishTime()));
		
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getZlzxByTitle(String title)
	{
		List<Zlzx> ls = zlzxDao.getZlzxByTitle(title);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Zlzx zlzx : ls )
		{
			array.put(new JSONObject(zlzx));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getZlzxById(String id)
	{
		Zlzx zlzx = zlzxDao.getZlzxById(id);
		if(zlzx == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(zlzx);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	
	public String getZlzxForPage(int start, int number)
	{
		List<Zlzx>ls = zlzxDao.getZlzxForPage(start, number);
		return procssListRet(ls);
	}
	
	private String procssListRet(List ls)
	{
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = zlzxDao.getZlzxTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Object zlzx : ls )
		{
			array.put(new JSONObject(zlzx));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("zlzx:" + ret);
		return ret;
	}
	
	public String getShortZlzxForPage(int start, int number)
	{
		List<ShortZlzx>ls = zlzxDao.getShortZlzxForPage(start, number);
		return procssListRet(ls);
	}
	
	public String getShortZlzxForPhonePage(int start, int number)
	{
		List<Zlzx>ls = zlzxDao.getZlzxForPage(start, number);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = zlzxDao.getZlzxTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Zlzx zlzx : ls)
		{
			String content = zlzx.getContent();
			String pictureUrl = HtmlParser.extractPicFromHtml(content);
			JSONObject j = new JSONObject(zlzx);
			j.put("content", pictureUrl);
			array.put(j);
		}
			
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("zlzx:" + ret);
		return ret;
	}
	
	public long deleteZlzxById(String id)
	{
		return zlzxDao.deleteZlzxById(id);
	}
	
	public boolean updateZlzx(Zlzx zy)
	{
		zy.setPublishTime(new Date());
		return zlzxDao.updateZlzx(zy);
	}
}
