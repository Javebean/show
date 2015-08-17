package com.novahome.data.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.novahome.data.dao.ExhibitorsDao;
import com.novahome.data.dao.VisitorDao;
import com.novahome.data.pojo.Exhibitors;
import com.novahome.data.pojo.Visitor;
import com.novahome.utils.BarcodeUtils;
import com.novahome.utils.ConfigUtils;
import com.novahome.utils.CutImageUtils;
import com.novahome.utils.MailUtil;

@Service("visitorService")
@Transactional(readOnly = false)
@Repository
public class VisitorService {

	private static final Logger logger = Logger.getLogger(VisitorService.class);
	@Resource(name = "visitorDao")
	private VisitorDao visitorDao;
	@Resource(name = "exhibitorsDao")
	private ExhibitorsDao exhibitorsDao;
	private static final String ROOT_STR = "ROOT";
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的现场证件申请\"}";

	private static final String NAME_CHI_STR = "姓名:";
	private static final String PHONE_CHI_STR = "手机:";
	private static final String ORG_CHI_STR = "公司:";
	
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
		JSONObject obj = new JSONObject();
		String idNo = visitor.getIdNo();
		int idType = visitor.getIdType();
		System.out.println(new JSONObject(visitor));
		String[]array;
		String nowpath = System.getProperty("user.dir");
		String tempdir = nowpath.replace("bin", "webapps");
		tempdir+="\\"+ ConfigUtils.getPrj();
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
		
		if(visitor.getType() == 1)
		{
			String eid = visitor.getEid();
			if(eid != null && !eid.isEmpty())
			{
				Exhibitors exhibit = exhibitorsDao.getExhibitorById(visitor.getEid());
				//展商处于驳回状态时，无法申请现场证件
				if(exhibit.getFirstState() == 2)
				{
					obj.put("result", false);
					obj.put("message", "当前展商申请已被驳回，无法申请现场证件，需等待展商申请重新提交！");
					String ret = obj.toString();
					logger.info(ret);
					return ret;
				}
				if(visitor.getOrg() == null  || visitor.getOrg().isEmpty())
				{
					visitor.setOrg(exhibit.getOrgName());
				}
			}
		}
		
		Visitor vi = visitorDao.getVisitorWithIdNoRegistered(idNo, idType);
		if(vi != null )
		{
			//	审批已经通过的无法重新申请
			if(vi.getState() == 1)
			{
				obj.put("result", false);
				obj.put("message", "该身份证件号码已注册审批通过，无法重新申请");
				obj.put("name", vi.getName());
				obj.put("id", vi.getId());
				String ret = obj.toString();
				logger.info(ret);
				return ret;
			}
			else
			{
				//除去id,applytime, reason, state, firststate,barcode, idNo, idType
				vi.setName(visitor.getName());
				vi.setSex(visitor.getSex());
				vi.setOrg(visitor.getOrg());
				vi.setPosition(visitor.getPosition());
				vi.setPhone(visitor.getPhone());
				vi.setEmail(visitor.getEmail());
				vi.setType(visitor.getType());
				
				vi.setBuyer(visitor.getBuyer());
				vi.setEid(visitor.getEid());

				String idBackStr = visitor.getIdBack();
				if(idBackStr != null &&  !idBackStr.isEmpty())
					vi.setIdBack(idBackStr);
				String idFontStr = visitor.getIdFont();
				if(idFontStr != null &&  !idFontStr.isEmpty())
					vi.setIdFont(idFontStr);
				String idPhotoStr = visitor.getPhoto();
				if(idPhotoStr != null &&  !idPhotoStr.isEmpty())
					vi.setPhoto(idPhotoStr);
				vi.setApplyTime(new Date());
				vi.setState(0);
				logger.info("resave Visitor");
				obj.put("result", true);
				obj.put("message", "您已成功修改证件！");
				obj.put("name", vi.getName());
				obj.put("photo", vi.getPhoto());
				obj.put("id", vi.getId());
				String ret = obj.toString();
				logger.info(ret);
				return ret; 
			}
		}
		
		visitor.setApplyTime(new Date());
		visitor.setState(0); //设定证件状态为申请状态
		String id = visitorDao.saveVisitor(visitor);
		logger.info("save Visitor");
		
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
		System.out.println(ret+"*****************************************");
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
				String picName = BarcodeUtils.createBarcode(id);
				String barcodeContent = NAME_CHI_STR + visitor.getName() + "\n" + ORG_CHI_STR + visitor.getOrg() + "\n"
						+ PHONE_CHI_STR + visitor.getPhone();
				
				String bardcode = BarcodeUtils.createBadgeBarcode(barcodeContent);
				visitor.setBarcode(bardcode);
				/*String nowpath = System.getProperty("user.dir");            
				String tempdir = nowpath.replace("bin", "webapps");*/
				String tempdir = ConfigUtils.getRemote();
				String prjName = ConfigUtils.getPrj();
				prjName = prjName.trim();
				if(!prjName.equals(ROOT_STR))
					tempdir+= ConfigUtils.getPrj();
				//String basePath = tempdir + "\\resources\\topicimages\\";
				String imgSrc = tempdir + Constants.BARCODE_MID_STR + picName;

				//String imgSrc = ConfigUtils.getRemote() + ConfigUtils.getPrj() + Constants.BARCODE_MID_STR + picName;
				logger.info("发送现场证件申请通过邮件...");
				String content = MailUtil.replaceVariable(Constants.VISITOR_APPROVED, visitor.getName(),imgSrc);
				
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
				String content = MailUtil.replaceVariable(Constants.VISITOR_REFUSE, visitor.getName(),reason);
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
