package com.novahome.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.novahome.data.dao.ConstructionDao;
import com.novahome.data.pojo.Construction;


@Service("constructionService")
@Transactional(readOnly = false)
@Repository

public class ConstructionService {

	private static final Logger logger = Logger.getLogger(ConstructionService.class);
	@Resource(name = "constructionDao")
	private ConstructionDao constructionDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的现场施工服务申请\"}";
	
	
	public String getConstructionTotalCount() 
	{
		long count = constructionDao.getConstructionTotalCount();
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String getConstructionCountByEid(String eid) 
	{
		long count = constructionDao.getConstructionCountByEid(eid);
		logger.debug("count:" + count);
		return "{'count':" + count +"}";
	}
	
	public String saveConstruction(Construction construction)
	{
		String id = constructionDao.saveConstruction(construction);
		logger.info("save construction");
		JSONObject obj = new JSONObject();
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功申请施工服务！");
		obj.put("type", construction.getType());
		obj.put("eid", construction.getEid());
		obj.put("id", id);
		
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getConstructionByEid(String eid)
	{
		List<Construction> ls = constructionDao.getConstructionByEid(eid);
		if(ls ==  null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
			
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Construction construction : ls )
		{
			array.put(new JSONObject(construction));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getConstructionById(String id)
	{
		Construction construction = constructionDao.getConstructionById(id);
		if(construction == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(construction);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	
	public String getConstructionForPage(int start, int number)
	{
		List<Construction>ls = constructionDao.getConstructionForPage(start, number);
		return procssListRet(ls);
	}
	
	private String procssListRet(List ls)
	{
		if(ls == null  || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = constructionDao.getConstructionTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Object construction : ls )
		{
			array.put(new JSONObject(construction));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.info("construction:" + ret);
		return ret;
	}
	
	
	public long deleteConstructionById(String id)
	{
		return constructionDao.deleteConstructionById(id);
	}
	
	public boolean updateConstruction(Construction construction)
	{
		return constructionDao.updateConstruction(construction);
	}
}
