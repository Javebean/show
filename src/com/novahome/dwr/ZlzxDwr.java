package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.Zlzx;
import com.novahome.data.service.ZlzxService;


@Scope("prototype")
@Component("zlzx_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "zlzx_dwr"), name = "zlzx_dwr")
public class ZlzxDwr {

	@Resource
	private ZlzxService zlzxService;
	
	@RemoteMethod
	public String getZlzxTotalCount() 
	{
		return zlzxService.getZlzxTotalCount();
	}
	
	@RemoteMethod
	public String saveZlzx(Zlzx zlzx)
	{
		return zlzxService.saveZlzx(zlzx);
	}
	
	@RemoteMethod
	public String getZlzxByTitle(String title)
	{
		return zlzxService.getZlzxByTitle(title);
	}
	
	@RemoteMethod
	public String getZlzxById(String id)
	{
		return zlzxService.getZlzxById(id);
	}
	
	
	@RemoteMethod
	public String getZlzxForPage(int start, int number)
	{
		return zlzxService.getZlzxForPage(start, number);
	}
	
	@RemoteMethod
	public String getShortZlzxForPage(int start, int number)
	{
		return zlzxService.getShortZlzxForPage(start, number);
	}
	
	@RemoteMethod
	public long deleteZlzxById(String id)
	{
		return zlzxService.deleteZlzxById(id);
	}
	
	@RemoteMethod
	public boolean updateZlzx(Zlzx zlzx)
	{
		return zlzxService.updateZlzx(zlzx);
	}
}
