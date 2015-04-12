package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.Zytz;
import com.novahome.data.service.ZytzService;

@Scope("prototype")
@Component("zytz_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "zytz_dwr"), name = "zytz_dwr")
public class ZytzDwr {

	@Resource
	private ZytzService zytzService;
	
	@RemoteMethod
	public String getZytzTotalCount() 
	{
		return zytzService.getZytzTotalCount();
	}
	
	@RemoteMethod
	public String saveZytz(Zytz zytz)
	{
		return zytzService.saveZytz(zytz);
	}
	
	@RemoteMethod
	public String getZytzByTitle(String title)
	{
		return zytzService.getZytzByTitle(title);
	}
	
	@RemoteMethod
	public String getZytzById(String id)
	{
		return zytzService.getZytzById(id);
	}
	
	
	@RemoteMethod
	public String getZytzForPage(int start, int number)
	{
		return zytzService.getZytzForPage(start, number);
	}
	
	@RemoteMethod
	public String getShortZytzForPage(int start, int number)
	{
		return zytzService.getShortZytzForPage(start, number);
	}
	
	@RemoteMethod
	public long deleteZytzById(String id)
	{
		return zytzService.deleteZytzById(id);
	}
	
	@RemoteMethod
	public boolean updateZytz(Zytz zytz)
	{
		return zytzService.updateZytz(zytz);
	}
}
