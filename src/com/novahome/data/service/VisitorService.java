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
import com.novahome.data.dao.VisitorDao;
import com.novahome.data.pojo.Visitor;

@Service("visitorService")
@Transactional(readOnly = false)
@Repository
public class VisitorService {

	private static final Logger logger = Logger.getLogger(VisitorService.class);
	@Resource(name = "visitorDao")
	private VisitorDao visitorDao;
	private static final String ERROR_STR= "{'error':'抱歉，没有找到指定的现场证件申请'}";

	public String getVisitorTotalCount() 
	{
		long count = visitorDao.getVisitorTotalCount();
		logger.debug("count:" + count);
		return "{'count':" + count +"}";
	}
	
	public String getVisitorCountByType(int type) 
	{
		long count = visitorDao.getVisitorCountByType(type);
		logger.debug("count:" + count);
		return "{'count':" + count +"}";
	}
	
	public String saveVisitor(Visitor visitor)
	{
		visitor.setApplyTime(new Date());
		String id = visitorDao.saveVisitor(visitor);
		logger.info("save Visitor");
		JSONObject obj = new JSONObject();
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功申请证件！");
		obj.put("name", visitor.getName());
		obj.put("photo", visitor.getPhoto());
		obj.put("id", id);
		
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getVisitorByEid(String eid)
	{
		List<Visitor> ls = visitorDao.getVisitorByEid(eid);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Visitor visitor : ls )
		{
			array.put(new JSONObject(visitor));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getVisitorById(String id)
	{
		Visitor visitor = visitorDao.getVisitorById(id);
		if(visitor == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(visitor);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getVisitorByOrg(String org)
	{
		List<Visitor> ls = visitorDao.getVisitorByOrg(org);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Visitor visitor : ls )
		{
			array.put(new JSONObject(visitor));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getVisitorForPage(int start, int number)
	{
		List<Visitor>ls = visitorDao.getVisitorForPage(start, number);
		return procssListRet(ls);
	}
	
	private String procssListRet(List ls)
	{
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = visitorDao.getVisitorTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Object visitor : ls )
		{
			array.put(new JSONObject(visitor));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("visitor:" + ret);
		return ret;
	}
	
	
	public long deleteVisitorById(String id)
	{
		return visitorDao.deleteVisitorById(id);
	}
	
	public boolean updateVisitor(Visitor visitor)
	{
		visitor.setApplyTime(new Date());
		return visitorDao.updateVisitor(visitor);
	}
}
