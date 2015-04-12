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
	
	@RemoteMethod
	public String getStaffTotalCount() 
	{
		return staffService.getStaffTotalCount();
	}
	
	@RemoteMethod
	public String saveStaff(Staff staff)
	{
		return staffService.saveStaff(staff);
	}
	
	@RemoteMethod
	public String getStaffById(String id)
	{
		return staffService.getStaffById(id);
	}
		
	@RemoteMethod
	public String getStaffByUserName(String userName)
	{
		return staffService.getStaffByUserName(userName);
	}
	
	@RemoteMethod
	public String login(String userName, String password)
	{
		return staffService.login(userName, password);
	}
	
	@RemoteMethod
	public long deleteStaffById(String id)
	{
		return staffService.deleteStaffById(id);
	}
	
	@RemoteMethod
	public boolean updateStaff(Staff staff)
	{
		return staffService.updateStaff(staff);
	}
}
