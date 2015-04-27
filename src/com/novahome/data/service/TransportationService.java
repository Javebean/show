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
import com.novahome.data.dao.TransportationDao;

import com.novahome.data.pojo.Transportation;

@Service("transportationService")
@Transactional(readOnly = false)
@Repository
public class TransportationService {
	
	private static final Logger logger = Logger.getLogger(TransportationService.class);
	@Resource(name = "transportationDao")
	private TransportationDao transportationDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的运输服务申请\"}";

	public String getTransportationTotalCount() 
	{
		long count = transportationDao.getTransportationTotalCount();
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String getTransportationCountByEid(String eid) 
	{
		long count = transportationDao.getTransportationCountByEid(eid);
		logger.debug("count:" + count);
		return "{'count':" + count +"}";
	}
	
	public String saveTransportation(Transportation transportation)
	{
		String id = transportationDao.saveTransportation(transportation);
		logger.info("save transportation");
		JSONObject obj = new JSONObject();
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功申请运输服务！");
		obj.put("type", transportation.getType());
		obj.put("eid", transportation.getEid());
		obj.put("id", id);
		
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getTransportationByEid(String eid)
	{
		List<Transportation> ls = transportationDao.getTransportationByEid(eid);
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Transportation transportation : ls )
		{
			array.put(new JSONObject(transportation));
		}
		obj.put("data", array);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getTransportationById(String id)
	{
		Transportation transportation = transportationDao.getTransportationById(id);
		if(transportation == null)
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		JSONObject obj = new JSONObject(transportation);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	
	public String getTransportationForPage(int start, int number)
	{
		List<Transportation>ls = transportationDao.getTransportationForPage(start, number);
		return procssListRet(ls);
	}
	
	private String procssListRet(List ls)
	{
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		long size = transportationDao.getTransportationTotalCount();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Object transportation : ls )
		{
			array.put(new JSONObject(transportation));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("transportation:" + ret);
		return ret;
	}
	
	
	public long deleteTransportationById(String id)
	{
		return transportationDao.deleteTransportationById(id);
	}
	
	public boolean updateTransportation(Transportation transportation)
	{
		return transportationDao.updateTransportation(transportation);
	}
	
	public String getTransportationStat()
	{
		Map<String, Integer>map =  new ConcurrentHashMap<String, Integer>();
		List<String>types = new ArrayList<String>();
		List<Transportation>ls = transportationDao.getAllTransportation();
		//System.out.println(ls.size());
		boolean flag = true;
		String typeValue = "";
		for(Transportation serv : ls)
		{
			//System.out.println(serv);
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
		logger.debug("transportation:" + ret);
		return ret;
	}
	

}
