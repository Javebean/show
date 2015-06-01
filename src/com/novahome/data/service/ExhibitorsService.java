package com.novahome.data.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.commonservice.Constants;
import com.novahome.commonservice.MD5;
import com.novahome.commonservice.PeopleState;
import com.novahome.commonservice.RandCodeGenerator;
import com.novahome.data.dao.ConstructionDao;
import com.novahome.data.dao.DisplayItemDao;

import com.novahome.data.dao.ExhibitorsDao;
import com.novahome.data.dao.SceneServDao;
import com.novahome.data.dao.TransportationDao;
import com.novahome.data.dao.VisitorDao;
import com.novahome.data.model.ShortExhibitor;
import com.novahome.data.model.TotalExhibitInfo;
import com.novahome.data.pojo.Construction;
import com.novahome.data.pojo.DisplayItem;
import com.novahome.data.pojo.Exhibitors;
import com.novahome.data.pojo.SceneServ;
import com.novahome.data.pojo.Transportation;
import com.novahome.data.pojo.Visitor;
import com.novahome.utils.MailUtil;


@Service("exhibitorsService")
@Transactional(readOnly = false)
@Repository
public class ExhibitorsService {

	private static final Logger logger = Logger.getLogger(ExhibitorsService.class);
	
	@Resource(name = "exhibitorsDao")
	private ExhibitorsDao exhibitorsDao;
	@Resource(name = "constructionDao")
	private ConstructionDao constructionDao;
	@Resource(name = "displayItemDao")
	private DisplayItemDao displayItemDao;
	@Resource(name = "sceneServDao")
	private SceneServDao sceneServDao;
	@Resource(name = "transportationDao")
	private TransportationDao transportationDao;
	@Resource(name = "visitorDao")
	private VisitorDao visitorDao;
	private static final String ERROR_STR= "{\"error\":\"抱歉，没有找到指定的展商\"}";
	private static final String NOTIFY_LOGIN_STR = "unauthorized";
	private static final String SUBJECT_OBJECTION = "抱歉，您的连博会展商申请被驳回";
	private static final String htmlPart1 = "<html><p>您好，</p><p>&nbsp; 抱歉，由于以下原因，您的连博会展商申请被驳回:</p><p><strong>";
	private static final String htmlPart2 = "</strong></p><p>请您根据驳回原因重新申请或者联系工作人员,请勿回复此邮件，谢谢！</p></html>";
	private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	
	public String getExhibitorsTotalCount()
	{
		long count = exhibitorsDao.getExhibitorsTotalCount(null);
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String getExhibitorsCountByState(int state)
	{
		long count = exhibitorsDao.getExhibitorsCountByState(state, null);
		logger.debug("count:" + count);
		return "{\"count\":" + count +"}";
	}
	
	public String getTotalExhibitInfoById(String id)
	{
		JSONObject obj = processTotalExhibitInfoById(id);
		String error = (String) obj.get("error");
		if(error != null && !error.isEmpty())
			return obj.toString();
		HttpSession session=  WebContextFactory.get().getSession();
		String userName = (String) session.getAttribute(Constants.SESSION_SHOW_NAME);
		
		if(userName == null || userName.isEmpty())
			userName = (String) session.getAttribute(Constants.SESSION_NAME);
		if(userName == null || userName.isEmpty())
			((JSONObject) obj.get("exhibitors")).put("phone", NOTIFY_LOGIN_STR);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getTotalExhibitInfoByIdWithServlet(String id)
	{
		JSONObject obj = processTotalExhibitInfoById(id);
		String error = (String) obj.get("error");
		if(error != null && !error.isEmpty())
			return obj.toString();
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	private JSONObject processTotalExhibitInfoById(String id)
	{
		Exhibitors exhibitor = exhibitorsDao.getExhibitorById(id);
		List<Construction>construction = constructionDao.getConstructionByEid(id);
		List<Transportation>transportation = transportationDao.getTransportationByEid(id);
		List<SceneServ>sceneServ = sceneServDao.getSceneServByEid(id);
		List<Visitor>visitor = visitorDao.getVisitorByEid(id);
		List<DisplayItem>displayItem = displayItemDao.getDisplayItemByEid(id);
		TotalExhibitInfo info = new TotalExhibitInfo();
		if(exhibitor == null)
		{
			logger.warn(ERROR_STR);
			try {
				return new JSONObject(ERROR_STR);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		info.setConstruction(construction);
		info.setDisplayItem(displayItem);
		info.setExhibitors(exhibitor);
		info.setSceneServ(sceneServ);
		info.setTransportation(transportation);
		info.setVisitor(visitor);
		JSONObject obj = new JSONObject(info);
		return obj;
	}
	
	public String getExhibitorById(String id)
	{
		JSONObject obj = processExhibitorById(id);
		String error = (String) obj.get("error");
		if(error != null && !error.isEmpty())
			return obj.toString();
		System.out.println("obj:"+ obj.toString());
		HttpSession session=  WebContextFactory.get().getSession();
		String userName = (String) session.getAttribute(Constants.SESSION_SHOW_NAME);	
		if(userName == null || userName.isEmpty())
			userName = (String) session.getAttribute(Constants.SESSION_NAME);
		if(userName == null || userName.isEmpty())
			obj.put("phone", NOTIFY_LOGIN_STR);
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	public String getExhibitorByIdWithServlet(String id)
	{
		JSONObject obj = processExhibitorById(id);
		String error = (String) obj.get("error");
		if(error != null && !error.isEmpty())
			return obj.toString();
		String ret = obj.toString();
		logger.debug(ret);
		return ret;
	}
	
	private JSONObject processExhibitorById(String id)
	{
		Exhibitors exhibitor = exhibitorsDao.getExhibitorById(id);
		if(exhibitor == null)
		{
			logger.warn(ERROR_STR);
			try {
				return new JSONObject(ERROR_STR);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JSONObject obj = new JSONObject(exhibitor);
		return obj;
	}
	
	public String getExhibitorForPage(int start, int number)
	{
		List<Exhibitors>ls = exhibitorsDao.getExhibitorForPage(start, number);
		long size = exhibitorsDao.getExhibitorsTotalCount(null);
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
		for(Object exhibitor : ls )
		{
			array.put(new JSONObject(exhibitor));
		}
		obj.put("data", array);
		obj.put("size", size);
		String ret = obj.toString();
		logger.debug("exhibitors:" + ret);
		return ret;
	}
	
	public String getShortExhibitorsForPage(int start, int number, String orgName)
	{
		long size = exhibitorsDao.getExhibitorsTotalCount(orgName);
		List<ShortExhibitor>ls = exhibitorsDao.getShortExhibitorForPage(start, number, orgName);
		return procssListRet(ls,size);
	}
	
	public String getShortExhibitorForPageByState(int start, int number, int state, String orgName)
	{
		if(state == -1)
		{
			return this.getShortExhibitorsForPage(start, number, orgName);
		}
		long size = exhibitorsDao.getExhibitorsCountByState(state, orgName);
		List<ShortExhibitor>ls = exhibitorsDao.getShortExhibitorForPageByState(start, number, state, orgName);
		return procssListRet(ls,size);
	}
	
	/**
	 * 2015.6.1新增，order排序
	 * @param start
	 * @param number
	 * @param state
	 * @param orgName
	 * @return
	 */
	public String getShortExhibitorForPageByStateLogoOrder(int start, int number, int state, String orgName)
	{
		if(state == -1)
		{
			//如果没有state限制就是全是time顺序
			return this.getShortExhibitorsForPage(start, number, orgName);
		}
		long size = exhibitorsDao.getExhibitorsCountByState(state, orgName);
		List<ShortExhibitor>ls = exhibitorsDao.getShortExhibitorForPageByStateLogoOrder(start, number, state, orgName);
		return procssListRet(ls,size);
	}
	
	public String saveExhibitor(Exhibitors exhibitor)
	{
		JSONObject obj = new JSONObject();
		String orgName = exhibitor.getOrgName();
		Exhibitors ex = exhibitorsDao.getExhibitorByOrgNameWithRegistered(orgName);
		if(ex != null)
		{
			obj.put("result", false);
			obj.put("message", "该公司已注册过，如有疑问请致电！");
			obj.put("username", ex.getUsername());	
			obj.put("id", ex.getId());
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		}
		exhibitor.setApplyTime(new Date());
		String userName = RandCodeGenerator.generateExhibitUser();
		String pwd = RandCodeGenerator.generatePwd();
		String encodePwd = MD5.compute(pwd);
		/**
		 * set username and password here
		 */
		exhibitor.setUsername(userName);
		exhibitor.setPassword(encodePwd);
		String id = exhibitorsDao.saveExhibitor(exhibitor);
		logger.info("save exhibitor");
		exhibitor.setId(id);
		
		
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功注册,即将跳转！");
		obj.put("username", userName);
		obj.put("password", pwd);
		obj.put("id", id);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
		
	}
	
	public String saveTotalExhibitInfo(Exhibitors exhibitor, List<Construction>construction,List<Transportation>transportation,
			List<SceneServ>sceneServ, List<Visitor>visitor,List<DisplayItem>displayItem)
	{
		JSONObject obj = new JSONObject();
		String orgName = exhibitor.getOrgName();
		Exhibitors ex = exhibitorsDao.getExhibitorByOrgNameWithRegistered(orgName);
		if(ex != null)
		{
			obj.put("result", false);
			obj.put("message", "该公司已注册过，如有疑问请致电！");
			obj.put("username", ex.getUsername());	
			obj.put("id", ex.getId());
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		}
		exhibitor.setApplyTime(new Date());
		String userName = RandCodeGenerator.generateExhibitUser();
		String pwd = RandCodeGenerator.generatePwd();
		String encodePwd = MD5.compute(pwd);
		/**
		 * set username and password here
		 */
		exhibitor.setUsername(userName);
		exhibitor.setPassword(encodePwd);
		String id = exhibitorsDao.saveExhibitor(exhibitor);
		logger.info("save exhibitor");
		exhibitor.setId(id);
		
		//return obj.toString();
		obj.put("result", true);
		obj.put("message", "您已成功注册,即将跳转！");
		obj.put("username", userName);
		obj.put("password", pwd);
		obj.put("id", id);
		
		for(Construction c : construction)
		{
			c.setEid(id);
			constructionDao.saveConstruction(c);
		}
		for(Transportation t : transportation)
		{
			t.setEid(id);
			transportationDao.saveTransportation(t);
		}
		for(SceneServ s : sceneServ)
		{
			s.setEid(id);
			sceneServDao.saveSceneServ(s);
		}
		for(Visitor v : visitor)
		{
			v.setApplyTime(new Date());
			v.setEid(id);
			v.setOrg(exhibitor.getOrgName());
			visitorDao.saveVisitor(v);
		}
		for(DisplayItem d : displayItem)
		{
			d.setEid(id);
			displayItemDao.saveDisplayItem(d);
		}
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String login(String userName, String password)
	{
		HttpSession session = WebContextFactory.get().getSession();
		JSONObject obj = new JSONObject();
		Exhibitors exhibitor = exhibitorsDao.getExhibitorsByUserName(userName);
		if (exhibitor == null) {
			obj.put("result", false);
			obj.put("message", "该用户不存在！");
			return obj.toString();
		}
		if (MD5.compute(password).equals(exhibitor.getPassword())) {
			session.setAttribute(Constants.SESSION_SHOW_ID, exhibitor.getId());
			session.setAttribute(Constants.SESSION_SHOW_NAME, exhibitor.getUsername());
			session.setAttribute(Constants.SESSION_SHOW_TYPE, PeopleState.EXHIBITOR);
			obj.put("result", true);
			obj.put("message", "成功登录");
			obj.put("username", exhibitor.getUsername());
			//obj.put("cookie", MD5.compute(audience.getId()+":"+audience.getPassword()));
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		} else {
			obj.put("result", false);
			obj.put("message", "密码错误！");
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		}
	}
	
	public String loginByServlet(String username,String password) {	
		JSONObject obj = new JSONObject();
		Exhibitors exhibitor = exhibitorsDao.getExhibitorsByUserName(username);
		if (exhibitor == null) {
			obj.put("result", false);
			obj.put("message", "该用户不存在！");
			return obj.toString();
		}
		if (MD5.compute(password).equals(exhibitor.getPassword())) {
			obj.put("result", true);
			obj.put("message", "成功登录");
			obj.put("username", exhibitor.getUsername());
			//obj.put("cookie", MD5.compute(audience.getId()+":"+audience.getPassword()));
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		} else {
			obj.put("result", false);
			obj.put("message", "密码错误！");
			String ret = obj.toString();
			logger.info(ret);
			return ret;
		}
	}
	
	public boolean updateExhibitor(Exhibitors exhibitor)
	{
		exhibitor.setApplyTime(new Date());
		return exhibitorsDao.updateExhibitor(exhibitor);
	}
	
	public boolean updateExhibitorState(String id, int state)
	{
		Exhibitors ex = exhibitorsDao.getExhibitorById(id);
		ex.setState(state);
		return true;
	}
	
	public boolean updateExhibitorStateReason(String id, int state, String reason)
	{
		Exhibitors ex = exhibitorsDao.getExhibitorById(id);
		ex.setState(state);
		if(state == 2)
		{
			ex.setReason(reason);
			String email = ex.getEmail();
			if(email != null && email.matches(EMAIL_REGEX))  
			{
				logger.info("发送邮件...");
				String content = htmlPart1 + reason + htmlPart2;
				logger.debug("content:" + content);
				MailUtil.sendMail(email, SUBJECT_OBJECTION, content);
			}
		}
		else
			ex.setReason("");
		return true;
	}
	
	public long deleteExhibitorById(String id)
	{
		return exhibitorsDao.deleteExhibitorById(id);
	}
	
	public boolean logout() {
		HttpSession session = WebContextFactory.get().getSession();
		session.removeAttribute(Constants.SESSION_SHOW_ID);
		session.removeAttribute(Constants.SESSION_SHOW_NAME);
		session.removeAttribute(Constants.SESSION_SHOW_TYPE);
		return true;
	}
	
	public String resetPsw(String id)
	{
		Exhibitors ex = exhibitorsDao.getExhibitorById(id);
		String pwd = RandCodeGenerator.generatePwd();
		String encodePwd = MD5.compute(pwd);
		ex.setPassword(encodePwd);
		JSONObject obj = new JSONObject();
		obj.put("password", pwd);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	
	public String getRecommenderStat()
	{
		List<String>ls = exhibitorsDao.getDistinctRecommenders();
		String recNameTol ="";
		String numTol = "";
		if(ls == null || ls.isEmpty())
		{
			logger.warn(ERROR_STR);
			return ERROR_STR;
		}
		for(String rec : ls)
		{
			if(rec== null || rec.isEmpty())
				continue;
			long num = exhibitorsDao.getExhibitorsCountByRecommender(rec);
			recNameTol += rec + ",";
			numTol += num + ",";
		}
		recNameTol = recNameTol.substring(0, recNameTol.length()-1);
		numTol = numTol.substring(0, numTol.length()-1);
		JSONObject obj = new JSONObject();
		obj.put("name", recNameTol);
		obj.put("num", numTol);
		String ret = obj.toString();
		logger.debug("recommenders:" + ret);
		return ret;
		
	}
}
