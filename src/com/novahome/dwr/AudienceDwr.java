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
	
	/**
	 * 获取注册观众数量
	 * @return json count
	 */
	@RemoteMethod
	public String getAudienceTotalCount() 
	{
		return audienceService.getAudienceTotalCount();
	}
	
	/**
	 * 存储注册观众
	 * @param audience
	 * @return
	 */
	@RemoteMethod
	public String saveAudience(Audience audience)
	{
		return audienceService.saveAudience(audience);
	}
	
	/**
	 * 通过用户名查找注册观众
	 * @param userName
	 * @return
	 */
	@RemoteMethod
	public String getAudienceByUserName(String userName)
	{
		return audienceService.getAudienceByUserName(userName);
	}
	
	/**
	 * 通过id查找注册观众
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getAudienceById(String id)
	{
		return audienceService.getAudienceById(id);
	}
	
	/**
	 * 注册观众注册
	 * @param username
	 * @param password
	 * @return 返回成功json信息
	 */
	@RemoteMethod
	public String login(String username,String password) {
		return audienceService.login(username, password);
	}
	
	/**
	 * 分页查询注册观众
	 * @param start 起始
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getAudienceForPage(int start, int number, String name)
	{
		return audienceService.getAudienceForPage(start, number, name);
	}
	
	/**
	 * 删除注册观众
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteAudienceById(String id)
	{
		return audienceService.deleteAudienceById(id);
	}
	
	/**
	 * 观众登出
	 * 
	 * @return
	 */
	@RemoteMethod
	public boolean logout()
	{
		return audienceService.logout();
	}
	
	/**
	 * 更新观众信息
	 * @param audience
	 * @return
	 */
	@RemoteMethod
	public boolean updateAudience(Audience audience)
	{
		return audienceService.updateAudience(audience);
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String resetPwd(String id)
	{
		return audienceService.resetPsw(id);
	}
	
	/**
	 * 获取观众地区及相关数量
	 * @return
	 */
	@RemoteMethod
	public String getRegionStat()
	{
		return audienceService.getRegionStat();
	}
	
}
