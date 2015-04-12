package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.Staff;
import com.novahome.data.service.StaffService;

@Scope("prototype")
@Component("staff_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "staff_dwr"), name = "staff_dwr")
public class StaffDwr {

	@Resource
	private StaffService staffService;
	
	/**
	 * 获取后台管理人员数量
	 * @return
	 */
	@RemoteMethod
	public String getStaffTotalCount() 
	{
		return staffService.getStaffTotalCount();
	}
	
	/**
	 * 存储一个后台管理人员
	 * @param staff
	 * @return
	 */
	@RemoteMethod
	public String saveStaff(Staff staff)
	{
		return staffService.saveStaff(staff);
	}
	
	/**
	 * 通过id查找后台管理人员
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getStaffById(String id)
	{
		return staffService.getStaffById(id);
	}
		
	/**
	 * 通过username查找后台管理人员
	 * @param userName
	 * @return
	 */
	@RemoteMethod
	public String getStaffByUserName(String userName)
	{
		return staffService.getStaffByUserName(userName);
	}

	/**
	 * 后台管理人员登录
	 * @param userName
	 * @param password
	 * @return 返回登录json信息
	 */
	@RemoteMethod
	public String login(String userName, String password)
	{
		return staffService.login(userName, password);
	}
	
	/**
	 * 删除后台管理人员
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteStaffById(String id)
	{
		return staffService.deleteStaffById(id);
	}
	
	/**
	 * 更新后台管理人员
	 * @param staff
	 * @return
	 */
	@RemoteMethod
	public boolean updateStaff(Staff staff)
	{
		return staffService.updateStaff(staff);
	}
}
