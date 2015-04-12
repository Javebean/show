package com.novahome.dwr;


import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Audience;
import com.novahome.data.service.AudienceService;;

@Scope("prototype")
@Component("audience_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "audience_dwr"), name = "audience_dwr")
public class AudienceDwr {
	
	@Resource
	private AudienceService audienceService;
	
	@RemoteMethod
	public String getAudienceTotalCount() 
	{
		return audienceService.getAudienceTotalCount();
	}
	
	@RemoteMethod
	public String saveAudience(Audience audience)
	{
		return audienceService.saveAudience(audience);
	}
	
	@RemoteMethod
	public String getAudienceByUserName(String userName)
	{
		return audienceService.getAudienceByUserName(userName);
	}
	
	@RemoteMethod
	public String getAudienceByName(String name)
	{
		return audienceService.getAudienceByName(name);
	}
	
	public String getAudienceById(String id)
	{
		return audienceService.getAudienceById(id);
	}
	
	@RemoteMethod
	public String login(String username,String password) {
		return audienceService.login(username, password);
	}
	
	@RemoteMethod
	public String getAudienceForPage(int start, int number)
	{
		return audienceService.getAudienceForPage(start, number);
	}
	
	@RemoteMethod
	public long deleteAudienceById(String id)
	{
		return audienceService.deleteAudienceById(id);
	}
	
}
