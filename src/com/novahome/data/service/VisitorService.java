package com.novahome.data.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.commonservice.Constants;
import com.novahome.data.dao.VisitorDao;
import com.novahome.data.pojo.Visitor;
import com.novahome.utils.CutImageUtils;
import com.novahome.utils.MailUtil;

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
		long count = visitorDao.getVisitorTotalCount(null);
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
		long count = visitorDao.getVisitorCountByState(state, null);
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
			if(array.length == 6)
			{
				int originX = Integer.parseInt(array[0]);
				int originY = Integer.parseInt(array[1]);
				int width = Integer.parseInt(array[2]);
				int height = Integer.parseInt(array[3]);
				int divWidth = Integer.parseInt(array[4]);
				int divHeight = Integer.parseInt(array[5]);
				String srcName = visitor.getPhoto();
				logger.info("srcName:********" + srcName );
				logger.info("x:"+ originX);
				logger.info("y:"+ originX);
				logger.info("width:"+ width);
				logger.info("height:"+ height);
				logger.info("divwidth:"+ divWidth);
				logger.info("divheight:"+ divHeight);
				String srcPath = "";
				if(srcName != null && !srcName.isEmpty())
				{
					srcPath += basePath + srcName;
					logger.info("srcPath:"+ srcPath);
					File srcFile = new File(srcPath);
					if(srcFile.exists())
					{
						int editedX=0, editedY = 0, editedWidth=0, editedHeight = 0;
						try {
							BufferedImage srcImage = ImageIO.read(srcFile);
							int picWidth = srcImage.getWidth();
							int picHeight = srcImage.getHeight();
							double widthscale = picWidth/1.0/divWidth;
							double heightscale = picHeight/1.0/divHeight;
							logger.info("widthscale:"+ widthscale);
							logger.info("heightscale:"+ heightscale);
							editedX = (int) (widthscale * originX);
							editedY = (int) (heightscale * originY);
							editedWidth = (int) (widthscale * width);
							editedHeight = (int) (heightscale * height);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String picName = null;
						try {
							picName = CutImageUtils.cut(srcFile, basePath,editedX,editedY,editedWidth,editedHeight);
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
	
	
	public String getVisitorForPage(int start, int number, String name)
	{
		long size = visitorDao.getVisitorTotalCount(name);
		List<Visitor>ls = visitorDao.getVisitorForPage(start, number, name);
		return procssListRet(ls,size);
	}
	
	public String getVisitorForPageByState(int start, int number,int state, String name)
	{
		if(state == -1)
		{
			return this.getVisitorForPage(start, number, name);
		}
		long size = visitorDao.getVisitorCountByState(state, name);
		List<Visitor>ls = visitorDao.getVisitorForPageByState(start, number, state, name);
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
		if(state == 1)
		{
			String email = visitor.getEmail();
			if(email != null && email.matches(Constants.EMAIL_REGEX))  
			{
				logger.info("发送现场证件申请通过邮件...");
				String content = Constants.VISITOR_APPROVED;
				logger.debug("content:" + content);
				MailUtil.sendMail(email, Constants.VISITOR_SUBJECT_APPROVED, content);
			}
		}
		return true;
	}
	
	public boolean updateVisitorStateReason(String id, int state, String reason)
	{
		Visitor visitor = visitorDao.getVisitorById(id);
		visitor.setState(state);
		if(state == 2)
		{
			visitor.setReason(reason);
			String email = visitor.getEmail();
			if(email != null && email.matches(Constants.EMAIL_REGEX))  
			{
				logger.info("发送现场证件申请驳回邮件...");
				String content = MailUtil.replaceVariable(Constants.VISITOR_REFUSE, reason);
				logger.debug("content:" + content);
				MailUtil.sendMail(email, Constants.VISITOR_SUBJECT_OBJECTION, content);
			}
		}
		else
		{
			visitor.setReason("");
		}
		return true;
	}
}
