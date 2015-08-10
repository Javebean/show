package com.novahome.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.novahome.data.dao.DisplayItemDao;
import com.novahome.data.dao.ExhibitorsDao;
import com.novahome.data.pojo.DisplayItem;
import com.novahome.data.pojo.SceneServ;

@Service("displayItemService")
@Transactional(readOnly = false)
@Repository
public class DisplayItemService {

	private static final Logger logger = Logger.getLogger(DisplayItemService.class);
	@Resource(name = "displayItemDao")
	private DisplayItemDao displayItemDao;
	@Resource(name = "exhibitorsDao")
	private ExhibitorsDao exhibitorsDao;
	
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的展品\"}";

	
	public String getDisplayItemTotalCount() 
	{
		long count = displayItemDao.getDisplayItemTotalCount();
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String getDisplayItemCountByEid(String eid) 
	{
		long count = displayItemDao.getDisplayItemCountByEid(eid);
		logger.debug("count:" + count);
		return "{'count':" + count +"}";
	}
	
	public String saveDisplayItem(DisplayItem displayItem)
	{
		String id = displayItemDao.saveDisplayItem(displayItem);
		logger.info("save DisplayItem");
		JSONObject obj = new JSONObject();
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功注册展品！");
		obj.put("name", displayItem.getName());
		obj.put("eid", displayItem.getEid());
		obj.put("id", id);
		
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getDisplayItemByEid(String eid)
	{
		List<DisplayItem> ls = displayItemDao.getDisplayItemByEid(eid);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(DisplayItem displayItem : ls )
		{
			array.put(new JSONObject(displayItem));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	
	public String getDisplayItemByUsername(String username)
	{
		List<DisplayItem> ls = displayItemDao.getDisplayItemByUsername(username);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(DisplayItem displayItem : ls )
		{
			array.put(new JSONObject(displayItem));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public boolean updateDisplayItemList(String username, List<DisplayItem>list)
	{
		logger.debug("update displayitemlist : " + username);
		String eid = exhibitorsDao.getIdByUsername(username);
		displayItemDao.deleteDisplayItemByEid(eid);
		if(list == null || list.isEmpty())
			return true;
		
		for(DisplayItem item : list)
		{
			item.setEid(eid);
			logger.debug(item);
			displayItemDao.saveDisplayItem(item);
		}
		return true;
	}
	
	public String getDisplayItemById(String id)
	{
		DisplayItem displayItem = displayItemDao.getDisplayItemById(id);
		if(displayItem == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(displayItem);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	
	public String getDisplayItemForPage(int start, int number)
	{
		List<DisplayItem>ls = displayItemDao.getDisplayItemForPage(start, number);
		return procssListRet(ls);
	}
	
	private String procssListRet(List ls)
	{
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = displayItemDao.getDisplayItemTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Object displayItem : ls )
		{
			array.put(new JSONObject(displayItem));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("DisplayItem:" + ret);
		return ret;
	}
	
	
	public long deleteDisplayItemById(String id)
	{
		return displayItemDao.deleteDisplayItemById(id);
	}
	
	public boolean updateDisplayItem(DisplayItem displayItem)
	{
		return displayItemDao.updateDisplayItem(displayItem);
	}
}
