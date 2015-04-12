package com.novahome.data.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.commonservice.Constants;
import com.novahome.commonservice.MD5;
import com.novahome.commonservice.PeopleState;
import com.novahome.data.dao.StaffDao;
import com.novahome.data.pojo.Staff;


@Service("staffService")
@Transactional(readOnly = false)
@Repository
public class StaffService {

	private static final Logger logger = Logger.getLogger(ZytzService.class);
	@Resource(name = "staffDao")
	private StaffDao staffDao;
	
	public String getStaffTotalCount() 
	{
		long count = staffDao.getStaffTotalCount();
		logger.info("count:" + count);
		return "{'count':" + count +"}";
	}
	
	public String saveStaff(Staff staff)
	{
		JSONObject obj = new JSONObject();
		String userName = staff.getUserName();
		if (staffDao.getStaffByUserName(userName) != null) {
			obj.put("result", false);
			obj.put("message", "已经存在姓名为" + userName + "的用户！");
			return userName.toString();
		}
		String encodePwd = MD5.compute(staff.getPassword());
		staff.setPassword(encodePwd);
		String id = staffDao.saveStaff(staff);
		logger.info("save staff");
		
		obj.put("result", true);
		obj.put("message", "成功注册后台管理人员！");
		obj.put("id", id);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String login(String userName, String password)
	{
		HttpSession session = WebContextFactory.get().getSession();
		
		JSONObject obj = new JSONObject();
		Staff staff = staffDao.getStaffByUserName(userName);
		if(staff == null)
		{
			obj.put("result", false);
			obj.put("message", "该用户不存在！");
			return obj.toString();
		}
		if (MD5.compute(password).equals(staff.getPassword())) {
			session.setAttribute(Constants.SESSION_SHOW_ID, staff.getId());
			session.setAttribute(Constants.SESSION_SHOW_NAME, staff.getUserName());
			session.setAttribute(Constants.SESSION_SHOW_TYPE, PeopleState.STAFF);
			obj.put("result", true);
			obj.put("message", "成功登录");
			obj.put("username", userName);
			//obj.put("cookie", MD5.compute(staff.getId()+":"+staff.getPassword()));
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
	
	
	public String getStaffById(String id)
	{
		Staff staff = staffDao.getStaffById(id);
		JSONObject obj = new JSONObject(staff);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	public String getStaffByUserName(String userName)
	{
		Staff staff = staffDao.getStaffByUserName(userName);
		JSONObject obj = new JSONObject(staff);
		String ret = obj.toString();
		logger.info(ret);
		return ret;
	}
	
	
	public long deleteStaffById(String id)
	{
		return staffDao.deleteStaffById(id);
	}
	
	public boolean updateStaff(Staff staff)
	{
		return staffDao.updateStaff(staff);
	}
}
