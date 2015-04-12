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
	
	/**
	 * 获取重要通知数量
	 * @return
	 */
	@RemoteMethod
	public String getZytzTotalCount() 
	{
		return zytzService.getZytzTotalCount();
	}
	
	/**
	 * 存储重要通知
	 * @param zytz
	 * @return
	 */
	@RemoteMethod
	public String saveZytz(Zytz zytz)
	{
		return zytzService.saveZytz(zytz);
	}
	
	/**
	 * 通过重要通知标题查找
	 * @param title
	 * @return
	 */
	@RemoteMethod
	public String getZytzByTitle(String title)
	{
		return zytzService.getZytzByTitle(title);
	}
	
	/**
	 * 通过id获取重要通知
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getZytzById(String id)
	{
		return zytzService.getZytzById(id);
	}
	
	/**
	 * 分页查询重要通知（包含所有信息，content在内）（不推荐）
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getZytzForPage(int start, int number)
	{
		return zytzService.getZytzForPage(start, number);
	}
	
	/**
	 * 分页查询重要通知（精简版，不包含content）
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getShortZytzForPage(int start, int number)
	{
		return zytzService.getShortZytzForPage(start, number);
	}
	
	/**
	 * 删除重要通知
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteZytzById(String id)
	{
		return zytzService.deleteZytzById(id);
	}
	
	/**
	 * 更新重要通知
	 * @param zytz
	 * @return
	 */
	@RemoteMethod
	public boolean updateZytz(Zytz zytz)
	{
		return zytzService.updateZytz(zytz);
	}
}
