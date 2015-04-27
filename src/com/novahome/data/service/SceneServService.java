package com.novahome.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.data.dao.SceneServDao;
import com.novahome.data.pojo.SceneServ;

@Service("sceneServService")
@Transactional(readOnly = false)
@Repository
public class SceneServService {

	private static final Logger logger = Logger.getLogger(SceneServService.class);
	@Resource(name = "sceneServDao")
	private SceneServDao sceneServDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的现场服务申请\"}";

	public String getSceneServTotalCount() 
	{
		long count = sceneServDao.getSceneServTotalCount();
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String getSceneServCountByEid(String eid) 
	{
		long count = sceneServDao.getSceneServCountByEid(eid);
		logger.debug("count:" + count);
		return "{'count':" + count +"}";
	}
	
	public String saveSceneServ(SceneServ sceneServ)
	{
		String id = sceneServDao.saveSceneServ(sceneServ);
		logger.info("save construction");
		JSONObject obj = new JSONObject();
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功申请现场服务！");
		obj.put("type", sceneServ.getType());
		obj.put("eid", sceneServ.getEid());
		obj.put("id", id);
		
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getSceneServByEid(String eid)
	{
		List<SceneServ> ls = sceneServDao.getSceneServByEid(eid);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(SceneServ sceneServ : ls )
		{
			array.put(new JSONObject(sceneServ));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getSceneServById(String id)
	{
		SceneServ sceneServ = sceneServDao.getSceneServById(id);
		if(sceneServ == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(sceneServ);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	
	public String getSceneServForPage(int start, int number)
	{
		List<SceneServ>ls = sceneServDao.getSceneServForPage(start, number);
		return procssListRet(ls);
	}
	
	private String procssListRet(List ls)
	{
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = sceneServDao.getSceneServTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Object sceneServ : ls )
		{
			array.put(new JSONObject(sceneServ));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("sceneServ:" + ret);
		return ret;
	}
		
	public long deleteSceneServById(String id)
	{
		return sceneServDao.deleteSceneServById(id);
	}
	
	public boolean updateSceneServ(SceneServ sceneServ)
	{
		return sceneServDao.updateSceneServ(sceneServ);
	}
	
	public String getSceneServStat()
	{
		Map<String, Integer>map =  new ConcurrentHashMap<String, Integer>();
		List<String>types = new ArrayList<String>();
		List<SceneServ>ls = sceneServDao.getAllSceneServ();
		
		boolean flag = true;
		String typeValue = "";
		for(SceneServ serv : ls)
		{
			String type = serv.getType();
			if(type!= null)
			{
				if(types.contains(type))
				{
					int num = map.get(type) + serv.getContent();
					map.put(type, num);
				}
				else
				{
					if(flag)
						flag = !flag;
					else
						typeValue += ",";
					types.add(type);
					int num = serv.getContent();
					map.put(type, num);
					typeValue += type;
				}
			}
		}
		String contentValue = "";
		for(String type : types)
			contentValue += map.get(type) + ",";
		contentValue = contentValue.substring(0, contentValue.length()-1);
		JSONObject obj = new JSONObject();
		obj.put("type", typeValue);
		obj.put("num", contentValue);
		String ret = obj.toString();
		logger.debug("sceneserv:" + ret);
		return ret;
	}
	
	
}
