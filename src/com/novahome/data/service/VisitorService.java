package com.novahome.data.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.commonservice.Constants;
import com.novahome.data.dao.VisitorDao;
import com.novahome.data.model.ShortExhibitor;
import com.novahome.data.pojo.Visitor;
import com.novahome.utils.CutImageUtils;

@Service("visitorService")
@Transactional(readOnly = false)
@Repository
public class VisitorService {

	private static final Logger logger = Logger.getLogger(VisitorService.class);
	@Resource(name = "visitorDao")
	private VisitorDao visitorDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的现场证件申请\"}";

	public String getVisitorTotalCount() 
	{
		long count = visitorDao.getVisitorTotalCount();
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String getVisitorCountByType(int type) 
	{
		long count = visitorDao.getVisitorCountByType(type);
		logger.debug("count:" + count);
		return "{'count':" + count +"}";
	}
	
	public String getVisitorCountByState(int state)
	{
		long count = visitorDao.getVisitorCountByState(state);
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String saveVisitor(Visitor visitor, String cutIndex)
	{
		String[]array;
		String nowpath = System.getProperty("user.dir");            
		String tempdir = nowpath.replace("bin", "webapps");
		tempdir+="\\"+Constants.PRJ_NAME; 
		String basePath = tempdir + "\\resources\\topicimages\\";
		System.out.println("*******" + basePath);
		if(cutIndex != null && !cutIndex.isEmpty())
		{
			array = cutIndex.split(",");
			if(array.length == 4)
			{
				int originX = Integer.parseInt(array[0]);
				int originY = Integer.parseInt(array[1]);
				int width = Integer.parseInt(array[2]);
				int height = Integer.parseInt(array[3]);
				String srcName = visitor.getPhoto();
				String srcPath = "";
				if(srcName != null && !srcName.isEmpty())
				{
					srcPath += basePath + srcName;
					File srcFile = new File(srcPath);
					if(srcFile.exists())
					{
						String picName = null;
						try {
							picName = CutImageUtils.cut(srcFile, basePath,originX,originY,width,height);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(picName != null && !picName.isEmpty())
							visitor.setPhoto(picName);
					}
				}
			}	
		}
			
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
	
	public String getVisitorByName(String name)
	{
		List<Visitor> ls = visitorDao.getVisitorByName(name);
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
		long size = visitorDao.getVisitorTotalCount();
		List<Visitor>ls = visitorDao.getVisitorForPage(start, number);
		return procssListRet(ls,size);
	}
	
	public String getVisitorForPageByState(int start, int number,int state)
	{
		if(state == -1)
		{
			return this.getVisitorForPage(start, number);
		}
		long size = visitorDao.getVisitorCountByState(state);
		List<Visitor>ls = visitorDao.getVisitorForPageByState(start, number, state);
		return procssListRet(ls,size);
	}
	
	private String procssListRet(List ls, long size)
	{
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		
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
	
	public boolean updateVisitorState(String id, int state)
	{
		Visitor visitor = visitorDao.getVisitorById(id);
		visitor.setState(state);
		return true;
	}
}
